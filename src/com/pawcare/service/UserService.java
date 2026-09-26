/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.service;

import com.pawcare.dao.UserDAO;
import com.pawcare.dao.UserDAOImpl;
import com.pawcare.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Arulthas
 */
public class UserService {
    
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAOImpl();
    }

    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }
}
