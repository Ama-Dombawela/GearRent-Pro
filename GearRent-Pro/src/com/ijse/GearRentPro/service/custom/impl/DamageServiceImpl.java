/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.service.custom.impl;

import com.ijse.GearRentPro.dao.Custom.DamageDao;
import com.ijse.GearRentPro.dao.DaoFactory;
import com.ijse.GearRentPro.dto.DamageDto;
import com.ijse.GearRentPro.entity.DamageEntity;
import java.util.ArrayList;
import java.util.List;
import com.ijse.GearRentPro.service.custom.DamageService;

/**
 *
 * @author User
 */
public class DamageServiceImpl implements DamageService {

    DamageDao damageDao = (DamageDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.DAMAGE);

    @Override
    public boolean saveDamage(DamageDto dto) throws Exception {
        return damageDao.save(new DamageEntity(
                dto.getDamageId(),
                dto.getRentalId(),
                dto.getDescription(),
                dto.getDamageCharge()
        ));
    }

    @Override
    public boolean updateDamage(DamageDto dto) throws Exception {
        return damageDao.update(new DamageEntity(
                dto.getDamageId(),
                dto.getRentalId(),
                dto.getDescription(),
                dto.getDamageCharge()
        ));
    }

    @Override
    public boolean deleteDamage(String id) throws Exception {
        return damageDao.delete(id);
    }

    @Override
    public DamageDto findDamage(String id) throws Exception {
        DamageEntity entity = damageDao.search(id);
        return new DamageDto(
                entity.getDamageId(),
                entity.getRentalId(),
                entity.getDescription(),
                entity.getDamageCharge()
        );
    }

    @Override
    public List<DamageDto> findAllDamages() throws Exception {
        ArrayList<DamageEntity> entities = damageDao.getAll();
        List<DamageDto> dtos = new ArrayList<>();
        for (DamageEntity entity : entities) {
            dtos.add(new DamageDto(
                    entity.getDamageId(),
                    entity.getRentalId(),
                    entity.getDescription(),
                    entity.getDamageCharge()
            ));
        }
        return dtos;
    }

    @Override
    public List<DamageDto> findDamagesByRentalId(String rentalId) throws Exception {
        ArrayList<DamageEntity> entities = damageDao.getByRentalId(rentalId);
        List<DamageDto> dtos = new ArrayList<>();
        for (DamageEntity entity : entities) {
            dtos.add(new DamageDto(
                    entity.getDamageId(),
                    entity.getRentalId(),
                    entity.getDescription(),
                    entity.getDamageCharge()
            ));
        }
        return dtos;

    }

}
