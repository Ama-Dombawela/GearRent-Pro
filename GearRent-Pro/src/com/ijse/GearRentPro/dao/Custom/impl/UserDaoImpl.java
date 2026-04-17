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
    /**
     * Loads a user by username and password.
     *
     * @param username login username
     * @param password login password
     * @return matching user entity, or null when credentials are invalid
     * @throws Exception when the query fails
     */
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
    /**
     * Inserts a new user row into the database.
     *
     * @param t user entity to persist
     * @return true when the insert succeeds
     * @throws Exception when the SQL execution fails
     */
    public boolean save(UserEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO users VALUES(?,?,?,?,?)",
                t.getUserId(), t.getUsername(), t.getPassword(),
                t.getRoleId(),
                t.getBranchId());
    }

    @Override
    /**
     * Updates an existing user row in the database.
     *
     * @param t user entity with updated values
     * @return true when the update succeeds
     * @throws Exception when the SQL execution fails
     */
    public boolean update(UserEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE users SET username=?, password=?, role_id=?, branch_id=? WHERE TRIM(user_id)=?",
                t.getUsername(), t.getPassword(), t.getRoleId(),
                t.getBranchId(), t.getUserId());
    }

    @Override
    /**
     * Deletes a user row by ID.
     *
     * @param id user identifier
     * @return true when the delete succeeds
     * @throws Exception when the SQL execution fails
     */
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM users WHERE TRIM(user_id)=?", id);
    }

    @Override
    /**
     * Loads one user row by ID.
     *
     * @param id user identifier
     * @return matching user entity, or null when not found
     * @throws Exception when the SQL execution fails
     */
    public UserEntity search(String id) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE TRIM(user_id)=?", id);
        if (resultSet.next()) {
            return mapRow(resultSet);
        }
        return null;
    }

    @Override
    /**
     * Loads all user rows.
     *
     * @return list of user entities
     * @throws Exception when the SQL execution fails
     */
    public ArrayList<UserEntity> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(SELECT_QUERY);
        ArrayList<UserEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    private UserEntity mapRow(ResultSet resultSet) throws Exception {

        String branchId = resultSet.getString("branch_id");

        return new UserEntity(
                resultSet.getString("user_id").trim(),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("role_id").trim(),
                branchId == null ? null : branchId.trim()
        );
    }

}
