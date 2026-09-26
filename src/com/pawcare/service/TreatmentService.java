/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.service;

import com.pawcare.dao.TreatmentDAO;
import com.pawcare.dao.TreatmentDAOImpl;
import com.pawcare.model.Treatment;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


/**
 *
 * @author Arulthas
 */
public class TreatmentService {
     private final TreatmentDAO treatmentDAO;

    public TreatmentService() {
        this.treatmentDAO = new TreatmentDAOImpl();
    }

    public void addTreatment(Treatment treatment) throws SQLException, IllegalArgumentException {
        validate(treatment);
        treatmentDAO.addTreatment(treatment);
    }

    public void updateTreatment(Treatment treatment) throws SQLException, IllegalArgumentException {
        validate(treatment);
        treatmentDAO.updateTreatment(treatment);
    }

    public void deleteTreatment(int treatmentId) throws SQLException {
        treatmentDAO.deleteTreatment(treatmentId);
    }

    public Treatment getTreatmentByAppointmentId(int appointmentId) throws SQLException {
        return treatmentDAO.getTreatmentByAppointmentId(appointmentId);
    }

    public List<Treatment> getAllTreatments() throws SQLException {
        return treatmentDAO.getAllTreatments();
    }

    private void validate(Treatment t) {
        if (t.getAppointmentId() <= 0) {
            throw new IllegalArgumentException("Please select an appointment.");
        }
        if (t.getDiagnosis() == null || t.getDiagnosis().trim().isEmpty()) {
            throw new IllegalArgumentException("Diagnosis is required.");
        }
        if (t.getTreatmentDate() == null) {
            throw new IllegalArgumentException("Please enter a valid treatment date.");
        }
        if (t.getTreatmentDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Treatment date cannot be in the future.");
        }
    }
}
