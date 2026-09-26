/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.controller;

import com.pawcare.dao.UserDAO;
import com.pawcare.dao.UserDAOImpl;
import com.pawcare.model.User;

import java.sql.SQLException;

/**
 *
 * @author Arulthas
 */
public class LoginController {

    private final UserDAO userDAO;

    public LoginController() {
        this.userDAO = new UserDAOImpl();
    }

    public User login(String username, String password) throws SQLException {
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            return null; // basic guard — real validation happens in Step 21
        }
        return userDAO.validateLogin(username.trim(), password);
    }
}
