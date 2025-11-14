
# Automation Testing Project

This repository contains a complete set of Selenium automation test scripts for [Booking_Rooms Website] a web-based hotel booking application.  

The purpose of this project is to ensure that all critical functionalities of the application work as expected, reduce manual testing effort, and improve the reliability and stability of the system. By using automation, we can execute repetitive test scenarios quickly and consistently across different browsers and environments.

## ✅ Key Features

- **Page Object Model (POM) Structure**: All pages of the application are represented as separate classes, making the code more readable, reusable, and easy to maintain.
- **Comprehensive Test Coverage**: Includes tests for the Home Page, Room Booking pages (Single, Double, Suite), Reservation process, and Admin login.
- **Cross-Browser Ready**: Can be executed on multiple browsers using Selenium WebDriver.
- **Configuration Management**: Centralized configuration using `ConfigReader` and driver setup using `DriverFactory` for easy environment management.
- **TestNG Integration**: Supports running tests in parallel, grouping, and generating detailed reports.

## 🗂️ Project Organization

- **Pages**: Represent each page of the website (e.g., HomePage, SingleRoomPage, ReservePage) and contain locators and page methods.
- **Tests**: Contains test classes that validate end-to-end functionality using the page objects.
- **Utilities**: Includes helper classes for WebDriver initialization, configuration reading, and other reusable functions.
- **Base Test Setup**: Common setup and teardown logic is placed in `BaseTest.java` to avoid code duplication.

This structure ensures that any new test or page can be added quickly without affecting existing tests, providing a scalable and maintainable automation framework.

## 🗂️ Project Structure
```markdown
AutomationInTesting/
│
├── src/
│ ├── main/java/
│ │ └── pages/
│ │ ├── HomePage.java
│ │ ├── SingleRoomPage.java
│ │ ├── DoubleRoomPage.java
│ │ ├── SuiteRoomPage.java
│ │ ├── ReservePage.java
│ │ └── AdminLoginPage.java
│ │
│ └── utils/
│ ├── DriverFactory.java
│ └── ConfigReader.java
│
├── src/test/java/
│ ├── tests/
│ │ ├── HomePageTest.java
│ │ ├── RoomBookingTest.java
│ │ ├── ReserveTest.java
│ │ └── AdminLoginTest.java
│ │
│ └── base/
│ └── BaseTest.java
│
├── testng.xml
├── pom.xml
└── README.md
```
---

## 🏨 Focus: Room Booking Page

The **RoomBookingTest.java** contains automation for:

- checking **HomePage**
- Booking **Single Room**
- Booking **Double Room**
- Booking **Suite Room**
- Navigating from **Home Page** to the specific room pages
- Validating the reservation flow through **ReservePage.java**
- checking **AdminLoginPage**

### Test Flow Example

1. Open **Home Page**
2. Navigate to **Room Booking Page**
3. Select room type (Single / Double / Suite)
4. Fill reservation form
5. Submit and verify reservation confirmation

---

## 💻 Prerequisites

- Java JDK 8+
- Maven
- Selenium WebDriver
- Browser drivers :ChromeDriver
- IDE :IntelliJ IDEA

---

## 🚀 How to Run the Tests

1. Clone the repository:
```bash
git clone [https://github.com/Qualitypluse/Automation-Test.git]
