
```markdown
# 🍽️ Food Ordering System (Spring Boot)

A robust, REST-based online food ordering system built with **Java 25** and **Spring Boot 4**. This system supports multi-restaurant onboarding, real-time capacity management, and dynamic restaurant selection strategies using an in-memory architecture.

---

## 📌 Project Overview
This project demonstrates a clean implementation of a backend system that handles complex business logic like order-to-restaurant assignment. It is designed with **SOLID principles** and **Clean Architecture** to be highly extensible and interview-ready.

### 🎯 Key Features
* **Restaurant Onboarding:** Register restaurants with specific menus, ratings, and maximum concurrent order capacities.
* **Dynamic Menu Management:** Add new items or update prices (deletions restricted to maintain order history integrity).
* **Smart Order Assignment:** Automatically routes orders based on:
    * **Lowest Bill Cost:** Finds the most economical option for the user.
    * **Highest Rating:** Prioritizes quality based on restaurant ratings.
* **Capacity Management:** Thread-safe logic to track active orders and release capacity upon completion.
* **Pluggable Strategy Pattern:** Easily swap or add new selection algorithms.
* **Zero-Dependency Persistence:** Pure in-memory implementation using optimized Java Collections.

---

## 🧱 Architecture & Design
The system follows a **Layered Architecture** to ensure separation of concerns:
`Controller` → `Service` → `Domain` → `Repository`

### Design Patterns Used
* **Strategy Pattern:** Decouples the selection logic (`LowestCostStrategy`, `HighestRatingStrategy`) from the order service.
* **DTO Pattern:** Ensures internal domain models are not exposed directly to the API.
* **Singleton/In-Memory Repo:** Simulates a database environment for high-performance retrieval.
* **Global Exception Handling:** Centralized error management using `@ControllerAdvice`.

---

## 📁 Project Structure
```text
food-ordering-system
│
├── src/main/java/com/example/foodordering
│   ├── controller     # REST API Endpoints
│   ├── service        # Business logic & Strategy orchestration
│   ├── domain         # Core Entities (Restaurant, Order, Menu)
│   ├── dto            # Data Transfer Objects for API Requests/Responses
│   ├── repository     # In-memory data storage logic
│   ├── strategy       # Selection Strategy implementations
│   ├── exception      # Custom exceptions & Global handler
│   └── util           # Constants and Helpers
│
└── pom.xml            # Project dependencies

```

---

## 🚀 Tech Stack

* **Language:** Java 25
* **Framework:** Spring Boot 4.x
* **Build Tool:** Maven

---

## 🔌 API Endpoints

### Restaurant Management

| Method | Endpoint | Description |
| --- | --- | --- |
| `POST` | `/restaurants` | Onboard a new restaurant |
| `PUT` | `/restaurants/{name}/menu/add` | Add new items to a menu |
| `PUT` | `/restaurants/{name}/menu/update` | Update existing item prices |
| `GET` | `/restaurants` | List all registered restaurants |

### Order Management

| Method | Endpoint | Description |
| --- | --- | --- |
| `POST` | `/orders` | Place a food order (triggers auto-assignment) |
| `PUT` | `/orders/{orderId}/complete` | Mark order as COMPLETED & release capacity |

---

## 📦 Sample Request Payloads

### 1. Onboard Restaurant

```json
{
  "name": "R3",
  "rating": 4.9,
  "maxOrders": 5,
  "menu": {
    "Idli": 15,
    "Dosa": 30
  }
}

```

### 2. Place Order

```json
{
  "orderId": "O1",
  "user": "Ashwin",
  "items": {
    "Idli": 3,
    "Dosa": 1
  },
  "selectionType": "LOWEST_COST"
}

```

---

## 🔄 Order Assignment Logic

The system follows a strict filtering and selection process:

1. **Eligibility Check:** Filters restaurants that possess all requested items in their menu.
2. **Capacity Check:** Filters out restaurants currently at their `maxOrders` limit.
3. **Strategy Application:** * `LOWEST_COST`: Calculates `sum(price * quantity)` for all candidates and picks the minimum.
* `HIGHEST_RATING`: Picks the candidate with the highest rating.


4. **Execution:** Atomically increments the restaurant's active order count.

---

## 🛠️ Getting Started

1. **Clone the repo:** `git clone https://github.com/your-username/food-ordering-system.git`
2. **Build:** `mvn clean install`
3. **Run:** `mvn spring-boot:run`
4. **Test:** Use Postman or cURL to hit `http://localhost:8080`.

---

## 🔮 Future Enhancements

* Integrate **Spring Data JPA** with PostgreSQL/MySQL for persistence.
* Add **Spring Security** (JWT) for user and owner roles.
* Add **Real-time Notifications** via WebSockets when an order is completed.

