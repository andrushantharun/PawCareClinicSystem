/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.dao;

import com.pawcare.model.Pet;
import com.pawcare.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arulthas
 */

public class PetDAOImpl implements PetDAO {

    @Override
    public void addPet(Pet pet) throws SQLException {
        String sql = "INSERT INTO pets (customer_id, pet_name, species, breed, age, gender) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pet.getCustomerId());
            ps.setString(2, pet.getPetName());
            ps.setString(3, pet.getSpecies());
            ps.setString(4, pet.getBreed());
            ps.setInt(5, pet.getAge());
            ps.setString(6, pet.getGender());

            ps.executeUpdate();
        }
    }

    @Override
    public void updatePet(Pet pet) throws SQLException {
        String sql = "UPDATE pets SET pet_name = ?, species = ?, breed = ?, age = ?, gender = ? "
                   + "WHERE pet_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pet.getPetName());
            ps.setString(2, pet.getSpecies());
            ps.setString(3, pet.getBreed());
            ps.setInt(4, pet.getAge());
            ps.setString(5, pet.getGender());
            ps.setInt(6, pet.getPetId());

            ps.executeUpdate();
        }
    }

    @Override
    public void deletePet(int petId) throws SQLException {
        String sql = "DELETE FROM pets WHERE pet_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, petId);
            ps.executeUpdate();
        }
    }

    @Override
    public Pet getPetById(int petId) throws SQLException {
        String sql = "SELECT * FROM pets WHERE pet_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, petId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Pet> getAllPets() throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM pets ORDER BY pet_name";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                pets.add(mapRow(rs));
            }
        }
        return pets;
    }

    @Override
    public List<Pet> getPetsByCustomerId(int customerId) throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM pets WHERE customer_id = ? ORDER BY pet_name";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    pets.add(mapRow(rs));
                }
            }
        }
        return pets;
    }

    private Pet mapRow(ResultSet rs) throws SQLException {
        return new Pet(
                rs.getInt("pet_id"),
                rs.getInt("customer_id"),
                rs.getString("pet_name"),
                rs.getString("species"),
                rs.getString("breed"),
                rs.getInt("age"),
                rs.getString("gender")
        );
    }
}
