/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pawcare.dao;

import com.pawcare.model.User;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author Arulthas
 */
public interface UserDAO {
    void addUser(User user) throws SQLException;
    void updateUser(User user) throws SQLException;
    void deleteUser(int userId) throws SQLException;
    User getUserById(int userId) throws SQLException;
    List<User> getAllUsers() throws SQLException;
    User validateLogin(String username, String password) throws SQLException;
}
