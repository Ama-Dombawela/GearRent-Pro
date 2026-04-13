/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dto;


/**
 *
 * @author User
 */
public class EquipmentDto {

    private String equipmentId;
    private String categoryId;
    private String branchId;
    private String brand;
    private String model;
    private int purchaseYear;
    private double baseDailyPrice;
    private double securityDeposit;
    private String status;

    public EquipmentDto() {
    }

    public EquipmentDto(String equipmentId, String categoryId, String branchId, String brand, String model, int purchaseYear, double baseDailyPrice, double securityDeposit, String status) {
        this.equipmentId = equipmentId;
        this.categoryId = categoryId;
        this.branchId = branchId;
        this.brand = brand;
        this.model = model;
        this.purchaseYear = purchaseYear;
        this.baseDailyPrice = baseDailyPrice;
        this.securityDeposit = securityDeposit;
        this.status = status;
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
     * @return the categoryId
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the purchaseYear
     */
    public int getPurchaseYear() {
        return purchaseYear;
    }

    /**
     * @param purchaseYear the purchaseYear to set
     */
    public void setPurchaseYear(int purchaseYear) {
        this.purchaseYear = purchaseYear;
    }

    /**
     * @return the baseDailyPrice
     */
    public double getBaseDailyPrice() {
        return baseDailyPrice;
    }

    /**
     * @param baseDailyPrice the baseDailyPrice to set
     */
    public void setBaseDailyPrice(double baseDailyPrice) {
        this.baseDailyPrice = baseDailyPrice;
    }

    /**
     * @return the securityDeposit
     */
    public double getSecurityDeposit() {
        return securityDeposit;
    }

    /**
     * @param securityDeposit the securityDeposit to set
     */
    public void setSecurityDeposit(double securityDeposit) {
        this.securityDeposit = securityDeposit;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EquipmentDto{" + "equipmentId=" + equipmentId + ", categoryId=" + categoryId + ", branchId=" + branchId + ", brand=" + brand + ", model=" + model + ", purchaseYear=" + purchaseYear + ", baseDailyPrice=" + baseDailyPrice + ", securityDeposit=" + securityDeposit + ", status=" + status + '}';
    }

}
