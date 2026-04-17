/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom;

import com.ijse.GearRentPro.dao.CrudDao;
import com.ijse.GearRentPro.entity.EquipmentEntity;
import java.util.ArrayList;

/**
 *
 * @author User
 */
// DAO interface for equipment data persistence operations.
public interface EquipmentDao extends CrudDao<EquipmentEntity, String> {

    // Retrieve all equipment belonging to a specific branch
    ArrayList<EquipmentEntity> getByBranch(String branchId) throws Exception;

    // Retrieve equipment filtered by status
    ArrayList<EquipmentEntity> getByStatus(String status) throws Exception;

    // Retrieve equipment filtered by category
    ArrayList<EquipmentEntity> getByCategory(String categoryId) throws Exception;

    // Fetch record for update operations with row locking
    EquipmentEntity searchForUpdate(String id) throws Exception;

}
