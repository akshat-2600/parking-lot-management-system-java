 # 🅿 Parking Lot Management System

### A Java-based console application for efficient parking space allocation, ticketing, billing, and real-time monitoring.


---

## 📌 Overview

This project is a console-based Parking Lot Management System developed using Core Java and Object-Oriented Programming principles.
It automates the processes of:

- Parking spot allocation

- Ticket generation

- Vehicle exit & billing

- Tracking available and occupied spots

- Maintaining ticket history and revenue logs


The system ensures accuracy, efficiency, and maintainability, making it suitable for real-world small to medium parking facilities.


---

## 🎯 Features

### ✔ Vehicle Entry

- Enter vehicle number & type (CAR/BIKE/TRUCK)

- Automatically assigns the nearest available spot

- Generates a timestamped parking ticket


### ✔ Vehicle Exit

- Validate ticket

- Calculate duration of stay

- Generate total bill

- Free up the occupied spot


### ✔ Real-Time Monitoring

- View available parking spots

- View occupied spots

- Search vehicle by number

- View active tickets


### ✔ Ticket & Revenue Management

- Ticket history (active + closed)

- Daily revenue calculation

- Basic reporting


### ✔ Error Handling

- Invalid ticket ID

- No spot available

- Invalid vehicle type

- Prevent duplicate exits


### ✔ Clean Object-Oriented Structure

- Modular classes

- Well-designed models & services

- Single responsibility principle



---

## 🧱 Technologies Used

- Java (OOP + Collections Framework)

- Java Time API

- Exception Handling

- UML & Documentation

- Git & GitHub



---

## 📂 Project Structure

parking-lot-management-system/

│

├── src/com/parkinglot

│   └── exceptions/

│   │      ├── InvalidTicketException.java 

│   │      ├── NoSpotAvailableException.java

│   │      └── PaymentFailedException.java

│   └── models/

│   │      ├── ParkingSpot.java

│   │      ├── SpotType.java

│   │      ├── TicketType.java

│   │      └── Vehicle.java

│   └── services/

│   │      ├── BillingService.java

│   │      ├── ParkingLotManager.java

│   │      ├── PaymentService.java

│   │      └── ReportGenerator.java

│   └── utils/

│   │      ├── Config.java

│   │      ├── ConsoleUtils.java

│   │      ├── LoggerConfig.java

│   │      └── TimeUtils.java

│   ├── Main.java

│

├── diagrams/

│   ├── System Architecture Diagram.png

│   ├── UML Class Diagram.png

│   ├── UML Sequence Diagram.png

│   ├── UML USE CASE DIAGRAM.png

│   └── Workflow Diagram.png

├── README.md

├── statement.md

└── project-report.pdf


---

## 📐 System Architecture

<p align="center">
  <img src="diagrams/System Architecture Diagram.png" width="700"/>
</p>

## 🎭 Use Case Diagram

<p align="center">
  <img src="diagrams/UML USE CASE DIAGRAM.png" width="700"/>
</p>

## 🔄 Workflow Diagram

<p align="center">
  <img src="diagrams/Workflow Diagram.png" width="700"/>
</p>

## 🔁 Sequence Diagram

<p align="center">
  <img src="diagrams/UML Sequence Diagram.png" width="700"/>
</p>

## 🧱 Class Diagram

<p align="center">
  <img src="diagrams/UML Class Diagram.png" width="700"/>
</p>


---


## 🚀 How to Run the Project

### ✔ Requirements

- Java JDK 8+

- Any IDE (IntelliJ / VS Code / Eclipse)

- Terminal or Command Prompt



---

## 🏁 Steps to Run

1. Clone the repository
 
```bash

     git clone https://github.com/<your-github-username>/parking-lot-management-system.git

```

2. Navigate to project directory

```bash

     cd parking-lot-management-system

```

3. Compile the code

 ```bash

     javac src/*.java

```

4. Run the application

```bash

     java src/Main

```

---

## 🖥 Application Menu (Sample)

========================================

      PARKING LOT MANAGEMENT SYSTEM     
      
========================================

1. Park Vehicle
2. Exit Vehicle 
3. Show Available Spots
4. Search Vehicle (Active)
5. Show Parking Lot Revenue
6. Exit System

----------------------------------------
Enter your choice:


---

## 🛠 Functional Modules

### 1️⃣ ParkingLotManager

- Core management class

- Handles parking, exiting, searching, tracking


### 2️⃣ BillingService

- Calculates duration

- Generates bill & revenue summary


### 3️⃣ Models

- Vehicle

- Ticket

- ParkingSpot


### 4️⃣ Utils

- Date formatting

- Printing helpers



---

## 🧪 Testing

### ✔ Manual Test Cases

At least 20+ test cases, including:

- Vehicle entry scenarios

- Exit and billing validation

- Invalid ticket handling

- No spot available situations

- Search functionality

- Duration calculation correctness


---

## 📊 Reporting & Output

### Console-based reports:

- Total revenue

- Ticket history

- Active tickets

- Spot occupancy




---

## 📄 Documentation

### This repository includes:

  ✔ README.md

- Complete project explanation.

  ✔ statement.md

- Problem statement

- Project scope

- Target users

- High-level features

  ✔ project-report.pdf

- Full academic project documentation (10–15 pages).

  ✔ diagrams

- UML + Architecture diagrams.


---

## 🔮 Future Enhancements (Optional)

- GUI dashboard using JavaFX

- Real-time parking lot heatmap

- Database integration (MySQL)

- REST API using Spring Boot

- Admin login system

- Dynamic pricing (peak hours)



---

## 🧑‍💻 Author

### Akshat Saxena
### Java Developer | VIT Bhopal University
### GitHub: akshat-2600


---
