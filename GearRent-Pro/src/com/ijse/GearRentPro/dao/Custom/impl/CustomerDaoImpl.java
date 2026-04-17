/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom.impl;

import com.ijse.GearRentPro.dao.CrudUtil;
import com.ijse.GearRentPro.dao.Custom.CustomerDao;
import com.ijse.GearRentPro.entity.CustomerEntity;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class CustomerDaoImpl implements CustomerDao {

    @Override
    /**
     * Inserts a new customer row into the database.
     *
     * @param t customer entity to persist
     * @return true when the insert succeeds
     * @throws Exception when the SQL execution fails
     */
    public boolean save(CustomerEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO customers VALUES(?,?,?,?,?,?,?)",
                t.getCustomerId(), t.getName(), t.getNicOrPassport(),
                t.getContactNo(), t.getEmail(), t.getAddress(),
                t.getMembershipId());
    }

    @Override
    /**
     * Updates an existing customer row in the database.
     *
     * @param t customer entity with updated values
     * @return true when the update succeeds
     * @throws Exception when the SQL execution fails
     */
    public boolean update(CustomerEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE customers SET name=?, nic_passport=?, contact_no=?, email=?, address=?, membership_id=? WHERE TRIM(customer_id)=?",
                t.getName(), t.getNicOrPassport(), t.getContactNo(),
                t.getEmail(), t.getAddress(), t.getMembershipId(),
                t.getCustomerId());
    }

    @Override
    /**
     * Deletes a customer row by ID.
     *
     * @param id customer identifier
     * @return true when the delete succeeds
     * @throws Exception when the SQL execution fails
     */
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM customers WHERE customer_id=?", id);
    }

    @Override
    /**
     * Loads one customer row by ID.
     *
     * @param id customer identifier
     * @return matching customer entity, or null when not found
     * @throws Exception when the SQL execution fails
     */
    public CustomerEntity search(String id) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                "SELECT * FROM customers WHERE customer_id=?", id);
        if (resultSet.next()) {
            return mapRow(resultSet);
        }
        return null;
    }

    @Override
    /**
     * Loads all customer rows.
     *
     * @return list of customer entities
     * @throws Exception when the SQL execution fails
     */
    public ArrayList<CustomerEntity> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM customers");
        ArrayList<CustomerEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    private CustomerEntity mapRow(ResultSet resultSet) throws Exception {
        return new CustomerEntity(
                resultSet.getString("customer_id").trim(),
                resultSet.getString("name"),
                resultSet.getString("nic_passport"),
                resultSet.getString("contact_no"),
                resultSet.getString("email"),
                resultSet.getString("address"),
                resultSet.getString("membership_id")
        );

    }
}
