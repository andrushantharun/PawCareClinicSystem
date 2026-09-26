/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pawcare.dao;

import com.pawcare.model.Pet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Arulthas
 */
public interface PetDAO {
    void addPet(Pet pet) throws SQLException;
    void updatePet(Pet pet) throws SQLException;
    void deletePet(int petId) throws SQLException;
    Pet getPetById(int petId) throws SQLException;
    List<Pet> getAllPets() throws SQLException;
    List<Pet> getPetsByCustomerId(int customerId) throws SQLException;
} 
