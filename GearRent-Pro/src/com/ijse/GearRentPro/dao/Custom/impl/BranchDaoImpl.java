/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom.impl;

import com.ijse.GearRentPro.dao.CrudUtil;
import com.ijse.GearRentPro.dao.Custom.BranchDao;
import com.ijse.GearRentPro.entity.BranchEntity;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class BranchDaoImpl implements BranchDao {

    @Override
    public boolean save(BranchEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO branches VALUES(?,?,?,?,?)",
                t.getBranchId(), t.getBranchCode(), t.getName(), t.getAddress(), t.getContactNumber());
    }

    @Override
    public boolean update(BranchEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE branches SET branch_code=?, name=?, address=?, contact_no=? WHERE TRIM(branch_id)=?",
                t.getBranchCode(),t.getName(), t.getAddress(), t.getContactNumber(), t.getBranchId()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM branches WHERE TRIM(branch_id)=?", id);
    }

    @Override
    public BranchEntity search(String id) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                "SELECT * FROM branches WHERE TRIM(branch_id)=?", id);
        if (resultSet.next()) {
            return mapRow(resultSet);
        }
        return null;
    }

    @Override
    public ArrayList<BranchEntity> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM branches");
        ArrayList<BranchEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    private BranchEntity mapRow(ResultSet resultSet) throws Exception {
        return new BranchEntity(
                resultSet.getString("branch_id").trim(),
                resultSet.getString("branch_code").trim(),
                resultSet.getString("name").trim(),
                resultSet.getString("address").trim(),
                resultSet.getString("contact_no").trim()
        );

    }
}
