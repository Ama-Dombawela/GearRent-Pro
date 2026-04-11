/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dao;

import com.ijse.GearRentPro.dao.Custom.impl.BranchDaoImpl;
import com.ijse.GearRentPro.dao.Custom.impl.CategoryDaoImpl;
import com.ijse.GearRentPro.dao.Custom.impl.CustomerDaoImpl;
import com.ijse.GearRentPro.dao.Custom.impl.EquipmentDaoImpl;
import com.ijse.GearRentPro.dao.Custom.impl.RentalDaoImpl;
import com.ijse.GearRentPro.dao.Custom.impl.ReservationDaoImpl;
import com.ijse.GearRentPro.dao.Custom.impl.UserDaoImpl;

/**
 *
 * @author User
 */
public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    private static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public SuperDao getDao(DaoTypes type) {
        switch (type) {
            case BRANCH:
                return new BranchDaoImpl();
            case CATEGORY:
                return new CategoryDaoImpl();
            case CUTOMER:
                return new CustomerDaoImpl();
            case EQUIPMENT:
                return new EquipmentDaoImpl();
            case RENTAL:
                return new RentalDaoImpl();
            case RESERVATION:
                return new ReservationDaoImpl();
            case USER:
                return new UserDaoImpl();
            default:
                throw new AssertionError();
        }
    }

    public enum DaoTypes {
        BRANCH, CATEGORY, CUTOMER, EQUIPMENT, RENTAL, RESERVATION, USER
    }
}
