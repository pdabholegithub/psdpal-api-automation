# 🔹 PSDPAL API Endpoints List

A consolidated catalog of all API endpoints automated under the PSDPAL API Automation Framework.

---

## 🔵 ReqRes Endpoints

| Endpoint               | Method | Description          |
|--------------------|--------|--------------------------|
| `/api/users`       | GET    | Get list of users       |
| `/api/users/{id}`  | GET    | Get user details by ID  |
| `/api/users`       | POST   | Create a new user       |
| `/api/users/{id}`  | PUT    | Update existing user    |
| `/api/users/{id}`  | DELETE | Delete user             |
| `/api/register`    | POST   | Register a new user     |
| `/api/login`       | POST   | Login and obtain token   |
| `/api/unknown`     | GET    | Fetch list of resources  |

---

## 🟢 RestfulBooker Endpoints

| Endpoint          | Method | Description                   |
|-------------------|--------|-------------------------------|
| `/auth`           | POST   | Generate authentication token |
| `/booking`        | POST   | Create new booking            |
| `/booking/{id}`   | GET    | Retrieve booking by ID        |
| `/booking`        | GET    | Filter bookings via queries   |
| `/booking/{id}`   | PUT    | Full update to booking        |
| `/booking/{id}`   | PATCH  | Partial update to booking     |
| `/booking/{id}`   | DELETE | Delete booking                |
| `/ping`           | GET    | Service health check          |

---

## 🌍 Environment-wise Base URLs

| Environment | ReqRes Base URL         | RestfulBooker Base URL                |
|-------------|--------------------------|------------------------------------ |
| DEV         | `dev.reqres.baseUrl`     | `dev.restfulbooker.baseUrl`     |
| QA          | `qa.reqres.baseUrl`      | `qa.restfulbooker.baseUrl`      |
| UAT         | `uat.reqres.baseUrl`     | `uat.restfulbooker.baseUrl`     |
| PROD        | `prod.reqres.baseUrl`    | `prod.restfulbooker.baseUrl`    |

