/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.util;

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

    public static String getCurrentBranchId() {

        String role = loggedInUser.getRoleId();
        if ("R002".equals(role) || "R003".equals(role)) {
            return loggedInUser.getBranchId();
        }
        return null;
    }

    public static boolean isAdmin() {
        return "R001".equals(loggedInUser.getRoleId());
    }

    public static boolean isBranchManager() {
        return "R002".equals(loggedInUser.getRoleId());
    }

    public static boolean isStaff() {
        return "R003".equals(loggedInUser.getRoleId());
    }
}
