# Company management

Company equipment management application.

# Features
* Displaying employees
* Adding new employees
* Searching employees
* Editing employees
* Displaying equipment
* Adding new equipment
* Searching equipment
* Editing equipment
* Displaying the rental history of an equipment
* Assigning equipment to a user
* Returning equipment
* Searching categories by category and names

# REST API Documentation
http://localhost:8080/swagger-ui/
![3](https://user-images.githubusercontent.com/36209711/164942539-c1d50b1c-960d-4a68-beae-29a940eb2521.PNG)

# Database - H2
Database works on `localhost:8080/h2-console` and JDBC URL: `jdbc:h2:mem:test`.
User name: `sa` and no password.
All configurations with the database are in the `application.properties` file.
Data are loaded from data.sql.
Schemas are created by Hibernate.

# Setup
Clone this repo to your desktop. Run applications using Spring Boot. You will then be able to access it at `localhost:8080`.

# Project is created with
* Java 11
* Spring Boot
* Spring Data JPA
* Spring Web
* Swagger
* Maven
* H2
* Liquibase
