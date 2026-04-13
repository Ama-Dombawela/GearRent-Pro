/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom.impl;

import com.ijse.GearRentPro.dao.CrudUtil;
import com.ijse.GearRentPro.dao.Custom.ReservationDao;
import com.ijse.GearRentPro.entity.ReservationEntity;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ReservationDaoImpl implements ReservationDao {

    private static final String SELECT_QUERY = "SELECT * FROM reservations";

    @Override
    public ArrayList<ReservationEntity> getByCutomer(String customerId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE TRIM(customer_id)=?", customerId);
        ArrayList<ReservationEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    @Override
    public ArrayList<ReservationEntity> getByBranch(String branchId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE TRIM(branch_id)=?", branchId);
        ArrayList<ReservationEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    @Override
    public boolean hasOverlap(String equipmentId, String startDate, String endDate) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                "SELECT * FROM reservations WHERE TRIM(equipment_id)=? AND status='Active' AND start_date <= ? AND end_date >= ?",
                equipmentId, endDate, startDate);
        return resultSet.next();
    }

    @Override
    public boolean save(ReservationEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO reservations VALUES(?,?,?,?,?,?,?)",
                t.getReservationId(),
                t.getEquipmentId(),
                t.getCustomerId(),
                t.getBranchId(),
                t.getStartDate(),
                t.getEndDate(),
                t.getReservationStatus().getDatabaseValue());
    }

    @Override
    public boolean update(ReservationEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE reservations SET equipment_id=?, customer_id=?, branch_id=?, start_date=?, end_date=?, status=? WHERE TRIM(reservation_id)=?",
                t.getEquipmentId(),
                t.getCustomerId(),
                t.getBranchId(),
                t.getStartDate(),
                t.getEndDate(),
                t.getReservationStatus().getDatabaseValue(),
                t.getReservationId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM reservations WHERE TRIM(reservation_id)=?", id);
    }

    @Override
    public ReservationEntity search(String id) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE TRIM(reservation_id)=?", id);
        if (resultSet.next()) {
            return mapRow(resultSet);
        }
        return null;
    }

    @Override
    public ArrayList<ReservationEntity> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(SELECT_QUERY);
        ArrayList<ReservationEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    private ReservationEntity mapRow(ResultSet resultSet) throws Exception {

        return new ReservationEntity(
                resultSet.getString("reservation_id").trim(),
                resultSet.getString("equipment_id").trim(),
                resultSet.getString("customer_id").trim(),
                resultSet.getString("branch_id").trim(),
                resultSet.getDate("start_date").toLocalDate(),
                resultSet.getDate("end_date").toLocalDate(),
                ReservationEntity.ReservationStatus.fromDbValues(resultSet.getString("status"))
        );
    }
}
