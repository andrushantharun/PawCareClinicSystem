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
public class Customer {
    private int customerId;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private LocalDate registeredDate;

  
    public Customer(String fullName, String phone, String email,
                     String address, LocalDate registeredDate) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.registeredDate = registeredDate;
    }

    public Customer(int customerId, String fullName, String phone, String email,
                     String address, LocalDate registeredDate) {
        this(fullName, phone, email, address, registeredDate);
        this.customerId = customerId;
    }


    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public LocalDate getRegisteredDate() { return registeredDate; }
    public void setRegisteredDate(LocalDate registeredDate) { this.registeredDate = registeredDate; }

    @Override
    public String toString() {

        return fullName + " (" + phone + ")";
    }

}
