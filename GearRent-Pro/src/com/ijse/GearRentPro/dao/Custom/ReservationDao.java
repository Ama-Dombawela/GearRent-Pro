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
// DAO interface for reservation data persistence operations.
public interface ReservationDao extends CrudDao<ReservationEntity, String> {

    // Retrieve all reservations for a specific customer
    ArrayList<ReservationEntity> getByCustomer(String customerId) throws Exception;

    // Retrieve all reservations for a specific branch
    ArrayList<ReservationEntity> getByBranch(String branchId) throws Exception;

    // Check for reservation date overlap
    boolean hasOverlap(String equipmentId, String startDate, String endDate) throws Exception;

    // Check for reservation date overlap with row locking
    boolean hasOverlapForUpdate(String equipmentId, String startDate, String endDate) throws Exception;

}
