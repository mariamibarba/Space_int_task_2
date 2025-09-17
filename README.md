# 🚀 REST API Test Automation (Reqres.in)

![Java](https://img.shields.io/badge/Java-17+-red?logo=java)
![Maven](https://img.shields.io/badge/Maven-3.9+-blue?logo=apachemaven)
![Cucumber](https://img.shields.io/badge/Cucumber-BDD-green?logo=cucumber)
![RestAssured](https://img.shields.io/badge/RestAssured-5.5.0-orange)

Automated test suite for the [Reqres API](https://reqres.in/) using **Java**, **RestAssured**, and **Cucumber (Gherkin)**.  
This project validates CRUD operations (`POST`, `GET`, `PUT/PATCH`, `DELETE`) with both positive and negative scenarios.

---

## 📂 Project Structure

.
├── pom.xml # Maven dependencies
├── README.md
└── src
└── test
├── java
│ ├── hooks/ # Cucumber hooks (setup/teardown)
│ ├── runners/ # Test runner
│ └── steps/ # Step definitions
└── resources
├── features/ # Gherkin feature files
└── cucumber.properties

yaml
Copy code

---

## 🧱 Features

- **Feature Files**: Written in Gherkin syntax to describe API behavior.
- **Step Definitions**: Implemented in Java using RestAssured.
- **Assertions**: Validate HTTP response codes, headers, and body content.
- **Scenarios**: Include both positive and negative test cases.
- **Documentation**: Setup and execution instructions included.

---

## ⚙️ Setup

### ✅ Prerequisites
- Java **17+**
- Maven **3.9+**
- Git
- *(Optional)* [Allure CLI](https://docs.qameta.io/allure/) for reports

### 📦 Install Dependencies
bash
mvn clean install

---
🧪 Example Feature

Feature: Create users
  Scenario: Create a user with valid payload
    Given I have a JSON body:
      """
      { "name": "mariami", "job": "QA" }
      """
    When I POST to "/api/users"
    Then the response status should be 201
    And the response body should contain keys "id" and "createdAt"

---

▶️ Running Tests
mvn test

Run specific tag
mvn test -Dcucumber.filter.tags="@create"

🔍 Endpoints Tested
Method	Endpoint	Description
POST	/api/users	Create user
GET	/api/users?page=2	List users
GET	/api/users/{id}	Single user
PUT	/api/users/{id}	Update user
PATCH	/api/users/{id}	Partial update
DELETE	/api/users/{id}	Delete user

📝 Notes
Scenarios are tagged: @create, @read, @update, @delete, @negative.
Negative tests include invalid payloads, non-existent users, and missing fields.
Base URL is configurable via cucumber.properties (default: https://reqres.in).
