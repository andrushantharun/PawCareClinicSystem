/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pawcare.dao;

import com.pawcare.model.Treatment;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author Arulthas
 */
public interface TreatmentDAO {
    void addTreatment(Treatment treatment) throws SQLException;
    void updateTreatment(Treatment treatment) throws SQLException;
    void deleteTreatment(int treatmentId) throws SQLException;
    Treatment getTreatmentByAppointmentId(int appointmentId) throws SQLException;
    List<Treatment> getAllTreatments() throws SQLException;
}