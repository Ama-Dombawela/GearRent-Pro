/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom.impl;

import com.ijse.GearRentPro.dao.CrudUtil;
import com.ijse.GearRentPro.dao.Custom.RentalDao;
import com.ijse.GearRentPro.entity.RentalEntity;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class RentalDaoImpl implements RentalDao {

    private static final String SELECT_QUERY = "SELECT * FROM rentals";

    @Override
    public ArrayList<RentalEntity> getByCutomer(String customerId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE customer_id=?", customerId);
        ArrayList<RentalEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    @Override
    public ArrayList<RentalEntity> getByBranch(String branchId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE branch_id=?", branchId);
        ArrayList<RentalEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    @Override
    public ArrayList<RentalEntity> getByStatus(String status) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE rental_status=?", status);
        ArrayList<RentalEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    @Override
    public ArrayList<RentalEntity> getOverdueRentals() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE rental_status='Overdue'");
        ArrayList<RentalEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    @Override
    public double getTotalActiveDepositByCutomer(String cutomerId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                "SELECT SUM(deposit_amount) AS total FROM rentals WHERE customer_id=? AND rental_status='Active'",
                cutomerId);
        if (resultSet.next()) {
            return resultSet.getDouble("total");
        }
        return 0.0;
    }

    @Override
    public boolean save(RentalEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO rentals VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                t.getRentalId(),
                t.getEquipmentId(),
                t.getCustomerId(),
                t.getBranchId(),
                t.getStartDate(),
                t.getEndDate(),
                t.getActualReturnDate(),
                t.getRentalAmount(),
                t.getDepositAmount(),
                t.getMembershipDiscount(),
                t.getLongRentalDiscount(),
                t.getFinalAmount(),
                t.getPaymentStatus().getDatabaseValue(),
                t.getRentalStatus().getDatabaseValue());

    }

    @Override
    public boolean update(RentalEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE rentals SET equipment_id=?, customer_id=?, branch_id=?, start_date=?, end_date=?, actual_return_date=?, rental_amount=?, deposit_amount=?, membership_discount=?,"
                + " long_rental_discount=?, final_amount=?, payment_status=?, rental_status=? WHERE rental_id=?",
                t.getEquipmentId(),
                t.getCustomerId(),
                t.getBranchId(),
                t.getStartDate(),
                t.getEndDate(),
                t.getActualReturnDate(),
                t.getRentalAmount(),
                t.getDepositAmount(),
                t.getMembershipDiscount(),
                t.getLongRentalDiscount(),
                t.getFinalAmount(),
                t.getPaymentStatus().getDatabaseValue(),
                t.getRentalStatus().getDatabaseValue(),
                t.getRentalId());

    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM rentals WHERE rental_id=?", id);
    }

    @Override
    public RentalEntity search(String id) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE rental_id=?", id);
        if (resultSet.next()) {
            return mapRow(resultSet);
        }
        return null;
    }

    @Override
    public ArrayList<RentalEntity> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(SELECT_QUERY);
        ArrayList<RentalEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    private RentalEntity mapRow(ResultSet resultSet) throws Exception {

        return new RentalEntity(
                resultSet.getString("rental_id"),
                resultSet.getString("equipment_id"),
                resultSet.getString("customer_id"),
                resultSet.getString("branch_id"),
                resultSet.getDate("start_date").toLocalDate(),
                resultSet.getDate("end_date").toLocalDate(),
                resultSet.getDate("actual_return_date") != null ? resultSet.getDate("actual_return_date").toLocalDate() : null,
                resultSet.getDouble("rental_amount"),
                resultSet.getDouble("deposit_amount"),
                resultSet.getDouble("membership_discount"),
                resultSet.getDouble("long_rental_discount"),
                resultSet.getDouble("final_amount"),
                RentalEntity.PaymentStatus.fromDbValues(resultSet.getString("payment_status")),
                RentalEntity.RentalStatus.fromDbValues(resultSet.getString("rental_status"))
        );
    }
}
