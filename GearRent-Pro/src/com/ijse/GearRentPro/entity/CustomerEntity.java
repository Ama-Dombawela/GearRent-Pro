/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.entity;

/**
 *
 * @author User
 */
public class CustomerEntity {

    private String customerId;
    private String name;
    private String nicOrPassport;
    private String contactNo;
    private String email;
    private String address;
    private String membershipId;

    public CustomerEntity() {
    }

    public CustomerEntity(String customerId, String name, String nicOrPassport, String contactNo, String email, String address, String membershipId) {
        this.customerId = customerId;
        this.name = name;
        this.nicOrPassport = nicOrPassport;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
        this.membershipId = membershipId;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
     * @return the nicOrPassport
     */
    public String getNicOrPassport() {
        return nicOrPassport;
    }

    /**
     * @param nicOrPassport the nicOrPassport to set
     */
    public void setNicOrPassport(String nicOrPassport) {
        this.nicOrPassport = nicOrPassport;
    }

    /**
     * @return the contactNo
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * @param contactNo the contactNo to set
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return the membershipId
     */
    public String getMembershipId() {
        return membershipId;
    }

    /**
     * @param membershipId the membershipId to set
     */
    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    @Override
    public String toString() {
        return "CustomerEntity{" + "customerId=" + customerId + ", name=" + name + ", nicOrPassport=" + nicOrPassport + ", contactNo=" + contactNo + ", email=" + email + ", address=" + address + ", membershipId=" + membershipId + '}';
    }

}
