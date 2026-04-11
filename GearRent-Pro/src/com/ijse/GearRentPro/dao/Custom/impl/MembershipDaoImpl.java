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
    public MembershipEntity getByLevel(String level) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                "SELECT * FROM membership_levels WHERE level_name=?", level
        );
        if (resultSet.next()) {
            return new MembershipEntity(
                    resultSet.getString("membership_id"),
                    resultSet.getString("level_name"),
                    resultSet.getDouble("discount_percentage")
            );
        }
        return null;
    }

    @Override
    public boolean save(MembershipEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO membership_levels (membership_id, level_name, discount_percentage) VALUES (?, ?, ?)",
                t.getMembershipId(),
                t.getLevel(),
                t.getDiscountPer()
        );
    }

    @Override
    public boolean update(MembershipEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE membership_levels SET level_name=?, discount_percentage=? WHERE membership_id=?",
                t.getLevel(),
                t.getDiscountPer(),
                t.getMembershipId()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM membership_levels WHERE membership_id=?", id
        );
    }

    @Override
    public MembershipEntity search(String id) throws Exception {
        ResultSet rs = CrudUtil.executeQuery(
                "SELECT * FROM membership_levels WHERE membership_id=?", id
        );
        if (rs.next()) {
            return new MembershipEntity(
                    rs.getString("membership_id"),
                    rs.getString("level_name"),
                    rs.getDouble("discount_percentage")
            );
        }
        return null;
    }

    @Override
    public ArrayList<MembershipEntity> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM membership_levels");
        ArrayList<MembershipEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new MembershipEntity(
                    resultSet.getString("membership_id"),
                    resultSet.getString("level_name"),
                    resultSet.getDouble("discount_percentage")
            ));
        }
        return list;
    }

}
