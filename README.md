---

🅿 Parking Lot Management System

A Java-based console application for efficient parking space allocation, ticketing, billing, and real-time monitoring.


---

📌 Overview

This project is a console-based Parking Lot Management System developed using Core Java and Object-Oriented Programming principles.
It automates the processes of:

Parking spot allocation

Ticket generation

Vehicle exit & billing

Tracking available and occupied spots

Maintaining ticket history and revenue logs


The system ensures accuracy, efficiency, and maintainability, making it suitable for real-world small to medium parking facilities.


---

🎯 Features

✔ Vehicle Entry

Enter vehicle number & type (CAR/BIKE/TRUCK)

Automatically assigns the nearest available spot

Generates a timestamped parking ticket


✔ Vehicle Exit

Validate ticket

Calculate duration of stay

Generate total bill

Free up the occupied spot


✔ Real-Time Monitoring

View available parking spots

View occupied spots

Search vehicle by number

View active tickets


✔ Ticket & Revenue Management

Ticket history (active + closed)

Daily revenue calculation

Basic reporting


✔ Error Handling

Invalid ticket ID

No spot available

Invalid vehicle type

Prevent duplicate exits


✔ Clean Object-Oriented Structure

Modular classes

Well-designed models & services

Single responsibility principle



---

🏗 System Architecture

┌─────────────────────────┐
 │     Console UI (Main)   │
 └─────────────┬───────────┘
               │
 ┌─────────────▼────────────┐
 │     Service Layer         │
 │  - ParkingLotManager      │
 │  - BillingService         │
 └─────────────┬────────────┘
               │
 ┌─────────────▼────────────┐
 │        Data Layer         │
 │  - ParkingSpot List       │
 │  - Active Tickets         │
 │  - Ticket History         │
 └───────────────────────────┘


---

🧱 Technologies Used

Java (OOP + Collections Framework)

Java Time API

Exception Handling

UML & Documentation

Git & GitHub



---

📂 Project Structure

parking-lot-management-system/
│
├── src/
│   ├── Main.java
│   ├── ParkingLotManager.java
│   ├── ParkingSpot.java
│   ├── Vehicle.java
│   ├── Ticket.java
│   ├── BillingService.java
│   ├── ConsoleUtils.java
│   ├── exceptions/
│   │      ├── NoSpotAvailableException.java
│   │      ├── InvalidTicketException.java
│   │      └── PaymentFailedException.java
│   └── utils/
│          └── DateUtils.java
│
├── diagrams/
│   ├── usecase.png
│   ├── classdiagram.png
│   ├── workflow-entry.png
│   ├── workflow-exit.png
│   └── architecture.png
│
├── README.md
├── statement.md
├── test-cases.pdf
└── project-report.pdf


---

🚀 How to Run the Project

✔ Requirements

Java JDK 8+

Any IDE (IntelliJ / VS Code / Eclipse)

Terminal or Command Prompt



---

🏁 Steps to Run

1. Clone the repository

git clone https://github.com/<your-github-username>/parking-lot-management-system.git

2. Navigate to project directory

cd parking-lot-management-system

3. Compile the code

javac src/*.java

4. Run the application

java src/Main


---

🖥 Application Menu (Sample)

========================================
      PARKING LOT MANAGEMENT SYSTEM     
========================================

1. Park Vehicle
2. Vehicle Exit
3. View Available Spots
4. View Occupied Spots
5. Search Vehicle
6. View Daily Revenue
7. Exit

----------------------------------------
Enter your choice:


---

🛠 Functional Modules

1️⃣ ParkingLotManager

Core management class

Handles parking, exiting, searching, tracking


2️⃣ BillingService

Calculates duration

Generates bill & revenue summary


3️⃣ Models

Vehicle

Ticket

ParkingSpot


4️⃣ Utils

Date formatting

Printing helpers



---

🧪 Testing

✔ Manual Test Cases

At least 20+ test cases, including:

Vehicle entry scenarios

Exit and billing validation

Invalid ticket handling

No spot available situations

Search functionality

Duration calculation correctness


(All test cases are included in test-cases.pdf.)


---

📊 Reporting & Output

Console-based reports:

Total revenue

Ticket history

Active tickets

Spot occupancy


Screenshot examples included in project-report and README (optional)



---

📄 Documentation

This repository includes:

✔ README.md

Complete project explanation.

✔ statement.md

Problem statement

Project scope

Target users

High-level features


✔ project-report.pdf

Full academic project documentation (10–15 pages).

✔ test-cases.pdf

Manual testing sheet with expected results.

✔ diagrams

UML + Architecture diagrams.


---

🔮 Future Enhancements (Optional)

GUI dashboard using JavaFX

Real-time parking lot heatmap

Database integration (MySQL)

REST API using Spring Boot

Admin login system

Dynamic pricing (peak hours)



---

🧑‍💻 Author

Akshat Saxena
Java Developer | VIT Bhopal University
GitHub: akshat-2600


---

⭐ Conclusion

This Parking Lot Management System demonstrates complete command over:

Java OOP

Exception Handling

Modular design

Real-time problem solving

Documentation & software engineering practices


It is fully functional, maintainable, and aligned with academic standards.


---

If you want, I can now create:
✔ statement.md
✔ Project Report (PDF-ready)
✔ Test Cases Document
✔ UML Diagrams Content (you can convert to images)

Just tell me!
