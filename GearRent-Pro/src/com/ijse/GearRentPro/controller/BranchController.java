/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.controller;

import com.ijse.GearRentPro.dto.BranchDto;
import com.ijse.GearRentPro.service.ServiceFactory;
import com.ijse.GearRentPro.service.custom.BranchService;
import java.util.List;

/**
 *
 * @author User
 */
public class BranchController {

    private final BranchService branchService = (BranchService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.BRANCH);

    // Handles branch management actions from the UI.
    public boolean saveBranch(BranchDto dto) throws Exception {
        return branchService.saveBranch(dto);
    }

    public boolean updateBranch(BranchDto dto) throws Exception {
        return branchService.updateBranch(dto);
    }

    public boolean deleteBranch(String id) throws Exception {
        return branchService.deleteBranch(id);
    }

    public BranchDto findBranch(String id) throws Exception {
        return branchService.findBranch(id);
    }

    public List<BranchDto> findAllBranches() throws Exception {
        return branchService.findAllBranches();
    }
}
