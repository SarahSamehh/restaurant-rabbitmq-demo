🍔 Restaurant Order System
A messaging demo using Spring Boot, RabbitMQ, and Docker.
An order is placed on the frontend, sent through a queue, and consumed by a kitchen service.

How it works
Frontend → Order Service → RabbitMQ → Kitchen Service

Frontend sends an order to the Order Service via API
Order Service creates and saves order, then publishes an event to RabbitMQ
RabbitMQ routes the message to the Kitchen Service
Kitchen Service listens, consumes and stores received events in memory
Frontend retrieves data from Kitchen Service via API 


Services
Service	Port	Role
Order Service	8081	Creates orders & publishes events
Kitchen Service	8082	Consumes messages & stores them
RabbitMQ	5672 / 15672	Message broker

Queue: kitchen.order.queue
Exchange: restaurant-exchange
Routing key: order.created

Run locally
1. Build services
mvn clean package -DskipTests
2. Start system
docker compose up --build

Make sure Docker Desktop is running before executing the compose command.

Access:

Frontend → http://localhost:5500
Order Service → http://localhost:8081
Kitchen Service → http://localhost:8082
RabbitMQ Dashboard → http://localhost:15672 (admin / admin123)


Screenshots
UI
<img width="1917" height="963" alt="image" src="https://github.com/user-attachments/assets/e5c68f60-a992-40d4-9748-1d7852ab842e" />

Sending an order, Kitchen receiving the message
<img width="1917" height="963" alt="image" src="https://github.com/user-attachments/assets/298dabe8-c8b2-4710-8049-d064873a0f50" />


Stack
Core Framework: Java 21 & Spring Boot 4
Messaging: Spring AMQP (RabbitMQ)
Database: H2 Database (In-Memory)
Containerization: Docker & Docker Compose
Docker + Docker Compose

P
