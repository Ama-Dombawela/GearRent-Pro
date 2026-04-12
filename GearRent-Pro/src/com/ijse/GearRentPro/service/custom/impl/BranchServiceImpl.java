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
        return branchDao.save(new BranchEntity(
                dto.getBranchId(),
                dto.getBranchCode(),
                dto.getName(),
                dto.getAddress(),
                dto.getContactNumber()
        ));
    }

    @Override
    public boolean updateBranch(BranchDto dto) throws Exception {
        return branchDao.update(new BranchEntity(
                dto.getBranchId(),
                dto.getBranchCode(),
                dto.getName(),
                dto.getAddress(),
                dto.getContactNumber()
        ));
    }

    @Override
    public boolean deleteBranch(String id) throws Exception {
        return branchDao.delete(id);
    }

    @Override
    public BranchDto findBranch(String id) throws Exception {
        BranchEntity entity = branchDao.search(id);
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

}
