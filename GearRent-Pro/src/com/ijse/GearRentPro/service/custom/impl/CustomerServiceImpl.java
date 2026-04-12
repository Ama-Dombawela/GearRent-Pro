/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.service.custom.impl;

import com.ijse.GearRentPro.dao.Custom.CustomerDao;
import com.ijse.GearRentPro.dao.DaoFactory;
import com.ijse.GearRentPro.dto.CustomerDto;
import com.ijse.GearRentPro.entity.CustomerEntity;
import com.ijse.GearRentPro.service.custom.CustomerService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class CustomerServiceImpl implements CustomerService {

    CustomerDao customerDao = (CustomerDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDto dto) throws Exception {
        return customerDao.save(new CustomerEntity(
                dto.getCustomerId(),
                dto.getName(),
                dto.getNicOrPassport(),
                dto.getContactNo(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getMembershipId()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws Exception {
        return customerDao.update(new CustomerEntity(
                dto.getCustomerId(),
                dto.getName(),
                dto.getNicOrPassport(),
                dto.getContactNo(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getMembershipId()
        ));
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return customerDao.delete(id);
    }

    @Override
    public CustomerDto findCustomer(String id) throws Exception {
        CustomerEntity entity = customerDao.search(id);
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
    public List<CustomerDto> findAllCustomers() throws Exception {
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

}
