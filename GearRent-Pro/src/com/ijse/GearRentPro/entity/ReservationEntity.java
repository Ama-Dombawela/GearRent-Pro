/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.entity;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public class ReservationEntity {

    private String reservationId;
    private EquipmentEntity equipment;
    private CustomerEntity customer;
    private BranchEntity branch;
    private LocalDate startDate;
    private LocalDate endDate;
    private ReservationStatus reservationStatus;

    public ReservationEntity() {
    }

    public ReservationEntity(String reservationId, EquipmentEntity equipment, CustomerEntity customer, BranchEntity branch, LocalDate startDate, LocalDate endDate, ReservationStatus reservationStatus) {
        this.reservationId = reservationId;
        this.equipment = equipment;
        this.customer = customer;
        this.branch = branch;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationStatus = reservationStatus;
    }

    //Enum for ReservationStatus
    public enum ReservationStatus {
        ACTIVE("Active"),
        CANCELLED("Cancelled"),
        CONVERTED("Converted");

        private final String databaseValue;

        ReservationStatus(String databaseValue) {

            this.databaseValue = databaseValue;
        }

        public String getDatabaseValue() {
            return databaseValue;
        }

        public static ReservationStatus fromDbValues(String databaseValue) {
            for (ReservationStatus status : values()) {
                if (status.databaseValue.equals(databaseValue)) {
                    return status;
                }
            }
            throw new IllegalArgumentException("Unknown paymentStatus: " + databaseValue);
        }
    }

    /**
     * @return the reservationId
     */
    public String getReservationId() {
        return reservationId;
    }

    /**
     * @param reservationId the reservationId to set
     */
    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    /**
     * @return the equipment
     */
    public EquipmentEntity getEquipment() {
        return equipment;
    }

    /**
     * @param equipment the equipment to set
     */
    public void setEquipment(EquipmentEntity equipment) {
        this.equipment = equipment;
    }

    /**
     * @return the customer
     */
    public CustomerEntity getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
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
     * @return the reservationStatus
     */
    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    /**
     * @param reservationStatus the reservationStatus to set
     */
    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    @Override
    public String toString() {
        return "ReservationEntity{" + "reservationId=" + reservationId + ", equipment=" + equipment + ", customer=" + customer + ", branch=" + branch + ", startDate=" + startDate + ", endDate=" + endDate + ", reservationStatus=" + reservationStatus + '}';
    }

}
