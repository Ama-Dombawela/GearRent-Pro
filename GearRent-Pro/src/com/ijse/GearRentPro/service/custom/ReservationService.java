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

    boolean saveReservation(ReservationDto dto) throws Exception;

    boolean updateReservation(ReservationDto dto) throws Exception;

    boolean cancelReservation(String id) throws Exception;

    boolean convertReservationToRental(ReservationDto reservation, RentalDto rental) throws Exception;

    boolean deleteReservation(String id) throws Exception;

    ReservationDto findReservation(String id) throws Exception;

    List<ReservationDto> findAllReservations() throws Exception;
}
