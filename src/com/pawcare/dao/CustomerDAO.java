/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.dao;

import com.pawcare.model.Customer;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Arulthas
 */
public interface CustomerDAO {
    void addCustomer(Customer customer) throws SQLException;
    void updateCustomer(Customer customer) throws SQLException;
    void deleteCustomer(int customerId) throws SQLException;
    Customer getCustomerById(int customerId) throws SQLException;
    List<Customer> getAllCustomers() throws SQLException;
}
