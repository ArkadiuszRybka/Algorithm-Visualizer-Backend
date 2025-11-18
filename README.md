# Algorithm Visualizer – Backend

**Spring Boot • JWT Security • PostgreSQL • Docker**

The backend service for the **Algorithm Visualizer** application. It provides **user management, authentication**, and a clean **REST API** consumed by a standalone **JavaFX desktop client**. The system uses **JWT-based security** and integrates with **PostgreSQL**, all containerized with **Docker** for consistent deployment.

---

## 🔎 Overview

The backend handles core business logic and exposes endpoints for:  
- User registration  
- Authentication (login)  
- Future algorithm-related operations  

The architecture is modular and prepared for **easy future expansion**.

---

## ✨ Features

- 🔐 **User registration & authentication** using JWT (Access + Refresh tokens)  
- 👤 **User management** with validation and unique email constraints  
- 🌐 **REST API** designed for JavaFX frontend integration  
- 🧩 **Modular structure** ready for additional algorithm modules or result history  
- 📚 **Clean separation** between domain, service, and controller layers  

---

## 🧰 Technology Stack

| Component   | Technology                          |
|------------|------------------------------------|
| Backend    | Java 17, Spring Boot                |
| Security   | JWT (Access & Refresh tokens)       |
| Database   | PostgreSQL                          |
| Build Tool | Maven                               |
| Container  | Docker + Docker Compose             |

---

## 🗄️ Requirements

- Docker 20.10+  
- Docker Compose 2.0+  
- JDK 17  

---

## 🚀 Running With Docker

1. **Clone the repository:**
```bash
git clone https://github.com/your-repo/algorithm-visualizer-backend.git
cd algorithm-visualizer-backend
```

2. **Create a .env file with the required variables:**
```bash
JWT_SECRET=your_jwt_secret
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
POSTGRES_DB=visualizer
```
3. **Start the services:**
```bash
docker-compose up --build -d
```
Docker will start the backend along with the PostgreSQL database, making the API immediately available.
