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

    @Override
    public ArrayList<EquipmentEntity> getByBranch(String branchId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE branch_id=? ", branchId);
        ArrayList<EquipmentEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    @Override
    public ArrayList<EquipmentEntity> getByStatus(String status) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE status=?", status);
        ArrayList<EquipmentEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    @Override
    public ArrayList<EquipmentEntity> getByCategory(String categoryId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE category_id=?", categoryId);
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
                t.getCategoryId(),
                t.getBranchId(),
                t.getBrand(), t.getModel(),
                t.getPurchaseYear(), t.getBaseDailyPrice(),
                t.getSecurityDeposit(),
                t.getStatus().getDatabaseValue());
    }

    @Override
    public boolean update(EquipmentEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE equipment SET category_id=?, branch_id=?, brand=?, model=?, purchase_year=?, base_daily_price=?, security_deposit=?, status=? WHERE equipment_id=?",
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

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM equipment WHERE equipment_id=?", id);
    }

    @Override
    public EquipmentEntity search(String id) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE equipment_id=?", id);
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

        return new EquipmentEntity(
                resultSet.getString("equipment_id"),
                resultSet.getString("category_id"),
                resultSet.getString("branch_id"),
                resultSet.getString("brand"),
                resultSet.getString("model"),
                resultSet.getInt("purchase_year"),
                resultSet.getDouble("base_daily_price"),
                resultSet.getDouble("security_deposit"),
                EquipmentEntity.Status.fromDbValues(resultSet.getString("status"))
        );

    }
}
