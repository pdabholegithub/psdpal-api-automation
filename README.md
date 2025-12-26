# API Automation 🏥⚙️
Enterprise-grade API test automation using Java + RestAssured + TestNG.

## 📌 Overview
PSDPal API Automation framework provides structured, scalable, and maintainable automation for ReqRes, RestfulBooker, and related APIs.

## 🚀 Features
- Environment switching (DEV / QA / PROD)
- Centralized request builder
- API client abstraction
- Response validation helpers
- Execution banners & metadata
- Modular endpoint structure

## 📁 Folder Structure
```
psdpal-api-automation
│── src/main/java
│   └── carepal.common
│── src/main/resources
│── src/test/java
│── docs/
│── pom.xml
```

## ▶️ How to Run Tests
```
mvn test -Denv=dev
mvn test -Denv=qa
mvn test -Denv=uat
mvn test -Denv=prod
```

## 🔄 Switching Environments
Define keys in:
`src/main/resources/application.properties`

## 🛠 Tech Stack
- Java 17,21
- RestAssured
- TestNG
- Maven

## ✍️ Contributors
- Prasad Dabhole
- PSDpal QA Automation Team

## 🕒 Last Updated
27 Nov 2025