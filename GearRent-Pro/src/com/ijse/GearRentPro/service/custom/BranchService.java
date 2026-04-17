/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.service.custom;

import com.ijse.GearRentPro.dto.BranchDto;
import com.ijse.GearRentPro.service.SuperService;
import java.util.List;

/**
 *
 * @author User
 */
public interface BranchService extends SuperService {

    // Save a new branch
    boolean saveBranch(BranchDto dto) throws Exception;

    // Update existing branch
    boolean updateBranch(BranchDto dto) throws Exception;

    // Delete branch by ID
    boolean deleteBranch(String id) throws Exception;

    // Find branch by ID
    BranchDto findBranch(String id) throws Exception;

    // Get all branches
    List<BranchDto> findAllBranches() throws Exception;

}
