# ⚙ Setup Guide – PSDPAL API Automation

## 📌 1. Prerequisites

Ensure the following tools are installed:

✅ Required Software
Java 17 or 21
Maven 3.8+
Git
IDE: IntelliJ IDEA / Eclipse / VS Code

🔍 Verify Installation
java -version
mvn -version
git --version


## 📥 2. Clone the Project

Clone the automation framework from GitHub:
git clone https://github.com/pdabholegithub/psdpal-api-automation


## 🧩 3. Import into Eclipse / IntelliJ

Eclipse
File → Import → Maven → Existing Maven Project
Select project folder → Finish  

IntelliJ
File → Open → Select project folder → IntelliJ auto-imports Maven


## 🏗 4. Build the Project

Run a clean build:
mvn clean install

This will:
Download all dependencies
Compile the project
Run unit/API tests (if not skipped)

## ▶ 5. Execute API Tests

Run DEV Environment
mvn test -Denv=dev

Run QA Environment
mvn test -Denv=qa

Run UAT Environment
mvn test -Denv=uat

Run PROD Environment

⚠ Recommended only for read-only APIs
mvn test -Denv=prod

Run a specific test class
mvn -Dtest=ReqResTests test

Run with Allure reporting (future phase)
mvn clean test allure:serve




## 🗂 6. Directory Structure Overview

## 📁 2. Project Structure

```
psdpal-api-automation/
│
├── src/main/java/
│   ├── psdpal/common/              → Core reusable framework modules
│   │   ├── APIClient.java
│   │   ├── ConfigReader.java
│   │   ├── RequestBuilder.java
│   │   ├── ResponseValidator.java
│   │   ├── TokenManager.java
│   │   ├── Banner.java
│   │   └── Utils.java
│   │
│   ├── psdpal/reqres/api/          → ReqRes endpoints, payloads, utils
│   └── psdpal/restfulbooker/api/   → Restful Booker modules
│
├── src/main/resources/             → application.properties + metadata files
│
├── src/test/java/
│   ├── psdpal/reqres/tests/        → ReqRes automation suite
│   ├── psdpal/restfulbooker/tests/ → RestfulBooker automation suite
│   └── psdpal/base/                → Base test config
│
├── docs/                           → Documentation (setup, endpoints, plan, roadmap)
│
└── pom.xml
```

## 🛠 7. Troubleshooting Guide

| Issue                       | Possible Cause                   | Solution                                                |
|-----------------------------|----------------------------------|---------------------------------------------------------|
| Environment file not loading | Wrong `-Denv` passed              | Ensure `application-<env>.properties` exists            |
| Base URL not found          | Missing key in properties        | Add `dev.reqres.baseUrl` / `qa.reqres.baseUrl`, etc     |
| Token is null or invalid    | Token expired or incorrect       | Update token in `application.properties`                |
| Request failing             | API server down or incorrect API | Validate endpoint manually in Postman                   |
| Dependency errors           | Maven cache corrupted            | Run: `mvn clean install -U`                             |
| 404 Not Found               | Wrong endpoint                   | Check `/docs/endpoints-list.md`                         |






