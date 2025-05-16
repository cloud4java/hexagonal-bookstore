# Hexagonal Bookstore

A Spring Boot application demonstrating the Hexagonal Architecture (also known as Ports and Adapters) pattern in a bookstore management system.

## 🏗️ Architecture

This project implements the Hexagonal Architecture pattern with the following components:

- **Domain Layer**: Contains the core business logic and entities
- **Application Layer**: Contains ports (interfaces) and services
- **Infrastructure Layer**: Contains adapters and configuration

### Project Structure
```
src/
├── main/
    ├── java/
    │   └── com/example/hexagonalbookstore/
    │       ├── application/          # Application layer
    │       │   ├── ports/           # Interfaces
    │       │   └── services/        # Use cases implementation
    │       ├── domain/              # Domain layer
    │       │   ├── model/          # Domain entities
    │       │   └── exception/       # Domain exceptions
    │       └── infrastructure/      # Infrastructure layer
    │           ├── adapters/       # Input/Output adapters
    │           └── config/         # Configuration
    └── resources/
        ├── application.properties   # Application configuration
        └── openapi.yaml            # API documentation
```

## 🚀 Features

- CRUD operations for books
- RESTful API with proper error handling
- OpenAPI documentation
- Hexagonal Architecture implementation
- JPA persistence
- Validation
- Exception handling

## 🛠️ Technologies

- Java 17
- Spring Boot 3.2.1
- Spring Data JPA
- H2 Database
- SpringDoc OpenAPI
- Maven
- Lombok

## 📝 API Documentation

The API documentation is available through Swagger UI when running the application:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI Spec: `http://localhost:8080/v3/api-docs`

## 🏃‍♂️ Running the Application

1. Ensure you have Java 17 and Maven installed
2. Clone the repository
3. Navigate to the project directory
4. Run the application:
```bash
mvn spring-boot:run
```

## 💡 API Endpoints

- `POST /api/books` - Create a new book
- `GET /api/books` - Get all books
- `GET /api/books/{id}` - Get a book by ID
- `PUT /api/books/{id}` - Update a book
- `DELETE /api/books/{id}` - Delete a book

## 🧪 Testing

Run the tests using:
```bash
mvn test
```

## 📦 Building

Build the application using:
```bash
mvn clean package
```

The JAR file will be generated in the `target` directory.

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

A Spring Boot application demonstrating the Hexagonal Architecture (also known as Ports and Adapters) pattern in a bookstore management system.

## 🏗️ Architecture

This project implements the Hexagonal Architecture pattern with the following components:

- **Domain Layer**: Contains the core business logic and entities
- **Application Layer**: Contains ports (interfaces) and services
- **Infrastructure Layer**: Contains adapters and configuration

### Project Structure
```
src/
├── main/
    ├── java/
    │   └── com/example/hexagonalbookstore/
    │       ├── application/          # Application layer
    │       │   ├── ports/           # Interfaces
    │       │   └── services/        # Use cases implementation
    │       ├── domain/              # Domain layer
    │       │   ├── model/          # Domain entities
    │       │   └── exception/       # Domain exceptions
    │       └── infrastructure/      # Infrastructure layer
    │           ├── adapters/       # Input/Output adapters
    │           └── config/         # Configuration
    └── resources/
        ├── application.properties   # Application configuration
        └── openapi.yaml            # API documentation
```

## 🚀 Features

- CRUD operations for books
- RESTful API with proper error handling
- OpenAPI documentation
- Hexagonal Architecture implementation
- JPA persistence
- Validation
- Exception handling

## 🛠️ Technologies

- Java 17
- Spring Boot 3.2.1
- Spring Data JPA
- H2 Database
- SpringDoc OpenAPI
- Maven
- Lombok

## 📝 API Documentation

The API documentation is available through Swagger UI when running the application:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI Spec: `http://localhost:8080/v3/api-docs`

## 🏃‍♂️ Running the Application

1. Ensure you have Java 17 and Maven installed
2. Clone the repository
3. Navigate to the project directory
4. Run the application:
```bash
mvn spring-boot:run
```

## 💡 API Endpoints

- `POST /api/books` - Create a new book
- `GET /api/books` - Get all books
- `GET /api/books/{id}` - Get a book by ID
- `PUT /api/books/{id}` - Update a book
- `DELETE /api/books/{id}` - Delete a book

## 🧪 Testing

Run the tests using:
```bash
mvn test
```

## 📦 Building

Build the application using:
```bash
mvn clean package
```

The JAR file will be generated in the `target` directory.

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.
