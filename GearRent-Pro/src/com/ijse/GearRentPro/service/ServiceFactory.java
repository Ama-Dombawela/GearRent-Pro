/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.service;

import com.ijse.GearRentPro.service.custom.impl.BranchServiceImpl;
import com.ijse.GearRentPro.service.custom.impl.CategoryServiceImpl;
import com.ijse.GearRentPro.service.custom.impl.CustomerServiceImpl;
import com.ijse.GearRentPro.service.custom.impl.EquipmentServiceImpl;
import com.ijse.GearRentPro.service.custom.impl.MembershipServiceImpl;
import com.ijse.GearRentPro.service.custom.impl.RentalServiceImpl;
import com.ijse.GearRentPro.service.custom.impl.ReservationServiceImpl;
import com.ijse.GearRentPro.service.custom.impl.ReturnServiceImpl;
import com.ijse.GearRentPro.service.custom.impl.UserServiceImpl;

/**
 *
 * @author User
 */
public class ServiceFactory {

    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }

    public SuperService getService(ServiceType type) {
        switch (type) {
            case BRANCH:
                return new BranchServiceImpl();
            case CATEGORY:
                return new CategoryServiceImpl();
            case CUSTOMER:
                return new CustomerServiceImpl();
            case EQUIPMENT:
                return new EquipmentServiceImpl();
            case RENTAL:
                return new RentalServiceImpl();
            case RESERVATION:
                return new ReservationServiceImpl();
            case USER:
                return new UserServiceImpl();
            case RETURN:
                return new ReturnServiceImpl();
            case MEMBERSHIP:
                return new MembershipServiceImpl();
            default:
                throw new AssertionError();
        }
    }

    public enum ServiceType {
        BRANCH, CATEGORY, CUSTOMER, EQUIPMENT, RENTAL, RESERVATION, USER, RETURN, MEMBERSHIP
    }

}
