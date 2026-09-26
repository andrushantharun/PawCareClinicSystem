/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.service;

import com.pawcare.dao.ServiceDAO;
import com.pawcare.dao.ServiceDAOImpl;
import com.pawcare.model.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author Arulthas
 */
public class ServiceManagementService {
    private final ServiceDAO serviceDAO;

    public ServiceManagementService() {
        this.serviceDAO = new ServiceDAOImpl();
    }

    public void addService(Service service) throws SQLException, IllegalArgumentException {
        validate(service);
        serviceDAO.addService(service);
    }

    public void updateService(Service service) throws SQLException, IllegalArgumentException {
        validate(service);
        serviceDAO.updateService(service);
    }

    public void deleteService(int serviceId) throws SQLException {
        serviceDAO.deleteService(serviceId);
    }

    public List<Service> getAllServices() throws SQLException {
        return serviceDAO.getAllServices();
    }

    private void validate(Service service) {
        if (service.getServiceName() == null || service.getServiceName().trim().isEmpty()) {
            throw new IllegalArgumentException("Service name is required.");
        }
        if (service.getServiceType() == null || service.getServiceType().trim().isEmpty()) {
            throw new IllegalArgumentException("Service type is required.");
        }
        if (service.getPrice() == null || service.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
    }
}
