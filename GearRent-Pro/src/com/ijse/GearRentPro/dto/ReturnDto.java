/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dto;

/**
 *
 * @author User
 */
public class ReturnDto {

    private String rentalId;
    private String actualReturnDate;
    private String damagedDescription;
    private double damageCharge;
    private double latefee;
    private double totalCharges;
    private double refundAmount;

    public ReturnDto() {
    }

    public ReturnDto(String rentalId, String actualReturnDate, String damagedDescription, double damageCharge, double latefee, double totalCharges, double refundAmount) {
        this.rentalId = rentalId;
        this.actualReturnDate = actualReturnDate;
        this.damagedDescription = damagedDescription;
        this.damageCharge = damageCharge;
        this.latefee = latefee;
        this.totalCharges = totalCharges;
        this.refundAmount = refundAmount;
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
     * @return the actualReturnDate
     */
    public String getActualReturnDate() {
        return actualReturnDate;
    }

    /**
     * @param actualReturnDate the actualReturnDate to set
     */
    public void setActualReturnDate(String actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    /**
     * @return the damagedDescription
     */
    public String getDamagedDescription() {
        return damagedDescription;
    }

    /**
     * @param damagedDescription the damagedDescription to set
     */
    public void setDamagedDescription(String damagedDescription) {
        this.damagedDescription = damagedDescription;
    }

    /**
     * @return the damageCharge
     */
    public double getDamageCharge() {
        return damageCharge;
    }

    /**
     * @param damageCharge the damageCharge to set
     */
    public void setDamageCharge(double damageCharge) {
        this.damageCharge = damageCharge;
    }

    /**
     * @return the latefee
     */
    public double getLatefee() {
        return latefee;
    }

    /**
     * @param latefee the latefee to set
     */
    public void setLatefee(double latefee) {
        this.latefee = latefee;
    }

    /**
     * @return the totalCharges
     */
    public double getTotalCharges() {
        return totalCharges;
    }

    /**
     * @param totalCharges the totalCharges to set
     */
    public void setTotalCharges(double totalCharges) {
        this.totalCharges = totalCharges;
    }

    /**
     * @return the refundAmount
     */
    public double getRefundAmount() {
        return refundAmount;
    }

    /**
     * @param refundAmount the refundAmount to set
     */
    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    @Override
    public String toString() {
        return "ReturnDto{" + "rentalId=" + rentalId + ", actualReturnDate=" + actualReturnDate + ", damagedDescription=" + damagedDescription + ", damageCharge=" + damageCharge + ", latefee=" + latefee + ", totalCharges=" + totalCharges + ", refundAmount=" + refundAmount + '}';
    }

}
