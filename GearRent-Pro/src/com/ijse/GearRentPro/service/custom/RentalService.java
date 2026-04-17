/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.service.custom;

import com.ijse.GearRentPro.dto.RentalDto;
import com.ijse.GearRentPro.service.SuperService;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author User
 */
public interface RentalService extends SuperService {

    // Save a new rental
    boolean saveRental(RentalDto dto) throws Exception;

    // Update existing rental
    boolean updateRental(RentalDto dto) throws Exception;

    // Delete rental by ID
    boolean deleteRental(String id) throws Exception;

    // Find rental by ID
    RentalDto findRental(String id) throws Exception;

    // Process rental return with damages and fees
    boolean processReturn(String rentalId, LocalDate actualReturnDate, String damageId, String damageDescription, double damageCharge) throws Exception;

    // Get all rentals
    List<RentalDto> findAllRentals() throws Exception;

}
