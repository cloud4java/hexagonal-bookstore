# Hexagonal Architecture Documentation

## Project Structure
```
com.example.hexagonalbookstore/
├── domain/
│   └── model/
├── application/
│   ├── ports/
│   │   ├── input/
│   │   └── output/
│   └── services/
└── infrastructure/
    └── adapters/
        ├── input/
        └── output/
```

## Architecture Layers Explanation

### 1. Domain Layer (`domain/`)
- **Purpose**: Contains the core business logic and entities
- **Content**: Business entities and value objects
- **Characteristics**:
  - Independent of frameworks and external concerns
  - Contains pure business logic
  - No dependencies on other layers
- **Example**: `Book.java` - Core domain entity representing a book in our system

### 2. Application Layer (`application/`)
- **Purpose**: Orchestrates the flow of data and coordinates business logic
- **Contains**:
  - **Ports**: Interfaces defining the contracts for incoming and outgoing operations
  - **Services**: Implementation of business use cases

#### 2.1 Ports (`ports/`)
- **Input Ports** (`input/`):
  - Define how the outside world can interact with our application
  - Used by UI or API controllers
  - Example: `BookServicePort.java` - Defines operations available to the outside world

- **Output Ports** (`output/`):
  - Define interfaces for external dependencies (like databases)
  - The application core depends on these abstractions
  - Example: `BookRepositoryPort.java` - Defines how we interact with data storage

#### 2.2 Services (`services/`)
- Implements the business use cases
- Coordinates between input ports and output ports
- Example: `BookService.java` - Implements business logic for book operations

### 3. Infrastructure Layer (`infrastructure/`)
- **Purpose**: Contains all external concerns and implementations
- **Contains**:
  - Technical details
  - Framework configurations
  - External integrations
  
#### 3.1 Adapters (`adapters/`)
- **Input Adapters** (`input/`):
  - Handle incoming requests
  - Convert external data format to internal format
  - Example: `BookController.java` - REST controller implementing the input port

- **Output Adapters** (`output/`):
  - Implement output ports
  - Handle external dependencies like databases
  - Example: `BookJpaAdapter.java` - JPA implementation of the repository port

## Benefits of This Architecture

1. **Separation of Concerns**
   - Clear boundaries between business logic and technical details
   - Each layer has a single responsibility

2. **Testability**
   - Business logic can be tested in isolation
   - Easy to mock external dependencies

3. **Flexibility**
   - Easy to change technical implementations without affecting business logic
   - Can swap databases or UI frameworks with minimal impact

4. **Maintainability**
   - Clear structure makes the codebase easier to understand
   - New developers can quickly grasp the system organization

5. **Independence**
   - Business logic is framework-agnostic
   - Can evolve the domain model without changing external dependencies

## Dependency Rule

The dependencies flow from outside to inside:
- Infrastructure → Application → Domain
- Inner layers don't know about outer layers
- All dependencies point inward

## Design Decisions

1. **Usage of Spring Boot**
   - While using Spring Boot, we keep it at the infrastructure layer
   - Domain and application layers remain framework-agnostic

2. **JPA Entity**
   - Although we use JPA annotations in the domain entity, in a stricter implementation, we could separate the domain entity from the persistence entity

3. **Package Structure**
   - Packages reflect the hexagonal architecture layers
   - Clear separation between ports and adapters

## How to Extend

1. **Adding New Features**
   - Create domain entities in domain layer
   - Define ports in application layer
   - Implement adapters in infrastructure layer

2. **Changing Database**
   - Only need to create new output adapter
   - No changes needed in domain or application layers

3. **Adding New UI**
   - Create new input adapter
   - Rest of the application remains unchanged
