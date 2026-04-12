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

    boolean saveCustomer(CustomerDto dto) throws Exception;

    boolean updateCustomer(CustomerDto dto) throws Exception;

    boolean deleteCustomer(String id) throws Exception;

    CustomerDto findCustomer(String id) throws Exception;

    List<CustomerDto> findAllCustomers() throws Exception;

}
