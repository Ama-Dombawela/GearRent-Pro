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

    boolean saveRental(RentalDto dto) throws Exception;

    boolean updateRental(RentalDto dto) throws Exception;

    boolean deleteRental(String id) throws Exception;

    RentalDto findRental(String id) throws Exception;

    boolean processReturn(String rentalId, LocalDate actualReturnDate, String damageId, String damageDescription, double damageCharge) throws Exception;

    List<RentalDto> findAllRentals() throws Exception;

}
