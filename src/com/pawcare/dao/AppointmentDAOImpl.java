/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.dao;

import com.pawcare.model.Appointment;
import com.pawcare.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Arulthas
 */
public class AppointmentDAOImpl implements AppointmentDAO {

    @Override
    public void addAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO appointments "
                   + "(customer_id, pet_id, service_id, user_id, appointment_date, appointment_time, status) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, appointment.getCustomerId());
            ps.setInt(2, appointment.getPetId());
            ps.setInt(3, appointment.getServiceId());
            ps.setInt(4, appointment.getUserId());
            ps.setDate(5, Date.valueOf(appointment.getAppointmentDate()));
            ps.setTime(6, Time.valueOf(appointment.getAppointmentTime()));
            ps.setString(7, appointment.getStatus());

            ps.executeUpdate();
        }
    }

    @Override
    public void updateAppointment(Appointment appointment) throws SQLException {
        String sql = "UPDATE appointments SET customer_id = ?, pet_id = ?, service_id = ?, "
                   + "user_id = ?, appointment_date = ?, appointment_time = ?, status = ? "
                   + "WHERE appointment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, appointment.getCustomerId());
            ps.setInt(2, appointment.getPetId());
            ps.setInt(3, appointment.getServiceId());
            ps.setInt(4, appointment.getUserId());
            ps.setDate(5, Date.valueOf(appointment.getAppointmentDate()));
            ps.setTime(6, Time.valueOf(appointment.getAppointmentTime()));
            ps.setString(7, appointment.getStatus());
            ps.setInt(8, appointment.getAppointmentId());

            ps.executeUpdate();
        }
    }

    @Override
    public void deleteAppointment(int appointmentId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE appointment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, appointmentId);
            ps.executeUpdate();
        }
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE appointment_id = ?";

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
    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments ORDER BY appointment_date, appointment_time";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                appointments.add(mapRow(rs));
            }
        }
        return appointments;
    }

    @Override
    public List<Map<String, Object>> getAppointmentDetailsList() throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();

        String sql = "SELECT a.appointment_id, c.full_name AS customer_name, p.pet_name, "
                   + "s.service_name, u.full_name AS staff_name, "
                   + "a.appointment_date, a.appointment_time, a.status "
                   + "FROM appointments a "
                   + "JOIN customers c ON a.customer_id = c.customer_id "
                   + "JOIN pets p ON a.pet_id = p.pet_id "
                   + "JOIN services s ON a.service_id = s.service_id "
                   + "JOIN users u ON a.user_id = u.user_id "
                   + "ORDER BY a.appointment_date, a.appointment_time";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            while (rs.next()) {
                // LinkedHashMap preserves column order for consistent table display
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(meta.getColumnLabel(i), rs.getObject(i));
                }
                results.add(row);
            }
        }
        return results;
    }

    @Override
    public boolean isTimeSlotTaken(int userId, LocalDate date, LocalTime time) throws SQLException {
        String sql = "SELECT COUNT(*) FROM appointments "
                   + "WHERE user_id = ? AND appointment_date = ? AND appointment_time = ? "
                   + "AND status != 'CANCELLED'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setDate(2, Date.valueOf(date));
            ps.setTime(3, Time.valueOf(time));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    private Appointment mapRow(ResultSet rs) throws SQLException {
        return new Appointment(
                rs.getInt("appointment_id"),
                rs.getInt("customer_id"),
                rs.getInt("pet_id"),
                rs.getInt("service_id"),
                rs.getInt("user_id"),
                rs.getDate("appointment_date").toLocalDate(),
                rs.getTime("appointment_time").toLocalTime(),
                rs.getString("status")
        );
    }
}