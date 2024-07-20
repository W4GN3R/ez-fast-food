
# Estrutura
br.com.fiap.ez.fastfood
├── adapters
│   ├── in
│   │   └── controller
│   │       ├── CustomerController.java
│   └── out
│       └── repository
│           ├── CustomerRepositoryImpl.java
│           └── JpaCustomerRepository.java
├── application
│   ├── ports
│   │   ├── in
│   │   │   └── CustomerService.java
│   │   └── out
│   │       └── CustomerRepository.java
│   ├── service
│   │   └── CustomerServiceImpl.java
│   └── dto
│       └── CustomerDTO.java
├── config
│   └── exception
│       └── CustomExceptionHandler.java
|        └── ErrorResponse.java
│   └── security
│       └── SecurityConfig.java
│        OpenApiConfig.java
├── domain
│   └── model
│       └── Customer.java
├── APIApplication.java


# Fluxo de chamada/referência entre as classes

1. Customer.java (class): Domain -> model

2. CustomerJpaRepository.java (Interface): Adapters -> Out -> repository

3. CustomerRepository.java (Interface): Application -> Ports -> Out

4. CustomerRepositoryImpl.java (class): Adapters -> Out -> repository

5. CustomerService.java (Interface): Application -> Ports -> In

6. CustomerServiceImpl.java (class): Application -> Service

7. CustomerController.java (class): Adapters -> In -> controller

8. APIApplication.java (class): APIApplication



### Explanation of the Structure

Here’s how each part of your structure fits into hexagonal architecture:

#### adapters.in.controller
This package contains classes that handle inbound interactions, such as HTTP requests.

- **CustomerController.java**: Handles HTTP requests related to customers.
- **ErrorResponse.java**: Represents error responses for the controller.

#### adapters.out.repository
This package contains classes that handle outbound interactions, such as database access.

- **CustomerRepositoryImpl.java**: Custom repository implementation for specific data access logic.
- **JpaCustomerRepository.java**: Spring Data JPA repository for the Customer entity.

#### application.port.in
This package defines inbound ports, which are interfaces for use cases.

- **CustomerService.java**: Interface defining customer-related use cases.

#### application.port.out
This package defines outbound ports, which are interfaces for persistence operations.

- **CustomerRepository.java**: Interface defining operations for customer persistence.

#### application.service
This package contains service implementations that handle business logic.

- **CustomerServiceImpl.java**: Implementation of the CustomerService interface, containing business logic for customers.

#### config.exception
This package contains configuration-related classes, including global exception handling.

- **CustomExceptionHandler.java**: Handles global exceptions and returns appropriate HTTP responses.

#### domain.model
This package contains the core domain model.

- **Customer.java**: The Customer entity representing the core domain logic.

#### dto
This package contains Data Transfer Objects (DTOs), which are used to transfer data between layers.

- **CustomerDTO.java**: Represents the data structure for customer responses, used to control what data is returned in the response.

#### root
- **APIApplication.java**: Main class for bootstrapping the Spring Boot application.

