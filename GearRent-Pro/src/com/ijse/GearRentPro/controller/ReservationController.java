/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.controller;

import com.ijse.GearRentPro.dto.RentalDto;
import com.ijse.GearRentPro.dto.ReservationDto;
import com.ijse.GearRentPro.service.ServiceFactory;
import com.ijse.GearRentPro.service.custom.ReservationService;
import java.util.List;

/**
 *
 * @author User
 */
public class ReservationController {

    private final ReservationService reservationService = (ReservationService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.RESERVATION);

    // Handles reservation workflow actions from the UI.
    public boolean saveReservation(ReservationDto dto) throws Exception {
        return reservationService.saveReservation(dto);
    }

    public boolean updateReservation(ReservationDto dto) throws Exception {
        return reservationService.updateReservation(dto);
    }

    public boolean cancelReservation(String id) throws Exception {
        return reservationService.cancelReservation(id);
    }

    public boolean convertReservationToRental(ReservationDto reservation, RentalDto rental) throws Exception {
        return reservationService.convertReservationToRental(reservation, rental);
    }

    public boolean deleteReservation(String id) throws Exception {
        return reservationService.deleteReservation(id);
    }

    public ReservationDto findReservation(String id) throws Exception {
        return reservationService.findReservation(id);
    }

    public List<ReservationDto> findAllReservations() throws Exception {
        return reservationService.findAllReservations();
    }
}
