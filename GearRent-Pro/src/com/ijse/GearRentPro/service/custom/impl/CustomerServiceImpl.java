/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.service.custom.impl;

import com.ijse.GearRentPro.dao.Custom.CustomerDao;
import com.ijse.GearRentPro.dao.Custom.MembershipDao;
import com.ijse.GearRentPro.dao.DaoFactory;
import com.ijse.GearRentPro.dto.CustomerDto;
import com.ijse.GearRentPro.entity.CustomerEntity;
import com.ijse.GearRentPro.entity.MembershipEntity;
import com.ijse.GearRentPro.service.custom.CustomerService;
import com.ijse.GearRentPro.util.AuthUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class CustomerServiceImpl implements CustomerService {

    CustomerDao customerDao = (CustomerDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.CUSTOMER);
    MembershipDao membershipDao = (MembershipDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.MEMBERSHIP);

    @Override
    /**
     * Creates a new customer after validating required fields and membership
     * eligibility.
     *
     * @param dto customer data from the UI
     * @return true when the customer is saved successfully
     * @throws Exception when validation, lookup, or persistence fails
     */
    public boolean saveCustomer(CustomerDto dto) throws Exception {
        AuthUtil.requireUser();
        if (dto.getCustomerId() == null || dto.getCustomerId().isBlank()) {
            throw new Exception("Customer ID is required.");
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new Exception("Customer name is required.");
        }
        if (dto.getNicOrPassport() == null || dto.getNicOrPassport().isBlank()) {
            throw new Exception("NIC/Passport is required.");
        }
        if (dto.getContactNo() == null || dto.getContactNo().isBlank()) {
            throw new Exception("Contact number is required.");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new Exception("Email is required.");
        }
        if (dto.getAddress() == null || dto.getAddress().isBlank()) {
            throw new Exception("Address is required.");
        }
        if (dto.getMembershipId() == null || dto.getMembershipId().isBlank()) {
            throw new Exception("Membership is required.");
        }
        MembershipEntity membership = membershipDao.search(dto.getMembershipId());
        if (membership == null) {
            throw new Exception("Membership not found.");
        }
        try {
            return customerDao.save(new CustomerEntity(
                    dto.getCustomerId(),
                    dto.getName(),
                    dto.getNicOrPassport(),
                    dto.getContactNo(),
                    dto.getEmail(),
                    dto.getAddress(),
                    dto.getMembershipId()
            ));
        } catch (SQLException e) {
            if (isDuplicateEntry(e)) {
                throw new Exception("This customer ID already exists. Please use a different customer ID.");
            }
            throw e;
        }
    }

    @Override
    /**
     * Updates an existing customer after validating the input and membership
     * reference.
     *
     * @param dto customer data from the UI
     * @return true when the customer is updated successfully
     * @throws Exception when validation, lookup, or persistence fails
     */
    public boolean updateCustomer(CustomerDto dto) throws Exception {
        AuthUtil.requireUser();
        if (dto.getCustomerId() == null || dto.getCustomerId().isBlank()) {
            throw new Exception("Customer ID is required.");
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new Exception("Customer name is required.");
        }
        if (dto.getNicOrPassport() == null || dto.getNicOrPassport().isBlank()) {
            throw new Exception("NIC/Passport is required.");
        }
        if (dto.getContactNo() == null || dto.getContactNo().isBlank()) {
            throw new Exception("Contact number is required.");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new Exception("Email is required.");
        }
        if (dto.getAddress() == null || dto.getAddress().isBlank()) {
            throw new Exception("Address is required.");
        }
        if (dto.getMembershipId() == null || dto.getMembershipId().isBlank()) {
            throw new Exception("Membership is required.");
        }
        MembershipEntity membership = membershipDao.search(dto.getMembershipId());
        if (membership == null) {
            throw new Exception("Membership not found.");
        }
        try {
            return customerDao.update(new CustomerEntity(
                    dto.getCustomerId(),
                    dto.getName(),
                    dto.getNicOrPassport(),
                    dto.getContactNo(),
                    dto.getEmail(),
                    dto.getAddress(),
                    dto.getMembershipId()
            ));
        } catch (SQLException e) {
            if (isDuplicateEntry(e)) {
                throw new Exception("This customer ID already exists. Please use a different customer ID.");
            }
            throw e;
        }
    }

    @Override
    /**
     * Deletes a customer by ID.
     *
     * @param id customer identifier
     * @return true when the customer is deleted successfully
     * @throws Exception when the user is not allowed or the delete fails
     */
    public boolean deleteCustomer(String id) throws Exception {
        AuthUtil.requireUser();
        return customerDao.delete(id);
    }

    @Override
    /**
     * Finds a customer by ID and maps the entity to a DTO.
     *
     * @param id customer identifier
     * @return customer data, or null when not found
     * @throws Exception when the user is not allowed or the query fails
     */
    public CustomerDto findCustomer(String id) throws Exception {
        AuthUtil.requireUser();
        CustomerEntity entity = customerDao.search(id);
        if (entity == null) {
            return null;
        }
        return new CustomerDto(
                entity.getCustomerId(),
                entity.getName(),
                entity.getNicOrPassport(),
                entity.getContactNo(),
                entity.getEmail(),
                entity.getAddress(),
                entity.getMembershipId()
        );
    }

    @Override
    /**
     * Loads all customers and converts them to DTOs.
     *
     * @return customer list for the UI layer
     * @throws Exception when the user is not allowed or the query fails
     */
    public List<CustomerDto> findAllCustomers() throws Exception {
        AuthUtil.requireUser();
        ArrayList<CustomerEntity> entities = customerDao.getAll();
        List<CustomerDto> dtos = new ArrayList<>();
        for (CustomerEntity entity : entities) {
            dtos.add(new CustomerDto(
                    entity.getCustomerId(),
                    entity.getName(),
                    entity.getNicOrPassport(),
                    entity.getContactNo(),
                    entity.getEmail(),
                    entity.getAddress(),
                    entity.getMembershipId()
            ));
        }
        return dtos;
    }

    /*
     * Checks whether a SQL exception is caused by a duplicate entry constraint violation.
     */
    private boolean isDuplicateEntry(SQLException exception) {
        String message = exception.getMessage();
        return exception.getErrorCode() == 1062
                || (exception.getSQLState() != null && exception.getSQLState().startsWith("23"))
                || (message != null && message.toLowerCase().contains("duplicate entry"));
    }

}
