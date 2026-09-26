/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.model;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Arulthas
 */
public class Payment {
    private int paymentId;
    private int appointmentId;
    private BigDecimal amount;
    private String paymentMethod;
    private LocalDate paymentDate;
    private String paymentStatus;

    public Payment(int appointmentId, BigDecimal amount, String paymentMethod,
                    LocalDate paymentDate, String paymentStatus) {
        this.appointmentId = appointmentId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }

    public Payment(int paymentId, int appointmentId, BigDecimal amount, String paymentMethod,
                    LocalDate paymentDate, String paymentStatus) {
        this(appointmentId, amount, paymentMethod, paymentDate, paymentStatus);
        this.paymentId = paymentId;
    }

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
   
}
