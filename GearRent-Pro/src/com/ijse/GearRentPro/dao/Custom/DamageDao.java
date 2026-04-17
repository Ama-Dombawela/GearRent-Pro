/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom;

import com.ijse.GearRentPro.dao.CrudDao;
import com.ijse.GearRentPro.entity.DamageEntity;
import java.util.ArrayList;

/**
 *
 * @author User
 */
// DAO interface for damage record data persistence operations.
public interface DamageDao extends CrudDao<DamageEntity, String> {

    // Retrieve all damage records for a specific rental
    ArrayList<DamageEntity> getByRentalId(String rentalId) throws Exception;

}
