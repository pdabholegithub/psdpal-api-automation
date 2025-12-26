# 🎯 PSDPAL API Automation – Scope Document

## 1. Objective
To define the scope, boundaries, and goals of the PSDPAL API Automation Framework, ensuring predictable, maintainable, and scalable API testing across multiple environments and services.

The framework aims to:
Reduce manual testing effort
Increase API reliability
Enable CI/CD-ready automated API validation


## 2. In Scope
The following items fall under the active scope of API automation:

# 🟦 API Services

ReqRes API
Restful Booker API

# 🔧 Validation & Functional Coverage

Health check / ping endpoints
GET / POST / PUT / DELETE flows
JSON schema validation
Mandatory & optional field validation
Error response validation
Authentication & token handling

# 🧪 Quality & Performance Checks

Response time validation
Header validation
Retry logic for intermittent failures

# 🌐 Environment Management

Automatic base URL switching (DEV, QA, UAT, PROD)
Environment-level configuration handling

# 📊 Execution & Reporting

Framework banner & environment metadata
Test execution via:
Maven
Jenkins
CI/CD Pipelines
Logging & enhanced debugging
Future support for Allure reporting

## 3. Out of Scope
These areas are currently NOT part of the API automation scope:
❌ UI/Web automation
❌ Load, stress, or endurance testing
❌ Security or penetration testing
❌ Database validation
❌ Testing of third-party APIs unless mocked or stubbed
❌ Performance benchmarking at scale


## 4. Success Criteria
The API automation initiative will be considered successful when:
🔹 90–95% API coverage for all included services
🔹 Zero repetitive manual API testing for core flows
🔹 Fully integrated CI/CD pipeline with automated triggers
🔹 Stable, maintainable, modular codebase
🔹 Consistent execution across environments (DEV → PROD)


## 5. Stakeholders
| Role          | Team                                    |
| ------------- | --------------------------------------- |
| QA Automation | Owner & Maintainer                      |
| Developers    | API functional implementation support   |
| DevOps        | CI/CD pipeline integration              |
| Product Team  | Requirement validation & prioritization |


## 6. Last Updated
`**Last Updated:** 🗓️ 27 Nov 2025`
