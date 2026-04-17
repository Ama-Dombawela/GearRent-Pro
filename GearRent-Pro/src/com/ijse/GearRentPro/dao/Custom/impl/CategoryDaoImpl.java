/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom.impl;

import com.ijse.GearRentPro.dao.CrudUtil;
import com.ijse.GearRentPro.dao.Custom.CategoryDao;
import com.ijse.GearRentPro.entity.CategoryEntity;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class CategoryDaoImpl implements CategoryDao {

    @Override
    /**
     * Inserts a new category row into the database.
     *
     * @param t category entity to persist
     * @return true when the insert succeeds
     * @throws Exception when the SQL execution fails
     */
    public boolean save(CategoryEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO categories VALUES(?,?,?,?,?,?,?)",
                t.getCategoryId(), t.getName(), t.getDescription(),
                t.getPriceFactor(), t.getWeekendMultiplier(), t.getLateFeePerDay(), t.isIsActive());
    }

    @Override
    /**
     * Updates an existing category row in the database.
     *
     * @param t category entity with updated values
     * @return true when the update succeeds
     * @throws Exception when the SQL execution fails
     */
    public boolean update(CategoryEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE categories SET name=?, description=?, price_factor=?, weekend_multiplier=?, late_fee_per_day=?, is_active=? WHERE TRIM(category_id)=?",
                t.getName(), t.getDescription(), t.getPriceFactor(),
                t.getWeekendMultiplier(), t.getLateFeePerDay(),
                t.isIsActive(), t.getCategoryId());
    }

    @Override
    /**
     * Deletes a category row by ID.
     *
     * @param id category identifier
     * @return true when the delete succeeds
     * @throws Exception when the SQL execution fails
     */
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM categories WHERE TRIM(category_id)=?", id);
    }

    @Override
    /**
     * Loads one category row by ID.
     *
     * @param id category identifier
     * @return matching category entity, or null when not found
     * @throws Exception when the SQL execution fails
     */
    public CategoryEntity search(String id) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM categories WHERE TRIM(category_id)=?", id);
        if (resultSet.next()) {
            return mapRow(resultSet);
        }
        return null;

    }

    @Override
    /**
     * Loads all category rows.
     *
     * @return list of category entities
     * @throws Exception when the SQL execution fails
     */
    public ArrayList<CategoryEntity> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM categories");
        ArrayList<CategoryEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    private CategoryEntity mapRow(ResultSet resultSet) throws Exception {
        return new CategoryEntity(
                resultSet.getString("category_id").trim(),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getDouble("price_factor"),
                resultSet.getDouble("weekend_multiplier"),
                resultSet.getDouble("late_fee_per_day"),
                resultSet.getBoolean("is_active")
        );

    }

}
