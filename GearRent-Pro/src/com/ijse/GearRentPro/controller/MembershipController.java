/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.controller;

import com.ijse.GearRentPro.dto.MembershipDto;
import com.ijse.GearRentPro.service.ServiceFactory;
import com.ijse.GearRentPro.service.custom.MembershipService;
import java.util.List;

/**
 *
 * @author User
 */
public class MembershipController {

    private final MembershipService membershipService = (MembershipService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.MEMBERSHIP);

    // Handles membership management actions from the UI.
    public boolean saveMembership(MembershipDto dto) throws Exception {
        return membershipService.saveMembership(dto);
    }

    public boolean updateMembership(MembershipDto dto) throws Exception {
        return membershipService.updateMembership(dto);
    }

    public boolean deleteMembership(String id) throws Exception {
        return membershipService.deleteMembership(id);
    }

    public MembershipDto findMembership(String id) throws Exception {
        return membershipService.findMembership(id);
    }

    public List<MembershipDto> findAllMemberships() throws Exception {
        return membershipService.findAllMemberships();
    }

}
