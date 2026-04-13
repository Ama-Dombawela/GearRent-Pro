/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro;

import com.ijse.GearRentPro.dto.UserDto;

/**
 *
 * @author User
 */
public class Session {

    private static UserDto loggedInUser;
    private static String userRole;

    public static UserDto getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(UserDto user) {
        loggedInUser = user;
    }

    public static String getUserRole() {
        return userRole;
    }

    public static void setUserRole(String role) {
        userRole = role;
    }

    public static void clear() {
        loggedInUser = null;
        userRole = null;
    }

}
