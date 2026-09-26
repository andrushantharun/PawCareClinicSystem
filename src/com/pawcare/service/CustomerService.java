/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.service;

import com.pawcare.dao.CustomerDAO;
import com.pawcare.dao.CustomerDAOImpl;
import com.pawcare.model.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;


/**
 *
 * @author Arulthas
 */
public class CustomerService {
 private final CustomerDAO customerDAO;

    // Basic patterns — good enough for coursework-level validation
    private static final Pattern PHONE_PATTERN = Pattern.compile("^0\\d{9}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");

    public CustomerService() {
        this.customerDAO = new CustomerDAOImpl();
    }

    public void addCustomer(Customer customer) throws SQLException, IllegalArgumentException {
        validate(customer);
        customerDAO.addCustomer(customer);
    }

    public void updateCustomer(Customer customer) throws SQLException, IllegalArgumentException {
        validate(customer);
        customerDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int customerId) throws SQLException {
        customerDAO.deleteCustomer(customerId);
    }

    public List<Customer> getAllCustomers() throws SQLException {
        return customerDAO.getAllCustomers();
    }

    /**
     * Validates a Customer before it reaches the database.
     * Throws IllegalArgumentException with a clear message the
     * view can show directly to the user.
     */
    private void validate(Customer customer) {
        if (customer.getFullName() == null || customer.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Full name is required.");
        }
        if (customer.getPhone() == null || !PHONE_PATTERN.matcher(customer.getPhone()).matches()) {
            throw new IllegalArgumentException("Phone number must be 10 digits starting with 0 (e.g. 0771234567).");
        }
        if (customer.getEmail() != null && !customer.getEmail().trim().isEmpty()
                && !EMAIL_PATTERN.matcher(customer.getEmail()).matches()) {
            throw new IllegalArgumentException("Email format is invalid.");
        }
    }
}

