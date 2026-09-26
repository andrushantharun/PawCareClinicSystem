/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.controller;

import com.pawcare.model.Pet;
import com.pawcare.service.PetService;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Arulthas
 */
public class PetController {
     private final PetService petService;

    public PetController() {
        this.petService = new PetService();
    }

    public void addPet(Pet pet) throws SQLException, IllegalArgumentException {
        petService.addPet(pet);
    }

    public void updatePet(Pet pet) throws SQLException, IllegalArgumentException {
        petService.updatePet(pet);
    }

    public void deletePet(int petId) throws SQLException {
        petService.deletePet(petId);
    }

    public List<Pet> getAllPets() throws SQLException {
        return petService.getAllPets();
    }

    public List<Pet> getPetsByCustomerId(int customerId) throws SQLException {
        return petService.getPetsByCustomerId(customerId);
    }
}
