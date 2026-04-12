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
        return categoryDao.save(new CategoryEntity(
                dto.getCategoryId(),
                dto.getName(),
                dto.getDescription(),
                dto.getPriceFactor(),
                dto.getWeekendMutiplier(),
                dto.getLateFeePerDay(),
                dto.isIsActive()
        ));
    }

    @Override
    public boolean updateCategory(CategoryDto dto) throws Exception {
        return categoryDao.update(new CategoryEntity(
                dto.getCategoryId(),
                dto.getName(),
                dto.getDescription(),
                dto.getPriceFactor(),
                dto.getWeekendMutiplier(),
                dto.getLateFeePerDay(),
                dto.isIsActive()
        ));
    }

    @Override
    public boolean deleteCategory(String id) throws Exception {
        return categoryDao.delete(id);
    }

    @Override
    public CategoryDto findCategory(String id) throws Exception {
        CategoryEntity entity = categoryDao.search(id);
        return new CategoryDto(
                entity.getCategoryId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPriceFactor(),
                entity.getWeekendMutiplier(),
                entity.getLateFeePerDay(),
                entity.isIsActive()
        );
    }

    @Override
    public List<CategoryDto> findAllCategories() throws Exception {
        ArrayList<CategoryEntity> entities = categoryDao.getAll();
        List<CategoryDto> dtos = new ArrayList<>();
        for (CategoryEntity entity : entities) {
            dtos.add(new CategoryDto(
                    entity.getCategoryId(),
                    entity.getName(),
                    entity.getDescription(),
                    entity.getPriceFactor(),
                    entity.getWeekendMutiplier(),
                    entity.getLateFeePerDay(),
                    entity.isIsActive()
            ));
        }
        return dtos;
    }
}
