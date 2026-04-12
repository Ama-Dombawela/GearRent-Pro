/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.controller;

import com.ijse.GearRentPro.dto.UserDto;
import com.ijse.GearRentPro.service.ServiceFactory;
import com.ijse.GearRentPro.service.custom.UserService;
import java.util.List;

/**
 *
 * @author User
 */
public class UserController {

    private final UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.USER);

    public boolean saveUser(UserDto dto) throws Exception {
        return userService.saveUser(dto);
    }

    public boolean updateUser(UserDto dto) throws Exception {
        return userService.updateUser(dto);
    }

    public boolean deleteUser(String id) throws Exception {
        return userService.deleteUser(id);
    }

    public UserDto findUser(String id) throws Exception {
        return userService.findUser(id);
    }

    public List<UserDto> findAllUsers() throws Exception {
        return userService.findAllUsers();
    }
}
