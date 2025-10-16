

# 🏦 Home Loan Management System – Backend

## 1️⃣ Project Overview

This is a **backend-only Spring Boot application** for a Home Loan Management System.
Features include:

| Feature               | Description                                 |
| --------------------- | ------------------------------------------- |
| 🔐 JWT Authentication | User & Admin login/register                 |
| 🏦 Loan Management    | Apply, view, approve/reject loans           |
| 🧾 EMI Schedule       | Auto-generated after approval               |
| 💸 Pay EMI            | Users can mark EMI as paid                  |
| 📄 Document Upload    | Users can upload KYC/property documents     |
| 👑 Admin Panel        | View all loans, approve/reject, monitor EMI |

**Tech Stack**:

* Java 17+ / Spring Boot 3
* Spring Security + JWT
* MySQL / PostgreSQL (any free online DB)
* Maven
* Lombok (for getters/setters)

---

## 2️⃣ Prerequisites

1. **Java 17+** installed and `JAVA_HOME` configured
2. **Maven** installed
3. **MySQL/PostgreSQL** database ready (e.g., free remote DB like PlanetScale or db4free.net)
4. **IntelliJ IDEA** (recommended)

---

## 3️⃣ Setup Project

### 3.1 Clone or create Maven project

```bash
mvn archetype:generate -DgroupId=com.example -DartifactId=homeloan -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
cd homeloan
```

### 3.2 Add Dependencies (`pom.xml`)

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.11.5</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.11.5</version>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.11.5</version>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.30</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

---

### 3.3 Configure DB (`application.properties`)

```properties
spring.datasource.url=jdbc:mysql://<DB_HOST>:3306/<DB_NAME>
spring.datasource.username=<DB_USER>
spring.datasource.password=<DB_PASS>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Document upload folder
file.upload-dir=uploads
```

---

## 4️⃣ Running the Application

1. **Build & install dependencies**

```bash
mvn clean install
```

2. **Run Spring Boot**

```bash
mvn spring-boot:run
```

The backend will run on:

```
http://localhost:8080
```

---

## 5️⃣ Postman Collection

**Import this JSON into Postman:**

```json
// Use the JSON I provided earlier (with all endpoints for Auth, Loan, EMI, Documents, Admin)
```

**Steps:**

1. **Register user/admin**

    * `POST /api/auth/register`
2. **Login**

    * `POST /api/auth/login`
    * Save JWT token as `{{token}}` (user) or `{{adminToken}}` (admin)
3. **User APIs**

    * Apply loan `/api/loans/apply`
    * View loans `/api/loans`
    * View EMI `/api/loans/{loanId}/emis`
    * Pay EMI `/api/loans/emi/{emiId}/pay`
    * Upload documents `/api/loans/{loanId}/documents/upload`
4. **Admin APIs**

    * Get all loans `/api/admin/loans`
    * Approve loan `/api/admin/loans/{id}/approve`
    * Reject loan `/api/admin/loans/{id}/reject`

---

## 6️⃣ Example Flow

1. **Admin registers & logs in** → get `adminToken`
2. **User registers & logs in** → get `token`
3. User applies for loan → uploads KYC/property documents
4. Admin approves/rejects the loan
5. EMI schedule is auto-generated
6. User pays EMI → status updates

---

## 7️⃣ Notes for Interview

* JWT authentication & role-based security is **fully implemented**
* Loan approval logic with EMI generation is **demo-ready**
* Document upload uses **local storage**; can be extended to S3
* Database can be **switched to PostgreSQL/MySQL** easily
* Ready to **show API testing in Postman**

---

This setup is **fully runnable in 30 minutes** on your machine and demonstrates **all required backend features** for your interview.

---

If you want, I can also make a **diagram showing API flow + roles** in a simple way — which looks great for an interview explanation.

Do you want me to do that?
