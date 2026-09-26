/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.service;

import com.pawcare.dao.PetDAO;
import com.pawcare.dao.PetDAOImpl;
import com.pawcare.model.Pet;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Arulthas
 */
public class PetService {
    private final PetDAO petDAO;

    public PetService() {
        this.petDAO = new PetDAOImpl();
    }

    public void addPet(Pet pet) throws SQLException, IllegalArgumentException {
        validate(pet);
        petDAO.addPet(pet);
    }

    public void updatePet(Pet pet) throws SQLException, IllegalArgumentException {
        validate(pet);
        petDAO.updatePet(pet);
    }

    public void deletePet(int petId) throws SQLException {
        petDAO.deletePet(petId);
    }

    public List<Pet> getAllPets() throws SQLException {
        return petDAO.getAllPets();
    }

    public List<Pet> getPetsByCustomerId(int customerId) throws SQLException {
        return petDAO.getPetsByCustomerId(customerId);
    }

    /**
     * Validates a Pet before it reaches the database.
     * A pet must always belong to a real, selected customer.
     */
    private void validate(Pet pet) {
        if (pet.getCustomerId() <= 0) {
            throw new IllegalArgumentException("Please select an owner (customer) for this pet.");
        }
        if (pet.getPetName() == null || pet.getPetName().trim().isEmpty()) {
            throw new IllegalArgumentException("Pet name is required.");
        }
        if (pet.getSpecies() == null || pet.getSpecies().trim().isEmpty()) {
            throw new IllegalArgumentException("Species is required.");
        }
        if (pet.getAge() < 0 || pet.getAge() > 40) {
            throw new IllegalArgumentException("Age must be between 0 and 40.");
        }
        if (pet.getGender() == null || pet.getGender().trim().isEmpty()) {
            throw new IllegalArgumentException("Please select a gender.");
        }
    }
}
