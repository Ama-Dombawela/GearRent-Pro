/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.service.custom;

import com.ijse.GearRentPro.dto.EquipmentDto;
import com.ijse.GearRentPro.service.SuperService;
import java.util.List;

/**
 *
 * @author User
 */
public interface EquipmentService extends SuperService {

    // Save a new equipment
    boolean saveEquipment(EquipmentDto dto) throws Exception;

    // Update existing equipment
    boolean updateEquipment(EquipmentDto dto) throws Exception;

    // Delete equipment by ID
    boolean deleteEquipment(String id) throws Exception;

    // Find equipment by ID
    EquipmentDto findEquipment(String id) throws Exception;

    // Get all equipment
    List<EquipmentDto> findAllEquipments() throws Exception;

}
