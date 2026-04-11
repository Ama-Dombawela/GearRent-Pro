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

    private String cutomerId;
    private String name;
    private String nicOrPassport;
    private String contactNo;
    private String email;
    private String address;
    private int membershipId;

    public CustomerEntity() {
    }

    public CustomerEntity(String cutomerId, String name, String nicOrPassport, String contactNo, String email, String address, int membershipId) {
        this.cutomerId = cutomerId;
        this.name = name;
        this.nicOrPassport = nicOrPassport;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
        this.membershipId = membershipId;
    }

    /**
     * @return the cutomerId
     */
    public String getCutomerId() {
        return cutomerId;
    }

    /**
     * @param cutomerId the cutomerId to set
     */
    public void setCutomerId(String cutomerId) {
        this.cutomerId = cutomerId;
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
    public int getMembershipId() {
        return membershipId;
    }

    /**
     * @param membershipId the membershipId to set
     */
    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    @Override
    public String toString() {
        return "CustomerEntity{" + "cutomerId=" + cutomerId + ", name=" + name + ", nicOrPassport=" + nicOrPassport + ", contactNo=" + contactNo + ", email=" + email + ", address=" + address + ", membershipId=" + membershipId + '}';
    }

}
