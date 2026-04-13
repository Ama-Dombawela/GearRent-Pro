/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.controller;

import com.ijse.GearRentPro.dto.RentalDto;
import com.ijse.GearRentPro.service.ServiceFactory;
import com.ijse.GearRentPro.service.custom.RentalService;
import java.util.List;

/**
 *
 * @author User
 */
public class RentalController {

    private final RentalService rentalService = (RentalService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.RENTAL);

    public boolean saveRental(RentalDto dto) throws Exception {
        return rentalService.saveRental(dto);
    }

    public boolean updateRental(RentalDto dto) throws Exception {
        return rentalService.updateRental(dto);
    }

    public boolean deleteRental(String id) throws Exception {
        return rentalService.deleteRental(id);
    }

    public RentalDto findRental(String id) throws Exception {
        return rentalService.findRental(id);
    }

    public List<RentalDto> findAllRentals() throws Exception {
        return rentalService.findAllRentals();
    }

}
