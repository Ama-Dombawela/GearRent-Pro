/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.dao.Custom;

import com.ijse.GearRentPro.dao.CrudDao;
import com.ijse.GearRentPro.entity.UserEntity;

/**
 *
 * @author User
 */
// DAO interface for user account data persistence operations.
public interface UserDao extends CrudDao<UserEntity, String> {

    // Retrieve user by username and password for authentication
    UserEntity getByUsernameAndPassword(String username, String password) throws Exception;

}
