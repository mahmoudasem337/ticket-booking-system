# 🎟️ Ticket Booking System
This project is a **Microservices-based Ticket Booking System** built with **Spring Boot** and modern distributed systems components.  
It demonstrates a complete microservice architecture with scalability, fault tolerance, and real-time synchronization.

---

## Architecture Overview

<img width="1320" height="810" alt="image" src="https://github.com/user-attachments/assets/07bbd4dc-b4f6-42f0-8c32-34bc3850de56" />


### Key Components:
- **Spring Cloud Gateway**
  - API Gateway for routing requests
  - Rate Limiting & Centralized Entry Point
- **Netflix Eureka**
  - Service Discovery for dynamic scaling and inter-service communication
- **Order Service**
  - Manages order placement.
- **Booking Service**
  - Handles booking logic.
- **Inventory Service**
  - Tracks available tickets.
- **Search Service**
  - Provides advanced search via **Elasticsearch**.
- **Data Ingestion Pipeline**
  - **Debezium** captures MySQL changes via **Binary Logs**
  - **Kafka** streams change events
  - **Search Service** updates Elasticsearch to up-to-date search
- **Redis**
  - Provides caching for high-performance lookups
  - Used as a **Distributed Lock Manager** to prevent overselling.

---
## Database Design
<img width="942" height="677" alt="image" src="https://github.com/user-attachments/assets/5b8c357f-ac35-48d8-872d-b330cd5e7a93" />

## Features

-  **Microservices Architecture** (independent deployable services)  
-  **Service Discovery & Load Balancing** with Eureka  
-  **Centralized API Gateway** with Rate Limiting & Routing  
-  **Booking & Order Management** with transaction safety  
-  **Inventory Management**  
-  **Real-time Data Synchronization** (CDC with Debezium + Kafka)  
-  **Advanced Search** powered by Elasticsearch  
-  **Caching Layer** for performance optimization
-  Database schema migrations are managed and versioned using **Flyway**.

---

## Tech Stack

<p align="center">
  <img src="https://www.vectorlogo.zone/logos/java/java-horizontal.svg" alt="Java" height="40"/>
  <img src="https://www.vectorlogo.zone/logos/springio/springio-ar21.svg" alt="Spring Boot" height="40"/>
  <img src="https://www.vectorlogo.zone/logos/mysql/mysql-horizontal.svg" alt="MySQL" height="40"/>
  <img src="https://www.vectorlogo.zone/logos/apache_kafka/apache_kafka-ar21.svg" alt="Kafka" height="40"/>
  <img src="https://www.vectorlogo.zone/logos/elastic/elastic-ar21.svg" alt="Elasticsearch" height="40"/>
  <img src="https://www.vectorlogo.zone/logos/redis/redis-ar21.svg" alt="Redis" height="40"/>
  <img src="https://www.vectorlogo.zone/logos/docker/docker-official.svg" alt="Docker" height="40"/>
  <img src="https://www.vectorlogo.zone/logos/netflix/netflix-ar21.svg" alt="Eureka" height="40"/>
  <img width="80" height="80" alt="1-removebg-preview" src="https://github.com/user-attachments/assets/0966a12b-ec21-436b-baa8-1ce806b26fd6" />
  <img width="80" height="80" alt="2-removebg-preview" src="https://github.com/user-attachments/assets/011e6647-70e0-40b8-93ce-5166da61b726" />

</p>

---
## Screenshots

### Eureka Server
<img width="1884" height="929" alt="image" src="https://github.com/user-attachments/assets/e0bfee9f-17c7-4a3e-a8a4-1de9de2daed0" />

### Elasticsearch
<img width="1917" height="983" alt="image" src="https://github.com/user-attachments/assets/b46507a6-2eeb-431b-a48c-d9b82528d3dc" />
<img width="1901" height="978" alt="image" src="https://github.com/user-attachments/assets/4b0e2269-5845-497c-b178-12828b6d0136" />

### Kafka
<img width="1889" height="915" alt="image" src="https://github.com/user-attachments/assets/ff71c2c2-c379-4a38-a33e-d4ea39434db3" />
<img width="1881" height="919" alt="image" src="https://github.com/user-attachments/assets/2de34b3d-551c-4cf1-a548-180e2ee4a370" />
<img width="1880" height="915" alt="image" src="https://github.com/user-attachments/assets/fc2407c0-61ab-403c-a652-bf8c1f81356e" />

---
## Getting Started

### Prerequisites
Make sure you have installed:
- [Java 17+]
- [Maven 3.9+]   
- [MySQL 8+]
- [Apache Kafka]
- [Zookeeper]
- [Redis]
- [Elasticsearch 8+] 
- [Debezium]
- [Flyway] 
- [Postman]


### Clone Repository
```bash
git clone https://github.com/mahmoudasem337/ticket-booking-system.git
cd ticket-booking-system
