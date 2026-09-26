/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Arulthas
 */
public class Appointment {
private int appointmentId;
    private int customerId;
    private int petId;
    private int serviceId;
    private int userId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String status;

    public Appointment(int customerId, int petId, int serviceId, int userId,
                        LocalDate appointmentDate, LocalTime appointmentTime, String status) {
        this.customerId = customerId;
        this.petId = petId;
        this.serviceId = serviceId;
        this.userId = userId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    public Appointment(int appointmentId, int customerId, int petId, int serviceId, int userId,
                        LocalDate appointmentDate, LocalTime appointmentTime, String status) {
        this(customerId, petId, serviceId, userId, appointmentDate, appointmentTime, status);
        this.appointmentId = appointmentId;
    }

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getPetId() { return petId; }
    public void setPetId(int petId) { this.petId = petId; }

    public int getServiceId() { return serviceId; }
    public void setServiceId(int serviceId) { this.serviceId = serviceId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }

    public LocalTime getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(LocalTime appointmentTime) { this.appointmentTime = appointmentTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Appointment #" + appointmentId + " on " + appointmentDate + " " + appointmentTime;
    }
}
