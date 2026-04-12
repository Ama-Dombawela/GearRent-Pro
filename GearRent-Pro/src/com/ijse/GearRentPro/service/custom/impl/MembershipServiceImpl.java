/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.service.custom.impl;

import com.ijse.GearRentPro.dao.Custom.MembershipDao;
import com.ijse.GearRentPro.dao.DaoFactory;
import com.ijse.GearRentPro.dto.MembershipDto;
import com.ijse.GearRentPro.entity.MembershipEntity;
import com.ijse.GearRentPro.service.custom.MembershipService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class MembershipServiceImpl implements MembershipService {

    MembershipDao membershipDao = (MembershipDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.MEMBERSHIP);

    @Override
    public boolean saveMembership(MembershipDto dto) throws Exception {
        return membershipDao.save(new MembershipEntity(
                dto.getMembershipId(),
                dto.getLevel(),
                dto.getDiscountPer()
        ));
    }

    @Override
    public boolean updateMembership(MembershipDto dto) throws Exception {
        return membershipDao.update(new MembershipEntity(
                dto.getMembershipId(),
                dto.getLevel(),
                dto.getDiscountPer()
        ));
    }

    @Override
    public boolean deleteMembership(String id) throws Exception {
        return membershipDao.delete(id);
    }

    @Override
    public MembershipDto findMembership(String id) throws Exception {
        MembershipEntity entity = membershipDao.search(id);
        return new MembershipDto(
                entity.getMembershipId(),
                entity.getLevel(),
                entity.getDiscountPer()
        );
    }

    @Override
    public List<MembershipDto> findAllMemberships() throws Exception {
        ArrayList<MembershipEntity> entities = membershipDao.getAll();
        List<MembershipDto> dtos = new ArrayList<>();
        for (MembershipEntity entity : entities) {
            dtos.add(new MembershipDto(
                    entity.getMembershipId(),
                    entity.getLevel(),
                    entity.getDiscountPer()
            ));
        }
        return dtos;
    }

}
