/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom.impl;

import com.ijse.GearRentPro.dao.CrudUtil;
import com.ijse.GearRentPro.dao.Custom.DamageDao;
import com.ijse.GearRentPro.entity.DamageEntity;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class DamageDaoImpl implements DamageDao {

    @Override
    /**
     * Loads all damage records for a specific rental.
     *
     * @param rentalId rental identifier
     * @return list of matching damage entities
     * @throws Exception when the query fails
     */
    public ArrayList<DamageEntity> getByRentalId(String rentalId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                "SELECT * FROM damages WHERE TRIM(rental_id)=?", rentalId);
        ArrayList<DamageEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;

    }

    @Override
    /**
     * Inserts a new damage row into the database.
     *
     * @param t damage entity to persist
     * @return true when the insert succeeds
     * @throws Exception when the SQL execution fails
     */
    public boolean save(DamageEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO damages VALUES(?,?,?,?)",
                t.getDamageId(),
                t.getRentalId(),
                t.getDescription(),
                t.getDamageCharge()
        );
    }

    @Override
    /**
     * Updates an existing damage row in the database.
     *
     * @param t damage entity with updated values
     * @return true when the update succeeds
     * @throws Exception when the SQL execution fails
     */
    public boolean update(DamageEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE damages SET rental_id=?, description=?, damage_charge=? WHERE TRIM(damage_id)=?",
                t.getRentalId(),
                t.getDescription(),
                t.getDamageCharge(),
                t.getDamageId()
        );
    }

    @Override
    /**
     * Deletes a damage row by ID.
     *
     * @param id damage identifier
     * @return true when the delete succeeds
     * @throws Exception when the SQL execution fails
     */
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM damages WHERE TRIM(damage_id)=?", id);
    }

    @Override
    /**
     * Loads one damage row by ID.
     *
     * @param id damage identifier
     * @return matching damage entity, or null when not found
     * @throws Exception when the SQL execution fails
     */
    public DamageEntity search(String id) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                "SELECT * FROM damages WHERE TRIM(damage_id)=?", id);
        if (resultSet.next()) {
            return mapRow(resultSet);
        }
        return null;
    }

    @Override
    /**
     * Loads all damage rows.
     *
     * @return list of damage entities
     * @throws Exception when the SQL execution fails
     */
    public ArrayList<DamageEntity> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM damages");
        ArrayList<DamageEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    private DamageEntity mapRow(ResultSet rs) throws Exception {
        return new DamageEntity(
                rs.getString("damage_id").trim(),
                rs.getString("rental_id").trim(),
                rs.getString("description"),
                rs.getDouble("damage_charge")
        );
    }

}
