/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom;

import com.ijse.GearRentPro.dao.CrudDao;
import com.ijse.GearRentPro.entity.RentalEntity;
import java.util.ArrayList;

/**
 *
 * @author User
 */
// DAO interface for rental transaction data persistence operations.
public interface RentalDao extends CrudDao<RentalEntity, String> {

    // Retrieve all rentals for a specific customer
    ArrayList<RentalEntity> getByCustomer(String customerId) throws Exception;

    // Retrieve all rentals for a specific branch
    ArrayList<RentalEntity> getByBranch(String branchId) throws Exception;

    // Retrieve rentals filtered by status
    ArrayList<RentalEntity> getByStatus(String status) throws Exception;

    // Retrieve all overdue rental records
    ArrayList<RentalEntity> getOverdueRentals() throws Exception;

    // Calculate total active deposit amount for a customer
    double getTotalActiveDepositByCustomer(String CustomerId) throws Exception;

    // Calculate total active deposit amount for a customer with row locking
    double getTotalActiveDepositByCustomerForUpdate(String CustomerId) throws Exception;

    // Check for active rental overlap with row locking
    boolean hasActiveOverlapForUpdate(String equipmentId, String startDate, String endDate) throws Exception;

    // Fetch record for update operations with row locking
    RentalEntity searchForUpdate(String rentalId) throws Exception;

}
