/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.service;

import com.pawcare.dao.PaymentDAO;
import com.pawcare.dao.PaymentDAOImpl;
import com.pawcare.model.Payment;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Arulthas
 */
public class PaymentService {
    private final PaymentDAO paymentDAO;

    public PaymentService() {
        this.paymentDAO = new PaymentDAOImpl();
    }

    public void addPayment(Payment payment) throws SQLException, IllegalArgumentException {
        validate(payment);
        paymentDAO.addPayment(payment);
    }

    public void updatePayment(Payment payment) throws SQLException, IllegalArgumentException {
        validate(payment);
        paymentDAO.updatePayment(payment);
    }

    public void deletePayment(int paymentId) throws SQLException {
        paymentDAO.deletePayment(paymentId);
    }

    public Payment getPaymentByAppointmentId(int appointmentId) throws SQLException {
        return paymentDAO.getPaymentByAppointmentId(appointmentId);
    }

    public List<Payment> getAllPayments() throws SQLException {
        return paymentDAO.getAllPayments();
    }

    private void validate(Payment p) {
        if (p.getAppointmentId() <= 0) {
            throw new IllegalArgumentException("Please select an appointment.");
        }
        if (p.getAmount() == null || p.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        if (p.getPaymentMethod() == null || p.getPaymentMethod().trim().isEmpty()) {
            throw new IllegalArgumentException("Please select a payment method.");
        }
        if (p.getPaymentDate() == null) {
            throw new IllegalArgumentException("Please enter a valid payment date.");
        }
        if (p.getPaymentDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Payment date cannot be in the future.");
        }
        if (p.getPaymentStatus() == null || p.getPaymentStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("Please select a payment status.");
        }
    }
}
