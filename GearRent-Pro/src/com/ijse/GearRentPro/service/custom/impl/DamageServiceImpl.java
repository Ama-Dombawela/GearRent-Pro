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
import com.ijse.GearRentPro.util.AuthUtil;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class DamageServiceImpl implements DamageService {

    DamageDao damageDao = (DamageDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.DAMAGE);

    @Override
    public boolean saveDamage(DamageDto dto) throws Exception {
        AuthUtil.requireUser();
        validateDamage(dto);
        try {
            return damageDao.save(new DamageEntity(
                    dto.getDamageId(),
                    dto.getRentalId(),
                    dto.getDescription(),
                    dto.getDamageCharge()
            ));
        } catch (SQLException e) {
            if (isDuplicateEntry(e)) {
                throw new Exception("This damage ID already exists. Please use a different damage ID.");
            }
            throw e;
        }
    }

    @Override
    public boolean updateDamage(DamageDto dto) throws Exception {
        AuthUtil.requireUser();
        validateDamage(dto);
        try {
            return damageDao.update(new DamageEntity(
                    dto.getDamageId(),
                    dto.getRentalId(),
                    dto.getDescription(),
                    dto.getDamageCharge()
            ));
        } catch (SQLException e) {
            if (isDuplicateEntry(e)) {
                throw new Exception("This damage ID already exists. Please use a different damage ID.");
            }
            throw e;
        }
    }

    @Override
    public boolean deleteDamage(String id) throws Exception {
        AuthUtil.requireUser();
        return damageDao.delete(id);
    }

    @Override
    public DamageDto findDamage(String id) throws Exception {
        AuthUtil.requireUser();
        DamageEntity entity = damageDao.search(id);
        if (entity == null) {
            return null;
        }
        return new DamageDto(
                entity.getDamageId(),
                entity.getRentalId(),
                entity.getDescription(),
                entity.getDamageCharge()
        );
    }

    @Override
    public List<DamageDto> findAllDamages() throws Exception {
        AuthUtil.requireUser();
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
        AuthUtil.requireUser();
        if (rentalId == null || rentalId.isBlank()) {
            throw new Exception("Rental ID is required.");
        }
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

    private void validateDamage(DamageDto dto) throws Exception {
        if (dto == null) {
            throw new Exception("Damage data is required.");
        }
        if (dto.getDamageId() == null || dto.getDamageId().isBlank()) {
            throw new Exception("Damage ID is required.");
        }
        if (dto.getRentalId() == null || dto.getRentalId().isBlank()) {
            throw new Exception("Rental ID is required.");
        }
        if (dto.getDescription() == null || dto.getDescription().isBlank()) {
            throw new Exception("Damage description is required.");
        }
        if (dto.getDamageCharge() < 0) {
            throw new Exception("Damage charge cannot be negative.");
        }
    }

    private boolean isDuplicateEntry(SQLException exception) {
        String message = exception.getMessage();
        return exception.getErrorCode() == 1062
                || (exception.getSQLState() != null && exception.getSQLState().startsWith("23"))
                || (message != null && message.toLowerCase().contains("duplicate entry"));
    }

}
