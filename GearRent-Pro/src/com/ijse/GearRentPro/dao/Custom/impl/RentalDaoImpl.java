/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom.impl;

import com.ijse.GearRentPro.dao.CrudUtil;
import com.ijse.GearRentPro.dao.Custom.RentalDao;
import com.ijse.GearRentPro.entity.BranchEntity;
import com.ijse.GearRentPro.entity.CategoryEntity;
import com.ijse.GearRentPro.entity.CustomerEntity;
import com.ijse.GearRentPro.entity.EquipmentEntity;
import com.ijse.GearRentPro.entity.RentalEntity;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class RentalDaoImpl implements RentalDao {

    private static final String SELECT_QUERY
            = "SELECT r.*, "
            + "e.brand, e.model, e.purchase_year, e.base_daily_price, e.security_deposit, e.status AS eq_status, "
            + "c.name AS cat_name, c.description, c.price_factor, c.weekend_multiplier, c.late_fee_per_day, c.is_active, "
            + "eb.branch_code AS eq_branch_code, eb.name AS eq_branch_name, eb.address AS eq_branch_addr, eb.contact_no AS eq_branch_contact, "
            + "cu.name AS cust_name, cu.nic_passport, cu.contact_no AS cust_contact, cu.email, cu.address AS cust_address, cu.membership_id, "
            + "b.branch_code AS rent_branch_code, b.name AS rent_branch_name, b.address AS rent_branch_addr, b.contact_no AS rent_branch_contact "
            + "FROM rentals r "
            + "JOIN equipment e ON r.equipment_id = e.equipment_id "
            + "JOIN categories c ON e.category_id = c.category_id "
            + "JOIN branches eb ON e.branch_id = eb.branch_id "
            + "JOIN customers cu ON r.customer_id = cu.customer_id "
            + "JOIN branches b ON r.branch_id = b.branch_id";

    @Override
    public ArrayList<RentalEntity> getByCutomer(String customerId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE r.customer_id=?", customerId);
        ArrayList<RentalEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    @Override
    public ArrayList<RentalEntity> getByBranch(String branchId) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE r.branch_id=?", branchId);
        ArrayList<RentalEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    @Override
    public ArrayList<RentalEntity> getByStatus(String status) throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE r.rental_status=?", status);
        ArrayList<RentalEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(mapRow(resultSet));
        }
        return list;
    }

    @Override
    public ArrayList<RentalEntity> getOverdueRentals() throws Exception {
        ResultSet resultSet = CrudUtil.executeQuery(
                SELECT_QUERY + " WHERE r.rental_status='Overdue'");
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
                t.getEquipment().getEquipmentId(),
                t.getCustomer().getCustomerId(),
                t.getBranch().getBranchId(),
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
                t.getEquipment().getEquipmentId(),
                t.getCustomer().getCustomerId(),
                t.getBranch().getBranchId(),
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
                SELECT_QUERY + " WHERE r.rental_id=?", id);
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
        BranchEntity branch = new BranchEntity( // ← rental's branch
                resultSet.getString("branch_id"),
                resultSet.getString("rent_branch_code"),
                resultSet.getString("rent_branch_name"),
                resultSet.getString("rent_branch_addr"),
                resultSet.getString("rent_branch_contact")
        );
        return new RentalEntity(
                resultSet.getString("rental_id"),
                equipment, customer, branch,
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
