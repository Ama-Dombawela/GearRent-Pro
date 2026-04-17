/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom.impl;

import com.ijse.GearRentPro.dao.CrudUtil;
import com.ijse.GearRentPro.dao.Custom.EquipmentDao;
import com.ijse.GearRentPro.entity.EquipmentEntity;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class EquipmentDaoImpl implements EquipmentDao {

    private static final String SELECT_QUERY = "SELECT * FROM equipment";

    /**
     * Loads equipment for a specific branch.
     *
     * @param branchId branch identifier
     * @return matching equipment entities
     * @throws Exception when the query fails
     */
    @Override
    public ArrayList<EquipmentEntity> getByBranch(String branchId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE TRIM(branch_id)=? ", branchId);
        ArrayList<EquipmentEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    /**
     * Loads equipment with a specific status.
     *
     * @param status equipment status value
     * @return matching equipment entities
     * @throws Exception when the query fails
     */
    @Override
    public ArrayList<EquipmentEntity> getByStatus(String status) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE TRIM(status)=?", status);
        ArrayList<EquipmentEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    /**
     * Loads equipment for a specific category.
     *
     * @param categoryId category identifier
     * @return matching equipment entities
     * @throws Exception when the query fails
     */
    @Override
    public ArrayList<EquipmentEntity> getByCategory(String categoryId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE TRIM(category_id)=?", categoryId);
        ArrayList<EquipmentEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    /**
     * Saves an equipment row using the entity values.
     *
     * @param t equipment entity to persist
     * @return true when the insert succeeds
     * @throws Exception when the SQL execution fails
     */
    @Override
    public boolean save(EquipmentEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO equipment VALUES(?,?,?,?,?,?,?,?,?)",
                t.getEquipmentId(),
                t.getCategoryId(),
                t.getBranchId(),
                t.getBrand(), t.getModel(),
                t.getPurchaseYear(), t.getBaseDailyPrice(),
                t.getSecurityDeposit(),
                t.getStatus().getDatabaseValue());
    }

    /**
     * Updates an equipment row using the entity values.
     *
     * @param t equipment entity with updated values
     * @return true when the update succeeds
     * @throws Exception when the SQL execution fails
     */
    @Override
    public boolean update(EquipmentEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE equipment SET category_id=?, branch_id=?, brand=?, model=?, purchase_year=?, base_daily_price=?, security_deposit=?, status=? WHERE TRIM(equipment_id)=?",
                t.getCategoryId(),
                t.getBranchId(),
                t.getBrand(),
                t.getModel(),
                t.getPurchaseYear(),
                t.getBaseDailyPrice(),
                t.getSecurityDeposit(),
                t.getStatus().getDatabaseValue(),
                t.getEquipmentId());
    }

    /**
     * Deletes an equipment row by ID.
     *
     * @param id equipment identifier
     * @return true when the delete succeeds
     * @throws Exception when the SQL execution fails
     */
    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM equipment WHERE TRIM(equipment_id)=?", id);
    }

    /**
     * Loads one equipment row by ID.
     *
     * @param id equipment identifier
     * @return matching equipment entity, or null when not found
     * @throws Exception when the SQL execution fails
     */
    @Override
    public EquipmentEntity search(String id) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE TRIM(equipment_id)=?", id);
        if (resultSet.next()) {
            return mapRow(resultSet);
        }
        return null;
    }

    /**
     * Loads one equipment row by ID while locking it for update.
     *
     * @param id equipment identifier
     * @return matching equipment entity, or null when not found
     * @throws Exception when the SQL execution fails
     */
    @Override
    public EquipmentEntity searchForUpdate(String id) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE TRIM(equipment_id)=? FOR UPDATE", id);
        if (resultSet.next()) {
            return mapRow(resultSet);
        }
        return null;
    }

    /**
     * Loads all equipment rows.
     *
     * @return list of equipment entities
     * @throws Exception when the SQL execution fails
     */
    @Override
    public ArrayList<EquipmentEntity> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(SELECT_QUERY);
        ArrayList<EquipmentEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    private EquipmentEntity mapRow(ResultSet resultSet) throws Exception {

        return new EquipmentEntity(
                resultSet.getString("equipment_id").trim(),
                resultSet.getString("category_id").trim(),
                resultSet.getString("branch_id").trim(),
                resultSet.getString("brand").trim(),
                resultSet.getString("model").trim(),
                resultSet.getInt("purchase_year"),
                resultSet.getDouble("base_daily_price"),
                resultSet.getDouble("security_deposit"),
                EquipmentEntity.Status.fromDbValues(resultSet.getString("status").trim())
        );

    }
}
