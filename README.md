# GearRent Pro - Multi-Branch Equipment Rental System

## 📌 Project Description
GearRent Pro is a smart, multi-branch equipment rental system designed for companies that rent professional gear like cameras, lenses, drones, lighting kits, and audio equipment. It’s built to streamline operations, maximize efficiency, and provide an excellent experience for both staff and customers.

### Key Features
- Multi-branch operation with branch-level data control.
- Equipment reservation and rental workflows.
- Pricing calculation using category price factor and weekend multiplier.
- Membership-based discounts (Regular, Silver, Gold) and long-rental discount logic.
- Return processing with late fee, damage charge, refund/extra-pay settlement.
- Role-based access using Admin, Branch Manager, and Staff permissions.
- Reports for overdue rentals, branch revenue, and equipment utilization.

## ⚙️ Database Configuration
Follow these steps to configure the database.

1. Start XAMPP and run MySQL.
2. Create a database.
	 - Recommended (matches Java connection URL):
	 - `CREATE DATABASE GearRentPro;`
	 - SQL dump metadata uses `gearrentpro`; on default MySQL setups this is typically case-insensitive.
3. Import the SQL script.
	 - File: `GearRent-Pro/gearrentpro.sql`
4. Verify/update Java DB connection settings.
	 - File: `GearRent-Pro/src/com/ijse/GearRentPro/db/DBConnection.java`
	 - Current values in code:
	 - URL: `jdbc:mysql://localhost:3306/GearRentPro`
	 - Username: `root`
	 - Password: (empty string)

## ▶️ How to Run the Application

### 1. Clone the repository

```bash
git clone https://github.com/Ama-Dombawela/GearRent-Pro.git

```
### NetBeans (Recommended)
1. Open NetBeans.
2. Open project folder: `GearRent-Pro`.
3. Build the project (Clean and Build).
4. Run the main class:
	 - `com.ijse.GearRentPro.Main`
	 - File: `GearRent-Pro/src/com/ijse/GearRentPro/Main.java`

### Run JAR from Command Line
After successful build:
1. Open terminal.
2. Go to project folder:
	 - `cd GearRent-Pro`
3. Run:
	 - `java -jar dist/GearRent-Pro.jar`

### Required Java Version
- Project compiler settings are currently:
	- `javac.source=24`
	- `javac.target=24`
- Use JDK 24 to match project configuration.

## 🔐 Default Login Credentials
Extracted from the `users` table in `gearrentpro.sql`.

| Role | Username | Password |
|---|---|---|
| Admin | admin | admin123 |
| Branch Manager | manager | manager123 |
| Staff | staff | staff123 |

## 🧱 System Architecture
The application follows a layered architecture.

- UI Layer
	- Swing forms in `view` package (`*.java`, `*.form`).
- Controller Layer
	- Controllers handle UI actions and call services.
- Service Layer
	- Business logic, validations, pricing, branch/role checks, transaction handling.
- DAO Layer
	- Data access interfaces and implementations using JDBC utility methods.
- Entity Layer
	- Database-mapped domain objects (`entity` package).

This structure keeps business logic separated from UI and persistence, which improves maintainability and testability.

## 📊 Sample Data
The SQL file includes preloaded sample records.

- Branches: 3
- Categories: 5
- Equipment items: 21
- Customers: 10
- Membership levels: 3
- Rentals: 10
	- Active: 4
	- Returned: 3
	- Overdue: 3
- Reservations: 3 (all Active in sample)
- Damage records: 2 (damaged rentals included)
- Return settlement records: 1

## 🛠 Technologies Used
- Java
- Java Swing 
- JDBC
- MySQL / MariaDB
- NetBeans (Ant-based project)
- MySQL Connector/J

### Prerequisites
- JDK 24
- NetBeans IDE (Ant project support)
- MySQL or MariaDB (XAMPP is supported)

### Expected Startup Behavior
- Application main class: `com.ijse.GearRentPro.Main`
- First screen displayed after launch: Login screen (`LoginView`)
- After successful login: Dashboard screen (`MainDashboardView`)


