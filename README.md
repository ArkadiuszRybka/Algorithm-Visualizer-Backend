Algorithm Visualizer – Backend

Spring Boot • JWT Security • PostgreSQL • Docker

The backend service for the Algorithm Visualizer application. It provides user management, authentication, and a clean REST API consumed by a standalone JavaFX desktop client. The system uses JWT-based security and integrates with a PostgreSQL database, all containerized with Docker for consistent deployment.

🔎 Overview

The backend delivers the core business logic and exposes endpoints for user registration, authentication, and future algorithm–related operations. The architecture is modular and prepared for incremental expansion.

✨ Features

🔐 User registration & authentication using JWT (Access + Refresh tokens)
👤 User management with validation and unique email constraints
🌐 REST API designed for JavaFX frontend integration
🧩 Modular structure ready for additional algorithm modules or result history
📚 Clean separation between domain, service, and controller layers

🧰 Technology Stack
Component	Technology
Backend	Java 17, Spring Boot
Security	JWT (access & refresh tokens)
Database	PostgreSQL
Build Tool	Maven
Containerization	Docker + Docker Compose
🗄️ Requirements

Docker 20.10+

Docker Compose 2.0+

JDK 17

🚀 Running With Docker

Clone the repository:

git clone https://github.com/your-repo/algorithm-visualizer-backend.git
cd algorithm-visualizer-backend


Create a .env file with the required variables:

JWT_SECRET=your_jwt_secret
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
POSTGRES_DB=visualizer


Start the services:

docker-compose up --build -d


Docker will spin up the backend together with the PostgreSQL database, making the API immediately available.
