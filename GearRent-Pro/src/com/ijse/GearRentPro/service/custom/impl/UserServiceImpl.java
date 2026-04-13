/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.service.custom.impl;

import com.ijse.GearRentPro.dao.Custom.UserDao;
import com.ijse.GearRentPro.dao.DaoFactory;
import com.ijse.GearRentPro.dto.UserDto;
import com.ijse.GearRentPro.entity.UserEntity;
import com.ijse.GearRentPro.service.custom.UserService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class UserServiceImpl implements UserService {

    UserDao userDao = (UserDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.USER);

    @Override
    public boolean saveUser(UserDto dto) throws Exception {
        return userDao.save(new UserEntity(
                dto.getUserId(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getRoleId(),
                dto.getBranchId()
        ));
    }

    @Override
    public boolean updateUser(UserDto dto) throws Exception {
        return userDao.update(new UserEntity(
                dto.getUserId(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getRoleId(),
                dto.getBranchId()
        ));
    }

    @Override
    public boolean deleteUser(String id) throws Exception {
        return userDao.delete(id);
    }

    @Override
    public UserDto findUser(String id) throws Exception {
        UserEntity entity = userDao.search(id);
        return new UserDto(
                entity.getUserId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getRoleId(),
                entity.getBranchId()
        );
    }

    @Override
    public List<UserDto> findAllUsers() throws Exception {
        ArrayList<UserEntity> entities = userDao.getAll();
        List<UserDto> dtos = new ArrayList<>();
        for (UserEntity entity : entities) {
            dtos.add(new UserDto(
                    entity.getUserId(),
                    entity.getUsername(),
                    entity.getPassword(),
                    entity.getRoleId(),
                    entity.getBranchId()
            ));
        }
        return dtos;
    }

    @Override
    public UserDto findUserByUsernameAndPassword(String username, String password) throws Exception {
        UserEntity entity = userDao.getByUsernameAndPassword(username, password);
        if (entity == null) {
            return null;
        }
        return new UserDto(
                entity.getUserId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getRoleId(),
                entity.getBranchId()
        );
    }

}
