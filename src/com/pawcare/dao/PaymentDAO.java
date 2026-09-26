/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pawcare.dao;

import com.pawcare.model.Payment;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Arulthas
 */
public interface PaymentDAO {
    void addPayment(Payment payment) throws SQLException;
    Payment getPaymentByAppointmentId(int appointmentId) throws SQLException;
    List<Payment> getAllPayments() throws SQLException;
    void updatePayment(Payment payment) throws SQLException;
    void deletePayment(int paymentId) throws SQLException;
}