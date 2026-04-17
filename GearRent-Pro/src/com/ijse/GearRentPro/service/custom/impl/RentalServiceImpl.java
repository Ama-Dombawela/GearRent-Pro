/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ijse.GearRentPro.service.custom.impl;

import com.ijse.GearRentPro.dao.Custom.DamageDao;
import com.ijse.GearRentPro.dao.Custom.EquipmentDao;
import com.ijse.GearRentPro.dao.Custom.RentalDao;
import com.ijse.GearRentPro.dao.Custom.ReservationDao;
import com.ijse.GearRentPro.dao.Custom.CategoryDao;
import com.ijse.GearRentPro.dao.Custom.CustomerDao;
import com.ijse.GearRentPro.dao.Custom.MembershipDao;
import com.ijse.GearRentPro.dao.CrudUtil;
import com.ijse.GearRentPro.dao.DaoFactory;
import com.ijse.GearRentPro.db.DBConnection;
import com.ijse.GearRentPro.dto.RentalDto;
import com.ijse.GearRentPro.entity.CategoryEntity;
import com.ijse.GearRentPro.entity.CustomerEntity;
import com.ijse.GearRentPro.entity.DamageEntity;
import com.ijse.GearRentPro.entity.EquipmentEntity;
import com.ijse.GearRentPro.entity.MembershipEntity;
import com.ijse.GearRentPro.entity.RentalEntity;
import com.ijse.GearRentPro.entity.ReservationEntity;
import com.ijse.GearRentPro.service.custom.RentalService;
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
public class RentalServiceImpl implements RentalService {

    RentalDao rentalDao = (RentalDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.RENTAL);
    EquipmentDao equipmentDao = (EquipmentDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.EQUIPMENT);
    DamageDao damageDao = (DamageDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.DAMAGE);
    ReservationDao reservationDao = (ReservationDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.RESERVATION);
    CategoryDao categoryDao = (CategoryDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.CATEGORY);
    CustomerDao customerDao = (CustomerDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.CUSTOMER);
    MembershipDao membershipDao = (MembershipDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.MEMBERSHIP);

    @Override
    public boolean saveRental(RentalDto dto) throws Exception {

        if (dto.getRentalId() == null || dto.getRentalId().isBlank()) {
            throw new Exception("Rental ID is required.");
        }
        if (dto.getEquipmentId() == null || dto.getEquipmentId().isBlank()) {
            throw new Exception("Equipment ID is required.");
        }
        if (dto.getCustomerId() == null || dto.getCustomerId().isBlank()) {
            throw new Exception("Customer ID is required.");
        }
        if (dto.getBranchId() == null || dto.getBranchId().isBlank()) {
            throw new Exception("Branch ID is required.");
        }
        if (dto.getStartDate() == null || dto.getEndDate() == null) {
            throw new Exception("Dates are required.");
        }
        if (dto.getPaymentStatus() == null || dto.getPaymentStatus().isBlank()) {
            throw new Exception("Payment status is required.");
        }
        if (dto.getRentalStatus() == null || dto.getRentalStatus().isBlank()) {
            dto.setRentalStatus("Active");
        }

        AuthUtil.requireBranchScopedAccess(dto.getBranchId());

        long days = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate());
        if (days <= 0) {
            throw new Exception("End date must be after start date.");
        }
        if (days > 30) {
            throw new Exception("Rental duration cannot exceed 30 days.");
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
                throw new Exception("Equipment is not available for rental.");
            }

            // Recompute pricing in service layer to prevent client-side tampering.
            calculateRentalPricing(dto, equipment);

            // Check deposit limit across rentals and reserved bookings.
            double totalHeldDeposits = rentalDao.getTotalActiveDepositByCustomerForUpdate(dto.getCustomerId());
            List<ReservationEntity> customerReservations = reservationDao.getByCustomer(dto.getCustomerId());
            for (ReservationEntity reservation : customerReservations) {
                if (reservation.getReservationStatus() != ReservationEntity.ReservationStatus.CANCELLED
                        && reservation.getReservationStatus() != ReservationEntity.ReservationStatus.CONVERTED) {
                    EquipmentEntity reservedEquipment = equipmentDao.search(reservation.getEquipmentId());
                    if (reservedEquipment != null) {
                        totalHeldDeposits += reservedEquipment.getSecurityDeposit();
                    }
                }
            }
            if (totalHeldDeposits + dto.getDepositAmount() > 500000) {
                throw new Exception("Customer deposit limit exceeded! Max LKR 500,000.");
            }

            if (rentalDao.hasActiveOverlapForUpdate(dto.getEquipmentId(), dto.getStartDate().toString(), dto.getEndDate().toString())) {
                throw new Exception("Equipment is already rented for the selected dates.");
            }

            if (reservationDao.hasOverlapForUpdate(dto.getEquipmentId(), dto.getStartDate().toString(), dto.getEndDate().toString())) {
                throw new Exception("Equipment is already reserved for the selected dates.");
            }

            RentalEntity existingRental = rentalDao.search(dto.getRentalId());
            if (existingRental != null) {
                throw new Exception("A rental with this ID already exists. Please use a different ID.");
            }

            boolean saved = rentalDao.save(new RentalEntity(
                    dto.getRentalId(),
                    dto.getEquipmentId(),
                    dto.getCustomerId(),
                    dto.getBranchId(),
                    dto.getStartDate(),
                    dto.getEndDate(),
                    dto.getActualReturnDate(),
                    dto.getRentalAmount(),
                    dto.getDepositAmount(),
                    dto.getMembershipDiscount(),
                    dto.getLongRentalDiscount(),
                    dto.getFinalAmount(),
                    RentalEntity.PaymentStatus.fromDbValues(dto.getPaymentStatus()),
                    RentalEntity.RentalStatus.fromDbValues(dto.getRentalStatus())
            ));

            if (!saved) {
                throw new Exception("Failed to save rental.");
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

    @Override
    public boolean updateRental(RentalDto dto) throws Exception {
        AuthUtil.requireBranchScopedAccess(dto.getBranchId());

        if (dto.getRentalId() == null || dto.getRentalId().isBlank()) {
            throw new Exception("Rental ID is required.");
        }
        if (dto.getEquipmentId() == null || dto.getEquipmentId().isBlank()) {
            throw new Exception("Equipment ID is required.");
        }
        if (dto.getCustomerId() == null || dto.getCustomerId().isBlank()) {
            throw new Exception("Customer ID is required.");
        }
        if (dto.getStartDate() == null || dto.getEndDate() == null) {
            throw new Exception("Dates are required.");
        }

        long days = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate());
        if (days <= 0) {
            throw new Exception("End date must be after start date.");
        }
        if (days > 30) {
            throw new Exception("Rental duration cannot exceed 30 days.");
        }

        EquipmentEntity equipment = equipmentDao.search(dto.getEquipmentId());
        if (equipment == null) {
            throw new Exception("Equipment not found.");
        }
            if (!Objects.equals(equipment.getBranchId(), dto.getBranchId())) {
            throw new Exception("Equipment does not belong to the selected branch.");
        }
        calculateRentalPricing(dto, equipment);

        return rentalDao.update(new RentalEntity(
                dto.getRentalId(),
                dto.getEquipmentId(),
                dto.getCustomerId(),
                dto.getBranchId(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getActualReturnDate(),
                dto.getRentalAmount(),
                dto.getDepositAmount(),
                dto.getMembershipDiscount(),
                dto.getLongRentalDiscount(),
                dto.getFinalAmount(),
                RentalEntity.PaymentStatus.fromDbValues(dto.getPaymentStatus()),
                RentalEntity.RentalStatus.fromDbValues(dto.getRentalStatus())
        ));

    }

    @Override
    public boolean deleteRental(String id) throws Exception {
        RentalEntity entity = rentalDao.search(id);
        if (entity != null) {
            AuthUtil.requireBranchScopedAccess(entity.getBranchId());
        }
        return rentalDao.delete(id);
    }

    @Override
    public RentalDto findRental(String id) throws Exception {
        RentalEntity entity = rentalDao.search(id);
        if (entity == null) {
            return null;
        }
        AuthUtil.requireBranchScopedAccess(entity.getBranchId());
        return new RentalDto(
                entity.getRentalId(),
                entity.getEquipmentId(),
                entity.getCustomerId(),
                entity.getBranchId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getActualReturnDate(),
                entity.getRentalAmount(),
                entity.getDepositAmount(),
                entity.getMembershipDiscount(),
                entity.getLongRentalDiscount(),
                entity.getFinalAmount(),
                entity.getPaymentStatus().getDatabaseValue(),
                entity.getRentalStatus().getDatabaseValue()
        );
    }

    @Override
    public boolean processReturn(String rentalId, LocalDate actualReturnDate, String damageId, String damageDescription, double damageCharge) throws Exception {
        if (rentalId == null || rentalId.isBlank()) {
            throw new Exception("Rental ID is required.");
        }
        if (actualReturnDate == null) {
            throw new Exception("Actual return date is required.");
        }
        if (damageCharge < 0) {
            throw new Exception("Damage charge cannot be negative.");
        }

        Connection connection = DBConnection.getInstance().getConnection();
        boolean oldAutoCommit = connection.getAutoCommit();

        try {
            connection.setAutoCommit(false);

            RentalEntity rental = rentalDao.searchForUpdate(rentalId);
            if (rental == null) {
                throw new Exception("Rental not found.");
            }
            AuthUtil.requireBranchScopedAccess(rental.getBranchId());
            if (rental.getRentalStatus() == RentalEntity.RentalStatus.RETURNED || rental.getRentalStatus() == RentalEntity.RentalStatus.CANCELLED) {
                throw new Exception("Rental is already closed.");
            }

            EquipmentEntity equipment = equipmentDao.searchForUpdate(rental.getEquipmentId());
            if (equipment == null) {
                throw new Exception("Equipment not found.");
            }
            if (!Objects.equals(equipment.getBranchId(), rental.getBranchId())) {
                throw new Exception("Equipment does not belong to the selected branch.");
            }

            boolean hasDamage = damageDescription != null && !damageDescription.isBlank();
            if (hasDamage && (damageId == null || damageId.isBlank())) {
                throw new Exception("Damage ID is required when damage is recorded.");
            }

            CategoryEntity category = categoryDao.search(equipment.getCategoryId());
            if (category == null) {
                throw new Exception("Category not found for returned equipment.");
            }

            long overdueDays = actualReturnDate.isAfter(rental.getEndDate())
                    ? ChronoUnit.DAYS.between(rental.getEndDate(), actualReturnDate)
                    : 0;
            double lateFee = overdueDays * category.getLateFeePerDay();
            double totalCharges = lateFee + damageCharge;
            double settlement = rental.getDepositAmount() - totalCharges;

            if (hasDamage) {
                boolean damageSaved = damageDao.save(new DamageEntity(
                        damageId,
                        rentalId,
                        damageDescription,
                        damageCharge
                ));
                if (!damageSaved) {
                    throw new Exception("Failed to save damage details.");
                }
            }

            rental.setActualReturnDate(actualReturnDate);
            rental.setRentalStatus(RentalEntity.RentalStatus.RETURNED);
            rental.setFinalAmount(rental.getFinalAmount() + totalCharges);
            rental.setPaymentStatus(settlement < 0
                    ? RentalEntity.PaymentStatus.PARTIALLY_PAID
                    : RentalEntity.PaymentStatus.PAID);
            boolean rentalUpdated = rentalDao.update(rental);
            if (!rentalUpdated) {
                throw new Exception("Failed to update rental return details.");
            }

                String returnId = rentalId != null && rentalId.startsWith("RN")
                    ? "RT" + rentalId.substring(2)
                    : "RT" + rentalId;
                boolean settlementSaved = CrudUtil.executeUpdate(
                    "INSERT INTO returns (return_id, rental_id, actual_return_date, late_fee, damage_charge, deposit_amount, refund_amount, extra_pay_amount, settlement_type) VALUES (?,?,?,?,?,?,?,?,?)",
                    returnId,
                    rentalId,
                    actualReturnDate,
                    lateFee,
                    damageCharge,
                    rental.getDepositAmount(),
                    settlement > 0 ? settlement : 0.0,
                    settlement < 0 ? Math.abs(settlement) : 0.0,
                    settlement < 0 ? "Extra Pay" : "Refund"
                );
            if (!settlementSaved) {
                throw new Exception("Failed to save return settlement details.");
            }

            equipment.setStatus(hasDamage ? EquipmentEntity.Status.UNDER_MAINTENANCE : EquipmentEntity.Status.AVAILABLE);
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

    @Override
    public List<RentalDto> findAllRentals() throws Exception {
        ArrayList<RentalEntity> entities = rentalDao.getAll();
        List<RentalDto> dtos = new ArrayList<>();
        String branchFilter = Session.getCurrentBranchId();
        for (RentalEntity entity : entities) {
            if (branchFilter != null && !branchFilter.equals(entity.getBranchId())) {
                continue;
            }
            dtos.add(new RentalDto(
                    entity.getRentalId(),
                    entity.getEquipmentId(),
                    entity.getCustomerId(),
                    entity.getBranchId(),
                    entity.getStartDate(),
                    entity.getEndDate(),
                    entity.getActualReturnDate(),
                    entity.getRentalAmount(),
                    entity.getDepositAmount(),
                    entity.getMembershipDiscount(),
                    entity.getLongRentalDiscount(),
                    entity.getFinalAmount(),
                    entity.getPaymentStatus().getDatabaseValue(),
                    entity.getRentalStatus().getDatabaseValue()
            ));
        }
        return dtos;
    }

    private void calculateRentalPricing(RentalDto dto, EquipmentEntity equipment) throws Exception {
        CategoryEntity category = categoryDao.search(equipment.getCategoryId());
        if (category == null) {
            throw new Exception("Category not found.");
        }

        CustomerEntity customer = customerDao.search(dto.getCustomerId());
        if (customer == null) {
            throw new Exception("Customer not found.");
        }

        long days = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate());
        long weekendDays = 0;
        for (LocalDate d = dto.getStartDate(); d.isBefore(dto.getEndDate()); d = d.plusDays(1)) {
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

        dto.setRentalAmount(rentalAmount);
        dto.setDepositAmount(equipment.getSecurityDeposit());
        dto.setMembershipDiscount(membershipDiscount);
        dto.setLongRentalDiscount(longRentalDiscount);
        dto.setFinalAmount(finalAmount);
    }

}
