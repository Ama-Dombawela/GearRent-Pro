/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom;

import com.ijse.GearRentPro.dao.CrudDao;
import com.ijse.GearRentPro.entity.MembershipEntity;

/**
 *
 * @author User
 */
// DAO interface for membership level data persistence operations.
public interface MembershipDao extends CrudDao<MembershipEntity, String> {

    // Retrieve membership level by its name
    MembershipEntity getByLevel(String level) throws Exception;

}
