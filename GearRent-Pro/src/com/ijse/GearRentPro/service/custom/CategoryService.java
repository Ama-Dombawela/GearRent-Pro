/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.service.custom;

import com.ijse.GearRentPro.dto.CategoryDto;
import com.ijse.GearRentPro.service.SuperService;
import java.util.List;

/**
 *
 * @author User
 */
public interface CategoryService extends SuperService {

    // Save a new category
    boolean saveCategory(CategoryDto dto) throws Exception;

    // Update existing category
    boolean updateCategory(CategoryDto dto) throws Exception;

    // Delete category by ID
    boolean deleteCategory(String id) throws Exception;

    // Find category by ID
    CategoryDto findCategory(String id) throws Exception;

    // Get all categories
    List<CategoryDto> findAllCategories() throws Exception;
}
