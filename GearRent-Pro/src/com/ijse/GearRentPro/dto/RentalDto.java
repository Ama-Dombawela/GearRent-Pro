/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dto;


import java.time.LocalDate;

/**
 *
 * @author User
 */
public class RentalDto {

    private String rentalId;
    private String equipmentId;
    private String customerId;
    private String branchId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualReturnDate;
    private double rentalAmount;
    private double depositAmount;
    private double membershipDiscount;
    private double longRentalDiscount;
    private double finalAmount;
    private String paymentStatus;
    private String rentalStatus;

    public RentalDto() {
    }

    public RentalDto(String rentalId, String equipmentId, String customerId, String branchId, LocalDate startDate, LocalDate endDate, LocalDate actualReturnDate, double rentalAmount, double depositAmount, double membershipDiscount, double longRentalDiscount, double finalAmount, String paymentStatus, String rentalStatus) {
        this.rentalId = rentalId;
        this.equipmentId = equipmentId;
        this.customerId = customerId;
        this.branchId = branchId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.actualReturnDate = actualReturnDate;
        this.rentalAmount = rentalAmount;
        this.depositAmount = depositAmount;
        this.membershipDiscount = membershipDiscount;
        this.longRentalDiscount = longRentalDiscount;
        this.finalAmount = finalAmount;
        this.paymentStatus = paymentStatus;
        this.rentalStatus = rentalStatus;
    }

    /**
     * @return the rentalId
     */
    public String getRentalId() {
        return rentalId;
    }

    /**
     * @param rentalId the rentalId to set
     */
    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    /**
     * @return the equipmentId
     */
    public String getEquipmentId() {
        return equipmentId;
    }

    /**
     * @param equipmentId the equipmentId to set
     */
    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
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
     * @return the startDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the actualReturnDate
     */
    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    /**
     * @param actualReturnDate the actualReturnDate to set
     */
    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    /**
     * @return the rentalAmount
     */
    public double getRentalAmount() {
        return rentalAmount;
    }

    /**
     * @param rentalAmount the rentalAmount to set
     */
    public void setRentalAmount(double rentalAmount) {
        this.rentalAmount = rentalAmount;
    }

    /**
     * @return the depositAmount
     */
    public double getDepositAmount() {
        return depositAmount;
    }

    /**
     * @param depositAmount the depositAmount to set
     */
    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    /**
     * @return the membershipDiscount
     */
    public double getMembershipDiscount() {
        return membershipDiscount;
    }

    /**
     * @param membershipDiscount the membershipDiscount to set
     */
    public void setMembershipDiscount(double membershipDiscount) {
        this.membershipDiscount = membershipDiscount;
    }

    /**
     * @return the longRentalDiscount
     */
    public double getLongRentalDiscount() {
        return longRentalDiscount;
    }

    /**
     * @param longRentalDiscount the longRentalDiscount to set
     */
    public void setLongRentalDiscount(double longRentalDiscount) {
        this.longRentalDiscount = longRentalDiscount;
    }

    /**
     * @return the finalAmount
     */
    public double getFinalAmount() {
        return finalAmount;
    }

    /**
     * @param finalAmount the finalAmount to set
     */
    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    /**
     * @return the paymentStatus
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * @param paymentStatus the paymentStatus to set
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * @return the rentalStatus
     */
    public String getRentalStatus() {
        return rentalStatus;
    }

    /**
     * @param rentalStatus the rentalStatus to set
     */
    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    @Override
    public String toString() {
        return "RentalDto{" + "rentalId=" + rentalId + ", equipmentId=" + equipmentId + ", customerId=" + customerId + ", branchId=" + branchId + ", startDate=" + startDate + ", endDate=" + endDate + ", actualReturnDate=" + actualReturnDate + ", rentalAmount=" + rentalAmount + ", depositAmount=" + depositAmount + ", membershipDiscount=" + membershipDiscount + ", longRentalDiscount=" + longRentalDiscount + ", finalAmount=" + finalAmount + ", paymentStatus=" + paymentStatus + ", rentalStatus=" + rentalStatus + '}';
    }

}
