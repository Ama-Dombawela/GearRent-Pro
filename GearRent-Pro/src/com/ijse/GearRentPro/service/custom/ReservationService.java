/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.service.custom;

import com.ijse.GearRentPro.dto.RentalDto;
import com.ijse.GearRentPro.dto.ReservationDto;
import com.ijse.GearRentPro.service.SuperService;
import java.util.List;

/**
 *
 * @author User
 */
public interface ReservationService extends SuperService {

    // Save a new reservation
    boolean saveReservation(ReservationDto dto) throws Exception;

    // Update existing reservation
    boolean updateReservation(ReservationDto dto) throws Exception;

    // Cancel a reservation
    boolean cancelReservation(String id) throws Exception;

    // Convert a reservation to a rental
    boolean convertReservationToRental(ReservationDto reservation, RentalDto rental) throws Exception;

    // Delete reservation by ID
    boolean deleteReservation(String id) throws Exception;

    // Find reservation by ID
    ReservationDto findReservation(String id) throws Exception;

    // Get all reservations
    List<ReservationDto> findAllReservations() throws Exception;
}
