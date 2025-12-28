# API Documentation 📚

Complete RESTful API documentation for Spring Boot Supermarket API.

---

## 🌐 Base Information

| Property | Value |
|----------|-------|
| **Base URL** | `http://localhost:8080/api` |
| **API Style** | REST + HATEOAS |
| **Data Format** | JSON (application/json) |
| **Authentication** | JWT Bearer Token (OAuth2) |
| **HAL Explorer** | `http://localhost:8080/explorer/index.html` |

---

## 🔐 Authentication

### Security Rules

| HTTP Method | Authentication Required |
|-------------|------------------------|
| `GET` | ❌ No (Public) |
| `POST` | ✅ Yes (JWT Token) |
| `PUT` | ✅ Yes (JWT Token) |
| `DELETE` | ✅ Yes (JWT Token) |

### Getting JWT Token

JWT token didapat dari **Google OAuth2** (issuer: `https://accounts.google.com`).

**Using Token in Request:**

```bash
curl -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE" \
     http://localhost:8080/api/barang
```

---

## 📦 Barang (Products) API

Base path: `/api/barang`

### List All Products

**Request:**
```http
GET /api/barang HTTP/1.1
Host: localhost:8080
Accept: application/json
```

**Response: `200 OK`**
```json
{
  "_embedded": {
    "barangList": [
      {
        "id_barang": "B001",
        "nama": "Chitato",
        "stok": 200,
        "harga": 3500.0,
        "persen_laba": 5.0,
        "diskon": 10.0,
        "id_jenis_brg": 101,
        "id_pemasok": "S001",
        "_links": {
          "self": {
            "href": "http://localhost:8080/api/barang/B001"
          },
          "barang": {
            "href": "http://localhost:8080/api/barang"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/barang"
    }
  }
}
```

---

### Get Product by ID

**Request:**
```http
GET /api/barang/B001 HTTP/1.1
Host: localhost:8080
Accept: application/json
```

**Response: `200 OK`**
```json
{
  "id_barang": "B001",
  "nama": "Chitato",
  "stok": 200,
  "harga": 3500.0,
  "persen_laba": 5.0,
  "diskon": 10.0,
  "id_jenis_brg": 101,
  "id_pemasok": "S001",
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/barang/B001"
    },
    "barang": {
      "href": "http://localhost:8080/api/barang"
    }
  }
}
```

**Response: `404 Not Found`** (if not exists)
```json
{
  "timestamp": "2024-12-27T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Barang dengan ID B999 tidak ditemukan",
  "path": "/api/barang/B999"
}
```

---

### Create New Product

**Request:**
```http
POST /api/barang HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Bearer YOUR_JWT_TOKEN

{
  "id_barang": "B999",
  "nama": "Pocari Sweat",
  "stok": 150,
  "harga": 6500.0,
  "persen_laba": 8.0,
  "diskon": 5.0,
  "id_jenis_brg": 102,
  "id_pemasok": "S003"
}
```

**Response: `201 Created`**
```json
{
  "id_barang": "B999",
  "nama": "Pocari Sweat",
  "stok": 150,
  "harga": 6500.0,
  "persen_laba": 8.0,
  "diskon": 5.0,
  "id_jenis_brg": 102,
  "id_pemasok": "S003",
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/barang/B999"
    },
    "barang": {
      "href": "http://localhost:8080/api/barang"
    }
  }
}
```

**Response: `409 Conflict`** (if ID already exists)
```json
{
  "timestamp": "2024-12-27T10:30:00",
  "status": 409,
  "error": "Conflict",
  "message": "Barang dengan ID B999 sudah ada",
  "path": "/api/barang"
}
```

---

### Update Product

**Request:**
```http
PUT /api/barang/B999 HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Bearer YOUR_JWT_TOKEN

{
  "nama": "Pocari Sweat Updated",
  "stok": 200,
  "harga": 7000.0,
  "persen_laba": 10.0,
  "diskon": 0.0,
  "id_jenis_brg": 102,
  "id_pemasok": "S003"
}
```

**Response: `200 OK`**
```json
{
  "id_barang": "B999",
  "nama": "Pocari Sweat Updated",
  "stok": 200,
  "harga": 7000.0,
  "persen_laba": 10.0,
  "diskon": 0.0,
  "id_jenis_brg": 102,
  "id_pemasok": "S003",
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/barang/B999"
    },
    "barang": {
      "href": "http://localhost:8080/api/barang"
    }
  }
}
```

---

### Delete Product

**Request:**
```http
DELETE /api/barang/B999 HTTP/1.1
Host: localhost:8080
Authorization: Bearer YOUR_JWT_TOKEN
```

**Response: `204 No Content`**

**Response: `404 Not Found`** (if not exists)
```json
{
  "timestamp": "2024-12-27T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Barang dengan ID B999 tidak ditemukan",
  "path": "/api/barang/B999"
}
```

---

## 🏷️ Jenis Barang (Product Categories) API

Base path: `/api/jenis-barang`

### List All Categories

**Request:**
```http
GET /api/jenis-barang HTTP/1.1
Host: localhost:8080
Accept: application/json
```

**Response: `200 OK`**
```json
{
  "_embedded": {
    "jenisBarangList": [
      {
        "id_jenis_brg": 100,
        "nama_jenis_brg": "Snack",
        "_links": {
          "self": {
            "href": "http://localhost:8080/api/jenis-barang/100"
          },
          "jenisBarang": {
            "href": "http://localhost:8080/api/jenis-barang"
          }
        }
      },
      {
        "id_jenis_brg": 101,
        "nama_jenis_brg": "Minuman",
        "_links": {
          "self": {
            "href": "http://localhost:8080/api/jenis-barang/101"
          },
          "jenisBarang": {
            "href": "http://localhost:8080/api/jenis-barang"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/jenis-barang"
    }
  }
}
```

### Get Category by ID

**Request:**
```http
GET /api/jenis-barang/100 HTTP/1.1
Host: localhost:8080
```

**Response: `200 OK`**
```json
{
  "id_jenis_brg": 100,
  "nama_jenis_brg": "Snack",
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/jenis-barang/100"
    },
    "jenisBarang": {
      "href": "http://localhost:8080/api/jenis-barang"
    }
  }
}
```

### Create New Category

**Request:**
```http
POST /api/jenis-barang HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Bearer YOUR_JWT_TOKEN

{
  "id_jenis_brg": 200,
  "nama_jenis_brg": "Makanan Beku"
}
```

**Response: `201 Created`**

### Update Category

**Request:**
```http
PUT /api/jenis-barang/200 HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Bearer YOUR_JWT_TOKEN

{
  "nama_jenis_brg": "Frozen Food"
}
```

**Response: `200 OK`**

### Delete Category

**Request:**
```http
DELETE /api/jenis-barang/200 HTTP/1.1
Host: localhost:8080
Authorization: Bearer YOUR_JWT_TOKEN
```

**Response: `204 No Content`**

---

## 🚚 Pemasok (Suppliers) API

Base path: `/api/pemasok`

### List All Suppliers

**Request:**
```http
GET /api/pemasok HTTP/1.1
Host: localhost:8080
```

**Response: `200 OK`**
```json
{
  "_embedded": {
    "pemasokList": [
      {
        "id_pemasok": "S001",
        "nama": "Pungkas Mandiri",
        "alamat": "Jl. Merapi No.212",
        "telepon": "08122848418",
        "email": "pt_puma@puma.com",
        "_links": {
          "self": {
            "href": "http://localhost:8080/api/pemasok/S001"
          },
          "pemasok": {
            "href": "http://localhost:8080/api/pemasok"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/pemasok"
    }
  }
}
```

### Get Supplier by ID

**Request:**
```http
GET /api/pemasok/S001 HTTP/1.1
Host: localhost:8080
```

**Response: `200 OK`**
```json
{
  "id_pemasok": "S001",
  "nama": "Pungkas Mandiri",
  "alamat": "Jl. Merapi No.212",
  "telepon": "08122848418",
  "email": "pt_puma@puma.com",
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/pemasok/S001"
    },
    "pemasok": {
      "href": "http://localhost:8080/api/pemasok"
    }
  }
}
```

### Create New Supplier

**Request:**
```http
POST /api/pemasok HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Bearer YOUR_JWT_TOKEN

{
  "id_pemasok": "S099",
  "nama": "Supplier Baru",
  "alamat": "Jl. Contoh No.123",
  "telepon": "08123456789",
  "email": "supplier@example.com"
}
```

**Response: `201 Created`**

### Update Supplier

**Request:**
```http
PUT /api/pemasok/S099 HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Bearer YOUR_JWT_TOKEN

{
  "nama": "Supplier Updated",
  "alamat": "Jl. Update No.456",
  "telepon": "08198765432",
  "email": "updated@example.com"
}
```

**Response: `200 OK`**

### Delete Supplier

**Request:**
```http
DELETE /api/pemasok/S099 HTTP/1.1
Host: localhost:8080
Authorization: Bearer YOUR_JWT_TOKEN
```

**Response: `204 No Content`**

---

## � Reporting API

Base path: `/api/reports`

### Get Daily Revenue

**Request:**
```http
GET /api/reports/daily-revenue?startDate=2024-01-01&endDate=2024-01-31 HTTP/1.1
Host: localhost:8080
```

**Response: `200 OK`**
```json
[
  {
    "date": "2024-01-01",
    "totalRevenue": 1500000.00,
    "transactionCount": 15
  },
  {
    "date": "2024-01-02",
    "totalRevenue": 2100000.00,
    "transactionCount": 20
  }
]
```

### Get Top Selling Products

**Request:**
```http
GET /api/reports/top-products?limit=5 HTTP/1.1
Host: localhost:8080
```

**Response: `200 OK`**
```json
[
  {
    "productName": "Chitato",
    "totalQuantitySold": 500,
    "totalRevenue": 1750000.00
  },
  {
    "productName": "Pocari Sweat",
    "totalQuantitySold": 350,
    "totalRevenue": 2275000.00
  }
]
```

### Get Low Stock Alert

**Request:**
```http
GET /api/reports/low-stock?threshold=10 HTTP/1.1
Host: localhost:8080
```

**Response: `200 OK`**
```json
[
  {
    "id_barang": "B005",
    "nama": "Roti Tawar",
    "stok": 5,
    "harga": 12000.0,
    "persen_laba": 5.0,
    "diskon": 0.0,
    "id_jenis_brg": 200,
    "id_pemasok": "S002"
  }
]
```

## �🔗 HATEOAS Links

Setiap response mengandung `_links` untuk navigation:

```json
{
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/barang/B001"
    },
    "barang": {
      "href": "http://localhost:8080/api/barang"
    }
  }
}
```

| Link Relation | Description |
|---------------|-------------|
| `self` | Link ke resource saat ini |
| `{resource}` | Link ke collection resource |

---

## ⚠️ Error Responses

All error responses follow this format:

```json
{
  "timestamp": "2024-12-27T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Detailed error message",
  "path": "/api/endpoint"
}
```

### Common HTTP Status Codes

| Code | Status | Description |
|------|--------|-------------|
| 200 | OK | Request successful |
| 201 | Created | Resource created successfully |
| 204 | No Content | Resource deleted successfully |
| 400 | Bad Request | Invalid request data |
| 401 | Unauthorized | Missing or invalid JWT token |
| 404 | Not Found | Resource not found |
| 409 | Conflict | Resource already exists |
| 500 | Internal Server Error | Server error |

---

## 🧪 Testing API

### Using cURL

```bash
# GET request
curl http://localhost:8080/api/barang

# POST request with authentication
curl -X POST http://localhost:8080/api/barang \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -d '{"id_barang":"B999","nama":"Test Product",...}'
  
# PUT request
curl -X PUT http://localhost:8080/api/barang/B999 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -d '{"nama":"Updated Product",...}'
  
# DELETE request
curl -X DELETE http://localhost:8080/api/barang/B999 \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### Using HAL Explorer

1. Start application: `./mvnw spring-boot:run`
2. Open browser: `http://localhost:8080/explorer/index.html`
3. Navigate using hyperlinks
4. Execute requests directly from UI

### Using Postman

1. Import collection (if available)
2. Set `Authorization` type to `Bearer Token`
3. Paste JWT token
4. Execute requests

---

## 📝 Notes

- All timestamps in responses use ISO 8601 format
- All numeric IDs are returned as integers
- All string IDs maintain their exact format (e.g., "B001", "S001")
- Email fields are nullable (can be `null`)
- Foreign key constraints are enforced (CASCADE on DELETE/UPDATE)

---

**For more information, see:**
- [README.md](README.md) - Project overview
- [CONTRIBUTING.md](CONTRIBUTING.md) - Development guide
