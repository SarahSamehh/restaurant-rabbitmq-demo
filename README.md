# Restaurant Order System

A messaging demo using **Spring Boot**, **RabbitMQ**, and **Docker**.  
An order is placed on the frontend, sent through a queue, and consumed by a kitchen service.

---

## How it works

```
Frontend → Order Service → RabbitMQ → Kitchen Service
```

1. Frontend sends an order to the Order Service via API
2. Order Service creates and saves the order, then publishes an event to RabbitMQ
3. RabbitMQ routes the message to the Kitchen Service
4. Kitchen Service consumes and stores received events in memory
5. Frontend retrieves data from Kitchen Service via API

---

## Services

| Service | Port | Role |
|---|---|---|
| Order Service | `8081` | Creates orders & publishes events |
| Kitchen Service | `8082` | Consumes messages & stores them |
| RabbitMQ | `5672` / `15672` | Message broker |

- **Queue:** `kitchen.order.queue`
- **Exchange:** `restaurant-exchange`
- **Routing key:** `order.created`

---

## Run locally

**1. Build services**
```bash
mvn clean package -DskipTests
```

**2. Start system**
```bash
docker compose up --build
```

> Make sure Docker Desktop is running before executing the compose command.

**Access:**

| | URL |
|---|---|
| Frontend | http://localhost:5500 |
| Order Service | http://localhost:8081 |
| Kitchen Service | http://localhost:8082 |
| RabbitMQ Dashboard | http://localhost:15672 — `admin / admin123` |

---

## Screenshots

### UI
<img width="1600" height="802" alt="image" src="https://github.com/user-attachments/assets/20a969ce-9b55-410c-bba5-b0c36e622529" />


### Sending an order & Kitchen receiving the message
<img width="1917" alt="Order flow" src="https://github.com/user-attachments/assets/298dabe8-c8b2-4710-8049-d064873a0f50" />

---

## Stack

| | |
|---|---|
| Language & Framework | Java 21 & Spring Boot 4 |
| Messaging | Spring AMQP (RabbitMQ) |
| Database | H2 (in-memory) |
| Containerization | Docker & Docker Compose |
