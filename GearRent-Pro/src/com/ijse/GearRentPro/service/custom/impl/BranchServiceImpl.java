/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.service.custom.impl;

import com.ijse.GearRentPro.dao.Custom.BranchDao;
import com.ijse.GearRentPro.dao.DaoFactory;
import com.ijse.GearRentPro.dto.BranchDto;
import com.ijse.GearRentPro.entity.BranchEntity;
import com.ijse.GearRentPro.service.custom.BranchService;
import com.ijse.GearRentPro.util.AuthUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class BranchServiceImpl implements BranchService {

    BranchDao branchDao = (BranchDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.BRANCH);

    @Override
    public boolean saveBranch(BranchDto dto) throws Exception {
        AuthUtil.requireAdmin();
        if (dto.getBranchId() == null || dto.getBranchId().isBlank()) {
            throw new Exception("Branch ID is required.");
        }
        if (dto.getBranchCode() == null || dto.getBranchCode().isBlank()) {
            throw new Exception("Branch code is required.");
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new Exception("Branch name is required.");
        }
        try {
            return branchDao.save(new BranchEntity(
                    dto.getBranchId(),
                    dto.getBranchCode(),
                    dto.getName(),
                    dto.getAddress(),
                    dto.getContactNumber()
            ));
        } catch (SQLException e) {
            if (isDuplicateEntry(e)) {
                throw new Exception("This branch ID or branch code already exists. Please use a unique branch record.");
            }
            throw e;
        }
    }

    @Override
    public boolean updateBranch(BranchDto dto) throws Exception {
        AuthUtil.requireAdmin();
        if (dto.getBranchId() == null || dto.getBranchId().isBlank()) {
            throw new Exception("Branch ID is required.");
        }
        if (dto.getBranchCode() == null || dto.getBranchCode().isBlank()) {
            throw new Exception("Branch code is required.");
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new Exception("Branch name is required.");
        }
        try {
            return branchDao.update(new BranchEntity(
                    dto.getBranchId(),
                    dto.getBranchCode(),
                    dto.getName(),
                    dto.getAddress(),
                    dto.getContactNumber()
            ));
        } catch (SQLException e) {
            if (isDuplicateEntry(e)) {
                throw new Exception("This branch ID or branch code already exists. Please use a unique branch record.");
            }
            throw e;
        }
    }

    @Override
    public boolean deleteBranch(String id) throws Exception {
        AuthUtil.requireAdmin();
        return branchDao.delete(id);
    }

    @Override
    public BranchDto findBranch(String id) throws Exception {
        AuthUtil.requireUser();
        BranchEntity entity = branchDao.search(id);
        if (entity == null) {
            return null;
        }
        return new BranchDto(
                entity.getBranchId(),
                entity.getBranchCode(),
                entity.getName(),
                entity.getAddress(),
                entity.getContactNumber()
        );
    }

    @Override
    public List<BranchDto> findAllBranches() throws Exception {
        AuthUtil.requireUser();
        ArrayList<BranchEntity> entities = branchDao.getAll();
        List<BranchDto> dtos = new ArrayList<>();
        for (BranchEntity entity : entities) {
            dtos.add(new BranchDto(
                    entity.getBranchId(),
                    entity.getBranchCode(),
                    entity.getName(),
                    entity.getAddress(),
                    entity.getContactNumber()
            ));
        }
        return dtos;
    }

    private boolean isDuplicateEntry(SQLException exception) {
        String message = exception.getMessage();
        return exception.getErrorCode() == 1062
                || (exception.getSQLState() != null && exception.getSQLState().startsWith("23"))
                || (message != null && message.toLowerCase().contains("duplicate entry"));
    }

}
