/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.controller;

import com.ijse.GearRentPro.dto.EquipmentDto;
import com.ijse.GearRentPro.service.ServiceFactory;
import com.ijse.GearRentPro.service.custom.EquipmentService;
import java.util.List;

/**
 *
 * @author User
 */
public class EquipmentController {

    private final EquipmentService equipmentService = (EquipmentService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.EQUIPMENT);

    // Handles equipment management actions from the UI.
    public boolean saveEquipment(EquipmentDto dto) throws Exception {
        return equipmentService.saveEquipment(dto);
    }

    public boolean updateEquipment(EquipmentDto dto) throws Exception {
        return equipmentService.updateEquipment(dto);
    }

    public boolean deleteEquipment(String id) throws Exception {
        return equipmentService.deleteEquipment(id);
    }

    public EquipmentDto findEquipment(String id) throws Exception {
        return equipmentService.findEquipment(id);
    }

    public List<EquipmentDto> findAllEquipments() throws Exception {
        return equipmentService.findAllEquipments();
    }

}
