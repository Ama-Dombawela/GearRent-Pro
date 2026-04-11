/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom.impl;

import com.ijse.GearRentPro.dao.CrudUtil;
import com.ijse.GearRentPro.dao.Custom.ReservationDao;
import com.ijse.GearRentPro.entity.BranchEntity;
import com.ijse.GearRentPro.entity.CategoryEntity;
import com.ijse.GearRentPro.entity.CustomerEntity;
import com.ijse.GearRentPro.entity.EquipmentEntity;
import com.ijse.GearRentPro.entity.ReservationEntity;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ReservationDaoImpl implements ReservationDao {

    private static final String SELECT_QUERY
            = "SELECT res.*, "
            + "e.brand, e.model, e.purchase_year, e.base_daily_price, e.security_deposit, e.status AS eq_status, "
            + "c.name AS cat_name, c.description, c.price_factor, c.weekend_multiplier, c.late_fee_per_day, c.is_active, "
            + "eb.branch_code AS eq_branch_code, eb.name AS eq_branch_name, eb.address AS eq_branch_addr, eb.contact_no AS eq_branch_contact, "
            + "cu.name AS cust_name, cu.nic_passport, cu.contact_no AS cust_contact, cu.email, cu.address AS cust_address, cu.membership_id, "
            + "b.branch_code AS res_branch_code, b.name AS res_branch_name, b.address AS res_branch_addr, b.contact_no AS res_branch_contact "
            + "FROM reservations res "
            + "JOIN equipment e ON res.equipment_id = e.equipment_id "
            + "JOIN categories c ON e.category_id = c.category_id "
            + "JOIN branches eb ON e.branch_id = eb.branch_id "
            + "JOIN customers cu ON res.customer_id = cu.customer_id "
            + "JOIN branches b ON res.branch_id = b.branch_id";

    @Override
    public ArrayList<ReservationEntity> getByCutomer(String customerId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE res.customer_id=?", customerId);
        ArrayList<ReservationEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    @Override
    public ArrayList<ReservationEntity> getByBranch(String branchId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE res.branch_id=?", branchId);
        ArrayList<ReservationEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    @Override
    public boolean hasOverlap(String equipmentId, String startDate, String endDate) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                "SELECT * FROM reservations WHERE equipment_id=? AND status='Active' AND start_date <= ? AND end_date >= ?",
                equipmentId, endDate, startDate);
        return resultSet.next();
    }

    @Override
    public boolean save(ReservationEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "INSERT INTO reservations VALUES(?,?,?,?,?,?,?)",
                t.getReservationId(),
                t.getEquipment().getEquipmentId(),
                t.getCustomer().getCutomerId(),
                t.getBranch().getBranchId(),
                t.getStartDate(),
                t.getEndDate(),
                t.getReservationStatus().getDatabaseValue());
    }

    @Override
    public boolean update(ReservationEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE reservations SET equipment_id=?, customer_id=?, branch_id=?, start_date=?, end_date=?, status=? WHERE reservation_id=?",
                t.getEquipment().getEquipmentId(),
                t.getCustomer().getCutomerId(),
                t.getBranch().getBranchId(),
                t.getStartDate(),
                t.getEndDate(),
                t.getReservationStatus().getDatabaseValue(),
                t.getReservationId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate(
                "DELETE FROM reservations WHERE reservation_id=?", id);
    }

    @Override
    public ReservationEntity search(String id) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE res.reservation_id=?", id);
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
        CategoryEntity category = new CategoryEntity(
                resultSet.getString("category_id"),
                resultSet.getString("cat_name"),
                resultSet.getString("description"),
                resultSet.getDouble("price_factor"),
                resultSet.getDouble("weekend_multiplier"),
                resultSet.getDouble("late_fee_per_day"),
                resultSet.getBoolean("is_active")
        );
        BranchEntity equipBranch = new BranchEntity(
                resultSet.getString("branch_id"),
                resultSet.getString("eq_branch_code"),
                resultSet.getString("eq_branch_name"),
                resultSet.getString("eq_branch_addr"),
                resultSet.getString("eq_branch_contact")
        );
        EquipmentEntity equipment = new EquipmentEntity(
                resultSet.getString("equipment_id"),
                category, equipBranch,
                resultSet.getString("brand"),
                resultSet.getString("model"),
                resultSet.getInt("purchase_year"),
                resultSet.getDouble("base_daily_price"),
                resultSet.getDouble("security_deposit"),
                EquipmentEntity.Status.fromDbValues(resultSet.getString("eq_status"))
        );
        CustomerEntity customer = new CustomerEntity(
                resultSet.getString("customer_id"),
                resultSet.getString("cust_name"),
                resultSet.getString("nic_passport"),
                resultSet.getString("cust_contact"),
                resultSet.getString("email"),
                resultSet.getString("cust_address"),
                resultSet.getInt("membership_id")
        );
        BranchEntity branch = new BranchEntity(
                resultSet.getString("branch_id"),
                resultSet.getString("res_branch_code"),
                resultSet.getString("res_branch_name"),
                resultSet.getString("res_branch_addr"),
                resultSet.getString("res_branch_contact")
        );
        return new ReservationEntity(
                resultSet.getString("reservation_id"),
                equipment, customer, branch,
                resultSet.getDate("start_date").toLocalDate(),
                resultSet.getDate("end_date").toLocalDate(),
                ReservationEntity.ReservationStatus.fromDbValues(resultSet.getString("status"))
        );
    }
}
