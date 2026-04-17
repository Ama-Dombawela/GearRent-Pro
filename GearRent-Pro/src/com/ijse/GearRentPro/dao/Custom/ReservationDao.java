/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom;

import com.ijse.GearRentPro.dao.CrudDao;
import com.ijse.GearRentPro.entity.ReservationEntity;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface ReservationDao extends CrudDao<ReservationEntity, String> {
    
    ArrayList<ReservationEntity> getByCustomer(String customerId)throws Exception;
    ArrayList<ReservationEntity> getByBranch(String branchId)throws Exception;
    boolean hasOverlap(String equipmentId, String startDate, String endDate)throws Exception;
    boolean hasOverlapForUpdate(String equipmentId, String startDate, String endDate)throws Exception;
    
}
