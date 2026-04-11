/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.entity;

/**
 *
 * @author User
 */
public class UserEntity {
    
    private String userId;
    private String username;
    private String password;
    private String roleId;
    private BranchEntity branch;

    public UserEntity() {
    }

    public UserEntity(String userId, String username, String password, String roleId, BranchEntity branch) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.branch = branch;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the branch
     */
    public BranchEntity getBranch() {
        return branch;
    }

    /**
     * @param branch the branch to set
     */
    public void setBranch(BranchEntity branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "userId=" + userId + ", username=" + username + ", password=" + password + ", roleId=" + roleId + ", branch=" + branch + '}';
    }
    
    
    
}
