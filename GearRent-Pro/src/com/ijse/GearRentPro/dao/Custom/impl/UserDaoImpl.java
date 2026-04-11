/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom.impl;

import com.ijse.GearRentPro.dao.CrudUtil;
import com.ijse.GearRentPro.dao.Custom.UserDao;
import com.ijse.GearRentPro.entity.BranchEntity;
import com.ijse.GearRentPro.entity.UserEntity;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class UserDaoImpl implements UserDao {

    private static final String SELECT_WITH_JOIN = "SELECT u.*, "
            + "b.branch_code, b.name AS branch_name, b.address AS branch_address, b.contact_no "
            + "FROM users u "
            + "LEFT JOIN branches b ON u.branch_id = b.branch_id";

    @Override
    public UserEntity getByUsernameAndPassword(String username, String password) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_WITH_JOIN + " WHERE u.username=? AND u.password=?",
                username, password);
        if (resultSet.next()) {
            return mapRow(resultSet);
        }
        return null;
    }

    @Override
    public boolean save(UserEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO users VALUES(?,?,?,?,?)",
                t.getUserId(), t.getUsername(), t.getPassword(),
                t.getRoleId(),
                t.getBranch() != null ? t.getBranch().getBranchId() : null);
    }

    @Override
    public boolean update(UserEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE users SET username=?, password=?, role_id=?, branch_id=? WHERE user_id=?",
                t.getUsername(), t.getPassword(), t.getRoleId(),
                t.getBranch() != null ? t.getBranch().getBranchId() : null,
                t.getUserId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM users WHERE user_id=?", id);
    }

    @Override
    public UserEntity search(String id) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_WITH_JOIN + " WHERE u.user_id=?", id);
        if (resultSet.next()) {
            return mapRow(resultSet);
        }
        return null;
    }

    @Override
    public ArrayList<UserEntity> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(SELECT_WITH_JOIN);
        ArrayList<UserEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    private UserEntity mapRow(ResultSet resultSet) throws Exception {
        BranchEntity branch = null;
        String branchId = resultSet.getString("branch_id");
        if (branchId != null) {
            branch = new BranchEntity(
                    branchId,
                    resultSet.getString("branch_code"),
                    resultSet.getString("branch_name"),
                    resultSet.getString("branch_address"),
                    resultSet.getString("contact_no")
            );
        }
        return new UserEntity(
                resultSet.getString("user_id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("role_id"),
                branch
        );
    }

}
