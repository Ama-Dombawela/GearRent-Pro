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

    boolean saveDamage(DamageDto dto) throws Exception;

    boolean updateDamage(DamageDto dto) throws Exception;

    boolean deleteDamage(String id) throws Exception;

    DamageDto findDamage(String id) throws Exception;

    List<DamageDto> findAllDamages() throws Exception;

    List<DamageDto> findDamagesByRentalId(String rentalId) throws Exception;
}
