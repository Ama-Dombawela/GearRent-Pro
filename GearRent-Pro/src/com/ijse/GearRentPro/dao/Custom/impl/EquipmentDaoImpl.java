/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom.impl;

import com.ijse.GearRentPro.dao.CrudUtil;
import com.ijse.GearRentPro.dao.Custom.EquipmentDao;
import com.ijse.GearRentPro.entity.BranchEntity;
import com.ijse.GearRentPro.entity.CategoryEntity;
import com.ijse.GearRentPro.entity.EquipmentEntity;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class EquipmentDaoImpl implements EquipmentDao {

    private static final String SELECT_QUERY
            = "SELECT e.*, "
            + "c.name AS cat_name, c.description, c.price_factor, c.weekend_multiplier, c.late_fee_per_day, c.is_active, "
            + "b.branch_code, b.name AS branch_name, b.address AS branch_address, b.contact_no "
            + "FROM equipment e "
            + "JOIN categories c ON e.category_id = c.category_id "
            + "JOIN branches b ON e.branch_id = b.branch_id";

    @Override
    public ArrayList<EquipmentEntity> getByBranch(String branchId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE e.branch_id=? ", branchId);
        ArrayList<EquipmentEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    @Override
    public ArrayList<EquipmentEntity> getByStatus(String status) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE e.status=?", status);
        ArrayList<EquipmentEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    @Override
    public ArrayList<EquipmentEntity> getByCategory(String categoryId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE e.category_id=?", categoryId);
        ArrayList<EquipmentEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    @Override
    public boolean save(EquipmentEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO equipment VALUES(?,?,?,?,?,?,?,?,?)",
                t.getEquipmentId(),
                t.getCategory().getCategoryId(),
                t.getBranch().getBranchId(),
                t.getBrand(), t.getModel(),
                t.getPurchaseYear(), t.getBaseDailyPrice(),
                t.getSecurityDeposit(),
                t.getStatus().getDatabaseValue());
    }

    @Override
    public boolean update(EquipmentEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE equipment SET category_id=?, branch_id=?, brand=?, model=?, purchase_year=?, base_daily_price=?, security_deposit=?, status=? WHERE equipment_id=?",
                t.getCategory().getCategoryId(),
                t.getBranch().getBranchId(),
                t.getBrand(),
                t.getModel(),
                t.getPurchaseYear(),
                t.getBaseDailyPrice(),
                t.getSecurityDeposit(),
                t.getStatus().getDatabaseValue(),
                t.getEquipmentId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM equipment WHERE equipment_id=?", id);
    }

    @Override
    public EquipmentEntity search(String id) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE e.equipment_id=?", id);
        if (resultSet.next()) {
            return mapRow(resultSet);
        }
        return null;
    }

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
        CategoryEntity category = new CategoryEntity(
                resultSet.getString("category_id"),
                resultSet.getString("cat_name"),
                resultSet.getString("description"),
                resultSet.getDouble("price_factor"),
                resultSet.getDouble("weekend_multiplier"),
                resultSet.getDouble("late_fee_per_day"),
                resultSet.getBoolean("is_active")
        );
        BranchEntity branch = new BranchEntity(
                resultSet.getString("branch_id"),
                resultSet.getString("branch_code"),
                resultSet.getString("branch_name"),
                resultSet.getString("branch_address"),
                resultSet.getString("contact_no")
        );
        return new EquipmentEntity(
                resultSet.getString("equipment_id"),
                category, branch,
                resultSet.getString("brand"),
                resultSet.getString("model"),
                resultSet.getInt("purchase_year"),
                resultSet.getDouble("base_daily_price"),
                resultSet.getDouble("security_deposit"),
                EquipmentEntity.Status.fromDbValues(resultSet.getString("status"))
        );

    }
}
