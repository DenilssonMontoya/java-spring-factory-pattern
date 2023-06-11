# Java Spring Factory Pattern Example

This is a Java Spring project that demonstrates the implementation of the Factory pattern. The Factory pattern is a creational design pattern that provides an interface for creating objects but allows subclasses to decide which class to instantiate.

## Prerequisites

To run this project, you need to have the following installed:

- Java 17
- Apache Maven

## Getting Started

Follow the steps below to get a local copy of the project and run it on your machine.

1. Clone the repository:

   ```bash
   git clone https://github.com/DenilssonMontoya/java-spring-factory-pattern.git
   ```

2. Navigate to the project directory:

   ```bash
   cd java-spring-factory-pattern
   ```

3. Build the project using Maven:

   ```bash
   ./mvnw clean install
   ```

4. Run the application:

   ```bash
   ./mvnw spring-boot:run
   ```

5. The application should now be running on `http://localhost:8080`.

## Implementation Details

This project demonstrates the Factory pattern by providing an example of a `FeeCalculatorIF` interface and its concrete implementations: `CorporateAccountFeeCalculator` and `PersonalAccountFeeCalculator`. The `FeeCalculatorFactory` class acts as the factory, responsible for creating instances of different types of fee calculator based on a provided account type.

The key components of this implementation are as follows:

- `FeeCalculatorIf` interface: Defines the contract for all fee-calculators in the system. It includes the method `calculateFee()`.

- `CorporateAccountFeeCalculator` and `PersonalAccountFeeCalculator` classes: Concrete implementations of the `FeeCalculatorIf` interface. They provide specific behavior for fee calculation.

- `AccountType` annotation type: Single value annotation that allows us to assign a value related to the account types.

- `FeeCalculatorFactory` class: The factory class that encapsulates the logic for creating instances of different fee calculator types based on the provided account type.

- `AccountController` class: A Spring MVC controller that demonstrates the usage of the factory pattern. It exposes two REST endpoints `/account/v1/create` and `/account/v1/calculate-fee/{accountId}`, the first one allows to create an account and the second one accepts an account id then returns the result of invoking the `calculateFee()` method on the corresponding fee-calculator instance created by the factory.

![diagram.png](img%2Fdiagram.png)