/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.model;

import java.math.BigDecimal;
/**
 *
 * @author Arulthas
 */
public class Service {
private int serviceId;
    private String serviceName;
    private String serviceType;
    private BigDecimal price;

    public Service(String serviceName, String serviceType, BigDecimal price) {
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.price = price;
    }

    public Service(int serviceId, String serviceName, String serviceType, BigDecimal price) {
        this(serviceName, serviceType, price);
        this.serviceId = serviceId;
    }

    public int getServiceId() { return serviceId; }
    public void setServiceId(int serviceId) { this.serviceId = serviceId; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    @Override
    public String toString() {
        return serviceName + " - Rs. " + price;
    } 
}
