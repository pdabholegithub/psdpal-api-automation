## PSDPAL API Automation – Framework Architecture
## 📌 1. Overview

The PSDPAL API Automation Framework is a modular, scalable, multi-environment API testing framework designed for:

ReqRes API
Restful Booker API
Any future REST APIs

Built using Java + RestAssured + TestNG, it follows a clean layered design ensuring reusability, maintainability, and CI/CD readiness

## 📂 2. Project Structure

```
psdpal-api-automation/
│
├── src/main/java/
│   ├── psdpal/common/              → Core reusable framework modules
│   ├── psdpal/reqres/api/          → ReqRes endpoints, payloads, utils
│   └── psdpal/restfulbooker/api/   → Restful Booker modules
│
├── src/main/resources/             → application.properties + metadata files
│
├── src/test/java/
│   ├── psdpal/reqres/tests/        → ReqRes test suite
│   ├── psdpal/restfulbooker/tests/ → Restful Booker test suite
│   └── psdpal/base/                → Base test configuration
│
├── docs/                           → Documentation (setup, endpoints, plan, roadmap)
└── pom.xml
```



## 🧩 3. Core Modules (Inside common/)

## 🔧 ConfigReader

Loads environment configuration dynamically
Supports DEV, QA, UAT, PROD
Single properties file controlling all APIs

## 🧱 RequestBuilder

Creates RestAssured request specifications
Adds query/path/body headers dynamically
Abstracts away repetitive request-building logic

## 🚀 APIClient

Central point to execute HTTP methods
Handles GET, POST, PUT, DELETE
Logs request and response
Works for all services

## 🌍 Endpoints

Central registry of all API routes
Keeps endpoint paths isolated from tests

## ✔ ResponseValidator

Common reusable assertions:
Status code
JSON format
Key existence
Schema validation (future)

## 🎨 Banner.java

Displays a console banner with:
Environment
Version
Owner
Execution start time

## 🧾 FrameworkInfo

Loads metadata from:
framework-version.txt
framework-owner.txt
framework-description.txt

## 📦 BaseAPI.java

Initializes:
Base URL
Default headers
Token (if required)
Request logging configuration

## 🛠 Utils.java

Common helper functions (UUIDs, timestamps, JSON parsing, etc.)



## 🌐 4. Environment Management

✔ Single application.properties file 
✔ Auto selects env based on CLI: mvn test -Denv=qa
✔ Supported environments:
DEV
QA
UAT
PROD
✔ Uses dynamic keys like:
qa.reqres.baseUrl
qa.restfulbooker.baseUrl


## 🧪 5. Test Structure

src/test/java/
│
├── psdpal/reqres/tests/               → Test cases for ReqRes APIs
├── psdpal/restfulbooker/tests/        → Test cases for Restful Booker APIs
└── psdpal/base/                       → Base test with @BeforeMethod, @BeforeSuite

## Base Test Responsibilities:

Print banner
Load env config
Initialize APIClient + RequestBuilder
Inject authentication token (RestfulBooker)
Setup logging
Test cleanups


## 🔄 6. Execution Flow

1. Start Test
2. ConfigReader loads environment
3. BaseAPI loads base URLs + tokens
4. RequestBuilder prepares request spec
5. APIClient executes HTTP request
6. ResponseValidator verifies response
7. TestNG report generated



## 🚀 7. Future Enhancements

| Enhancement         | Description                           |
| ------------------- | ------------------------------------- |
| Allure Reporting    | Rich HTML reporting & dashboards      |
| Parallel Execution  | Faster test cycles                    |
| Schema Validation   | JSON schema-based validation          |
| Data-Driven Tests   | CSV, Excel, JSON based                |
| Mock Server Support | WireMock integration                  |
| Retry Mechanism     | Auto retry on flaky endpoints         |
| CI/CD Pipeline      | Jenkins or GitHub Actions integration |



