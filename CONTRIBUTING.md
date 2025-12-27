# Contributing to Spring Boot Supermarket API 🤝

Terima kasih sudah tertarik untuk berkontribusi! Document ini berisi guidelines untuk membantu Anda berkontribusi ke project ini.

---

## 📋 Table of Contents

- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [Development Workflow](#development-workflow)
- [Coding Standards](#coding-standards)
- [Adding New Domain](#adding-new-domain)
- [Commit Guidelines](#commit-guidelines)
- [Pull Request Process](#pull-request-process)

---

## Code of Conduct

Project ini mengikuti prinsip:
- ✅ Respect sesama kontributor
- ✅ Komunikasi yang constructive
- ✅ Fokus pada pembelajaran dan improvement
- ✅ Open untuk feedback dan diskusi

---

## Getting Started

### Prerequisites

Pastikan sudah setup development environment:

```bash
# 1. Fork repository
# 2. Clone fork Anda
git clone https://github.com/YOUR_USERNAME/spring-boot-supermarket-api.git
cd spring-boot-supermarket-api

# 3. Add upstream remote
git remote add upstream https://github.com/fathurcoding/spring-boot-supermarket-api.git

# 4. Install dependencies
./mvnw clean install
```

---

## Development Workflow

### 1. Create Feature Branch

```bash
# Update main branch
git checkout main
git pull upstream main

# Create feature branch
git checkout -b feature/nama-fitur
```

**Branch naming convention:**

| Type | Format | Example |
|------|--------|---------|
| New Feature | `feature/nama-fitur` | `feature/add-karyawan-domain` |
| Bug Fix | `bugfix/nama-bug` | `bugfix/fix-barang-validation` |
| Documentation | `docs/nama-doc` | `docs/update-api-guide` |
| Refactoring | `refactor/nama-refactor` | `refactor/simplify-exception-handling` |

### 2. Make Changes

```bash
# Edit files
# Test changes
./mvnw test

# Verify code compiles
./mvnw clean package -DskipTests
```

### 3. Commit Changes

```bash
git add .
git commit -m "feat: add karyawan domain with CRUD operations"
```

### 4. Push & Create PR

```bash
git push origin feature/nama-fitur
```

Kemudian buat Pull Request di GitHub.

---

## Coding Standards

### Java Code Style

#### 1. Naming Conventions

```java
// Class names: PascalCase
public class BarangService { }

// Methods: camelCase
public Barang createBarang() { }

// Constants: UPPER_SNAKE_CASE
private static final int MAX_STOCK = 1000;

// Variables: camelCase
private String namaBarang;
```

#### 2. Package Structure

Ikuti clean architecture pattern:

```
ui.ft.ccit.faculty.transaksi.{domain}/
├── controller/
│   └── {Domain}Controller.java
├── model/
│   ├── {Domain}.java
│   └── {Domain}Repository.java
└── view/
    ├── {Domain}Service.java
    ├── {Domain}NotFoundException.java
    └── {Domain}AlreadyExistsException.java
```

#### 3. JavaDoc Comments

Wajib untuk **public classes** dan **public methods**:

```java
/**
 * Service untuk mengelola data Barang.
 * 
 * <p>Service ini menyediakan operasi CRUD untuk entity Barang
 * dengan validasi business logic.</p>
 * 
 * @author Your Name
 * @version 1.0
 * @since 2024-12-27
 */
@Service
public class BarangService {
    
    /**
     * Membuat barang baru.
     * 
     * @param barang entity barang yang akan dibuat
     * @return barang yang telah dibuat dengan ID
     * @throws BarangAlreadyExistsException jika barang dengan ID sama sudah ada
     */
    public Barang createBarang(Barang barang) {
        // implementation
    }
}
```

#### 4. Exception Handling

Gunakan custom exceptions:

```java
// ✅ Good
if (repository.existsById(id)) {
    throw new BarangAlreadyExistsException(id);
}

// ❌ Bad
if (repository.existsById(id)) {
    throw new RuntimeException("Barang already exists");
}
```

#### 5. REST Controller Pattern

```java
@RestController
@RequestMapping("/api/namaresource")
public class NamaResourceController {
    
    private final NamaResourceService service;
    
    // Constructor injection (preferred)
    public NamaResourceController(NamaResourceService service) {
        this.service = service;
    }
    
    @GetMapping
    public CollectionModel<EntityModel<NamaResource>> getAll() {
        // implementation with HATEOAS links
    }
    
    @GetMapping("/{id}")
    public EntityModel<NamaResource> getById(@PathVariable String id) {
        // implementation
    }
    
    @PostMapping
    public ResponseEntity<EntityModel<NamaResource>> create(@RequestBody NamaResource resource) {
        // implementation with 201 Created
    }
}
```

### Configuration Files

#### YAML Files

```yaml
# Use 2 spaces for indentation
spring:
  application:
    name: transaksi
  
  # Add comments for clarity
  datasource:
    url: jdbc:mysql://localhost:3306/penjualan  # Database URL
    username: root                               # DB username
```

---

## Adding New Domain

Langkah-langkah menambahkan domain baru (misal: **Karyawan**):

### 1. Create Entity

`src/main/java/ui/ft/ccit/faculty/transaksi/karyawan/model/Karyawan.java`

```java
package ui.ft.ccit.faculty.transaksi.karyawan.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entity untuk data Karyawan.
 */
@Entity
@Table(name = "karyawan")
public class Karyawan {
    
    @Id
    @Column(name = "id_karyawan", length = 4)
    private String idKaryawan;
    
    @Column(name = "nama", nullable = false, length = 20)
    private String nama;
    
    @Column(name = "jenis_kelamin", nullable = false, length = 1)
    private String jenisKelamin;
    
    @Column(name = "alamat", nullable = false, length = 50)
    private String alamat;
    
    @Column(name = "telepon", length = 15)
    private String telepon;
    
    @Column(name = "tgl_lahir", nullable = false)
    private LocalDate tglLahir;
    
    @Column(name = "gaji", nullable = false)
    private Double gaji;
    
    // Constructor, Getter, Setter
}
```

### 2. Create Repository

`src/main/java/ui/ft/ccit/faculty/transaksi/karyawan/model/KaryawanRepository.java`

```java
package ui.ft.ccit.faculty.transaksi.karyawan.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KaryawanRepository extends JpaRepository<Karyawan, String> {
}
```

### 3. Create Custom Exceptions

`src/main/java/ui/ft/ccit/faculty/transaksi/karyawan/view/KaryawanNotFoundException.java`

```java
package ui.ft.ccit.faculty.transaksi.karyawan.view;

import ui.ft.ccit.faculty.transaksi.DataNotFoundException;

public class KaryawanNotFoundException extends DataNotFoundException {
    public KaryawanNotFoundException(String id) {
        super("Karyawan dengan ID " + id + " tidak ditemukan");
    }
}
```

`src/main/java/ui/ft/ccit/faculty/transaksi/karyawan/view/KaryawanAlreadyExistsException.java`

```java
package ui.ft.ccit.faculty.transaksi.karyawan.view;

import ui.ft.ccit.faculty.transaksi.DataAlreadyExistsException;

public class KaryawanAlreadyExistsException extends DataAlreadyExistsException {
    public KaryawanAlreadyExistsException(String id) {
        super("Karyawan dengan ID " + id + " sudah ada");
    }
}
```

### 4. Create Service

`src/main/java/ui/ft/ccit/faculty/transaksi/karyawan/view/KaryawanService.java`

```java
package ui.ft.ccit.faculty.transaksi.karyawan.view;

import org.springframework.stereotype.Service;
import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;
import ui.ft.ccit.faculty.transaksi.karyawan.model.KaryawanRepository;

import java.util.List;

/**
 * Service untuk business logic Karyawan.
 */
@Service
public class KaryawanService {
    
    private final KaryawanRepository repository;
    
    public KaryawanService(KaryawanRepository repository) {
        this.repository = repository;
    }
    
    public List<Karyawan> findAll() {
        return repository.findAll();
    }
    
    public Karyawan findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new KaryawanNotFoundException(id));
    }
    
    public Karyawan create(Karyawan karyawan) {
        if (repository.existsById(karyawan.getIdKaryawan())) {
            throw new KaryawanAlreadyExistsException(karyawan.getIdKaryawan());
        }
        return repository.save(karyawan);
    }
    
    public Karyawan update(String id, Karyawan karyawan) {
        findById(id); // Check exists
        karyawan.setIdKaryawan(id);
        return repository.save(karyawan);
    }
    
    public void delete(String id) {
        findById(id); // Check exists
        repository.deleteById(id);
    }
}
```

### 5. Create Controller

`src/main/java/ui/ft/ccit/faculty/transaksi/karyawan/controller/KaryawanController.java`

```java
package ui.ft.ccit.faculty.transaksi.karyawan.controller;

import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;
import ui.ft.ccit.faculty.transaksi.karyawan.view.KaryawanService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * REST Controller untuk endpoint Karyawan.
 */
@RestController
@RequestMapping("/api/karyawan")
public class KaryawanController {
    
    private final KaryawanService service;
    
    public KaryawanController(KaryawanService service) {
        this.service = service;
    }
    
    @GetMapping
    public CollectionModel<EntityModel<Karyawan>> getAll() {
        List<EntityModel<Karyawan>> karyawanList = service.findAll().stream()
                .map(this::toModel)
                .collect(Collectors.toList());
        
        return CollectionModel.of(karyawanList,
                linkTo(methodOn(KaryawanController.class).getAll()).withSelfRel());
    }
    
    @GetMapping("/{id}")
    public EntityModel<Karyawan> getById(@PathVariable String id) {
        return toModel(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity<EntityModel<Karyawan>> create(@RequestBody Karyawan karyawan) {
        Karyawan created = service.create(karyawan);
        return ResponseEntity
                .created(linkTo(methodOn(KaryawanController.class).getById(created.getIdKaryawan())).toUri())
                .body(toModel(created));
    }
    
    @PutMapping("/{id}")
    public EntityModel<Karyawan> update(@PathVariable String id, @RequestBody Karyawan karyawan) {
        return toModel(service.update(id, karyawan));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    private EntityModel<Karyawan> toModel(Karyawan karyawan) {
        return EntityModel.of(karyawan,
                linkTo(methodOn(KaryawanController.class).getById(karyawan.getIdKaryawan())).withSelfRel(),
                linkTo(methodOn(KaryawanController.class).getAll()).withRel("karyawan"));
    }
}
```

### 6. Create Test

`src/test/java/ui/ft/ccit/faculty/transaksi/karyawan/KaryawanRepositoryTest.java`

```java
package ui.ft.ccit.faculty.transaksi.karyawan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;
import ui.ft.ccit.faculty.transaksi.karyawan.model.KaryawanRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class KaryawanRepositoryTest {
    
    @Autowired
    private KaryawanRepository repository;
    
    @Test
    void shouldFindKaryawanById() {
        Karyawan karyawan = repository.findById("K001").orElse(null);
        assertThat(karyawan).isNotNull();
        assertThat(karyawan.getNama()).isEqualTo("Tuti");
    }
}
```

---

## Commit Guidelines

### Commit Message Format

```
<type>(<scope>): <subject>

<body>

<footer>
```

#### Types

| Type | Deskripsi | Example |
|------|-----------|---------|
| `feat` | New feature | `feat(karyawan): add CRUD operations` |
| `fix` | Bug fix | `fix(barang): correct validation logic` |
| `docs` | Documentation | `docs(readme): update setup instructions` |
| `style` | Code formatting | `style: apply code formatter` |
| `refactor` | Code refactoring | `refactor(service): simplify exception handling` |
| `test` | Add/update tests | `test(barang): add repository tests` |
| `chore` | Maintenance | `chore: update dependencies` |

#### Examples

```bash
feat(pelanggan): implement pelanggan domain with CRUD

- Add Pelanggan entity, repository, service
- Add PelangganController with HATEOAS
- Add custom exceptions
- Add repository tests

Closes #12

---

fix(barang): fix stock validation

Stock should not accept negative values.

---

docs(contributing): add domain creation guide

Add step-by-step guide for creating new domains
following clean architecture pattern.
```

---

## Pull Request Process

### 1. Before Creating PR

**Checklist:**

- [ ] Code mengikuti coding standards
- [ ] Semua tests pass (`./mvnw test`)
- [ ] Application build successfully (`./mvnw clean package`)
- [ ] JavaDoc lengkap untuk public API
- [ ] Commit messages mengikuti convention
- [ ] Branch up-to-date dengan `main`

### 2. Creating PR

**Title format:**
```
[TYPE] Short description
```

**Examples:**
- `[FEATURE] Add Karyawan domain`
- `[BUGFIX] Fix barang stock validation`
- `[DOCS] Update API documentation`

**PR Description Template:**

```markdown
## Description
Brief description of changes.

## Type of Change
- [ ] New feature
- [ ] Bug fix
- [ ] Documentation update
- [ ] Code refactoring

## Changes Made
- Change 1
- Change 2
- Change 3

## Testing
- [ ] Unit tests added/updated
- [ ] Manual testing performed
- [ ] All tests passing

## Screenshots (if applicable)
Add screenshots for UI changes.

## Related Issues
Closes #issue_number
```

### 3. Code Review

**Ekspektasi:**
- Minimal 1 approval dari maintainer
- Semua GitHub Actions checks harus pass
- Address semua review comments
- Squash commits jika diminta

### 4. After Merge

```bash
# Update local main
git checkout main
git pull upstream main

# Delete feature branch
git branch -d feature/nama-fitur
git push origin --delete feature/nama-fitur
```

---

## Questions?

Jika ada pertanyaan:

1. **Check documentation:** README.md, API.md
2. **Search existing issues:** GitHub Issues
3. **Create new issue:** Jelaskan masalah dengan detail
4. **Join discussion:** GitHub Discussions

---

**Happy Contributing! 🚀**

> Remember: Every  contribution, no matter how small, is valuable!
