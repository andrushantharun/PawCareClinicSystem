/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.model;

/**
 *
 * @author Arulthas
 */
public class Pet {
    private int petId;
    private int customerId;
    private String petName;
    private String species;
    private String breed;
    private int age;
    private String gender;

    public Pet(int customerId, String petName, String species,
               String breed, int age, String gender) {
        this.customerId = customerId;
        this.petName = petName;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
    }

    public Pet(int petId, int customerId, String petName, String species,
               String breed, int age, String gender) {
        this(customerId, petName, species, breed, age, gender);
        this.petId = petId;
    }

    public int getPetId() { return petId; }
    public void setPetId(int petId) { this.petId = petId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    @Override
    public String toString() {
        return petName + " (" + species + ")";
    }
}
