/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom.impl;

import com.ijse.GearRentPro.dao.CrudUtil;
import com.ijse.GearRentPro.dao.Custom.MembershipDao;
import com.ijse.GearRentPro.entity.MembershipEntity;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class MembershipDaoImpl implements MembershipDao {

    @Override
    /**
     * Loads a membership by its level name.
     *
     * @param level membership level name
     * @return matching membership entity, or null when not found
     * @throws Exception when the query fails
     */
    public MembershipEntity getByLevel(String level) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                "SELECT * FROM membership_levels WHERE level_name=?", level
        );
        if (resultSet.next()) {
            return new MembershipEntity(
                    resultSet.getString("membership_id").trim(),
                    resultSet.getString("level_name"),
                    resultSet.getDouble("discount_percentage")
            );
        }
        return null;
    }

    @Override
    /**
     * Inserts a new membership row into the database.
     *
     * @param t membership entity to persist
     * @return true when the insert succeeds
     * @throws Exception when the SQL execution fails
     */
    public boolean save(MembershipEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO membership_levels (membership_id, level_name, discount_percentage) VALUES (?, ?, ?)",
                t.getMembershipId(),
                t.getLevel(),
                t.getDiscountPer()
        );
    }

    @Override
    /**
     * Updates an existing membership row in the database.
     *
     * @param t membership entity with updated values
     * @return true when the update succeeds
     * @throws Exception when the SQL execution fails
     */
    public boolean update(MembershipEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE membership_levels SET level_name=?, discount_percentage=? WHERE TRIM(membership_id)=?",
                t.getLevel(),
                t.getDiscountPer(),
                t.getMembershipId()
        );
    }

    @Override
    /**
     * Deletes a membership row by ID.
     *
     * @param id membership identifier
     * @return true when the delete succeeds
     * @throws Exception when the SQL execution fails
     */
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM membership_levels WHERE TRIM(membership_id)=?", id
        );
    }

    @Override
    /**
     * Loads one membership row by ID.
     *
     * @param id membership identifier
     * @return matching membership entity, or null when not found
     * @throws Exception when the SQL execution fails
     */
    public MembershipEntity search(String id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery(
                "SELECT * FROM membership_levels WHERE TRIM(membership_id)=?", id
        );
        if (rs.next()) {
            return new MembershipEntity(
                    rs.getString("membership_id").trim(),
                    rs.getString("level_name"),
                    rs.getDouble("discount_percentage")
            );
        }
        return null;
    }

    @Override
    /**
     * Loads all membership rows.
     *
     * @return list of membership entities
     * @throws Exception when the SQL execution fails
     */
    public ArrayList<MembershipEntity> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM membership_levels");
        ArrayList<MembershipEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new MembershipEntity(
                    resultSet.getString("membership_id").trim(),
                    resultSet.getString("level_name"),
                    resultSet.getDouble("discount_percentage")
            ));
        }
        return list;
    }

}
