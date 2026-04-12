/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.controller;

import com.ijse.GearRentPro.dto.CategoryDto;
import com.ijse.GearRentPro.service.ServiceFactory;
import com.ijse.GearRentPro.service.custom.CategoryService;
import java.util.List;

/**
 *
 * @author User
 */
public class CategoryController {

    private final CategoryService categoryService = (CategoryService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.CATEGORY);

    public boolean saveCategory(CategoryDto dto) throws Exception {
        return categoryService.saveCategory(dto);
    }

    public boolean updateCategory(CategoryDto dto) throws Exception {
        return categoryService.updateCategory(dto);
    }

    public boolean deleteCategory(String id) throws Exception {
        return categoryService.deleteCategory(id);
    }

    public CategoryDto findCategory(String id) throws Exception {
        return categoryService.findCategory(id);
    }

    public List<CategoryDto> findAllCategories() throws Exception {
        return categoryService.findAllCategories();
    }

}
