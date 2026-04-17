/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.controller;

import com.ijse.GearRentPro.dto.DamageDto;
import com.ijse.GearRentPro.service.ServiceFactory;
import com.ijse.GearRentPro.service.custom.DamageService;
import java.util.List;

/**
 *
 * @author User
 */
public class DamageController {

    private final DamageService damageService = (DamageService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.DAMAGE);

    // Handles damage record actions from the UI.
    public boolean saveDamage(DamageDto dto) throws Exception {
        return damageService.saveDamage(dto);
    }

    public boolean updateDamage(DamageDto dto) throws Exception {
        return damageService.updateDamage(dto);
    }

    public boolean deleteDamage(String id) throws Exception {
        return damageService.deleteDamage(id);
    }

    public DamageDto findDamage(String id) throws Exception {
        return damageService.findDamage(id);
    }

    public List<DamageDto> findAllDamages() throws Exception {
        return damageService.findAllDamages();
    }

    public List<DamageDto> findDamagesByRentalId(String rentalId) throws Exception {
        return damageService.findDamagesByRentalId(rentalId);
    }

}
