/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.controller;

import com.pawcare.model.Service;
import com.pawcare.service.ServiceManagementService;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Arulthas
 */
public class ServiceController {
     private final ServiceManagementService serviceManagementService;

    public ServiceController() {
        this.serviceManagementService = new ServiceManagementService();
    }

    public void addService(Service service) throws SQLException, IllegalArgumentException {
        serviceManagementService.addService(service);
    }

    public void updateService(Service service) throws SQLException, IllegalArgumentException {
        serviceManagementService.updateService(service);
    }

    public void deleteService(int serviceId) throws SQLException {
        serviceManagementService.deleteService(serviceId);
    }

    public List<Service> getAllServices() throws SQLException {
        return serviceManagementService.getAllServices();
    }
}
