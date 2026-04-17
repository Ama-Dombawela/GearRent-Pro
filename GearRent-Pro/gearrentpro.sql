-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 17, 2026 at 02:57 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gearrentpro`
--

-- --------------------------------------------------------

--
-- Table structure for table `branches`
--

CREATE TABLE `branches` (
  `branch_id` char(6) NOT NULL,
  `branch_code` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `contact_no` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `branches`
--

INSERT INTO `branches` (`branch_id`, `branch_code`, `name`, `address`, `contact_no`) VALUES
('B001', 'PAN', 'Panadura Branch', 'Panadura', '0381999999'),
('B002', 'GAL', 'Galle Branch', 'Galle', '0912222222'),
('B003', 'COL', 'Colombo Branch', 'Colombo', '0113333333');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `category_id` char(6) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `price_factor` decimal(5,2) DEFAULT NULL,
  `weekend_multiplier` decimal(5,2) DEFAULT NULL,
  `late_fee_per_day` decimal(10,2) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`category_id`, `name`, `description`, `price_factor`, `weekend_multiplier`, `late_fee_per_day`, `is_active`) VALUES
('C001', 'Camera', 'Professional cameras', 1.00, 1.20, 1500.00, 1),
('C002', 'Lens', 'Camera lenses', 0.80, 1.10, 1000.00, 1),
('C003', 'Drone', 'Aerial drones', 1.50, 1.30, 3000.00, 1),
('C004', 'Lighting', 'Lighting kits', 0.70, 1.10, 800.00, 1),
('C005', 'Audio', 'Audio equipment', 0.60, 1.00, 600.00, 1);

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customer_id` char(6) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `nic_passport` varchar(30) DEFAULT NULL,
  `contact_no` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `membership_id` char(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customer_id`, `name`, `nic_passport`, `contact_no`, `email`, `address`, `membership_id`) VALUES
('CU001', 'Nimal Perera', '901234567V', '0771111111', 'nimal@gmail.com', 'Panadura', 'M001'),
('CU002', 'Kamal Silva', '880123456V', '0772222222', 'kamal@gmail.com', 'Galle', 'M002'),
('CU003', 'Saman Fernando', '921234567V', '0773333333', 'saman@gmail.com', 'Colombo', 'M003'),
('CU004', 'Dilini Perera', '950123456V', '0774444444', 'dilini@gmail.com', 'Negombo', 'M001'),
('CU005', 'Tharindu Jayasuriya', '930987654V', '0775555555', 'tharindu@gmail.com', 'Matara', 'M002'),
('CU006', 'Piumi Rathnayake', '960123789V', '0776666666', 'piumi@gmail.com', 'Kandy', 'M003'),
('CU007', 'Isuru Bandara', '940987321V', '0777777777', 'isuru@gmail.com', 'Kurunegala', 'M001'),
('CU008', 'Sanduni Silva', '970456123V', '0778888888', 'sanduni@gmail.com', 'Colombo', 'M002'),
('CU009', 'Ravindu Lakshan', '990112233V', '0779999999', 'ravindu@gmail.com', 'Panadura', 'M003'),
('CU010', 'Chamod Fernando', '200012345678', '0711111111', 'chamod@gmail.com', 'Galle', 'M001');

-- --------------------------------------------------------

--
-- Table structure for table `damages`
--

CREATE TABLE `damages` (
  `damage_id` char(6) NOT NULL,
  `rental_id` char(6) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `damage_charge` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `damages`
--

INSERT INTO `damages` (`damage_id`, `rental_id`, `description`, `damage_charge`) VALUES
('D001', 'RN003', 'Broken lens mount', 15000.00),
('DM001', 'RN002', 'Cracked lens', 5000.00);

-- --------------------------------------------------------

--
-- Table structure for table `equipment`
--

CREATE TABLE `equipment` (
  `equipment_id` char(6) NOT NULL,
  `category_id` char(6) DEFAULT NULL,
  `branch_id` char(6) DEFAULT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `purchase_year` int(11) DEFAULT NULL,
  `base_daily_price` decimal(10,2) DEFAULT NULL,
  `security_deposit` decimal(10,2) DEFAULT NULL,
  `status` enum('Available','Reserved','Rented','Under Maintenance') NOT NULL DEFAULT 'Available'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `equipment`
--

INSERT INTO `equipment` (`equipment_id`, `category_id`, `branch_id`, `brand`, `model`, `purchase_year`, `base_daily_price`, `security_deposit`, `status`) VALUES
('E001', 'C001', 'B001', 'Canon', 'EOS R6', 2022, 13000.00, 100000.00, 'Available'),
('E002', 'C001', 'B001', 'Nikon', 'Z6 II', 2021, 11000.00, 90000.00, 'Available'),
('E003', 'C002', 'B001', 'Sony', 'FE 50mm', 2020, 7000.00, 50000.00, 'Available'),
('E004', 'C003', 'B001', 'DJI', 'Mini 3 Pro', 2023, 18000.00, 140000.00, 'Available'),
('E005', 'C004', 'B001', 'Godox', 'SL60W', 2021, 6000.00, 40000.00, 'Available'),
('E006', 'C001', 'B002', 'Sony', 'A7 III', 2020, 10000.00, 90000.00, 'Under Maintenance'),
('E007', 'C002', 'B002', 'Canon', 'RF 24-105', 2022, 8000.00, 60000.00, 'Available'),
('E008', 'C003', 'B002', 'DJI', 'Air 2S', 2023, 22000.00, 160000.00, 'Available'),
('E009', 'C004', 'B002', 'Aputure', 'Light Dome', 2021, 6500.00, 45000.00, 'Available'),
('E010', 'C005', 'B002', 'Rode', 'NTG4+', 2022, 5000.00, 30000.00, 'Available'),
('E011', 'C001', 'B003', 'Panasonic', 'GH5', 2019, 9000.00, 85000.00, 'Available'),
('E012', 'C002', 'B003', 'Sigma', '18-35mm', 2020, 7500.00, 55000.00, 'Available'),
('E013', 'C003', 'B003', 'DJI', 'Mavic 2 Pro', 2021, 21000.00, 150000.00, 'Available'),
('E014', 'C004', 'B003', 'Nanlite', 'Forza 60', 2022, 7000.00, 50000.00, 'Available'),
('E015', 'C005', 'B003', 'Zoom', 'H6', 2021, 4500.00, 30000.00, 'Available'),
('E016', 'C001', 'B003', 'Canon', 'EOS R5', 2023, 14000.00, 120000.00, 'Available'),
('E017', 'C002', 'B002', 'Sony', 'FE 70-200', 2022, 9000.00, 70000.00, 'Available'),
('E018', 'C005', 'B001', 'Shure', 'SM7B', 2021, 5500.00, 35000.00, 'Available'),
('E019', 'C004', 'B002', 'Godox', 'VL150', 2022, 8000.00, 60000.00, 'Available'),
('E020', 'C003', 'B001', 'DJI', 'Avata', 2023, 23000.00, 170000.00, 'Available'),
('E030', 'C003', 'B002', 'amazon', '64-ar', 2021, 1400.00, 145000.00, 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `membership_levels`
--

CREATE TABLE `membership_levels` (
  `membership_id` char(6) NOT NULL,
  `level_name` varchar(20) DEFAULT NULL,
  `discount_percentage` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `membership_levels`
--

INSERT INTO `membership_levels` (`membership_id`, `level_name`, `discount_percentage`) VALUES
('M001', 'Regular', 0.00),
('M002', 'Silver', 5.00),
('M003', 'Gold', 10.00);

-- --------------------------------------------------------

--
-- Stand-in structure for view `overdue_rentals`
-- (See below for the actual view)
--
CREATE TABLE `overdue_rentals` (
`rental_id` char(6)
,`customer_name` varchar(100)
,`branch_name` varchar(100)
,`equipment_model` varchar(50)
,`end_date` date
,`days_overdue` int(7)
);

-- --------------------------------------------------------

--
-- Table structure for table `rentals`
--

CREATE TABLE `rentals` (
  `rental_id` char(6) NOT NULL,
  `equipment_id` char(6) DEFAULT NULL,
  `customer_id` char(6) DEFAULT NULL,
  `branch_id` char(6) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `actual_return_date` date DEFAULT NULL,
  `rental_amount` decimal(12,2) DEFAULT NULL,
  `deposit_amount` decimal(12,2) DEFAULT NULL,
  `membership_discount` decimal(12,2) DEFAULT NULL,
  `long_rental_discount` decimal(12,2) DEFAULT NULL,
  `final_amount` decimal(12,2) DEFAULT NULL,
  `payment_status` enum('Paid','Partially Paid','Unpaid') DEFAULT 'Unpaid',
  `rental_status` enum('Active','Returned','Overdue','Cancelled') DEFAULT 'Active'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rentals`
--

INSERT INTO `rentals` (`rental_id`, `equipment_id`, `customer_id`, `branch_id`, `start_date`, `end_date`, `actual_return_date`, `rental_amount`, `deposit_amount`, `membership_discount`, `long_rental_discount`, `final_amount`, `payment_status`, `rental_status`) VALUES
('RN001', 'E002', 'CU004', 'B001', '2024-11-01', '2024-11-05', '2024-11-05', 50000.00, 90000.00, 0.00, 0.00, 50000.00, 'Paid', 'Returned'),
('RN002', 'E006', 'CU005', 'B002', '2024-11-01', '2024-11-05', '2026-04-20', 55000.00, 90000.00, 2500.00, 0.00, 52500.00, 'Unpaid', 'Returned'),
('RN003', 'E012', 'CU006', 'B003', '2024-10-01', '2024-10-07', '2024-10-10', 70000.00, 85000.00, 3500.00, 5000.00, 61500.00, 'Paid', 'Returned'),
('RN004', 'E001', 'CU001', 'B001', '2026-04-01', '2026-04-05', NULL, 65000.00, 100000.00, 0.00, 0.00, 165000.00, 'Paid', 'Active'),
('RN005', 'E004', 'CU002', 'B001', '2026-04-03', '2026-04-08', NULL, 90000.00, 140000.00, 4500.00, 0.00, 225500.00, 'Paid', 'Active'),
('RN006', 'E008', 'CU003', 'B002', '2026-03-01', '2026-03-05', NULL, 88000.00, 160000.00, 8800.00, 0.00, 239200.00, 'Unpaid', 'Overdue'),
('RN007', 'E013', 'CU004', 'B003', '2026-03-10', '2026-03-15', NULL, 105000.00, 150000.00, 0.00, 0.00, 255000.00, 'Unpaid', 'Overdue'),
('RN008', 'E007', 'CU005', 'B002', '2026-03-20', '2026-03-25', NULL, 40000.00, 60000.00, 2000.00, 0.00, 98000.00, 'Paid', 'Overdue'),
('RN009', 'E011', 'CU006', 'B003', '2026-04-05', '2026-04-10', NULL, 45000.00, 85000.00, 4500.00, 0.00, 125500.00, 'Paid', 'Active'),
('RN010', 'E018', 'CU007', 'B001', '2026-04-08', '2026-04-12', NULL, 22000.00, 35000.00, 0.00, 0.00, 57000.00, 'Unpaid', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `reservation_id` char(6) NOT NULL,
  `equipment_id` char(6) DEFAULT NULL,
  `customer_id` char(6) DEFAULT NULL,
  `branch_id` char(6) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `status` enum('Active','Cancelled','Converted') NOT NULL DEFAULT 'Active'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`reservation_id`, `equipment_id`, `customer_id`, `branch_id`, `start_date`, `end_date`, `status`) VALUES
('RV001', 'E001', 'CU001', 'B001', '2024-12-01', '2024-12-05', 'Active'),
('RV002', 'E005', 'CU002', 'B001', '2024-12-03', '2024-12-07', 'Active'),
('RV003', 'E010', 'CU003', 'B002', '2024-12-10', '2024-12-15', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `returns`
--

CREATE TABLE `returns` (
  `return_id` char(6) NOT NULL,
  `rental_id` char(6) NOT NULL,
  `actual_return_date` date NOT NULL,
  `late_fee` decimal(12,2) NOT NULL DEFAULT 0.00,
  `damage_charge` decimal(12,2) NOT NULL DEFAULT 0.00,
  `deposit_amount` decimal(12,2) NOT NULL,
  `refund_amount` decimal(12,2) NOT NULL DEFAULT 0.00,
  `extra_pay_amount` decimal(12,2) NOT NULL DEFAULT 0.00,
  `settlement_type` enum('Refund','Extra Pay') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `returns`
--

INSERT INTO `returns` (`return_id`, `rental_id`, `actual_return_date`, `late_fee`, `damage_charge`, `deposit_amount`, `refund_amount`, `extra_pay_amount`, `settlement_type`, `created_at`) VALUES
('RT001', 'RN001', '2026-04-05', 0.00, 0.00, 90000.00, 40000.00, 0.00, 'Refund', '2026-04-17 12:56:31');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role_id` char(6) NOT NULL,
  `role_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role_id`, `role_name`) VALUES
('R001', 'ADMIN'),
('R002', 'BRANCH_MANAGER'),
('R003', 'STAFF');

-- --------------------------------------------------------

--
-- Table structure for table `system_config`
--

CREATE TABLE `system_config` (
  `config_id` char(6) NOT NULL,
  `config_name` varchar(50) DEFAULT NULL,
  `config_value` decimal(12,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `system_config`
--

INSERT INTO `system_config` (`config_id`, `config_name`, `config_value`) VALUES
('SC001', 'max_deposit_per_customer', 500000.00),
('SC002', 'long_rental_discount_percent', 10.00);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` char(6) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role_id` char(6) NOT NULL,
  `branch_id` char(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `role_id`, `branch_id`) VALUES
('U001', 'admin', 'admin123', 'R001', 'B001'),
('U002', 'manager', 'manager123', 'R002', 'B001'),
('U003', 'staff', 'staff123', 'R003', 'B001');

-- --------------------------------------------------------

--
-- Structure for view `overdue_rentals`
--
DROP TABLE IF EXISTS `overdue_rentals`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `overdue_rentals`  AS SELECT `r`.`rental_id` AS `rental_id`, `c`.`name` AS `customer_name`, `b`.`name` AS `branch_name`, `e`.`model` AS `equipment_model`, `r`.`end_date` AS `end_date`, to_days(curdate()) - to_days(`r`.`end_date`) AS `days_overdue` FROM (((`rentals` `r` join `customers` `c` on(`r`.`customer_id` = `c`.`customer_id`)) join `branches` `b` on(`r`.`branch_id` = `b`.`branch_id`)) join `equipment` `e` on(`r`.`equipment_id` = `e`.`equipment_id`)) WHERE `r`.`rental_status` = 'Active' AND curdate() > `r`.`end_date` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `branches`
--
ALTER TABLE `branches`
  ADD PRIMARY KEY (`branch_id`),
  ADD UNIQUE KEY `branch_code` (`branch_code`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customer_id`),
  ADD KEY `customers_ibfk_1` (`membership_id`);

--
-- Indexes for table `damages`
--
ALTER TABLE `damages`
  ADD PRIMARY KEY (`damage_id`),
  ADD KEY `damages_ibfk_1` (`rental_id`);

--
-- Indexes for table `equipment`
--
ALTER TABLE `equipment`
  ADD PRIMARY KEY (`equipment_id`),
  ADD KEY `equipment_ibfk_1` (`category_id`),
  ADD KEY `equipment_ibfk_2` (`branch_id`);

--
-- Indexes for table `membership_levels`
--
ALTER TABLE `membership_levels`
  ADD PRIMARY KEY (`membership_id`),
  ADD UNIQUE KEY `level_name` (`level_name`);

--
-- Indexes for table `rentals`
--
ALTER TABLE `rentals`
  ADD PRIMARY KEY (`rental_id`),
  ADD KEY `rentals_ibfk_1` (`equipment_id`),
  ADD KEY `rentals_ibfk_2` (`customer_id`),
  ADD KEY `rentals_ibfk_3` (`branch_id`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`reservation_id`),
  ADD KEY `reservations_ibfk_1` (`equipment_id`),
  ADD KEY `reservations_ibfk_2` (`customer_id`),
  ADD KEY `reservations_ibfk_3` (`branch_id`);

--
-- Indexes for table `returns`
--
ALTER TABLE `returns`
  ADD PRIMARY KEY (`return_id`),
  ADD UNIQUE KEY `uk_returns_rental` (`rental_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`),
  ADD UNIQUE KEY `role_name` (`role_name`);

--
-- Indexes for table `system_config`
--
ALTER TABLE `system_config`
  ADD PRIMARY KEY (`config_id`),
  ADD UNIQUE KEY `config_name` (`config_name`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `fk_users_branch` (`branch_id`),
  ADD KEY `users_ibfk_1` (`role_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`membership_id`) REFERENCES `membership_levels` (`membership_id`);

--
-- Constraints for table `damages`
--
ALTER TABLE `damages`
  ADD CONSTRAINT `damages_ibfk_1` FOREIGN KEY (`rental_id`) REFERENCES `rentals` (`rental_id`);

--
-- Constraints for table `equipment`
--
ALTER TABLE `equipment`
  ADD CONSTRAINT `equipment_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`),
  ADD CONSTRAINT `equipment_ibfk_2` FOREIGN KEY (`branch_id`) REFERENCES `branches` (`branch_id`);

--
-- Constraints for table `rentals`
--
ALTER TABLE `rentals`
  ADD CONSTRAINT `rentals_ibfk_1` FOREIGN KEY (`equipment_id`) REFERENCES `equipment` (`equipment_id`),
  ADD CONSTRAINT `rentals_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  ADD CONSTRAINT `rentals_ibfk_3` FOREIGN KEY (`branch_id`) REFERENCES `branches` (`branch_id`);

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`equipment_id`) REFERENCES `equipment` (`equipment_id`),
  ADD CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  ADD CONSTRAINT `reservations_ibfk_3` FOREIGN KEY (`branch_id`) REFERENCES `branches` (`branch_id`);

--
-- Constraints for table `returns`
--
ALTER TABLE `returns`
  ADD CONSTRAINT `fk_returns_rental` FOREIGN KEY (`rental_id`) REFERENCES `rentals` (`rental_id`) ON DELETE CASCADE;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_users_branch` FOREIGN KEY (`branch_id`) REFERENCES `branches` (`branch_id`),
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
