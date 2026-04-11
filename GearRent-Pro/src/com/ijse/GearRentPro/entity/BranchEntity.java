/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.entity;

/**
 *
 * @author User
 */
public class BranchEntity {

    private String branchId;
    private String branchCode;
    private String name;
    private String address;
    private String contactNumber;

    public BranchEntity() {
    }

    public BranchEntity(String branchId, String branchCode, String name, String address, String contactNumber) {
        this.branchId = branchId;
        this.branchCode = branchCode;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    /**
     * @return the branchId
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    /**
     * @return the branchCode
     */
    public String getBranchCode() {
        return branchCode;
    }

    /**
     * @param branchCode the branchCode to set
     */
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the contactNumber
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * @param contactNumber the contactNumber to set
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "BranchEntity{" + "branchId=" + branchId + ", branchCode=" + branchCode + ", name=" + name + ", address=" + address + ", contactNumber=" + contactNumber + '}';
    }

}
