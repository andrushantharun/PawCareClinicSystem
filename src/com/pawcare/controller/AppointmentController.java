/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.controller;

import com.pawcare.exception.InvalidAppointmentException;
import com.pawcare.model.Appointment;
import com.pawcare.service.AppointmentService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Arulthas
 */
public class AppointmentController {
     private final AppointmentService appointmentService;

    public AppointmentController() {
        this.appointmentService = new AppointmentService();
    }

    public void addAppointment(Appointment appointment)
            throws SQLException, IllegalArgumentException, InvalidAppointmentException {
        appointmentService.addAppointment(appointment);
    }

    public void updateAppointment(Appointment appointment)
            throws SQLException, IllegalArgumentException, InvalidAppointmentException {
        appointmentService.updateAppointment(appointment);
    }

    public void deleteAppointment(int appointmentId) throws SQLException {
        appointmentService.deleteAppointment(appointmentId);
    }

    public List<Appointment> getAllAppointments() throws SQLException {
        return appointmentService.getAllAppointments();
    }

    public List<Map<String, Object>> getAppointmentDetailsList() throws SQLException {
        return appointmentService.getAppointmentDetailsList();
    }
    
    public Appointment getAppointmentById(int appointmentId) throws SQLException {
    return appointmentService.getAppointmentById(appointmentId);
    }
}
