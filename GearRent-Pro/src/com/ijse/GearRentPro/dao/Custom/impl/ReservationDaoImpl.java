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

    /**
     * Loads reservations for a specific customer.
     *
     * @param customerId customer identifier
     * @return matching reservation entities
     * @throws Exception when the query fails
     */
    @Override
    public ArrayList<ReservationEntity> getByCustomer(String customerId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE TRIM(customer_id)=?", customerId);
        ArrayList<ReservationEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    /**
     * Loads reservations for a specific branch.
     *
     * @param branchId branch identifier
     * @return matching reservation entities
     * @throws Exception when the query fails
     */
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

    /**
     * Checks whether an equipment item already has an active reservation
     * overlap.
     *
     * @param equipmentId equipment identifier
     * @param startDate proposed reservation start date
     * @param endDate proposed reservation end date
     * @return true when an overlapping reservation exists
     * @throws Exception when the query fails
     */
    @Override
    public boolean hasOverlap(String equipmentId, String startDate, String endDate) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                "SELECT * FROM reservations WHERE TRIM(equipment_id)=? AND status='Active' AND start_date <= ? AND end_date >= ?",
                equipmentId, endDate, startDate);
        return resultSet.next();
    }

    /**
     * Checks for an overlapping reservation while locking the matching rows.
     *
     * @param equipmentId equipment identifier
     * @param startDate proposed reservation start date
     * @param endDate proposed reservation end date
     * @return true when an overlapping reservation exists
     * @throws Exception when the query fails
     */
    @Override
    public boolean hasOverlapForUpdate(String equipmentId, String startDate, String endDate) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                "SELECT reservation_id FROM reservations WHERE TRIM(equipment_id)=? AND status='Active' AND start_date <= ? AND end_date >= ? FOR UPDATE",
                equipmentId, endDate, startDate);
        return resultSet.next();
    }

    /**
     * Saves a reservation row using the entity values.
     *
     * @param t reservation entity to persist
     * @return true when the insert succeeds
     * @throws Exception when the SQL execution fails
     */
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

    /**
     * Updates a reservation row using the entity values.
     *
     * @param t reservation entity with updated values
     * @return true when the update succeeds
     * @throws Exception when the SQL execution fails
     */
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

    /**
     * Deletes a reservation row by ID.
     *
     * @param id reservation identifier
     * @return true when the delete succeeds
     * @throws Exception when the SQL execution fails
     */
    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM reservations WHERE TRIM(reservation_id)=?", id);
    }

    /**
     * Loads one reservation row by ID.
     *
     * @param id reservation identifier
     * @return matching reservation entity, or null when not found
     * @throws Exception when the SQL execution fails
     */
    @Override
    public ReservationEntity search(String id) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE TRIM(reservation_id)=?", id);
        if (resultSet.next()) {
            return mapRow(resultSet);
        }
        return null;
    }

    /**
     * Loads all reservation rows.
     *
     * @return list of reservation entities
     * @throws Exception when the SQL execution fails
     */
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
