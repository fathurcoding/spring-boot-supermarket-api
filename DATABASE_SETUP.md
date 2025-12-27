# Database Setup Guide

## Error: "Unknown database 'penjualan'"

If you're getting this error, it means the database hasn't been created yet. Follow one of the methods below to set it up.

---

## Method 1: Docker Setup (Recommended) ✅

The easiest way is to use Docker Compose which handles everything automatically.

```bash
# Start all services (MySQL + phpMyAdmin + App)
docker-compose up -d

# Wait for MySQL to initialize (about 30 seconds)
# Database and tables are created automatically!

# Check if it's running
docker-compose ps

# View logs
docker-compose logs mysql
```

**Access phpMyAdmin:** http://localhost:8081
- Username: `root`
- Password: `root123`

The `db/penjualan.sql` script runs automatically on first startup!

---

## Method 2: Local MySQL Setup

### Step 1: Create Database

```bash
# Connect to MySQL
mysql -u root -p

# Run in MySQL console:
```

```sql
CREATE DATABASE IF NOT EXISTS penjualan 
  CHARACTER SET utf8mb4 
  COLLATE utf8mb4_general_ci;

USE penjualan;
```

Or run the quick setup script:

```bash
mysql -u root -p < db/00-create-database.sql
```

### Step 2: Import Schema and Data

```bash
mysql -u root -p penjualan < db/penjualan.sql
```

### Step 3: Update Password (if needed)

Edit `src/main/resources/application-local.yaml`:

```yaml
spring:
  datasource:
    password: your_mysql_password_here
```

---

## Verify Setup

### Option A: Via MySQL Command Line

```bash
mysql -u root -p penjualan -e "SHOW TABLES;"
```

Expected output:
```
+---------------------+
| Tables_in_penjualan |
+---------------------+
| barang              |
| detail_transaksi    |
| jenis_barang        |
| karyawan            |
| pelanggan           |
| pemasok             |
| transaksi           |
+---------------------+
```

### Option B: Run Tests

```bash
./mvnw test
```

All tests should pass if database is set up correctly.

---

## Quick Reference

**Database Connection:**
- Host: `localhost`
- Port: `3306`
- Database: `penjualan`
- Username: `root`
- Password: `` (empty for local) atau `root123` (for Docker)

**Files:**
- Database schema: `db/penjualan.sql`
- Quick setup: `db/00-create-database.sql`
- Docker setup: `docker-compose.yml`
- Local config: `src/main/resources/application-local.yaml`

---

## Troubleshooting

### "Access denied for user 'root'@'localhost'"

Update your password in `application-local.yaml`:

```yaml
spring:
  datasource:
    password: your_actual_password
```

### "Table doesn't exist"

Re-run the import:

```bash
mysql -u root -p penjualan < db/penjualan.sql
```

### Tests still fail after setup

Make sure you're using the right profile:

```bash
./mvnw test -Dspring.profiles.active=local
```

Or just:

```bash
./mvnw test
```

(Tests use `@ActiveProfiles("local")` by default)
