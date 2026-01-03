# 🧪 PSDPAL API Automation – Test Plan

## 1. Test Strategy

The PSDPAL API Automation Framework follows a hybrid strategy combining:
Functional API Testing
Schema validation
Boundary & negative testing
Authentication validation
Environment-aware execution (DEV, QA, UAT, PROD)
Regression automation for stable endpoints
The focus is on stability, reusability, CI/CD readiness, and consistent coverage across services like:
ReqRes API
Restful Booker API

## 2. Test Types Covered
✔ Functional Testing
Status codes
Response payload validation
Headers validation
CRUD operations testing

✔ Integration Testing
API-to-API interaction
Upstream & downstream dependency validation

✔ Regression Testing
Full set execution on every PR merge
Ensures no breaking changes after updates

✔ Smoke Testing
Lightweight checks on:
/ping
/auth
Key endpoints in ReqRes

## 3. Tools & Frameworks
Component	Tool / Technology
API Client	RestAssured
Build Tool	Maven
Test Runner	TestNG
Reporting	TestNG HTML + CI Logs
Language	Java 21
CI/CD	Jenkins / GitHub Actions

## 4. Test Execution
Local Execution
mvn test -Denv=dev

CI/CD Execution (Jenkins / GitHub Actions)
Triggered automatically via webhook or scheduled run
Environment passed dynamically using pipeline variables
Auto-publishing of TestNG report and logs

## 5. Entry Criteria

API contract / Swagger / Postman collection available
Endpoint stable and accessible
Test data prepared for required flows
Required environment up & running (DEV/QA/UAT/PROD)
Authentication tokens available (if required)

## 6. Exit Criteria

All Critical and High severity tests passed
No blocker or major defects open
TestNG HTML report generated & archived in CI
Logs available for debugging
Endpoint coverage ≥ planned baseline

## 7. Risks & Mitigations
Risk	                          Impact	                          Mitigation
Environment instability	          Test failures	                      Use retry logic, health checks
Token expiration	              Authentication failures	          Auto-regenerate using TokenManager
Third-party dependency failures	  API unavailability	              Mock where possible
Rate limits / throttling	      Unexpected failures	              Apply delay + backoff strategy

## 8. Deliverables

STAGING / QA
TestNG HTML Report
Execution logs
API coverage report
Schema validation summaries

UAT
Test summary documentation
Logs + Jenkins artifacts
Coverage and regression insights

PROD
Smoke test results
Logs
Coverage summary
CI/CD artifacts
