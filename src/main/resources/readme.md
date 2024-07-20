# Estrutura
```
br.com.fiap.ez.fastfood
├── adapters
│   ├── in
│   │   ├── controller
│   │   │   ├── CustomerController.java
│   │   │   ├── ErrorResponse.java
│   │   │   └── ProductController.java
│   └── out
│       └── repository
│           ├── CustomerJpaRepository.java
│           ├── CustomerRepositoryImpl.java
│           ├── ProductJpaRepository.java
│           └── ProductRepositoryImpl.java
├── application
│   ├── dto
│   │   ├── CustomerDTO.java
│   │   └── ProductDTO.java
│   ├── port
│   │   ├── in
│   │   │   ├── CustomerService.java
│   │   │   └── ProductService.java
│   │   └── out
│   │       ├── CustomerRepository.java
│   │       └── ProductRepository.java
│   └── service
│       ├── CustomerServiceImpl.java
│       └── ProductServiceImpl.java
├── config
│   └── exception
│       ├── BusinessException.java
│       └── CustomExceptionHandler.java
├── domain
│   └── model
│       ├── Category.java
│       ├── Customer.java
│       └── Product.java
└── APIApplication.java

# Fluxo de chamada/referência entre as classes

1. Customer.java (class): Domain -> model

2. CustomerJpaRepository.java (Interface): Adapters -> Out -> repository

3. CustomerRepository.java (Interface): Application -> Ports -> Out

4. CustomerRepositoryImpl.java (class): Adapters -> Out -> repository

5. CustomerService.java (Interface): Application -> Ports -> In

6. CustomerServiceImpl.java (class): Application -> Service

7. CustomerController.java (class): Adapters -> In -> controller

8. Product.java (class): Domain -> model

9. ProductJpaRepository.java (Interface): Adapters -> Out -> repository

10. ProductRepository.java (Interface): Application -> Ports -> Out

11. ProductRepositoryImpl.java (class): Adapters -> Out -> repository

12. ProductService.java (Interface): Application -> Ports -> In

13. ProductServiceImpl.java (class): Application -> Service

14. ProductController.java (class): Adapters -> In -> controller

15. APIApplication.java (class): APIApplication




### Explanation of the Structure

Here’s how each part of your structure fits into hexagonal architecture:

### `adapters.in.controller`

This package contains classes responsible for handling inbound interactions, such as HTTP requests. Classes here handle incoming requests and route them to the appropriate processing within the application.

- **CustomerController.java**: Controls requests related to customers, such as creating, updating, listing, or deleting customers.
- **ProductController.java**: Controls requests related to products, such as creating, updating, listing, or deleting products.
- **ErrorResponse.java**: Represents standardized error responses that can be sent back to clients in case of failures or errors.

### `adapters.out.repository`

This package contains classes responsible for handling outbound interactions, such as database access or external systems. Concrete repository implementations reside here, providing methods for data persistence and retrieval.

- **CustomerRepositoryImpl.java**: Implements the CustomerRepository interface to provide specific persistence operations for customers.
- **ProductRepositoryImpl.java**: Implements the ProductRepository interface to provide specific persistence operations for products.
- **CustomerJpaRepository.java**: Spring Data JPA interface for interacting with the Customer entity in the database.
- **ProductJpaRepository.java**: Spring Data JPA interface for interacting with the Product entity in the database.

### `application.port.in`

This package defines inbound ports for the application, i.e., interfaces representing the application's use cases. Interfaces here define contracts that service classes must implement to handle specific use cases.

- **CustomerService.java**: Interface defining operations that can be performed on customer entities, such as creating, updating, listing, or deleting customers.
- **ProductService.java**: Interface defining operations that can be performed on product entities, such as creating, updating, listing, or deleting products.

### `application.port.out`

This package defines outbound ports for the application, i.e., interfaces defining operations needed to access and persist data. These interfaces are implemented by the output adapters (`out`).

- **CustomerRepository.java**: Interface defining persistence operations for customer entities.
- **ProductRepository.java**: Interface defining persistence operations for product entities.

### `application.service`

This package contains concrete implementations of application services, where specific business logic is implemented. Services use the contracts defined in the inbound ports to interact with the system and perform specific operations.

- **CustomerServiceImpl.java**: Concrete implementation of CustomerService, containing business logic related to customers.
- **ProductServiceImpl.java**: Concrete implementation of ProductService, containing business logic related to products.

### `config.exception`

This package contains classes related to exception handling within the application. This includes custom exceptions that may be thrown during execution and a global exception handler that translates these exceptions into appropriate HTTP responses to provide feedback to clients.

- **BusinessException.java**: Custom exception to represent specific business errors that may occur during execution.
- **CustomExceptionHandler.java**: Class that intercepts global exceptions and translates them into appropriate HTTP responses to provide feedback to clients.

### `domain.model`

This package contains the core domain entities of the application, representing the core domain model. Entities encapsulate the state and essential behavior of the application, representing real-world concepts fundamental to the application's operation.

- **Customer.java**: Entity representing a customer in the application, encapsulating its attributes and related methods.
- **Product.java**: Entity representing a product in the application, encapsulating its attributes and related methods.
- **Category.java**: Enumeration defining possible categories for products, such as "Burger", "Order Side", "Drink", and "Dessert".

### `dto`

This package contains Data Transfer Objects (DTOs), which are data structures used to transfer information between different layers of the application. DTOs help control which data is sent or received in response to external requests.

- **CustomerDTO.java**: DTO defining the data structure to represent customer information in API responses.
- **ProductDTO.java**: DTO defining the data structure to represent product information in API responses.

### `root`

- **APIApplication.java**: Main class that initializes the Spring Boot application. This class configures and starts all components of the application, allowing it to run and respond to HTTP requests.
