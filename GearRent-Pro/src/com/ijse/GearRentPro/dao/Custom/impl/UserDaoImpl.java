/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom.impl;

import com.ijse.GearRentPro.dao.CrudUtil;
import com.ijse.GearRentPro.dao.Custom.UserDao;
import com.ijse.GearRentPro.entity.UserEntity;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class UserDaoImpl implements UserDao {

    private static final String SELECT_QUERY = "SELECT * FROM users";

    @Override
    public UserEntity getByUsernameAndPassword(String username, String password) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE username=? AND password=?",
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
                t.getBranchId());
    }

    @Override
    public boolean update(UserEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE users SET username=?, password=?, role_id=?, branch_id=? WHERE TRIM(user_id)=?",
                t.getUsername(), t.getPassword(), t.getRoleId(),
                t.getBranchId(), t.getUserId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM users WHERE TRIM(user_id)=?", id);
    }

    @Override
    public UserEntity search(String id) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE TRIM(user_id)=?", id);
        if (resultSet.next()) {
            return mapRow(resultSet);
        }
        return null;
    }

    @Override
    public ArrayList<UserEntity> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(SELECT_QUERY);
        ArrayList<UserEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    private UserEntity mapRow(ResultSet resultSet) throws Exception {

        return new UserEntity(
                resultSet.getString("user_id").trim(),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("role_id").trim(),
                resultSet.getString("branch_id").trim()
        );
    }

}
