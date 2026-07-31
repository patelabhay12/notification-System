# Distributed Notification System

A highly scalable, distributed notification system built with a microservices architecture using Spring Boot, Apache Kafka, MongoDB, and Redis.

## Architecture Overview

The system is designed to handle high-throughput notification delivery (e.g., Email, SMS, Push) asynchronously. It leverages an event-driven architecture utilizing Apache Kafka for inter-service communication and decoupled processing.

### Microservices

1. **Notification-API-Service**
   - **Role**: Acts as the API Gateway for external clients to interact with the system.
   - **Responsibilities**: 
     - Exposes REST APIs for creating and managing notification templates.
     - Accepts notification requests and publishes them to Kafka topics for asynchronous processing.
     - Interacts with MongoDB to store configurations, tenants, and template data.

2. **Notification-Proccessor-Service**
   - **Role**: The core engine for processing incoming notification requests.
   - **Responsibilities**: 
     - Consumes raw notification events from Kafka.
     - Resolves templates, applies user preferences, and determines the appropriate delivery channels.
     - Uses Redis for caching, rate limiting, and deduplication.
     - Routes the processed messages to channel-specific Kafka topics.

3. **Channel-Worker-Service**
   - **Role**: The delivery worker responsible for the final dispatch of notifications.
   - **Responsibilities**:
     - Subscribes to channel-specific Kafka topics (e.g., `email-channel`, `sms-channel`).
     - Integrates with third-party providers (e.g., SendGrid, Twilio, Firebase, etc.) to actually send the messages.
     - Handles dispatch logic, retries, and failure scenarios.

4. **Audit-Service**
   - **Role**: Observability, compliance, and tracking.
   - **Responsibilities**:
     - Listens to audit event streams from all other services across the ecosystem.
     - Logs the lifecycle of a notification (e.g., received, processed, sent, failed) for tracking, debugging, and analytical purposes.

## Technology Stack

- **Framework**: Java 21, Spring Boot (Web, Data MongoDB, Data Redis, Kafka)
- **Message Broker**: Apache Kafka (3-node cluster configured with KRaft)
- **Database**: MongoDB (NoSQL storage for templates and application data)
- **Cache**: Redis (in-memory caching and rate limiting) & RedisInsight for UI management
- **Containerization**: Docker & Docker Compose
- **Build Tool**: Maven

## Getting Started

### Prerequisites

- [Docker](https://docs.docker.com/get-docker/) and Docker Compose
- [Java 21](https://jdk.java.net/21/)
- [Maven](https://maven.apache.org/) (or use the provided `./mvnw` wrapper)

### Infrastructure Setup

The project includes a `docker-compose.yml` file to spin up all the necessary infrastructure dependencies locally.

To start the infrastructure, navigate to the root directory and run:

```bash
docker-compose up -d
```

This will start the following services:
- **Redis** on port `6379`
- **RedisInsight** on port `5540` (Access the UI via `http://localhost:5540`)
- **MongoDB** on port `27017`
- **Kafka Cluster** (3 brokers) on ports `9092`, `9093`, `9094`

### Running the Application Services

You can run each service individually using the Maven wrapper. Navigate to each service directory and execute:

```bash
./mvnw spring-boot:run
```

*Note: Make sure to start the infrastructure via Docker Compose before running the Spring Boot applications, as they depend on Kafka, MongoDB, and Redis to boot up successfully.*

## Project Structure

```text
notification-System/
├── Audit-Service/                   # Service for tracking notification lifecycle
├── Channel-Worker-Service/          # Service for dispatching messages via external channels
├── Notification-API-Service/        # Gateway and Template management REST API
├── Notification-Proccessor-Service/ # Core processing, routing, and caching
├── docker-compose.yml               # Infrastructure provisioning (Kafka, Mongo, Redis)
```
