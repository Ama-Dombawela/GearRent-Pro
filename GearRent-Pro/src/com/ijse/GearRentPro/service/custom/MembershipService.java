/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.service.custom;

import com.ijse.GearRentPro.dto.MembershipDto;
import com.ijse.GearRentPro.service.SuperService;
import java.util.List;

/**
 *
 * @author User
 */
public interface MembershipService extends SuperService {

    // Save a new membership level
    boolean saveMembership(MembershipDto dto) throws Exception;

    // Update existing membership level
    boolean updateMembership(MembershipDto dto) throws Exception;

    // Delete membership level by ID
    boolean deleteMembership(String id) throws Exception;

    // Find membership level by ID
    MembershipDto findMembership(String id) throws Exception;

    // Get all membership levels
    List<MembershipDto> findAllMemberships() throws Exception;

}
