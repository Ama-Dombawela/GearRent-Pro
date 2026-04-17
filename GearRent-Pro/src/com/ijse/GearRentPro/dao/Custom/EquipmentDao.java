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
public interface EquipmentDao extends CrudDao<EquipmentEntity, String>{
    
    ArrayList<EquipmentEntity> getByBranch(String branchId)throws Exception;
    ArrayList<EquipmentEntity> getByStatus(String status)throws Exception;
    ArrayList<EquipmentEntity> getByCategory(String categoryId)throws Exception;
    EquipmentEntity searchForUpdate(String id) throws Exception;
    
}
