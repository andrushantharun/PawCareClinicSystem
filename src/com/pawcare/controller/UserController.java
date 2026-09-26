/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.controller;

import com.pawcare.model.User;
import com.pawcare.service.UserService;

import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Arulthas
 */
public class UserController {
     private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public List<User> getAllUsers() throws SQLException {
        return userService.getAllUsers();
    }
}
