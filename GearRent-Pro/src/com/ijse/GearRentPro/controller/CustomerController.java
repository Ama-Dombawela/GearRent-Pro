/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.controller;

import com.ijse.GearRentPro.dto.CustomerDto;
import com.ijse.GearRentPro.service.ServiceFactory;
import com.ijse.GearRentPro.service.custom.CustomerService;
import java.util.List;

/**
 *
 * @author User
 */
public class CustomerController {

    private final CustomerService customerService = (CustomerService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.CUSTOMER);

    public boolean saveCustomer(CustomerDto dto) throws Exception {
        return customerService.saveCustomer(dto);
    }

    public boolean updateCustomer(CustomerDto dto) throws Exception {
        return customerService.updateCustomer(dto);
    }

    public boolean deleteCustomer(String id) throws Exception {
        return customerService.deleteCustomer(id);
    }

    public CustomerDto findCustomer(String id) throws Exception {
        return customerService.findCustomer(id);
    }

    public List<CustomerDto> findAllCustomers() throws Exception {
        return customerService.findAllCustomers();
    }

}
