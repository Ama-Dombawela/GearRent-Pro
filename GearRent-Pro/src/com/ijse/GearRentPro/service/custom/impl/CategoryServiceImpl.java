/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.service.custom.impl;

import com.ijse.GearRentPro.dao.Custom.CategoryDao;
import com.ijse.GearRentPro.dao.DaoFactory;
import com.ijse.GearRentPro.dto.CategoryDto;
import com.ijse.GearRentPro.entity.CategoryEntity;
import com.ijse.GearRentPro.service.custom.CategoryService;
import com.ijse.GearRentPro.util.AuthUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class CategoryServiceImpl implements CategoryService {

    CategoryDao categoryDao = (CategoryDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.CATEGORY);

    @Override
    public boolean saveCategory(CategoryDto dto) throws Exception {
        AuthUtil.requireAdminOrManager();
        if (dto.getCategoryId() == null || dto.getCategoryId().isBlank()) {
            throw new Exception("Category ID is required.");
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new Exception("Category name is required.");
        }
        if (dto.getPriceFactor() <= 0) {
            throw new Exception("Price factor must be greater than 0.");
        }
        if (dto.getWeekendMultiplier() < 1) {
            throw new Exception("Weekend multiplier must be at least 1.0.");
        }
        if (dto.getLateFeePerDay() < 0) {
            throw new Exception("Late fee cannot be negative.");
        }

        try {
            return categoryDao.save(new CategoryEntity(
                    dto.getCategoryId(),
                    dto.getName(),
                    dto.getDescription(),
                    dto.getPriceFactor(),
                    dto.getWeekendMultiplier(),
                    dto.getLateFeePerDay(),
                    dto.isIsActive()
            ));
        } catch (SQLException e) {
            if (isDuplicateEntry(e)) {
                throw new Exception("This category ID already exists. Please use a different category ID.");
            }
            throw e;
        }
    }

    @Override
    public boolean updateCategory(CategoryDto dto) throws Exception {
        AuthUtil.requireAdminOrManager();
        try {
            return categoryDao.update(new CategoryEntity(
                    dto.getCategoryId(),
                    dto.getName(),
                    dto.getDescription(),
                    dto.getPriceFactor(),
                    dto.getWeekendMultiplier(),
                    dto.getLateFeePerDay(),
                    dto.isIsActive()
            ));
        } catch (SQLException e) {
            if (isDuplicateEntry(e)) {
                throw new Exception("This category ID already exists. Please use a different category ID.");
            }
            throw e;
        }
    }

    @Override
    public boolean deleteCategory(String id) throws Exception {
        AuthUtil.requireAdminOrManager();
        return categoryDao.delete(id);
    }

    @Override
    public CategoryDto findCategory(String id) throws Exception {
        AuthUtil.requireUser();
        CategoryEntity entity = categoryDao.search(id);
        if (entity == null) {
            return null;
        }
        return new CategoryDto(
                entity.getCategoryId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPriceFactor(),
                entity.getWeekendMultiplier(),
                entity.getLateFeePerDay(),
                entity.isIsActive()
        );
    }

    @Override
    public List<CategoryDto> findAllCategories() throws Exception {
        AuthUtil.requireUser();
        ArrayList<CategoryEntity> entities = categoryDao.getAll();
        List<CategoryDto> dtos = new ArrayList<>();
        for (CategoryEntity entity : entities) {
            dtos.add(new CategoryDto(
                    entity.getCategoryId(),
                    entity.getName(),
                    entity.getDescription(),
                    entity.getPriceFactor(),
                    entity.getWeekendMultiplier(),
                    entity.getLateFeePerDay(),
                    entity.isIsActive()
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
