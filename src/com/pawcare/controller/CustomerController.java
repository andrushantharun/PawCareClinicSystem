/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.controller;

import com.pawcare.model.Customer;
import com.pawcare.service.CustomerService;

import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author Arulthas
 */
public class CustomerController {
private final CustomerService customerService;

    public CustomerController() {
        this.customerService = new CustomerService();
    }

    public void addCustomer(Customer customer) throws SQLException, IllegalArgumentException {
        customerService.addCustomer(customer);
    }

    public void updateCustomer(Customer customer) throws SQLException, IllegalArgumentException {
        customerService.updateCustomer(customer);
    }

    public void deleteCustomer(int customerId) throws SQLException {
        customerService.deleteCustomer(customerId);
    }

    public List<Customer> getAllCustomers() throws SQLException {
        return customerService.getAllCustomers();
    }
}
