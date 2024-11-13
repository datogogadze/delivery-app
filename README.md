# E-Commerce Microservices Platform

An enterprise-grade e-commerce platform built using microservices architecture, powered by Spring Boot 3.0 and Java 17. This distributed system enables seamless online shopping experiences through independent, scalable services.

## System Architecture

Our platform consists of five core microservices and a shared utilities package, each handling specific business domains:

```
┌─────────────────┐     ┌─────────────────┐     ┌─────────────────┐
│   User Portal   │     │ Product Catalog │     │  Order Engine   │
└────────┬────────┘     └────────┬────────┘     └────────┬────────┘
         │                       │                        │
         │                       │                        │
┌────────┴────────────────────┬─┴────────────────────┬──┴────────┐
│                             │                       │           │
│          Message Bus (Kafka)│         MySQL        │  Common   │
│                             │                       │  Library  │
└─────────────────────────────┴───────────────────┬──┴──────────┘
                                                  │
                              ┌──────────────────┬┴───────────────┐
                              │                  │                │
                         ┌────┴─────┐      ┌─────┴────┐     ┌────┴─────┐
                         │ Delivery │      │   APIs    │     │  Alerts  │
                         └──────────┘      └──────────┘     └──────────┘
```

### Core Services

#### User Portal
- Identity management
- Authentication flows
- Profile customization
- Access control

#### Product Catalog
- Item management
- Inventory control
- Category organization
- Price administration

#### Order Engine
- Cart operations
- Transaction processing
- Order lifecycle
- Payment integration

#### Delivery Manager
- Shipment tracking
- Logistics coordination
- Delivery scheduling
- Address validation

#### Alert System
- Order confirmations
- Shipping updates
- Status notifications
- System announcements

#### Shared Utilities
Common codebase providing:
- Data models
- Security utilities
- Common configurations
- Shared constants

## Technology Stack

- **Runtime**: Java 17
- **Framework**: Spring Boot 3.0
- **Messaging**: Apache Kafka
- **Storage**: MySQL
- **Containerization**: Docker
- **Orchestration**: Docker Compose

## Quick Start

### System Requirements

Ensure these tools are installed and configured:
- Docker Engine
- Docker Compose
- MySQL Server
- Kafka Tools

### Deployment Steps

1. Get the codebase:
```bash
git clone https://github.com/datogogadze/flatrock-task.git
cd flatrock-task
```

2. Prepare shared components:
```bash
cd common
docker build -t flatrock_common .
cd ..
```

3. Launch the platform:
```bash
docker-compose up -d
```

## Development Notes

- Services communicate asynchronously via Kafka events
- Each service maintains its own MySQL database
- Common library must be built before service deployment
- Services are individually scalable through Docker Compose