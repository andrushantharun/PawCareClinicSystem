/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pawcare.view;

import com.pawcare.controller.AppointmentController;
import com.pawcare.controller.CustomerController;
import com.pawcare.controller.PetController;
import com.pawcare.controller.ServiceController;
import com.pawcare.controller.UserController;
import com.pawcare.exception.InvalidAppointmentException;
import com.pawcare.model.Appointment;
import com.pawcare.model.Customer;
import com.pawcare.model.Pet;
import com.pawcare.model.Service;
import com.pawcare.model.User;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Map;

/**
 *
 * @author Arulthas
 */
public class AppointmentView extends javax.swing.JFrame {
    
    private final AppointmentController appointmentController = new AppointmentController();
    private final CustomerController customerController = new CustomerController();
    private final PetController petController = new PetController();
    private final ServiceController serviceController = new ServiceController();
    private final UserController userController = new UserController();
    private final User loggedInUser;
    private int selectedAppointmentId = 0;
    /**
     * Creates new form AppointmentView
     * @param loggedInUser
     */
    public AppointmentView(User loggedInUser) {
    this.loggedInUser = loggedInUser;
    initComponents();
    loadCustomerDropdown();
    loadServiceDropdown();
    loadStaffDropdown();
    loadAppointmentTable();
}

    private void loadCustomerDropdown() {
    try {
        cmbCustomer.removeAllItems();
        for (Customer c : customerController.getAllCustomers()) {
            cmbCustomer.addItem(c);
        }
        loadPetDropdownForSelectedCustomer();
    } catch (SQLException ex) {
        showError("Could not load customers: " + ex.getMessage());
    }
}

private void loadPetDropdownForSelectedCustomer() {
    cmbPet.removeAllItems();
    Customer selected = (Customer) cmbCustomer.getSelectedItem();
    if (selected == null) return;

    try {
        for (Pet p : petController.getPetsByCustomerId(selected.getCustomerId())) {
            cmbPet.addItem(p);
        }
    } catch (SQLException ex) {
        showError("Could not load pets: " + ex.getMessage());
    }
}

private void loadServiceDropdown() {
    try {
        cmbService.removeAllItems();
        for (Service s : serviceController.getAllServices()) {
            cmbService.addItem(s);
        }
    } catch (SQLException ex) {
        showError("Could not load services: " + ex.getMessage());
    }
}

private void loadStaffDropdown() {
    try {
        cmbStaff.removeAllItems();
        for (User u : userController.getAllUsers()) {
            cmbStaff.addItem(u);
        }
    } catch (SQLException ex) {
        showError("Could not load staff: " + ex.getMessage());
    }
}

private void loadAppointmentTable() {
    try {
        DefaultTableModel model = (DefaultTableModel) tblAppointments.getModel();
        model.setRowCount(0);

        for (Map<String, Object> row : appointmentController.getAppointmentDetailsList()) {
            model.addRow(new Object[]{
                row.get("appointment_id"),
                row.get("customer_name"),
                row.get("pet_name"),
                row.get("service_name"),
                row.get("staff_name"),
                row.get("appointment_date"),
                row.get("appointment_time"),
                row.get("status")
            });
        }
    } catch (SQLException ex) {
        showError("Could not load appointments: " + ex.getMessage());
    }
}

private LocalDate parseDate() {
    try {
        return LocalDate.parse(txtDate.getText().trim());
    } catch (DateTimeParseException ex) {
        throw new IllegalArgumentException("Date must be in yyyy-MM-dd format, e.g. 2026-10-05");
    }
}

private LocalTime parseTime() {
    try {
        return LocalTime.parse(txtTime.getText().trim());
    } catch (DateTimeParseException ex) {
        throw new IllegalArgumentException("Time must be in HH:mm format, e.g. 14:30");
    }
}

private void clearForm() {
    if (cmbCustomer.getItemCount() > 0) cmbCustomer.setSelectedIndex(0);
    if (cmbService.getItemCount() > 0) cmbService.setSelectedIndex(0);
    if (cmbStaff.getItemCount() > 0) cmbStaff.setSelectedIndex(0);
    if (cmbStatus.getItemCount() > 0) cmbStatus.setSelectedIndex(0);
    txtDate.setText("");
    txtTime.setText("");
    selectedAppointmentId = 0;
    tblAppointments.clearSelection();
    lblMessage.setText("");
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbCustomer = new javax.swing.JComboBox<>();
        cmbPet = new javax.swing.JComboBox<>();
        cmbService = new javax.swing.JComboBox<>();
        cmbStaff = new javax.swing.JComboBox<>();
        txtDate = new javax.swing.JTextField();
        txtTime = new javax.swing.JTextField();
        cmbStatus = new javax.swing.JComboBox<>();
        lblMessage = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAppointments = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Pet :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Customer :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("Service :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("Staff :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("Time (HH:mm) :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("Date (yyyy-MM-dd) :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("Status :");

        cmbCustomer.addItemListener(this::cmbCustomerItemStateChanged);

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Scheduled", "Completed", "Cancelled" }));

        lblMessage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMessage.setForeground(java.awt.Color.white);
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnAdd.setText("Add");
        btnAdd.addActionListener(this::btnAddActionPerformed);

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnClear.setText("Clear");
        btnClear.addActionListener(this::btnClearActionPerformed);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        tblAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Customer", "Pet", "Service", "Staff", "Date", "Time", "Status"
            }
        ));
        tblAppointments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAppointmentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAppointments);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(txtTime))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnUpdate)
                                    .addGap(48, 48, 48)
                                    .addComponent(btnClear))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnAdd)
                                    .addGap(48, 48, 48)
                                    .addComponent(btnDelete)))
                            .addGap(65, 65, 65)
                            .addComponent(btnBack))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblMessage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(cmbStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbService, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbPet, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(cmbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbStaff, 0, 163, Short.MAX_VALUE)
                                    .addComponent(txtDate))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbPet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnDelete)
                            .addComponent(btnBack))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdate)
                            .addComponent(btnClear)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
    try {
        Appointment appointment = buildAppointmentFromForm(0);
        appointmentController.addAppointment(appointment);
        showSuccess("Appointment booked successfully.");
        clearForm();
        loadAppointmentTable();
    } catch (IllegalArgumentException | InvalidAppointmentException ex) {
        showError(ex.getMessage());
    } catch (SQLException ex) {
        showError("Database error: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnAddActionPerformed

    private void tblAppointmentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAppointmentsMouseClicked
        int row = tblAppointments.getSelectedRow();
    if (row == -1) return;

    DefaultTableModel model = (DefaultTableModel) tblAppointments.getModel();
    selectedAppointmentId = (int) model.getValueAt(row, 0);

    try {
        Appointment appointment = appointmentController.getAppointmentById(selectedAppointmentId);
        if (appointment == null) return;

        selectComboItemById(cmbCustomer, appointment.getCustomerId());
        loadPetDropdownForSelectedCustomer();
        selectComboItemById(cmbPet, appointment.getPetId());
        selectComboItemById(cmbService, appointment.getServiceId());
        selectComboItemById(cmbStaff, appointment.getUserId());

        txtDate.setText(appointment.getAppointmentDate().toString());
        txtTime.setText(appointment.getAppointmentTime().toString());
        cmbStatus.setSelectedItem(appointment.getStatus());
        lblMessage.setText("");
    } catch (SQLException ex) {
        showError("Could not load appointment: " + ex.getMessage());
    }
    }//GEN-LAST:event_tblAppointmentsMouseClicked
    private void selectComboItemById(javax.swing.JComboBox<?> combo, int id) {
    for (int i = 0; i < combo.getItemCount(); i++) {
        Object item = combo.getItemAt(i);
        int itemId = switch (item) {
            case Customer c -> c.getCustomerId();
            case Pet p -> p.getPetId();
            case Service s -> s.getServiceId();
            case User u -> u.getUserId();
            default -> -1;
};
        if (itemId == id) {
            combo.setSelectedIndex(i);
            return;
        }
    }
}
    private void cmbCustomerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCustomerItemStateChanged
         if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
        loadPetDropdownForSelectedCustomer();
    }
    }//GEN-LAST:event_cmbCustomerItemStateChanged

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (selectedAppointmentId == 0) {
        showError("Select an appointment from the table first.");
        return;
    }
    try {
        Appointment appointment = buildAppointmentFromForm(selectedAppointmentId);
        appointmentController.updateAppointment(appointment);
        showSuccess("Appointment updated successfully.");
        clearForm();
        loadAppointmentTable();
    } catch (IllegalArgumentException | InvalidAppointmentException ex) {
        showError(ex.getMessage());
    } catch (SQLException ex) {
        showError("Database error: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedAppointmentId == 0) {
        showError("Select an appointment from the table first.");
        return;
    }
    int confirm = javax.swing.JOptionPane.showConfirmDialog(
            this, "Delete this appointment?", "Confirm Delete",
            javax.swing.JOptionPane.YES_NO_OPTION);
    if (confirm != javax.swing.JOptionPane.YES_OPTION) return;

    try {
        appointmentController.deleteAppointment(selectedAppointmentId);
        showSuccess("Appointment deleted.");
        clearForm();
        loadAppointmentTable();
    } catch (SQLException ex) {
        showError("Database error: " + ex.getMessage());
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        new DashboardView(loggedInUser).setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed
    private Appointment buildAppointmentFromForm(int appointmentId) {
    Customer customer = (Customer) cmbCustomer.getSelectedItem();
    Pet pet = (Pet) cmbPet.getSelectedItem();
    Service service = (Service) cmbService.getSelectedItem();
    User staff = (User) cmbStaff.getSelectedItem();

    int customerId = (customer != null) ? customer.getCustomerId() : 0;
    int petId = (pet != null) ? pet.getPetId() : 0;
    int serviceId = (service != null) ? service.getServiceId() : 0;
    int userId = (staff != null) ? staff.getUserId() : 0;
    String status = (String) cmbStatus.getSelectedItem();

    return new Appointment(appointmentId, customerId, petId, serviceId, userId,
            parseDate(), parseTime(), status);
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<Customer> cmbCustomer;
    private javax.swing.JComboBox<Pet> cmbPet;
    private javax.swing.JComboBox<Service> cmbService;
    private javax.swing.JComboBox<User> cmbStaff;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JTable tblAppointments;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables
}
