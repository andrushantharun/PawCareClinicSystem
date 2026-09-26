/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.dao;

import com.pawcare.model.Treatment;
import com.pawcare.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Arulthas
 */
public class TreatmentDAOImpl implements TreatmentDAO {

    @Override
    public void addTreatment(Treatment treatment) throws SQLException {
        String sql = "INSERT INTO treatments (appointment_id, diagnosis, medication, notes, treatment_date) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, treatment.getAppointmentId());
            ps.setString(2, treatment.getDiagnosis());
            ps.setString(3, treatment.getMedication());
            ps.setString(4, treatment.getNotes());
            ps.setDate(5, Date.valueOf(treatment.getTreatmentDate()));

            ps.executeUpdate();
        }
    }

    @Override
    public void updateTreatment(Treatment treatment) throws SQLException {
        String sql = "UPDATE treatments SET diagnosis = ?, medication = ?, notes = ? "
                   + "WHERE appointment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, treatment.getDiagnosis());
            ps.setString(2, treatment.getMedication());
            ps.setString(3, treatment.getNotes());
            ps.setInt(4, treatment.getAppointmentId());

            ps.executeUpdate();
        }
    }

    @Override
    public Treatment getTreatmentByAppointmentId(int appointmentId) throws SQLException {
        String sql = "SELECT * FROM treatments WHERE appointment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, appointmentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Treatment> getAllTreatments() throws SQLException {
        List<Treatment> treatments = new ArrayList<>();
        String sql = "SELECT * FROM treatments ORDER BY treatment_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                treatments.add(mapRow(rs));
            }
        }
        return treatments;
    }
    
    @Override
    public void deleteTreatment(int treatmentId) throws SQLException {
        String sql = "DELETE FROM treatments WHERE treatment_id = ?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, treatmentId);
            ps.executeUpdate();
            }
        }

    private Treatment mapRow(ResultSet rs) throws SQLException {
        return new Treatment(
                rs.getInt("treatment_id"),
                rs.getInt("appointment_id"),
                rs.getString("diagnosis"),
                rs.getString("medication"),
                rs.getString("notes"),
                rs.getDate("treatment_date").toLocalDate()
        );
    }
}