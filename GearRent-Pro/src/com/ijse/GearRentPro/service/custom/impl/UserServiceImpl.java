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
import com.ijse.GearRentPro.util.AuthUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class UserServiceImpl implements UserService {

    UserDao userDao = (UserDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.USER);

    /**
     * Creates a new user account after validating credentials and role
     * assignment.
     *
     * @param dto user data from the UI
     * @return true when the user is saved successfully
     * @throws Exception when validation, authorization, or persistence fails
     */
    @Override
    public boolean saveUser(UserDto dto) throws Exception {
        AuthUtil.requireAdmin();
        validateUser(dto);
        String branchId = normalizeBranchId(dto.getRoleId(), dto.getBranchId());
        try {
            return userDao.save(new UserEntity(
                    dto.getUserId().trim(),
                    dto.getUsername().trim(),
                    dto.getPassword().trim(),
                    dto.getRoleId().trim(),
                    branchId
            ));
        } catch (SQLException e) {
            if (isDuplicateEntry(e)) {
                throw new Exception("This user ID or username already exists. Please choose a different user record.");
            }
            throw e;
        }
    }

    /**
     * Updates a user account after validating credentials and role assignment.
     *
     * @param dto user data from the UI
     * @return true when the user is updated successfully
     * @throws Exception when validation, authorization, or persistence fails
     */
    @Override
    public boolean updateUser(UserDto dto) throws Exception {
        AuthUtil.requireAdmin();
        validateUser(dto);
        String branchId = normalizeBranchId(dto.getRoleId(), dto.getBranchId());
        try {
            return userDao.update(new UserEntity(
                    dto.getUserId().trim(),
                    dto.getUsername().trim(),
                    dto.getPassword().trim(),
                    dto.getRoleId().trim(),
                    branchId
            ));
        } catch (SQLException e) {
            if (isDuplicateEntry(e)) {
                throw new Exception("This user ID or username already exists. Please choose a different user record.");
            }
            throw e;
        }
    }

    /**
     * Deletes a user by ID.
     *
     * @param id user identifier
     * @return true when the user is deleted successfully
     * @throws Exception when authorization or persistence fails
     */
    @Override
    public boolean deleteUser(String id) throws Exception {
        AuthUtil.requireAdmin();
        return userDao.delete(id);
    }

    /**
     * Finds a user by ID and maps it to a DTO.
     *
     * @param id user identifier
     * @return user data, or null when not found
     * @throws Exception when authorization or query execution fails
     */
    @Override
    public UserDto findUser(String id) throws Exception {
        AuthUtil.requireAdmin();
        UserEntity entity = userDao.search(id);
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

    /**
     * Loads all users and converts them to DTOs.
     *
     * @return user list for the UI layer
     * @throws Exception when authorization or query execution fails
     */
    @Override
    public List<UserDto> findAllUsers() throws Exception {
        AuthUtil.requireAdmin();
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

    /**
     * Authenticates a user using username and password.
     *
     * @param username login username
     * @param password login password
     * @return matching user data, or null when credentials are invalid
     * @throws Exception when query execution fails
     */
    @Override
    public UserDto findUserByUsernameAndPassword(String username, String password) throws Exception {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return null;
        }
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

    /**
     * Validates user DTO for required fields, valid role assignment, and branch
     * scope requirements. Admin users (R001) do not require a branch
     * assignment, while other roles must have one.
     *
     * @param dto the user data to validate
     * @throws Exception if any required field is missing, role is invalid, or
     * branch requirement is not met
     */
    private void validateUser(UserDto dto) throws Exception {
        if (dto == null) {
            throw new Exception("User data is required.");
        }
        if (dto.getUserId() == null || dto.getUserId().isBlank()) {
            throw new Exception("User ID is required.");
        }
        if (dto.getUsername() == null || dto.getUsername().isBlank()) {
            throw new Exception("Username is required.");
        }
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new Exception("Password is required.");
        }
        if (dto.getRoleId() == null || dto.getRoleId().isBlank()) {
            throw new Exception("Role ID is required.");
        }
        if (!"R001".equals(dto.getRoleId().trim()) && !"R002".equals(dto.getRoleId().trim()) && !"R003".equals(dto.getRoleId().trim())) {
            throw new Exception("Invalid role ID.");
        }
        if (!"R001".equals(dto.getRoleId().trim()) && (dto.getBranchId() == null || dto.getBranchId().isBlank())) {
            throw new Exception("Branch ID is required for non-admin users.");
        }
    }

    /**
     * Normalizes branch ID assignment based on role: admin users (R001) may
     * have null branch, while other roles retain the assigned branch ID with
     * whitespace trimming.
     *
     * @param roleId the user's role identifier
     * @param branchId the assigned branch ID
     * @return the normalized branch ID, or null for admin users
     */
    private String normalizeBranchId(String roleId, String branchId) {
        if (roleId != null && "R001".equals(roleId.trim())) {
            return (branchId == null || branchId.isBlank()) ? null : branchId.trim();
        }
        return branchId == null ? null : branchId.trim();
    }

    /*
     * Checks whether a SQL exception is caused by a duplicate entry constraint violation.
     */
    private boolean isDuplicateEntry(SQLException exception) {
        String message = exception.getMessage();
        return exception.getErrorCode() == 1062
                || (exception.getSQLState() != null && exception.getSQLState().startsWith("23"))
                || (message != null && message.toLowerCase().contains("duplicate entry"));
    }

}
