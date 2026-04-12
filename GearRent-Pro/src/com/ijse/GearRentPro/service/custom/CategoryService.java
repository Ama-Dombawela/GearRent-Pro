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

    boolean saveCategory(CategoryDto dto) throws Exception;

    boolean updateCategory(CategoryDto dto) throws Exception;

    boolean deleteCategory(String id) throws Exception;

    CategoryDto findCategory(String id) throws Exception;

    List<CategoryDto> findAllCategories() throws Exception;
}
