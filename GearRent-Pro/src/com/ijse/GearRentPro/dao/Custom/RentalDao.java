/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom;

import com.ijse.GearRentPro.dao.CrudDao;
import com.ijse.GearRentPro.entity.EquipmentEntity;
import com.ijse.GearRentPro.entity.RentalEntity;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface RentalDao extends CrudDao<RentalEntity, String>{
    
    ArrayList<RentalEntity> getByCutomer(String customerId)throws Exception;
    ArrayList<RentalEntity> getByBranch(String branchId)throws Exception;
    ArrayList<RentalEntity> getByStatus(String status)throws Exception;
    ArrayList<RentalEntity> getOverdueRentals()throws Exception;
    double getTotalActiveDepositByCutomer(String cutomerId)throws Exception;
    
}
