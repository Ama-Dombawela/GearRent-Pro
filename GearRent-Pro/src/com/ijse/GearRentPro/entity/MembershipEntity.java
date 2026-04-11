/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.entity;

/**
 *
 * @author User
 */
public class MembershipEntity {

    private String membershipId;
    private String level;
    private double discountPer;

    public MembershipEntity() {
    }

    public MembershipEntity(String membershipId, String level, double discountPer) {
        this.membershipId = membershipId;
        this.level = level;
        this.discountPer = discountPer;
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

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the discountPer
     */
    public double getDiscountPer() {
        return discountPer;
    }

    /**
     * @param discountPer the discountPer to set
     */
    public void setDiscountPer(double discountPer) {
        this.discountPer = discountPer;
    }

    @Override
    public String toString() {
        return "MembershipEntity{" + "membershipId=" + membershipId + ", level=" + level + ", discountPer=" + discountPer + '}';
    }

}
