/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.model;

import java.time.LocalDate;


/**
 *
 * @author Arulthas
 */
public class Treatment {
private int treatmentId;
    private int appointmentId;
    private String diagnosis;
    private String medication;
    private String notes;
    private LocalDate treatmentDate;

    public Treatment(int appointmentId, String diagnosis, String medication,
                      String notes, LocalDate treatmentDate) {
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
        this.medication = medication;
        this.notes = notes;
        this.treatmentDate = treatmentDate;
    }

    public Treatment(int treatmentId, int appointmentId, String diagnosis, String medication,
                      String notes, LocalDate treatmentDate) {
        this(appointmentId, diagnosis, medication, notes, treatmentDate);
        this.treatmentId = treatmentId;
    }

    public int getTreatmentId() { return treatmentId; }
    public void setTreatmentId(int treatmentId) { this.treatmentId = treatmentId; }

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getMedication() { return medication; }
    public void setMedication(String medication) { this.medication = medication; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDate getTreatmentDate() { return treatmentDate; }
    public void setTreatmentDate(LocalDate treatmentDate) { this.treatmentDate = treatmentDate; }   
}
