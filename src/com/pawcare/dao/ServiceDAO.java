/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pawcare.dao;

import com.pawcare.model.Service;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author Arulthas
 */
public interface ServiceDAO {
    void addService(Service service) throws SQLException;
    void updateService(Service service) throws SQLException;
    void deleteService(int serviceId) throws SQLException;
    Service getServiceById(int serviceId) throws SQLException;
    List<Service> getAllServices() throws SQLException;
}
