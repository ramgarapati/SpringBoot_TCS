# SpringBoot_TCS CRUD Application
Java Spring Boot Coding Challenge: Customer Management API.
This is a simple Spring Boot application that demonstrates CRUD (Create, Read, Update, Delete) operations using Spring Boot, Spring Data JPA, and an in-memory H2 database.

## Features

- Create a new Customer
- Retrieve a list of Customer
- Retrieve a single Customer by ID
- Retrieve a single Customer by Name
- Retrieve a single Customer by Email
- Update an existing Customer
- Delete an Customer by ID

## Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- H2 Database (for development/testing)
- Maven > 3.6.3

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6.3 or higher

### Clone the Repository

```bash
git clone https://github.com/ramgarapati/SpringBoot_TCS.git
cd SpringBoot_TCS

mvn spring-boot:run

H2 Database Console
URL: http://localhost:8080/h2-console

spring.datasource.driver-class-name=org.h2.Driver

JDBC URL: jdbc:h2:mem:testdb

User Name: sa

Password: password

API Endpoints
Assume the entity is User with fields like id, name, and email.

### API Endpoints

| Method | Endpoint                             | Description          |
| ------ | -----------------                    | -------------------- |
| GET    | `/springpoc/createCustomer`          | Get all users        |
| GET    | `/springpoc/customers/{id}`          | Get user by ID       |
| GET   | `/springpoc/customers?name=**`        | Create a new user    |
| GET    | `/springpoc/customers?email=**`      | Update existing user |
| PUT    | `/springpoc/updateCustomer/{id}`     | Update existing user |
| DELETE | `/springpoc/deleteCustomer/{id}`     | Delete user by ID    |

Sample JSON for POST/PUT

{
    "name":"Ram Garapati",
    "email":"ramgarapati9@gmail.com",
    "annualSpend":2000.00,
    "lastPurchaseDate":"05/14/2025"
}

Project Structure

src/
├── main/
│   ├── java/
│   │   └── com.ram.tcs.customer.management/
│   │       ├── controller/
│   │       ├── entity/
│   │       ├── repository/
│   │       ├── service/
│   │       └── SpringBootCustomerManagementProjectApplication.java
│   └── resources/
│       ├── application.properties
│       └── data.sql

---

Would you like the actual code (controller, entity, service, etc.) as well to go with this README?

API Documentation with OpenAPI
http://localhost:8080/swagger-ui/index.html
http://localhost:8080/api-docs

## License