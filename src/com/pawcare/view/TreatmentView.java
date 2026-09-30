/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pawcare.view;

import com.pawcare.controller.AppointmentController;
import com.pawcare.controller.TreatmentController;
import com.pawcare.model.Treatment;
import com.pawcare.model.User;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Arulthas
 */
public class TreatmentView extends javax.swing.JFrame {
    
    private final TreatmentController treatmentController = new TreatmentController();
    private final AppointmentController appointmentController = new AppointmentController();
    private final User loggedInUser;
    private int selectedTreatmentId = 0; // 0 = Add mode
    private final List<Integer> appointmentIdsForCombo = new ArrayList<>();
    private final Map<Integer, String> appointmentLabelsById = new LinkedHashMap<>();
    /**
     * Creates new form TreatmentView
     */
    public TreatmentView(User loggedInUser) {
    this.loggedInUser = loggedInUser;
    initComponents();
    loadAppointmentDropdown();
    loadTreatmentTable();
}
    private void loadAppointmentDropdown() {
    try {
        cmbAppointment.removeAllItems();
        appointmentIdsForCombo.clear();
        appointmentLabelsById.clear();

        for (Map<String, Object> row : appointmentController.getAppointmentDetailsList()) {
            int appointmentId = (int) row.get("appointment_id");
            String label = row.get("customer_name") + " - " + row.get("pet_name")
                    + " - " + row.get("appointment_date") + " " + row.get("appointment_time");
            cmbAppointment.addItem(label);
            appointmentIdsForCombo.add(appointmentId);
            appointmentLabelsById.put(appointmentId, label);
        }
        loadTreatmentForSelectedAppointment();
    } catch (SQLException ex) {
        showError("Could not load appointments: " + ex.getMessage());
    }
}

/** Auto-loads an existing treatment for the currently selected appointment, if one exists. */
private void loadTreatmentForSelectedAppointment() {
    int index = cmbAppointment.getSelectedIndex();
    if (index < 0 || index >= appointmentIdsForCombo.size()) return;

    int appointmentId = appointmentIdsForCombo.get(index);
    try {
        Treatment existing = treatmentController.getTreatmentByAppointmentId(appointmentId);
        if (existing != null) {
            selectedTreatmentId = existing.getTreatmentId();
            txtDiagnosis.setText(existing.getDiagnosis());
            txtMedication.setText(existing.getMedication());
            txtNotes.setText(existing.getNotes());
            txtTreatmentDate.setText(existing.getTreatmentDate().toString());
            lblMessage.setForeground(new Color(0, 0, 160));
            lblMessage.setText("Existing treatment loaded — Update will modify it.");
        } else {
            selectedTreatmentId = 0;
            txtDiagnosis.setText("");
            txtMedication.setText("");
            txtNotes.setText("");
            txtTreatmentDate.setText("");
            lblMessage.setText("");
        }
    } catch (SQLException ex) {
        showError("Could not check existing treatment: " + ex.getMessage());
    }
}

private void loadTreatmentTable() {
    try {
        DefaultTableModel model = (DefaultTableModel) tblTreatments.getModel();
        model.setRowCount(0);

        for (Treatment t : treatmentController.getAllTreatments()) {
            String appointmentLabel = appointmentLabelsById.getOrDefault(
                    t.getAppointmentId(), "Appointment #" + t.getAppointmentId());
            model.addRow(new Object[]{
                t.getTreatmentId(),
                appointmentLabel,
                t.getDiagnosis(),
                t.getMedication(),
                t.getNotes(),
                t.getTreatmentDate()
            });
        }
    } catch (SQLException ex) {
        showError("Could not load treatments: " + ex.getMessage());
    }
}

private LocalDate parseTreatmentDate() {
    try {
        return LocalDate.parse(txtTreatmentDate.getText().trim());
    } catch (DateTimeParseException ex) {
        throw new IllegalArgumentException("Date must be in yyyy-MM-dd format, e.g. 2026-09-20");
    }
}

private void clearForm() {
    if (cmbAppointment.getItemCount() > 0) {
        cmbAppointment.setSelectedIndex(0);
    }
    loadTreatmentForSelectedAppointment();
    tblTreatments.clearSelection();
}

private void showError(String message) {
    lblMessage.setForeground(Color.RED);
    lblMessage.setText(message);
}

private void showSuccess(String message) {
    lblMessage.setForeground(new Color(0, 128, 0));
    lblMessage.setText(message);
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();
        cmbAppointment = new javax.swing.JComboBox<>();
        txtDiagnosis = new javax.swing.JTextField();
        txtMedication = new javax.swing.JTextField();
        txtTreatmentDate = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNotes = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTreatments = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Diagnosis :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Appointment :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("Medication :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("Treatment Date (yyyy-MM-dd)  :");

        lblMessage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMessage.setForeground(java.awt.Color.white);
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cmbAppointment.addItemListener(this::cmbAppointmentItemStateChanged);

        txtNotes.setColumns(20);
        txtNotes.setLineWrap(true);
        txtNotes.setRows(5);
        txtNotes.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtNotes);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("Notes :");

        tblTreatments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Appointment", "Diagnosis", "Medication", "Notes", "Date"
            }
        ));
        tblTreatments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTreatmentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTreatments);

        btnAdd.setText("Add");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        btnClear.setText("Clear");
        btnClear.addActionListener(this::btnClearActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnUpdate)
                            .addComponent(btnAdd))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnDelete)
                                .addGap(18, 18, 18)
                                .addComponent(btnBack))
                            .addComponent(btnClear)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMedication, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDiagnosis, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(106, 106, 106)
                                    .addComponent(cmbAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTreatmentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cmbAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtDiagnosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMedication, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtTreatmentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnBack))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnClear))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (selectedTreatmentId == 0) {
        showError("No existing treatment to update for this appointment.");
        return;
    }
    try {
        int index = cmbAppointment.getSelectedIndex();
        int appointmentId = appointmentIdsForCombo.get(index);

        Treatment treatment = new Treatment(
                selectedTreatmentId,
                appointmentId,
                txtDiagnosis.getText().trim(),
                txtMedication.getText().trim(),
                txtNotes.getText().trim(),
                parseTreatmentDate()
        );
        treatmentController.updateTreatment(treatment);
        showSuccess("Treatment updated successfully.");
        loadAppointmentDropdown();
        loadTreatmentTable();
    } catch (IllegalArgumentException ex) {
        showError(ex.getMessage());
    } catch (SQLException ex) {
        showError("Database error: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (selectedTreatmentId != 0) {
        showError("This appointment already has a treatment. Use Update instead.");
        return;
    }
    try {
        int index = cmbAppointment.getSelectedIndex();
        int appointmentId = appointmentIdsForCombo.get(index);

        Treatment treatment = new Treatment(
                appointmentId,
                txtDiagnosis.getText().trim(),
                txtMedication.getText().trim(),
                txtNotes.getText().trim(),
                parseTreatmentDate()
        );
        treatmentController.addTreatment(treatment);
        showSuccess("Treatment recorded successfully.");
        loadAppointmentDropdown();
        loadTreatmentTable();
    } catch (IllegalArgumentException ex) {
        showError(ex.getMessage());
    } catch (SQLException ex) {
        showError("Database error: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedTreatmentId == 0) {
        showError("No existing treatment to delete for this appointment.");
        return;
    }
    int confirm = javax.swing.JOptionPane.showConfirmDialog(
            this, "Delete this treatment record?", "Confirm Delete",
            javax.swing.JOptionPane.YES_NO_OPTION);
    if (confirm != javax.swing.JOptionPane.YES_OPTION) return;

    try {
        treatmentController.deleteTreatment(selectedTreatmentId);
        showSuccess("Treatment deleted.");
        loadAppointmentDropdown();
        loadTreatmentTable();
    } catch (SQLException ex) {
        showError("Database error: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new DashboardView(loggedInUser).setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void tblTreatmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTreatmentsMouseClicked
        int row = tblTreatments.getSelectedRow();
    if (row == -1) return;

    DefaultTableModel model = (DefaultTableModel) tblTreatments.getModel();
    int treatmentId = (int) model.getValueAt(row, 0);

    try {
        for (Treatment t : treatmentController.getAllTreatments()) {
            if (t.getTreatmentId() == treatmentId) {
                int comboIndex = appointmentIdsForCombo.indexOf(t.getAppointmentId());
                if (comboIndex >= 0) {
                    cmbAppointment.setSelectedIndex(comboIndex); // triggers auto-load via item listener
                }
                break;
            }
        }
    } catch (SQLException ex) {
        showError("Could not load treatment: " + ex.getMessage());
    }
    }//GEN-LAST:event_tblTreatmentsMouseClicked

    private void cmbAppointmentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAppointmentItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
        loadTreatmentForSelectedAppointment();
    }
    }//GEN-LAST:event_cmbAppointmentItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbAppointment;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JTable tblTreatments;
    private javax.swing.JTextField txtDiagnosis;
    private javax.swing.JTextField txtMedication;
    private javax.swing.JTextArea txtNotes;
    private javax.swing.JTextField txtTreatmentDate;
    // End of variables declaration//GEN-END:variables
}
