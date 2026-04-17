# GearRent Pro - Multi-Branch Equipment Rental System

## 📌 Project Description
GearRent Pro is a smart, multi-branch equipment rental system designed for companies that rent professional gear like cameras, lenses, drones, lighting kits, and audio equipment. It’s built to streamline operations, maximize efficiency, and provide an excellent experience for both staff and customers.

---

### Key Features
- Multi-branch operation with branch-level data control.
- Equipment reservation and rental workflows.
- Pricing calculation using category price factor and weekend multiplier.
- Membership-based discounts (Regular, Silver, Gold) and long-rental discount logic.
- Return processing with late fee, damage charge, refund/extra-pay settlement.
- Role-based access using Admin, Branch Manager, and Staff permissions.
- Reports for overdue rentals, branch revenue, and equipment utilization.

---

## ⚙️ Prerequisites

Make sure you have the following installed before running the project:

| Requirement | Details |
|---|---|
| JDK | **JDK 24** (project is compiled with `javac.source=24`) |
| IDE | NetBeans (Ant project support) |
| Database | MySQL or MariaDB (XAMPP recommended) |
| DB Driver | MySQL Connector/J |

---

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/Ama-Dombawela/GearRent-Pro.git
```

### 2. Set Up the Database

1. Start **XAMPP** and make sure **MySQL** is running.
2. Create the database:
```sql
   CREATE DATABASE GearRentPro;
```
3. Import the SQL file:
   - File: `GearRent-Pro/gearrentpro.sql`
4. Verify the connection settings in:
   - `GearRent-Pro/src/com/ijse/GearRentPro/db/DBConnection.java`
   - Default values:
     - **URL:** `jdbc:mysql://localhost:3306/GearRentPro`
     - **Username:** `root`
     - **Password:** *(empty)*

### 3. Run the App

**Option A — NetBeans (Recommended)**
1. Open NetBeans.
2. Open the project folder: `GearRent-Pro`
3. Click **Clean and Build**.
4. Run the main class: `com.ijse.GearRentPro.Main`

**Option B — Command Line**
```bash
cd GearRent-Pro
java -jar dist/GearRent-Pro.jar
```

> ⚠️ **JDK 24 is required.** The project is compiled with `javac.source=24` and `javac.target=24`. Using a different JDK version will cause build failures.

---

## 🔐 Default Login Credentials

| Role | Username | Password |
|---|---|---|
| Admin | `admin` | `admin123` |
| Branch Manager | `manager` | `manager123` |
| Staff | `staff` | `staff123` |

---

## 🧱 System Architecture

The application follows a clean layered architecture:

```
UI Layer (Swing forms)
        ↓
Controller Layer (handles UI actions)
        ↓
Service Layer (business logic, pricing, validations)
        ↓
DAO Layer (JDBC data access)
        ↓
Entity Layer (DB-mapped domain objects)
```

This keeps business logic separated from UI and persistence, making the codebase easier to maintain and test.

---

## 🗄️ Sample Data (Preloaded)

| Entity | Count | Notes |
|---|---|---|
| Branches | 3 | |
| Categories | 5 | |
| Equipment items | 21 | |
| Customers | 10 | |
| Membership levels | 3 | Regular, Silver, Gold |
| Rentals | 10 | 4 active, 3 returned, 3 overdue |
| Reservations | 3 | All active |
| Damage records | 2 | From damaged rentals |
| Return settlement records | 1 | |

---

## 🛠️ Tech Stack

| Technology | Details |
|---|---|
| Language | Java |
| UI Framework | Java Swing |
| Database | MySQL / MariaDB |
| DB Connectivity | JDBC + MySQL Connector/J |
| IDE | NetBeans (Ant-based project) |
| Java Version | JDK 24 |

---

## 📌 Notes

- **Main class:** `com.ijse.GearRentPro.Main`
- **First screen after launch:** Login screen (`LoginView`)
- **After successful login:** Dashboard (`MainDashboardView`)
- XAMPP is fully supported for local MySQL/MariaDB hosting.