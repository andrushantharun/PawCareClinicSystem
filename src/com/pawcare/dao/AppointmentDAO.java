/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pawcare.dao;

import com.pawcare.model.Appointment;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Arulthas
 */
public interface AppointmentDAO {

    void addAppointment(Appointment appointment) throws SQLException;
    void updateAppointment(Appointment appointment) throws SQLException;
    void deleteAppointment(int appointmentId) throws SQLException;
    Appointment getAppointmentById(int appointmentId) throws SQLException;
    List<Appointment> getAllAppointments() throws SQLException;

    List<Map<String, Object>> getAppointmentDetailsList() throws SQLException;

    boolean isTimeSlotTaken(int userId, java.time.LocalDate date, java.time.LocalTime time) throws SQLException;
}
    
