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

    /**
     * Loads rentals for a specific customer.
     *
     * @param customerId customer identifier
     * @return matching rental entities
     * @throws Exception when the query fails
     */
    @Override
    public ArrayList<RentalEntity> getByCustomer(String customerId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE TRIM(customer_id)=?", customerId);
        ArrayList<RentalEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    /**
     * Loads rentals for a specific branch.
     *
     * @param branchId branch identifier
     * @return matching rental entities
     * @throws Exception when the query fails
     */
    @Override
    public ArrayList<RentalEntity> getByBranch(String branchId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE TRIM(branch_id)=?", branchId);
        ArrayList<RentalEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    /**
     * Loads rentals with a specific rental status.
     *
     * @param status rental status value
     * @return matching rental entities
     * @throws Exception when the query fails
     */
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

    /**
     * Loads all overdue rentals.
     *
     * @return overdue rental entities
     * @throws Exception when the query fails
     */
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

    /**
     * Calculates the active deposit total for a customer.
     *
     * @param CustomerId customer identifier
     * @return active deposit total
     * @throws Exception when the query fails
     */
    @Override
    public double getTotalActiveDepositByCustomer(String CustomerId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                "SELECT SUM(deposit_amount) AS total FROM rentals WHERE TRIM(customer_id)=? AND rental_status='Active'",
                CustomerId);
        if (resultSet.next()) {
            return resultSet.getDouble("total");
        }
        return 0.0;
    }

    /**
     * Calculates the active deposit total for a customer while locking matching
     * rows.
     *
     * @param CustomerId customer identifier
     * @return active deposit total
     * @throws Exception when the query fails
     */
    @Override
    public double getTotalActiveDepositByCustomerForUpdate(String CustomerId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                "SELECT SUM(deposit_amount) AS total FROM rentals WHERE TRIM(customer_id)=? AND rental_status='Active' FOR UPDATE",
                CustomerId);
        if (resultSet.next()) {
            return resultSet.getDouble("total");
        }
        return 0.0;
    }

    /**
     * Checks whether an equipment item already has an active rental overlap.
     *
     * @param equipmentId equipment identifier
     * @param startDate proposed rental start date
     * @param endDate proposed rental end date
     * @return true when an overlapping rental exists
     * @throws Exception when the query fails
     */
    @Override
    public boolean hasActiveOverlapForUpdate(String equipmentId, String startDate, String endDate) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                "SELECT rental_id FROM rentals WHERE TRIM(equipment_id)=? AND rental_status IN ('Active','Overdue') AND start_date <= ? AND end_date >= ? FOR UPDATE",
                equipmentId,
                endDate,
                startDate
        );
        return resultSet.next();
    }

    /**
     * Saves a rental row using the entity values.
     *
     * @param t rental entity to persist
     * @return true when the insert succeeds
     * @throws Exception when the SQL execution fails
     */
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

    /**
     * Updates a rental row using the entity values.
     *
     * @param t rental entity with updated values
     * @return true when the update succeeds
     * @throws Exception when the SQL execution fails
     */
    @Override
    public boolean update(RentalEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE rentals SET equipment_id=?, customer_id=?, branch_id=?, start_date=?, end_date=?, actual_return_date=?, rental_amount=?, deposit_amount=?, membership_discount=?,"
                + " long_rental_discount=?, final_amount=?, payment_status=?, rental_status=? WHERE TRIM(rental_id)=?",
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

    /**
     * Deletes a rental row by ID.
     *
     * @param id rental identifier
     * @return true when the delete succeeds
     * @throws Exception when the SQL execution fails
     */
    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM rentals WHERE TRIM(rental_id)=?", id);
    }

    /**
     * Loads one rental row by ID.
     *
     * @param id rental identifier
     * @return matching rental entity, or null when not found
     * @throws Exception when the SQL execution fails
     */
    @Override
    public RentalEntity search(String id) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE TRIM(rental_id)=?", id);
        if (resultSet.next()) {
            return mapRow(resultSet);
        }
        return null;
    }

    /**
     * Loads one rental row by ID while locking it for update.
     *
     * @param rentalId rental identifier
     * @return matching rental entity, or null when not found
     * @throws Exception when the SQL execution fails
     */
    @Override
    public RentalEntity searchForUpdate(String rentalId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE TRIM(rental_id)=? FOR UPDATE", rentalId);
        if (resultSet.next()) {
            return mapRow(resultSet);
        }
        return null;
    }

    /**
     * Loads all rental rows.
     *
     * @return list of rental entities
     * @throws Exception when the SQL execution fails
     */
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
                resultSet.getString("rental_id").trim(),
                resultSet.getString("equipment_id").trim(),
                resultSet.getString("customer_id").trim(),
                resultSet.getString("branch_id").trim(),
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
