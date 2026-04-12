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

    boolean saveBranch(BranchDto dto) throws Exception;

    boolean updateBranch(BranchDto dto) throws Exception;

    boolean deleteBranch(String id) throws Exception;

    BranchDto findBranch(String id) throws Exception;

    List<BranchDto> findAllBranches() throws Exception;

}
