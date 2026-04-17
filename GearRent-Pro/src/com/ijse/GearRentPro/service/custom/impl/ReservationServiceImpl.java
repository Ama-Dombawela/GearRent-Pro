/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.service.custom.impl;

import com.ijse.GearRentPro.dao.Custom.ReservationDao;
import com.ijse.GearRentPro.dao.Custom.EquipmentDao;
import com.ijse.GearRentPro.dao.Custom.RentalDao;
import com.ijse.GearRentPro.dao.Custom.CategoryDao;
import com.ijse.GearRentPro.dao.Custom.CustomerDao;
import com.ijse.GearRentPro.dao.Custom.MembershipDao;
import com.ijse.GearRentPro.dao.DaoFactory;
import com.ijse.GearRentPro.db.DBConnection;
import com.ijse.GearRentPro.dto.RentalDto;
import com.ijse.GearRentPro.dto.ReservationDto;
import com.ijse.GearRentPro.entity.CategoryEntity;
import com.ijse.GearRentPro.entity.CustomerEntity;
import com.ijse.GearRentPro.entity.MembershipEntity;
import com.ijse.GearRentPro.entity.RentalEntity;
import com.ijse.GearRentPro.entity.ReservationEntity;
import com.ijse.GearRentPro.entity.EquipmentEntity;
import com.ijse.GearRentPro.service.custom.ReservationService;
import com.ijse.GearRentPro.util.AuthUtil;
import com.ijse.GearRentPro.util.Session;
import java.sql.Connection;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class ReservationServiceImpl implements ReservationService {

    ReservationDao reservationDao = (ReservationDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.RESERVATION);
    EquipmentDao equipmentDao = (EquipmentDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.EQUIPMENT);
    RentalDao rentalDao = (RentalDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.RENTAL);
    CategoryDao categoryDao = (CategoryDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.CATEGORY);
    CustomerDao customerDao = (CustomerDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.CUSTOMER);
    MembershipDao membershipDao = (MembershipDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.MEMBERSHIP);

    @Override
    /**
     * Creates a reservation after validating dates, overlap rules, and deposit
     * limits.
     *
     * @param dto the reservation data
     * @return true if successful
     * @throws Exception if validation or database operation fails
     */
    public boolean saveReservation(ReservationDto dto) throws Exception {

        //required fields
        if (dto.getReservationId() == null || dto.getReservationId().isBlank()) {
            throw new Exception("Reservation ID is required");
        }
        if (dto.getEquipmentId() == null || dto.getEquipmentId().isBlank()) {
            throw new Exception("Equipment ID is required");
        }
        if (dto.getCustomerId() == null || dto.getCustomerId().isBlank()) {
            throw new Exception("Customer ID is required");
        }
        if (dto.getStartDate() == null || dto.getEndDate() == null) {
            throw new Exception("Dates are required");
        }
        if (dto.getReservationStatus() == null || dto.getReservationStatus().isBlank()) {
            dto.setReservationStatus("Active");
        }

        AuthUtil.requireBranchScopedAccess(dto.getBranchId());

        // Max 30 days
        long days = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate());
        if (days <= 0) {
            throw new Exception("End date must be after start date.");
        }
        if (days > 30) {
            throw new Exception("Reservation duration cannot exceed 30 days.");
        }

        Connection connection = DBConnection.getInstance().getConnection();
        boolean oldAutoCommit = connection.getAutoCommit();

        try {
            connection.setAutoCommit(false);

            EquipmentEntity equipment = equipmentDao.searchForUpdate(dto.getEquipmentId());
            if (equipment == null) {
                throw new Exception("Equipment not found.");
            }
            if (!Objects.equals(equipment.getBranchId(), dto.getBranchId())) {
                throw new Exception("Equipment does not belong to the selected branch.");
            }
            if (equipment.getStatus() == EquipmentEntity.Status.RENTED || equipment.getStatus() == EquipmentEntity.Status.UNDER_MAINTENANCE) {
                throw new Exception("Equipment is not available for reservation.");
            }

            double totalHeldDeposit = equipment.getSecurityDeposit();

            List<ReservationEntity> allReservationsForDeposit = reservationDao.getByCustomer(dto.getCustomerId());
            for (ReservationEntity existing : allReservationsForDeposit) {
                if (existing.getReservationStatus() != ReservationEntity.ReservationStatus.CANCELLED
                        && existing.getReservationStatus() != ReservationEntity.ReservationStatus.CONVERTED) {
                    EquipmentEntity existingEquipment = equipmentDao.search(existing.getEquipmentId());
                    if (existingEquipment != null) {
                        totalHeldDeposit += existingEquipment.getSecurityDeposit();
                    }
                }
            }

            double activeRentalDeposits = rentalDao.getTotalActiveDepositByCustomerForUpdate(dto.getCustomerId());
            totalHeldDeposit += activeRentalDeposits;

            if (totalHeldDeposit > 500000) {
                throw new Exception("Customer deposit limit exceeded! Max LKR 500,000.");
            }

            if (rentalDao.hasActiveOverlapForUpdate(dto.getEquipmentId(), dto.getStartDate().toString(), dto.getEndDate().toString())) {
                throw new Exception("Equipment is already rented for the selected dates.");
            }
            if (reservationDao.hasOverlapForUpdate(dto.getEquipmentId(), dto.getStartDate().toString(), dto.getEndDate().toString())) {
                throw new Exception("Equipment is already reserved for the selected dates.");
            }

            boolean saved = reservationDao.save(new ReservationEntity(
                    dto.getReservationId(),
                    dto.getEquipmentId(),
                    dto.getCustomerId(),
                    dto.getBranchId(),
                    dto.getStartDate(),
                    dto.getEndDate(),
                    ReservationEntity.ReservationStatus.fromDbValues(dto.getReservationStatus())
            ));

            if (!saved) {
                throw new Exception("Failed to save reservation.");
            }

            connection.commit();
            return true;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(oldAutoCommit);
        }
    }

    @Override
    /**
     * Updates a reservation after validating date ranges and branch
     * consistency.
     *
     * @param dto the updated reservation data
     * @return true if successful
     * @throws Exception if validation or database operation fails
     */
    public boolean updateReservation(ReservationDto dto) throws Exception {
        if (dto.getReservationId() == null || dto.getReservationId().isBlank()) {
            throw new Exception("Reservation ID is required");
        }
        if (dto.getEquipmentId() == null || dto.getEquipmentId().isBlank()) {
            throw new Exception("Equipment ID is required");
        }
        if (dto.getCustomerId() == null || dto.getCustomerId().isBlank()) {
            throw new Exception("Customer ID is required");
        }
        if (dto.getStartDate() == null || dto.getEndDate() == null) {
            throw new Exception("Dates are required");
        }

        long days = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate());
        if (days <= 0) {
            throw new Exception("End date must be after start date.");
        }
        if (days > 30) {
            throw new Exception("Reservation duration cannot exceed 30 days.");
        }

        ReservationEntity existing = reservationDao.search(dto.getReservationId());
        if (existing == null) {
            throw new Exception("Reservation not found.");
        }
        AuthUtil.requireBranchScopedAccess(existing.getBranchId());
        if (!Objects.equals(existing.getBranchId(), dto.getBranchId())) {
            throw new Exception("Reservation branch cannot be changed.");
        }

        EquipmentEntity equipment = equipmentDao.search(dto.getEquipmentId());
        if (equipment == null) {
            throw new Exception("Equipment not found.");
        }
        if (!Objects.equals(equipment.getBranchId(), dto.getBranchId())) {
            throw new Exception("Equipment does not belong to the selected branch.");
        }
        return reservationDao.update(new ReservationEntity(
                dto.getReservationId(),
                dto.getEquipmentId(),
                dto.getCustomerId(),
                dto.getBranchId(),
                dto.getStartDate(),
                dto.getEndDate(),
                ReservationEntity.ReservationStatus.fromDbValues(dto.getReservationStatus())
        ));
    }

    @Override
    /**
     * Cancels a reservation when it has not already been converted.
     *
     * @param id the reservation ID
     * @return true if successful
     * @throws Exception if the reservation is already converted or database
     * operation fails
     */
    public boolean cancelReservation(String id) throws Exception {
        ReservationEntity existing = reservationDao.search(id);
        if (existing == null) {
            throw new Exception("Reservation not found.");
        }
        AuthUtil.requireBranchScopedAccess(existing.getBranchId());
        if (existing.getReservationStatus() == ReservationEntity.ReservationStatus.CONVERTED) {
            throw new Exception("Converted reservation cannot be cancelled.");
        }
        existing.setReservationStatus(ReservationEntity.ReservationStatus.CANCELLED);
        return reservationDao.update(existing);
    }

    @Override
    /**
     * Converts a reservation into a rental inside a single transaction.
     *
     * @param reservation the reservation to convert
     * @param rental the new rental data
     * @return true if successful
     * @throws Exception if conversion fails and transaction is rolled back
     */
    public boolean convertReservationToRental(ReservationDto reservation, RentalDto rental) throws Exception {
        if (reservation == null || rental == null) {
            throw new Exception("Reservation and rental data are required.");
        }

        AuthUtil.requireBranchScopedAccess(reservation.getBranchId());

        Connection connection = DBConnection.getInstance().getConnection();
        boolean oldAutoCommit = connection.getAutoCommit();

        try {
            connection.setAutoCommit(false);

            ReservationEntity currentReservation = reservationDao.search(reservation.getReservationId());
            if (currentReservation == null) {
                throw new Exception("Reservation not found.");
            }
            if (currentReservation.getReservationStatus() == ReservationEntity.ReservationStatus.CONVERTED) {
                throw new Exception("Reservation is already converted.");
            }
            if (currentReservation.getReservationStatus() == ReservationEntity.ReservationStatus.CANCELLED) {
                throw new Exception("Cancelled reservation cannot be converted.");
            }

            AuthUtil.requireBranchScopedAccess(currentReservation.getBranchId());

            EquipmentEntity equipment = equipmentDao.searchForUpdate(reservation.getEquipmentId());
            if (equipment == null) {
                throw new Exception("Equipment not found.");
            }
            if (!Objects.equals(currentReservation.getBranchId(), rental.getBranchId())) {
                throw new Exception("Rental branch must match the reservation branch.");
            }
            if (!Objects.equals(equipment.getBranchId(), currentReservation.getBranchId())) {
                throw new Exception("Equipment does not belong to the selected branch.");
            }
            if (equipment.getStatus() == EquipmentEntity.Status.RENTED) {
                throw new Exception("Equipment is already rented.");
            }

            calculateRentalPricing(rental, equipment);

            double totalHeldDeposit = rentalDao.getTotalActiveDepositByCustomerForUpdate(rental.getCustomerId());
            List<ReservationEntity> customerReservations = reservationDao.getByCustomer(rental.getCustomerId());
            for (ReservationEntity existingReservation : customerReservations) {
                if (existingReservation.getReservationStatus() != ReservationEntity.ReservationStatus.CANCELLED
                        && existingReservation.getReservationStatus() != ReservationEntity.ReservationStatus.CONVERTED
                        && !Objects.equals(existingReservation.getReservationId(), currentReservation.getReservationId())) {
                    EquipmentEntity reservedEquipment = equipmentDao.search(existingReservation.getEquipmentId());
                    if (reservedEquipment != null) {
                        totalHeldDeposit += reservedEquipment.getSecurityDeposit();
                    }
                }
            }
            if (totalHeldDeposit + rental.getDepositAmount() > 500000) {
                throw new Exception("Customer deposit limit exceeded! Max LKR 500,000.");
            }

            if (rentalDao.hasActiveOverlapForUpdate(rental.getEquipmentId(), rental.getStartDate().toString(), rental.getEndDate().toString())) {
                throw new Exception("Equipment is already rented for the selected dates.");
            }

            String rentalId = generateNextRentalId();
            boolean rentalSaved = rentalDao.save(new RentalEntity(
                    rentalId,
                    rental.getEquipmentId(),
                    rental.getCustomerId(),
                    rental.getBranchId(),
                    rental.getStartDate(),
                    rental.getEndDate(),
                    rental.getActualReturnDate(),
                    rental.getRentalAmount(),
                    rental.getDepositAmount(),
                    rental.getMembershipDiscount(),
                    rental.getLongRentalDiscount(),
                    rental.getFinalAmount(),
                    RentalEntity.PaymentStatus.fromDbValues(rental.getPaymentStatus()),
                    RentalEntity.RentalStatus.fromDbValues(rental.getRentalStatus())
            ));

            if (!rentalSaved) {
                throw new Exception("Failed to save rental.");
            }

            currentReservation.setReservationStatus(ReservationEntity.ReservationStatus.CONVERTED);
            boolean reservationUpdated = reservationDao.update(currentReservation);
            if (!reservationUpdated) {
                throw new Exception("Failed to update reservation status.");
            }

            equipment.setStatus(EquipmentEntity.Status.RENTED);
            boolean equipmentUpdated = equipmentDao.update(equipment);
            if (!equipmentUpdated) {
                throw new Exception("Failed to update equipment status.");
            }

            connection.commit();
            return true;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(oldAutoCommit);
        }
    }

    /**
     * Generates the next sequential rental ID by scanning all existing rentals,
     * extracting numeric portions, finding the maximum, and incrementing by
     * one.
     *
     * @return the next rental ID in the format RNxxx where xxx is a zero-padded
     * number
     * @throws Exception if database query fails
     */
    private String generateNextRentalId() throws Exception {
        ArrayList<RentalEntity> rentals = rentalDao.getAll();
        int maxNumber = 0;

        for (RentalEntity rental : rentals) {
            String rentalId = rental.getRentalId();
            if (rentalId == null) {
                continue;
            }

            String trimmedId = rentalId.trim();
            if (!trimmedId.startsWith("RN")) {
                continue;
            }

            try {
                int numericPart = Integer.parseInt(trimmedId.substring(2));
                if (numericPart > maxNumber) {
                    maxNumber = numericPart;
                }
            } catch (NumberFormatException ignored) {
                // Skip malformed rental IDs and continue scanning existing records.
            }
        }

        return String.format("RN%03d", maxNumber + 1);
    }

    @Override
    /**
     * Deletes a reservation when the current user has access to its branch.
     *
     * @param id the reservation ID
     * @return true if successful
     * @throws Exception if authorization or database operation fails
     */
    public boolean deleteReservation(String id) throws Exception {
        ReservationEntity entity = reservationDao.search(id);
        if (entity != null) {
            AuthUtil.requireBranchScopedAccess(entity.getBranchId());
        }
        return reservationDao.delete(id);
    }

    @Override
    /**
     * Finds a reservation by ID and maps it to a DTO.
     *
     * @param id the reservation ID
     * @return the reservation DTO or null if not found
     * @throws Exception if database operation fails
     */
    public ReservationDto findReservation(String id) throws Exception {
        ReservationEntity entity = reservationDao.search(id);
        if (entity == null) {
            return null;
        }
        AuthUtil.requireBranchScopedAccess(entity.getBranchId());
        return new ReservationDto(
                entity.getReservationId(),
                entity.getEquipmentId(),
                entity.getCustomerId(),
                entity.getBranchId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getReservationStatus().getDatabaseValue()
        );
    }

    @Override
    /**
     * Loads all reservations and filters them by the current branch scope.
     *
     * @return list of reservation DTOs for the current branch
     * @throws Exception if database operation fails
     */
    public List<ReservationDto> findAllReservations() throws Exception {
        ArrayList<ReservationEntity> entities = reservationDao.getAll();
        List<ReservationDto> dtos = new ArrayList<>();
        String branchFilter = Session.getCurrentBranchId();
        for (ReservationEntity entity : entities) {
            if (branchFilter != null && !branchFilter.equals(entity.getBranchId())) {
                continue;
            }
            dtos.add(new ReservationDto(
                    entity.getReservationId(),
                    entity.getEquipmentId(),
                    entity.getCustomerId(),
                    entity.getBranchId(),
                    entity.getStartDate(),
                    entity.getEndDate(),
                    entity.getReservationStatus().getDatabaseValue()
            ));
        }
        return dtos;
    }

    /**
     * Calculates all pricing components for a rental including base amount,
     * discounts, and final settlement value. Looks up the equipment's category
     * to apply price factors and weekend multipliers, retrieves membership data
     * for discounts, and computes rental, membership, and long-rental discounts
     * before setting the final amount.
     *
     * @param rental rental DTO to populate with pricing data
     * @param equipment the equipment entity to calculate pricing for
     * @throws Exception if category or customer lookup fails
     */
    private void calculateRentalPricing(RentalDto rental, EquipmentEntity equipment) throws Exception {
        CategoryEntity category = categoryDao.search(equipment.getCategoryId());
        if (category == null) {
            throw new Exception("Category not found.");
        }

        CustomerEntity customer = customerDao.search(rental.getCustomerId());
        if (customer == null) {
            throw new Exception("Customer not found.");
        }

        long days = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate());
        long weekendDays = 0;
        for (LocalDate d = rental.getStartDate(); d.isBefore(rental.getEndDate()); d = d.plusDays(1)) {
            if (d.getDayOfWeek() == DayOfWeek.SATURDAY || d.getDayOfWeek() == DayOfWeek.SUNDAY) {
                weekendDays++;
            }
        }
        long weekDays = days - weekendDays;

        double rentalAmount = (equipment.getBaseDailyPrice() * category.getPriceFactor() * weekDays)
                + (equipment.getBaseDailyPrice() * category.getPriceFactor() * category.getWeekendMultiplier() * weekendDays);

        MembershipEntity membership = null;
        if (customer.getMembershipId() != null && !customer.getMembershipId().isBlank()) {
            membership = membershipDao.search(customer.getMembershipId());
        }

        double membershipDiscount = membership != null ? rentalAmount * (membership.getDiscountPer() / 100.0) : 0.0;
        double longRentalDiscount = days >= 7 ? rentalAmount * 0.10 : 0.0;
        double finalAmount = rentalAmount - membershipDiscount - longRentalDiscount;

        rental.setRentalAmount(rentalAmount);
        rental.setDepositAmount(equipment.getSecurityDeposit());
        rental.setMembershipDiscount(membershipDiscount);
        rental.setLongRentalDiscount(longRentalDiscount);
        rental.setFinalAmount(finalAmount);
    }

}
