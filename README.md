Product Service with Java Spring Boot
This project is a Product Service built using Java Spring Boot. It integrates the Fake Store 
API to fetch product data and implements a custom product service using SQL (with Hibernate JPA) 
for storing and managing products. Additionally, Redis is used to reduce API latency by caching 
product data with expiry settings.

Features
1. Integrated with Fake Store API to fetch external product data.
2. Created custom Product Service using Hibernate JPA for database interactions.
3. Used SQL Database for storing and managing products.
4. Implemented Service Layer Interface for decoupling service logic for Fake Store API and custom product service.
5. Utilized Redis caching to reduce API latency, with an expiry mechanism to maintain data freshness.

**Technologies Used**
Java 17: Programming language for backend development.
Spring Boot: Framework for building and deploying the application.
Spring Data JPA: For working with relational databases and object-relational mapping (ORM).
Hibernate JPA: ORM for database interaction.
Fake Store API: External API to fetch mock product data.
Redis: In-memory data store for caching.
MySQL: Database for storing product information.
Spring Security (optional): For API security if required.