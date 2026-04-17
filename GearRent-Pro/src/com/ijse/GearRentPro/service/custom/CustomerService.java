/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.service.custom;

import com.ijse.GearRentPro.dto.CustomerDto;
import com.ijse.GearRentPro.service.SuperService;
import java.util.List;

/**
 *
 * @author User
 */
public interface CustomerService extends SuperService {

    // Save a new customer
    boolean saveCustomer(CustomerDto dto) throws Exception;

    // Update existing customer
    boolean updateCustomer(CustomerDto dto) throws Exception;

    // Delete customer by ID
    boolean deleteCustomer(String id) throws Exception;

    // Find customer by ID
    CustomerDto findCustomer(String id) throws Exception;

    // Get all customers
    List<CustomerDto> findAllCustomers() throws Exception;

}
