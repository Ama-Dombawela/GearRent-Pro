/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.service.custom;

import com.ijse.GearRentPro.dto.UserDto;
import com.ijse.GearRentPro.service.SuperService;
import java.util.List;

/**
 *
 * @author User
 */
public interface UserService extends SuperService {

    // Save a new user account
    boolean saveUser(UserDto dto) throws Exception;

    // Update existing user account
    boolean updateUser(UserDto dto) throws Exception;

    // Delete user account by ID
    boolean deleteUser(String id) throws Exception;

    // Find user account by ID
    UserDto findUser(String id) throws Exception;

    // Get all user accounts
    List<UserDto> findAllUsers() throws Exception;

    // Authenticate user by username and password
    UserDto findUserByUsernameAndPassword(String username, String password) throws Exception;
}
