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
import com.ijse.GearRentPro.util.AuthUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class MembershipServiceImpl implements MembershipService {

    MembershipDao membershipDao = (MembershipDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.MEMBERSHIP);

    @Override
    /**
     * Creates a membership level after validating uniqueness and discount
     * bounds.
     *
     * @param dto membership data from the UI
     * @return true when the membership is saved successfully
     * @throws Exception when validation, authorization, or persistence fails
     */
    public boolean saveMembership(MembershipDto dto) throws Exception {
        AuthUtil.requireAdmin();

        if (dto.getMembershipId() == null || dto.getMembershipId().isBlank()) {
            throw new Exception("Membership ID is required.");
        }
        if (dto.getLevel() == null || dto.getLevel().isBlank()) {
            throw new Exception("Membership level is required.");
        }
        if (dto.getDiscountPer() < 0 || dto.getDiscountPer() > 100) {
            throw new Exception("Discount must be between 0 and 100.");
        }

        String membershipId = dto.getMembershipId().trim();
        String level = dto.getLevel().trim();

        MembershipEntity existingById = membershipDao.search(membershipId);
        if (existingById != null) {
            throw new Exception("A membership with this ID already exists. Please use a different ID.");
        }

        MembershipEntity existingByLevel = membershipDao.getByLevel(level);
        if (existingByLevel != null) {
            throw new Exception("This membership level is already in use. Please choose another level name.");
        }

        return membershipDao.save(new MembershipEntity(
                membershipId,
                level,
                dto.getDiscountPer()
        ));
    }

    @Override
    /**
     * Updates a membership level after checking uniqueness and discount bounds.
     *
     * @param dto membership data from the UI
     * @return true when the membership is updated successfully
     * @throws Exception when validation, authorization, or persistence fails
     */
    public boolean updateMembership(MembershipDto dto) throws Exception {
        AuthUtil.requireAdmin();

        if (dto.getMembershipId() == null || dto.getMembershipId().isBlank()) {
            throw new Exception("Membership ID is required.");
        }
        if (dto.getLevel() == null || dto.getLevel().isBlank()) {
            throw new Exception("Membership level is required.");
        }
        if (dto.getDiscountPer() < 0 || dto.getDiscountPer() > 100) {
            throw new Exception("Discount must be between 0 and 100.");
        }

        String membershipId = dto.getMembershipId().trim();
        String level = dto.getLevel().trim();

        MembershipEntity existingMembership = membershipDao.search(membershipId);
        if (existingMembership == null) {
            throw new Exception("Membership not found for update.");
        }

        MembershipEntity existingByLevel = membershipDao.getByLevel(level);
        if (existingByLevel != null && !existingByLevel.getMembershipId().trim().equals(membershipId)) {
            throw new Exception("This membership level is already in use. Please choose another level name.");
        }

        return membershipDao.update(new MembershipEntity(
                membershipId,
                level,
                dto.getDiscountPer()
        ));
    }

    @Override
    /**
     * Deletes a membership by ID.
     *
     * @param id membership identifier
     * @return true when the membership is deleted successfully
     * @throws Exception when authorization or persistence fails
     */
    public boolean deleteMembership(String id) throws Exception {
        AuthUtil.requireAdmin();
        return membershipDao.delete(id);
    }

    @Override
    /**
     * Finds a membership by ID.
     *
     * @param id membership identifier
     * @return membership data, or null when not found
     * @throws Exception when authorization or query execution fails
     */
    public MembershipDto findMembership(String id) throws Exception {
        AuthUtil.requireUser();
        MembershipEntity entity = membershipDao.search(id);
        if (entity == null) {
            return null;
        }
        return new MembershipDto(
                entity.getMembershipId(),
                entity.getLevel(),
                entity.getDiscountPer()
        );
    }

    @Override
    /**
     * Loads all memberships and converts them to DTOs.
     *
     * @return membership list for the UI layer
     * @throws Exception when authorization or query execution fails
     */
    public List<MembershipDto> findAllMemberships() throws Exception {
        AuthUtil.requireUser();
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
