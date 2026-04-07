/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.entity;

import static com.ijse.GearRentPro.entity.RentalEntity.RentalStatus.values;

/**
 *
 * @author User
 */
public class EquipmentEntity {

    private int equipmentId;
    private CategoryEntity category;
    private BranchEntity branch;
    private String brand;
    private String model;
    private int purchaseYear;
    private double baseDailyPrice;
    private double securityDeposit;
    private Status status;

    public EquipmentEntity() {
    }

    public EquipmentEntity(int equipmentId, CategoryEntity category, BranchEntity branch, String brand, String model, int purchaseYear, double baseDailyPrice, double securityDeposit, Status status) {
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

    //Enum for Status
    public enum Status {
        AVAILABLE("Available"),
        RESERVED("Reserved"),
        RENTED("Rented"),
        UNDER_MAINTENANCE("Under Maintenance");

        private final String databaseValue;

        Status(String databaseValue) {

            this.databaseValue = databaseValue;
        }

        public String getDatabaseValue() {
            return databaseValue;
        }

        public static Status fromDbValues(String databaseValue) {
            for (Status status : values()) {
                if (status.databaseValue.equals(databaseValue)) {
                    return status;
                }
            }
            throw new IllegalArgumentException("Unknown paymentStatus: " + databaseValue);
        }
    }

    /**
     * @return the equipmentId
     */
    public int getEquipmentId() {
        return equipmentId;
    }

    /**
     * @param equipmentId the equipmentId to set
     */
    public void setEquipmentId(int equipmentId) {
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
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EquipmentEntity{" + "equipmentId=" + equipmentId + ", category=" + category + ", branch=" + branch + ", brand=" + brand + ", model=" + model + ", purchaseYear=" + purchaseYear + ", baseDailyPrice=" + baseDailyPrice + ", securityDeposit=" + securityDeposit + ", status=" + status + '}';
    }

}
