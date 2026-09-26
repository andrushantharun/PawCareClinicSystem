/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.service;

import com.pawcare.dao.AppointmentDAO;
import com.pawcare.dao.AppointmentDAOImpl;
import com.pawcare.exception.InvalidAppointmentException;
import com.pawcare.model.Appointment;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Arulthas
 */
public class AppointmentService {
    private final AppointmentDAO appointmentDAO;

    public AppointmentService() {
        this.appointmentDAO = new AppointmentDAOImpl();
    }
    
    public Appointment getAppointmentById(int appointmentId) throws SQLException {
    return appointmentDAO.getAppointmentById(appointmentId);
}

    public void addAppointment(Appointment appointment)
            throws SQLException, IllegalArgumentException, InvalidAppointmentException {
        validateFields(appointment);
        checkForConflict(appointment, null);
        appointmentDAO.addAppointment(appointment);
    }

    public void updateAppointment(Appointment appointment)
            throws SQLException, IllegalArgumentException, InvalidAppointmentException {
        validateFields(appointment);
        checkForConflict(appointment, appointment.getAppointmentId());
        appointmentDAO.updateAppointment(appointment);
    }

    public void deleteAppointment(int appointmentId) throws SQLException {
        appointmentDAO.deleteAppointment(appointmentId);
    }

    public List<Appointment> getAllAppointments() throws SQLException {
        return appointmentDAO.getAllAppointments();
    }

    public List<Map<String, Object>> getAppointmentDetailsList() throws SQLException {
        return appointmentDAO.getAppointmentDetailsList();
    }

    /** Basic field-level checks — missing selections, missing date/time. */
    private void validateFields(Appointment a) {
        if (a.getCustomerId() <= 0) throw new IllegalArgumentException("Please select a customer.");
        if (a.getPetId() <= 0) throw new IllegalArgumentException("Please select a pet.");
        if (a.getServiceId() <= 0) throw new IllegalArgumentException("Please select a service.");
        if (a.getUserId() <= 0) throw new IllegalArgumentException("Please select a staff member.");
        if (a.getAppointmentDate() == null) throw new IllegalArgumentException("Please enter a valid date.");
        if (a.getAppointmentTime() == null) throw new IllegalArgumentException("Please enter a valid time.");
        if (a.getStatus() == null || a.getStatus().trim().isEmpty())
            throw new IllegalArgumentException("Please select a status.");
        if (a.getAppointmentDate().isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Appointment date cannot be in the past.");
    }

    /**
     * Business rule: a staff member cannot have two appointments at the
     * same date and time. excludeAppointmentId lets an update skip the
     * conflict check against its own existing slot.
     */
    private void checkForConflict(Appointment a, Integer excludeAppointmentId) throws SQLException, InvalidAppointmentException {
        boolean taken = appointmentDAO.isTimeSlotTaken(a.getUserId(), a.getAppointmentDate(), a.getAppointmentTime());

        if (taken && excludeAppointmentId != null) {
            // If it's the same appointment being edited without changing its own slot, that's fine.
            Appointment existing = appointmentDAO.getAppointmentById(excludeAppointmentId);
            boolean sameSlotAsBefore = existing != null
                    && existing.getUserId() == a.getUserId()
                    && existing.getAppointmentDate().equals(a.getAppointmentDate())
                    && existing.getAppointmentTime().equals(a.getAppointmentTime());
            if (sameSlotAsBefore) {
                taken = false;
            }
        }

        if (taken) {
            throw new InvalidAppointmentException(
                    "This staff member already has an appointment at that date and time.");
        }
    }
}
