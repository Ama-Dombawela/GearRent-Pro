/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dto;

import com.ijse.GearRentPro.entity.BranchEntity;
import com.ijse.GearRentPro.entity.CategoryEntity;
import com.ijse.GearRentPro.entity.EquipmentEntity;

/**
 *
 * @author User
 */
public class EquipmentDto {

    private String equipmentId;
    private CategoryEntity category;
    private BranchEntity branch;
    private String brand;
    private String model;
    private int purchaseYear;
    private double baseDailyPrice;
    private double securityDeposit;
    private EquipmentEntity.Status status;

    public EquipmentDto() {
    }

    public EquipmentDto(String equipmentId, CategoryEntity category, BranchEntity branch, String brand, String model, int purchaseYear, double baseDailyPrice, double securityDeposit, EquipmentEntity.Status status) {
        this.equipmentId = equipmentId;
        this.category = category;
        this.branch = branch;
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
     * @return the category
     */
    public CategoryEntity getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(CategoryEntity category) {
        this.category = category;
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
    public EquipmentEntity.Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(EquipmentEntity.Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EquipmentDto{" + "equipmentId=" + equipmentId + ", category=" + category + ", branch=" + branch + ", brand=" + brand + ", model=" + model + ", purchaseYear=" + purchaseYear + ", baseDailyPrice=" + baseDailyPrice + ", securityDeposit=" + securityDeposit + ", status=" + status + '}';
    }

}
