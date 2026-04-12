/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dto;

/**
 *
 * @author User
 */
public class DamageDto {

    private String damageId;
    private String rentalId;
    private String description;
    private double damageCharge;

    public DamageDto() {
    }

    public DamageDto(String damageId, String rentalId, String description, double damageCharge) {
        this.damageId = damageId;
        this.rentalId = rentalId;
        this.description = description;
        this.damageCharge = damageCharge;
    }

    /**
     * @return the damageId
     */
    public String getDamageId() {
        return damageId;
    }

    /**
     * @param damageId the damageId to set
     */
    public void setDamageId(String damageId) {
        this.damageId = damageId;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "DamageDto{" + "damageId=" + damageId + ", rentalId=" + rentalId + ", description=" + description + ", damageCharge=" + damageCharge + '}';
    }

}
