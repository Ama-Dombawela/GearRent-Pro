/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.dto;

/**
 *
 * @author User
 */
public class CategoryDto {

    private String categoryId;
    private String name;
    private String description;
    private double priceFactor;
    private double weekendMutiplier;
    private double lateFeePerDay;
    private boolean isActive;

    public CategoryDto() {
    }

    public CategoryDto(String categoryId, String name, String description, double priceFactor, double weekendMutiplier, double lateFeePerDay, boolean isActive) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.priceFactor = priceFactor;
        this.weekendMutiplier = weekendMutiplier;
        this.lateFeePerDay = lateFeePerDay;
        this.isActive = isActive;
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
     * @return the priceFactor
     */
    public double getPriceFactor() {
        return priceFactor;
    }

    /**
     * @param priceFactor the priceFactor to set
     */
    public void setPriceFactor(double priceFactor) {
        this.priceFactor = priceFactor;
    }

    /**
     * @return the weekendMutiplier
     */
    public double getWeekendMutiplier() {
        return weekendMutiplier;
    }

    /**
     * @param weekendMutiplier the weekendMutiplier to set
     */
    public void setWeekendMutiplier(double weekendMutiplier) {
        this.weekendMutiplier = weekendMutiplier;
    }

    /**
     * @return the lateFeePerDay
     */
    public double getLateFeePerDay() {
        return lateFeePerDay;
    }

    /**
     * @param lateFeePerDay the lateFeePerDay to set
     */
    public void setLateFeePerDay(double lateFeePerDay) {
        this.lateFeePerDay = lateFeePerDay;
    }

    /**
     * @return the isActive
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "CategoryDto{" + "categoryId=" + categoryId + ", name=" + name + ", description=" + description + ", priceFactor=" + priceFactor + ", weekendMutiplier=" + weekendMutiplier + ", lateFeePerDay=" + lateFeePerDay + ", isActive=" + isActive + '}';
    }

}
