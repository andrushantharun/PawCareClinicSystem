package com.pawcare.main;

import com.pawcare.model.Customer;
import com.pawcare.service.CustomerService;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        CustomerService service = new CustomerService();

        // Test 1: invalid phone — should be rejected
        try {
            Customer bad = new Customer("Test Bad Phone", "12345", "a@b.com", "Colombo", LocalDate.now());
            service.addCustomer(bad);
            System.out.println("PROBLEM: bad phone was accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly rejected: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

        // Test 2: valid customer — should be accepted
        try {
            Customer good = new Customer("Valid Customer", "0779998888", "valid@example.com", "Galle", LocalDate.now());
            service.addCustomer(good);
            System.out.println("Valid customer added successfully.");
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println("Unexpected rejection: " + e.getMessage());
        }
    }
}