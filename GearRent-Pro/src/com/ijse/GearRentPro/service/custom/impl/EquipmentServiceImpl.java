/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.service.custom.impl;

import com.ijse.GearRentPro.dao.Custom.BranchDao;
import com.ijse.GearRentPro.dao.Custom.CategoryDao;
import com.ijse.GearRentPro.dao.Custom.EquipmentDao;
import com.ijse.GearRentPro.dao.DaoFactory;
import com.ijse.GearRentPro.entity.BranchEntity;
import com.ijse.GearRentPro.entity.CategoryEntity;
import com.ijse.GearRentPro.dto.EquipmentDto;
import com.ijse.GearRentPro.entity.EquipmentEntity;
import com.ijse.GearRentPro.service.custom.EquipmentService;
import com.ijse.GearRentPro.util.AuthUtil;
import com.ijse.GearRentPro.util.Session;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class EquipmentServiceImpl implements EquipmentService {

    EquipmentDao equipmentDao = (EquipmentDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.EQUIPMENT);
    CategoryDao categoryDao = (CategoryDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.CATEGORY);
    BranchDao branchDao = (BranchDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.BRANCH);

    @Override
    public boolean saveEquipment(EquipmentDto dto) throws Exception {
        AuthUtil.requireBranchScopedAccess(dto.getBranchId());
        if (dto.getEquipmentId() == null || dto.getEquipmentId().isBlank()) {
            throw new Exception("Equipment ID is required.");
        }
        if (dto.getCategoryId() == null || dto.getCategoryId().isBlank()) {
            throw new Exception("Category ID is required.");
        }
        if (dto.getBranchId() == null || dto.getBranchId().isBlank()) {
            throw new Exception("Branch ID is required.");
        }
        BranchEntity branch = branchDao.search(dto.getBranchId().trim());
        if (branch == null) {
            throw new Exception("Branch ID not found. Please enter a correct ID.");
        }
        CategoryEntity category = categoryDao.search(dto.getCategoryId().trim());
        if (category == null) {
            throw new Exception("Category ID not found. Please enter a correct ID.");
        }
        if (dto.getBrand() == null || dto.getBrand().isBlank()) {
            throw new Exception("Brand is required.");
        }
        if (dto.getModel() == null || dto.getModel().isBlank()) {
            throw new Exception("Model is required.");
        }
        if (dto.getPurchaseYear() < 1900) {
            throw new Exception("Purchase year is invalid.");
        }
        if (dto.getBaseDailyPrice() <= 0) {
            throw new Exception("Base daily price must be greater than 0.");
        }
        if (dto.getSecurityDeposit() < 0) {
            throw new Exception("Security deposit cannot be negative.");
        }
        if (dto.getStatus() == null || dto.getStatus().isBlank()) {
            throw new Exception("Status is required.");
        }
        try {
            return equipmentDao.save(new EquipmentEntity(
                    dto.getEquipmentId(),
                    dto.getCategoryId(),
                    dto.getBranchId(),
                    dto.getBrand(),
                    dto.getModel(),
                    dto.getPurchaseYear(),
                    dto.getBaseDailyPrice(),
                    dto.getSecurityDeposit(),
                    EquipmentEntity.Status.valueOf(dto.getStatus().toUpperCase().replace(" ","_"))
            ));
        } catch (SQLException e) {
            if (isDuplicateEntry(e)) {
                throw new Exception("This equipment ID already exists. Please use a different equipment ID.");
            }
            throw e;
        }
    }

    @Override
    public boolean updateEquipment(EquipmentDto dto) throws Exception {
        AuthUtil.requireBranchScopedAccess(dto.getBranchId());
        if (dto.getEquipmentId() == null || dto.getEquipmentId().isBlank()) {
            throw new Exception("Equipment ID is required.");
        }
        if (dto.getCategoryId() == null || dto.getCategoryId().isBlank()) {
            throw new Exception("Category ID is required.");
        }
        if (dto.getBranchId() == null || dto.getBranchId().isBlank()) {
            throw new Exception("Branch ID is required.");
        }
        BranchEntity branch = branchDao.search(dto.getBranchId().trim());
        if (branch == null) {
            throw new Exception("Branch ID not found. Please enter a correct ID.");
        }
        CategoryEntity category = categoryDao.search(dto.getCategoryId().trim());
        if (category == null) {
            throw new Exception("Category ID not found. Please enter a correct ID.");
        }
        if (dto.getBrand() == null || dto.getBrand().isBlank()) {
            throw new Exception("Brand is required.");
        }
        if (dto.getModel() == null || dto.getModel().isBlank()) {
            throw new Exception("Model is required.");
        }
        if (dto.getPurchaseYear() < 1900) {
            throw new Exception("Purchase year is invalid.");
        }
        if (dto.getBaseDailyPrice() <= 0) {
            throw new Exception("Base daily price must be greater than 0.");
        }
        if (dto.getSecurityDeposit() < 0) {
            throw new Exception("Security deposit cannot be negative.");
        }
        if (dto.getStatus() == null || dto.getStatus().isBlank()) {
            throw new Exception("Status is required.");
        }
        try {
            return equipmentDao.update(new EquipmentEntity(
                    dto.getEquipmentId(),
                    dto.getCategoryId(),
                    dto.getBranchId(),
                    dto.getBrand(),
                    dto.getModel(),
                    dto.getPurchaseYear(),
                    dto.getBaseDailyPrice(),
                    dto.getSecurityDeposit(),
                    EquipmentEntity.Status.valueOf(dto.getStatus().toUpperCase().replace(" ","_"))
            ));
        } catch (SQLException e) {
            if (isDuplicateEntry(e)) {
                throw new Exception("This equipment ID already exists. Please use a different equipment ID.");
            }
            throw e;
        }
    }

    @Override
    public boolean deleteEquipment(String id) throws Exception {
        EquipmentEntity entity = equipmentDao.search(id);
        if (entity != null) {
            AuthUtil.requireBranchScopedAccess(entity.getBranchId());
        }
        return equipmentDao.delete(id);
    }

    @Override
    public EquipmentDto findEquipment(String id) throws Exception {
        EquipmentEntity entity = equipmentDao.search(id);
        if (entity == null) {
            return null;
        }
        AuthUtil.requireBranchScopedAccess(entity.getBranchId());
        return new EquipmentDto(
                entity.getEquipmentId(),
                entity.getCategoryId(),
                entity.getBranchId(),
                entity.getBrand(),
                entity.getModel(),
                entity.getPurchaseYear(),
                entity.getBaseDailyPrice(),
                entity.getSecurityDeposit(),
                entity.getStatus().name()
        );
    }

    @Override
    public List<EquipmentDto> findAllEquipments() throws Exception {
        ArrayList<EquipmentEntity> entities = equipmentDao.getAll();
        List<EquipmentDto> dtos = new ArrayList<>();
        String branchFilter = Session.getCurrentBranchId();
        for (EquipmentEntity entity : entities) {
            if (branchFilter != null && !branchFilter.equals(entity.getBranchId())) {
                continue;
            }
            dtos.add(new EquipmentDto(
                    entity.getEquipmentId(),
                    entity.getCategoryId(),
                    entity.getBranchId(),
                    entity.getBrand(),
                    entity.getModel(),
                    entity.getPurchaseYear(),
                    entity.getBaseDailyPrice(),
                    entity.getSecurityDeposit(),
                    entity.getStatus().name()
            ));
        }
        return dtos;
    }

    private boolean isDuplicateEntry(SQLException exception) {
        String message = exception.getMessage();
        return exception.getErrorCode() == 1062
                || (exception.getSQLState() != null && exception.getSQLState().startsWith("23"))
                || (message != null && message.toLowerCase().contains("duplicate entry"));
    }

}
