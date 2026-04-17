/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ijse.GearRentPro;

import com.ijse.GearRentPro.db.DBConnection;
import com.ijse.GearRentPro.view.LoginView;
import java.sql.Connection;

/**
 *
 * @author User
 */
/*
 * Main entry point for the GearRent-Pro application.
 * Initializes the database connection and launches the login interface.
 */
public class Main {

    /**
     * Starts the application, checks the database connection, and opens the
     * login window.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            System.out.println("Database Connected Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new LoginView().setVisible(true);
    }

}
