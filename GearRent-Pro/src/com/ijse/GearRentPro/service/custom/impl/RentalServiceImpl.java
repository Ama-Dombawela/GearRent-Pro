/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.service.custom.impl;

import com.ijse.GearRentPro.dao.Custom.RentalDao;
import com.ijse.GearRentPro.dao.DaoFactory;
import com.ijse.GearRentPro.dto.RentalDto;
import com.ijse.GearRentPro.entity.RentalEntity;
import com.ijse.GearRentPro.service.custom.RentalService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class RentalServiceImpl implements RentalService {

    RentalDao rentalDao = (RentalDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.RENTAL);

    @Override
    public boolean saveRental(RentalDto dto) throws Exception {
        return rentalDao.save(new RentalEntity(
                dto.getRentalId(),
                dto.getEquipment(),
                dto.getCustomer(),
                dto.getBranch(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getActualReturnDate(),
                dto.getRentalAmount(),
                dto.getDepositAmount(),
                dto.getMembershipDiscount(),
                dto.getLongRentalDiscount(),
                dto.getFinalAmount(),
                dto.getPaymentStatus(),
                dto.getRentalStatus()
        ));

    }

    @Override
    public boolean updateRental(RentalDto dto) throws Exception {
        return rentalDao.update(new RentalEntity(
                dto.getRentalId(),
                dto.getEquipment(),
                dto.getCustomer(),
                dto.getBranch(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getActualReturnDate(),
                dto.getRentalAmount(),
                dto.getDepositAmount(),
                dto.getMembershipDiscount(),
                dto.getLongRentalDiscount(),
                dto.getFinalAmount(),
                dto.getPaymentStatus(),
                dto.getRentalStatus()
        ));

    }

    @Override
    public boolean deleteRental(String id) throws Exception {
        return rentalDao.delete(id);
    }

    @Override
    public RentalDto findRental(String id) throws Exception {
        RentalEntity entity = rentalDao.search(id);
        return new RentalDto(
                entity.getRentalId(),
                entity.getEquipment(),
                entity.getCustomer(),
                entity.getBranch(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getActualReturnDate(),
                entity.getRentalAmount(),
                entity.getDepositAmount(),
                entity.getMembershipDiscount(),
                entity.getLongRentalDiscount(),
                entity.getFinalAmount(),
                entity.getPaymentStatus(),
                entity.getRentalStatus()
        );
    }

    @Override
    public List<RentalDto> findAllRentals() throws Exception {
        ArrayList<RentalEntity> entities = rentalDao.getAll();
        List<RentalDto> dtos = new ArrayList<>();
        for (RentalEntity entity : entities) {
            dtos.add(new RentalDto(
                    entity.getRentalId(),
                    entity.getEquipment(),
                    entity.getCustomer(),
                    entity.getBranch(),
                    entity.getStartDate(),
                    entity.getEndDate(),
                    entity.getActualReturnDate(),
                    entity.getRentalAmount(),
                    entity.getDepositAmount(),
                    entity.getMembershipDiscount(),
                    entity.getLongRentalDiscount(),
                    entity.getFinalAmount(),
                    entity.getPaymentStatus(),
                    entity.getRentalStatus()
            ));
        }
        return dtos;
    }

}
