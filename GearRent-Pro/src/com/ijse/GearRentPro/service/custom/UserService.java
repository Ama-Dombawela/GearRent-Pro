/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ijse.GearRentPro.service.custom;

import com.ijse.GearRentPro.dto.UserDto;
import com.ijse.GearRentPro.service.SuperService;
import java.util.List;

/**
 *
 * @author User
 */
public interface UserService extends SuperService{

    boolean saveUser(UserDto dto) throws Exception;

    boolean updateUser(UserDto dto) throws Exception;

    boolean deleteUser(String id) throws Exception;

    UserDto findUser(String id) throws Exception;

    List<UserDto> findAllUsers() throws Exception;
    
    UserDto findUserByUsernameAndPassword(String username, String password)throws Exception;
}
