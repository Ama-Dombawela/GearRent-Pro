/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.service.custom.impl;

import com.ijse.GearRentPro.dao.Custom.EquipmentDao;
import com.ijse.GearRentPro.dao.DaoFactory;
import com.ijse.GearRentPro.dto.EquipmentDto;
import com.ijse.GearRentPro.entity.EquipmentEntity;
import com.ijse.GearRentPro.service.custom.EquipmentService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class EquipmentServiceImpl implements EquipmentService {

    EquipmentDao equipmentDao = (EquipmentDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.EQUIPMENT);

    @Override
    public boolean saveEquipment(EquipmentDto dto) throws Exception {
        return equipmentDao.save(new EquipmentEntity(
                dto.getEquipmentId(),
                dto.getCategory(),
                dto.getBranch(),
                dto.getBrand(),
                dto.getModel(),
                dto.getPurchaseYear(),
                dto.getBaseDailyPrice(),
                dto.getSecurityDeposit(),
                dto.getStatus()
        ));
    }

    @Override
    public boolean updateEquipment(EquipmentDto dto) throws Exception {
        return equipmentDao.update(new EquipmentEntity(
                dto.getEquipmentId(),
                dto.getCategory(),
                dto.getBranch(),
                dto.getBrand(),
                dto.getModel(),
                dto.getPurchaseYear(),
                dto.getBaseDailyPrice(),
                dto.getSecurityDeposit(),
                dto.getStatus()
        ));
    }

    @Override
    public boolean deleteEquipment(String id) throws Exception {
        return equipmentDao.delete(id);
    }

    @Override
    public EquipmentDto findEquipment(String id) throws Exception {
        EquipmentEntity entity = equipmentDao.search(id);
        return new EquipmentDto(
                entity.getEquipmentId(),
                entity.getCategory(),
                entity.getBranch(),
                entity.getBrand(),
                entity.getModel(),
                entity.getPurchaseYear(),
                entity.getBaseDailyPrice(),
                entity.getSecurityDeposit(),
                entity.getStatus()
        );
    }

    @Override
    public List<EquipmentDto> findAllEquipments() throws Exception {
        ArrayList<EquipmentEntity> entities = equipmentDao.getAll();
        List<EquipmentDto> dtos = new ArrayList<>();
        for (EquipmentEntity entity : entities) {
            dtos.add(new EquipmentDto(
                    entity.getEquipmentId(),
                    entity.getCategory(),
                    entity.getBranch(),
                    entity.getBrand(),
                    entity.getModel(),
                    entity.getPurchaseYear(),
                    entity.getBaseDailyPrice(),
                    entity.getSecurityDeposit(),
                    entity.getStatus()
            ));
        }
        return dtos;
    }

}
