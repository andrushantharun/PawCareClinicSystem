/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.dao;

import com.pawcare.model.Service;
import com.pawcare.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Arulthas
 */
public class ServiceDAOImpl implements ServiceDAO {

    @Override
    public void addService(Service service) throws SQLException {
        String sql = "INSERT INTO services (service_name, service_type, price) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, service.getServiceName());
            ps.setString(2, service.getServiceType());
            ps.setBigDecimal(3, service.getPrice());

            ps.executeUpdate();
        }
    }

    @Override
    public void updateService(Service service) throws SQLException {
        String sql = "UPDATE services SET service_name = ?, service_type = ?, price = ? "
                   + "WHERE service_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, service.getServiceName());
            ps.setString(2, service.getServiceType());
            ps.setBigDecimal(3, service.getPrice());
            ps.setInt(4, service.getServiceId());

            ps.executeUpdate();
        }
    }

    @Override
    public void deleteService(int serviceId) throws SQLException {
        String sql = "DELETE FROM services WHERE service_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, serviceId);
            ps.executeUpdate();
        }
    }

    @Override
    public Service getServiceById(int serviceId) throws SQLException {
        String sql = "SELECT * FROM services WHERE service_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, serviceId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Service> getAllServices() throws SQLException {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM services ORDER BY service_name";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                services.add(mapRow(rs));
            }
        }
        return services;
    }

    private Service mapRow(ResultSet rs) throws SQLException {
        return new Service(
                rs.getInt("service_id"),
                rs.getString("service_name"),
                rs.getString("service_type"),
                rs.getBigDecimal("price")
        );
    }
}