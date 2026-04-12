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

    boolean saveMembership(MembershipDto dto) throws Exception;

    boolean updateMembership(MembershipDto dto) throws Exception;

    boolean deleteMembership(String id) throws Exception;

    MembershipDto findMembership(String id) throws Exception;

    List<MembershipDto> findAllMemberships() throws Exception;

}
