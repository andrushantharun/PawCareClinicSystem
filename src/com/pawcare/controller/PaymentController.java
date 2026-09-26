/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.controller;

import com.pawcare.model.Payment;
import com.pawcare.service.PaymentService;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Arulthas
 */
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController() {
        this.paymentService = new PaymentService();
    }

    public void addPayment(Payment payment) throws SQLException, IllegalArgumentException {
        paymentService.addPayment(payment);
    }

    public void updatePayment(Payment payment) throws SQLException, IllegalArgumentException {
        paymentService.updatePayment(payment);
    }

    public void deletePayment(int paymentId) throws SQLException {
        paymentService.deletePayment(paymentId);
    }

    public Payment getPaymentByAppointmentId(int appointmentId) throws SQLException {
        return paymentService.getPaymentByAppointmentId(appointmentId);
    }

    public List<Payment> getAllPayments() throws SQLException {
        return paymentService.getAllPayments();
    }
}
