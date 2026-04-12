/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.service.custom.impl;

import com.ijse.GearRentPro.dao.Custom.ReservationDao;
import com.ijse.GearRentPro.dao.DaoFactory;
import com.ijse.GearRentPro.dto.ReservationDto;
import com.ijse.GearRentPro.entity.ReservationEntity;
import com.ijse.GearRentPro.service.custom.ReservationService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class ReservationServiceImpl implements ReservationService {

    ReservationDao reservationDao = (ReservationDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.RESERVATION);

    @Override
    public boolean saveReservation(ReservationDto dto) throws Exception {
        return reservationDao.save(new ReservationEntity(
                dto.getReservationId(),
                dto.getEquipment(),
                dto.getCustomer(),
                dto.getBranch(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getReservationStatus()
        ));
    }

    @Override
    public boolean updateReservation(ReservationDto dto) throws Exception {
        return reservationDao.update(new ReservationEntity(
                dto.getReservationId(),
                dto.getEquipment(),
                dto.getCustomer(),
                dto.getBranch(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getReservationStatus()
        ));
    }

    @Override
    public boolean deleteReservation(String id) throws Exception {
        return reservationDao.delete(id);
    }

    @Override
    public ReservationDto findReservation(String id) throws Exception {
        ReservationEntity entity = reservationDao.search(id);
        return new ReservationDto(
                entity.getReservationId(),
                entity.getEquipment(),
                entity.getCustomer(),
                entity.getBranch(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getReservationStatus()
        );
    }

    @Override
    public List<ReservationDto> findAllReservations() throws Exception {
        ArrayList<ReservationEntity> entities = reservationDao.getAll();
        List<ReservationDto> dtos = new ArrayList<>();
        for (ReservationEntity entity : entities) {
            dtos.add(new ReservationDto(
                    entity.getReservationId(),
                    entity.getEquipment(),
                    entity.getCustomer(),
                    entity.getBranch(),
                    entity.getStartDate(),
                    entity.getEndDate(),
                    entity.getReservationStatus()
            ));
        }
        return dtos;
    }

}
