# Spring Boot Supermarket API 🛒

API RESTful untuk sistem transaksi jual beli supermarket, dibangun dengan **Spring Boot 4.0.0** dan **Java 25**. Project ini cocok untuk pembelajaran dan portfolio mahasiswa yang ingin memahami clean architecture, HATEOAS, dan microservices.

[![Java](https://img.shields.io/badge/Java-25-orange.svg)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![Build Status](https://github.com/fathurcoding/spring-boot-supermarket-api/workflows/CI/CD%20Pipeline/badge.svg)](https://github.com/fathurcoding/spring-boot-supermarket-api/actions)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

**🔗 Quick Links:**
- 📚 [API Documentation (Swagger UI)](http://localhost:8080/swagger-ui.html) - Interactive API docs
- 🔍 [API Specs (OpenAPI)](http://localhost:8080/v3/api-docs) - JSON/YAML format
- 🧪 [HAL Explorer](http://localhost:8080/) - HATEOAS browser


---

## 📖 Table of Contents

- [Features](#-features)
- [Architecture](#-architecture)
- [Prerequisites](#-prerequisites)
- [Quick Start](#-quick-start)
- [Database Schema](#-database-schema)
- [API Endpoints](#-api-endpoints)
- [Development](#-development)
- [Testing](#-testing)
- [Deployment](#-deployment)
- [Contributing](#-contributing)
- [License](#-license)

---

## ✨ Features

### Core Features
- ✅ RESTful API dengan HATEOAS (Hypermedia as the Engine of Application State)
- ✅ **Interactive API Documentation** dengan Swagger/OpenAPI UI
- ✅ JWT Authentication dengan OAuth2 Resource Server
- ✅ **Mock Login Endpoint** untuk demo JWT dengan role-based access
- ✅ Multi-environment configuration (local, docker)
- ✅ Docker & Docker Compose support
- ✅ Database migration dengan SQL scripts
- ✅ HAL Explorer untuk API testing interaktif
- ✅ Global exception handling
- ✅ Clean architecture pattern
- ✅ **Automated CI/CD** dengan GitHub Actions
- ✅ **DTO Pattern** untuk validasi input dan keamanan data (Phase 2)
- ✅ **Pagination & Sorting** support untuk semua list endpoint (Phase 2)
- ✅ **Complex Business Logic** (Stok manajemen otomatis & kalkulasi diskon member) (Phase 2)
- ✅ **Reporting Module** (Daily Revenue & Top Selling Products) (Phase 3)
- ✅ **Robust Testing** dengan H2 In-Memory Database (Phase 3)

### Professional Features (Bonus)
- 🎁 **Swagger/OpenAPI Documentation** - Interactive API documentation di `/swagger-ui.html`
- 🎁 **GitHub Actions CI/CD** - Automated build, test, dan artifacts
- 🎁 **JWT Mock Authentication** - Demo login dengan 3 user roles (ADMIN, MANAGER, CASHIER)


---

## 🏗️ Architecture

### Tech Stack

| Layer | Technology |
|-------|-----------|
| **Framework** | Spring Boot 4.0.0 |
| **Language** | Java 25 (JDK 25) |
| **Build Tool** | Maven |
| **Database** | MySQL 8.0 |
| **ORM** | Spring Data JPA (Hibernate) |
| **Security** | Spring Security + OAuth2 JWT |
| **API Style** | REST + HATEOAS |
| **Containerization** | Docker + Docker Compose |
| **CI/CD** | GitHub Actions |

### Design Pattern

Project mengikuti **Clean Architecture** dengan struktur:

```
src/main/java/ui/ft/ccit/faculty/transaksi/
├── {domain}/
│   ├── controller/     # REST endpoints
│   ├── model/          # Entity & Repository
│   └── view/           # Service & Custom Exceptions
├── SecurityConfig.java
├── GlobalExceptionHandler.java
└── TransaksiApplication.java
```

---

## 📋 Prerequisites

Pastikan sudah terinstall:

- **Java Development Kit (JDK) 25** - [Download](https://openjdk.org/)
- **Maven 3.9+** - [Download](https://maven.apache.org/download.cgi)
- **MySQL 8.0** - [Download](https://dev.mysql.com/downloads/mysql/)
- **Docker & Docker Compose** (opsional) - [Download](https://www.docker.com/products/docker-desktop)
- **Git** - [Download](https://git-scm.com/downloads)

Verifikasi instalasi:
```bash
java -version      # Harus Java 25
mvn -version       # Harus Maven 3.9+
mysql --version    # Harus MySQL 8.0
docker --version   # (opsional)
```

---

## 🚀 Quick Start

### Option 1: Docker (Recommended)

**Paling mudah dan cepat!** Tidak perlu setup MySQL manual.

```bash
# 1. Clone repository
git clone https://github.com/fathurcoding/spring-boot-supermarket-api.git
cd spring-boot-supermarket-api

# 2. Build aplikasi
./mvnw clean package -DskipTests

# 3. Jalankan dengan Docker Compose
docker-compose up --build
```

**Akses:**
- API: http://localhost:8080
- phpMyAdmin: http://localhost:8081 (user: `root`, password: `rootpassword`)
- HAL Explorer: http://localhost:8080/explorer/index.html

---

### Option 2: Local Development

**Butuh MySQL terinstall di local.**

```bash
# 1. Clone repository
git clone https://github.com/fathurcoding/spring-boot-supermarket-api.git
cd spring-boot-supermarket-api

# 2. Setup database MySQL
mysql -u root -p
CREATE DATABASE penjualan;
USE penjualan;
SOURCE db/penjualan.sql;
EXIT;

# 3. Konfigurasi database di application-local.yaml
# Edit src/main/resources/application-local.yaml:
# - Set username dan password MySQL Anda
# - Pastikan port 3306 tersedia

# 4. Jalankan aplikasi
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

**Akses:**
- API: http://localhost:8080
- HAL Explorer: http://localhost:8080/explorer/index.html

---

## 🗄️ Database Schema

Database `penjualan` memiliki **7 tabel utama**:

### Entity Relationship Diagram

```
┌─────────────┐       ┌──────────────┐       ┌──────────────┐
│ JENIS_BARANG│◄──┐   │   PEMASOK    │◄──┐   │   KARYAWAN   │
└─────────────┘   │   └──────────────┘   │   └──────────────┘
                  │                       │           │
                  │   ┌──────────────┐   │           │
                  └───┤    BARANG    ├───┘           │
                      └──────┬───────┘               │
                             │                       │
                             │                       │
                      ┌──────▼───────┐               │
                      │DETAIL_TRANSAK│◄──────────────┤
                      └──────▲───────┘               │
                             │                       │
                      ┌──────┴───────┐       ┌───────▼──────┐
                      │  TRANSAKSI   ├───────►│  PELANGGAN   │
                      └──────────────┘       └──────────────┘
```

### Tabel Detail

| Tabel | Deskripsi | Status |
|-------|-----------|--------|
| `jenis_barang` | Kategori produk (Snack, Minuman, dll) | ✅ Implemented |
| `pemasok` | Data supplier | ✅ Implemented |
| `barang` | Data produk dengan harga & stok | ✅ Implemented |
| `karyawan` | Data karyawan supermarket | ⏳ Planned |
| `pelanggan` | Data customer (membership) | ⏳ Planned |
| `transaksi` | Header transaksi penjualan | ⏳ Planned |
| `detail_transaksi` | Detail item per transaksi | ⏳ Planned |

---

## 🌐 API Endpoints

### Overview

Base URL: `http://localhost:8080/api`

**Security:**
- 🟢 `GET` methods: Public (no auth required)
- 🔴 `POST`, `PUT`, `DELETE`: Requires JWT token

### Implemented Endpoints

#### Barang (Products)
```
GET    /api/barang           # List all products
GET    /api/barang/{id}      # Get product by ID
POST   /api/barang           # Create new product (requires auth)
PUT    /api/barang/{id}      # Update product (requires auth)
DELETE /api/barang/{id}      # Delete product (requires auth)
```

#### Jenis Barang (Product Categories)
```
GET    /api/jenis-barang           # List all categories
GET    /api/jenis-barang/{id}      # Get category by ID
POST   /api/jenis-barang           # Create new category (requires auth)
PUT    /api/jenis-barang/{id}      # Update category (requires auth)
DELETE /api/jenis-barang/{id}      # Delete category (requires auth)
```

#### Pemasok (Suppliers)
```
GET    /api/pemasok           # List all suppliers
GET    /api/pemasok/{id}      # Get supplier by ID
POST   /api/pemasok           # Create new supplier (requires auth)
PUT    /api/pemasok/{id}      # Update supplier (requires auth)
DELETE /api/pemasok/{id}      # Delete supplier (requires auth)
```

**📖 Dokumentasi lengkap:** Lihat [API.md](API.md) untuk request/response examples.

### Testing API

**Menggunakan HAL Explorer (Web UI):**
```
http://localhost:8080/explorer/index.html
```

**Menggunakan cURL:**
```bash
# GET all products
curl http://localhost:8080/api/barang

# GET single product
curl http://localhost:8080/api/barang/B001

# POST create product (need JWT token)
curl -X POST http://localhost:8080/api/barang \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "id_barang": "B999",
    "nama": "Test Product",
    "stok": 100,
    "harga": 5000,
    "persen_laba": 10,
    "diskon": 5,
    "id_jenis_brg": 100,
    "id_pemasok": "S001"
  }'
```

---

## 💻 Development

### Project Structure

```
spring-boot-supermarket-api/
├── .github/workflows/      # CI/CD workflows
├── db/                     # Database scripts
│   └── penjualan.sql      # Initial schema + data
├── src/
│   ├── main/
│   │   ├── java/          # Source code
│   │   └── resources/     # Configuration files
│   └── test/              # Test cases
├── target/                # Build output (gitignored)
├── docker-compose.yml     # Docker services
├── Dockerfile            # App container image
├── pom.xml               # Maven configuration
└── README.md             # This file
```

### Adding New Domain

Ingin menambahkan domain baru (misal: `Karyawan`)? Ikuti template:

1. **Create package:** `ui.ft.ccit.faculty.transaksi.karyawan`
2. **Add model:** `Karyawan.java` (entity) + `KaryawanRepository.java`
3. **Add service:** `KaryawanService.java` + custom exceptions
4. **Add controller:** `KaryawanController.java`

**📖 Tutorial lengkap:** Lihat [CONTRIBUTING.md](CONTRIBUTING.md)

### Configuration Profiles

| Profile | File | Usage |
|---------|------|-------|
| `local` | `application-local.yaml` | Development di local machine |
| `docker` | `application-docker.yaml` | Running di Docker container |

Switch profile:
```bash
# Local
./mvnw spring-boot:run -Dspring-boot.run.profiles=local

# Docker
docker-compose up  # Auto use docker profile
```

---

## 🧪 Testing

### Reliability Update (Phase 3)
Project ini sekarang menggunakan **H2 In-Memory Database** untuk integration tests. Ini memastikan tests berjalan cepat dan konsisten tanpa dependency ke database local.

- **Unit Tests:** Menggunakan Mockito untuk business logic.
- **Integration Tests:** `@SpringBootTest` dengan profile `test` (H2).

### Run Unit Tests

```bash
./mvnw test
```

### Run with Coverage

```bash
./mvnw clean test jacoco:report
```

Coverage report: `target/site/jacoco/index.html`

### Manual API Testing

1. **Start application** (local atau docker)
2. **Open HAL Explorer:** `http://localhost:8080/explorer/index.html`
3. **Navigate** ke endpoint yang ingin ditest
4. **Execute** request dan lihat response

---

## 🚢 Deployment

### Build JAR

```bash
./mvnw clean package -DskipTests
```

Output: `target/transaksi-0.0.1-SNAPSHOT.jar`

### Run JAR

```bash
java -jar target/transaksi-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
```

### Docker Image

Build dan push ke registry:

```bash
# Build
docker build -t fathurcoding/transaksi-app:latest .

# Push to Docker Hub
docker login
docker push fathurcoding/transaksi-app:latest
```

---

## 🤝 Contributing

Contributions are welcome! Please read [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

### Quick Contribution Steps

1. Fork repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

---

## 📄 License

Project ini menggunakan **MIT License** - bebas digunakan untuk belajar dan portfolio!

```
MIT License - Copyright (c) 2024 

Permission is hereby granted, free of charge, to use, copy, modify, 
merge, publish, distribute, sublicense, and/or sell copies of this software.
```

---

## 👨‍💻 Author

**Made with ❤️ for CCIT Students**

- GitHub: [@fathurcoding](https://github.com/fathurcoding)
- Project: [spring-boot-supermarket-api](https://github.com/fathurcoding/spring-boot-supermarket-api)

---

## 🙏 Acknowledgments

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Security](https://spring.io/projects/spring-security)
- CCIT Faculty - UI untuk pembelajaran dan guidance

---

**Happy Coding! 🚀✨**

> 💡 **Tip:** Star ⭐ repository ini jika bermanfaat untuk pembelajaran Anda!