/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.service.custom;

import com.ijse.GearRentPro.dto.DamageDto;
import com.ijse.GearRentPro.service.SuperService;
import java.util.List;

/**
 *
 * @author User
 */
public interface DamageService extends SuperService {

    // Save a new damage record
    boolean saveDamage(DamageDto dto) throws Exception;

    // Update existing damage record
    boolean updateDamage(DamageDto dto) throws Exception;

    // Delete damage record by ID
    boolean deleteDamage(String id) throws Exception;

    // Find damage record by ID
    DamageDto findDamage(String id) throws Exception;

    // Get all damage records
    List<DamageDto> findAllDamages() throws Exception;

    // Get damage records for a rental
    List<DamageDto> findDamagesByRentalId(String rentalId) throws Exception;
}
