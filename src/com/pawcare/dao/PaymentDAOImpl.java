/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.dao;

import com.pawcare.model.Payment;
import com.pawcare.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Arulthas
 */
public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public void addPayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO payments (appointment_id, amount, payment_method, payment_date, payment_status) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, payment.getAppointmentId());
            ps.setBigDecimal(2, payment.getAmount());
            ps.setString(3, payment.getPaymentMethod());
            ps.setDate(4, Date.valueOf(payment.getPaymentDate()));
            ps.setString(5, payment.getPaymentStatus());

            ps.executeUpdate();
        }
    }

    @Override
    public Payment getPaymentByAppointmentId(int appointmentId) throws SQLException {
        String sql = "SELECT * FROM payments WHERE appointment_id = ?";

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
    public List<Payment> getAllPayments() throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments ORDER BY payment_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                payments.add(mapRow(rs));
            }
        }
        return payments;
    }
    
    @Override
    public void updatePayment(Payment payment) throws SQLException {
        String sql = "UPDATE payments SET appointment_id = ?, amount = ?, payment_method = ?, "
               + "payment_date = ?, payment_status = ? WHERE payment_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, payment.getAppointmentId());
        ps.setBigDecimal(2, payment.getAmount());
        ps.setString(3, payment.getPaymentMethod());
        ps.setDate(4, Date.valueOf(payment.getPaymentDate()));
        ps.setString(5, payment.getPaymentStatus());
        ps.setInt(6, payment.getPaymentId());

        ps.executeUpdate();
    }
}

@Override
    public void deletePayment(int paymentId) throws SQLException {
        String sql = "DELETE FROM payments WHERE payment_id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, paymentId);
        ps.executeUpdate();
    }
}

    private Payment mapRow(ResultSet rs) throws SQLException {
        return new Payment(
                rs.getInt("payment_id"),
                rs.getInt("appointment_id"),
                rs.getBigDecimal("amount"),
                rs.getString("payment_method"),
                rs.getDate("payment_date").toLocalDate(),
                rs.getString("payment_status")
        );
    }
}