/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.controller;

import com.pawcare.model.Treatment;
import com.pawcare.service.TreatmentService;

import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Arulthas
 */
public class TreatmentController {
    private final TreatmentService treatmentService;

    public TreatmentController() {
        this.treatmentService = new TreatmentService();
    }

    public void addTreatment(Treatment treatment) throws SQLException, IllegalArgumentException {
        treatmentService.addTreatment(treatment);
    }

    public void updateTreatment(Treatment treatment) throws SQLException, IllegalArgumentException {
        treatmentService.updateTreatment(treatment);
    }

    public void deleteTreatment(int treatmentId) throws SQLException {
        treatmentService.deleteTreatment(treatmentId);
    }

    public Treatment getTreatmentByAppointmentId(int appointmentId) throws SQLException {
        return treatmentService.getTreatmentByAppointmentId(appointmentId);
    }

    public List<Treatment> getAllTreatments() throws SQLException {
        return treatmentService.getAllTreatments();
    }
}
