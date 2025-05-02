# DAO JDBC CRUD Example with Unit & Integration Tests

This project demonstrates a clean, testable, and modular implementation of the **DAO (Data Access Object)** pattern using **Spring JDBC (JdbcTemplate)**. The application models a simple **Author–Book** relationship and focuses on implementing and testing basic **CRUD operations** with an in-memory or test-configured relational database.

---

## 🛠 Project Structure

```
src/
├── main/
│   └── java/com/devtiro/dao_jdbc/
│       ├── dao/
│       │   ├── AuthorDao.java
│       │   ├── BookDao.java
│       │   └── impl/
│       │       ├── AuthorDaoImpl.java
│       │       └── BookDaoImpl.java
│       ├── domain/
│       │   ├── Author.java
│       │   └── Book.java
├── test/
│   └── java/com/devtiro/dao_jdbc/
│       ├── dao/
│       │   └── impl/
│       │       ├── AuthorDaoImplTests.java       (Unit Tests)
│       │       ├── BookDaoImplTests.java
│       │       ├── AuthorDaoImplIntegrationTests.java (Integration Tests)
│       │       └── BookDaoImplIntegrationTests.java
│       ├── DaoJdbcApplicationTests.java
│       └── TestDataUtil.java
```

---

## Features

* Full **CRUD support** for `Author` and `Book` entities:

    * `create`, `findOne`, `findAll`, `update`, `delete`
* Clean use of `JdbcTemplate` for database interaction
* Comprehensive **unit tests** using **Mockito**
* Fully integrated **integration tests** using **Spring Boot Test**
* Use of **Lombok** for concise model declarations

---

## Testing Overview

### Unit Tests

Located in `AuthorDaoImplTests` and `BookDaoImplTests`, these tests:

* Validate correct SQL generation
* Do **not** require a real database
* Use **Mockito** to verify interactions with `JdbcTemplate`

### Integration Tests

Found in `AuthorDaoImplIntegrationTests` and `BookDaoImplIntegrationTests`, these:

* Run with **Spring Boot context**
* Interact with the actual in-memory/test-configured database
* Verify full end-to-end behavior of DAO methods

---

## Database Schema

```sql
DROP TABLE IF EXISTS "books";
DROP TABLE IF EXISTS "authors";

CREATE TABLE "authors" (
    "id" BIGINT DEFAULT nextval('authors_id_seq') NOT NULL,
    "name" TEXT,
    "age" INTEGER,
    CONSTRAINT "authors_pkey" PRIMARY KEY ("id")
);

CREATE TABLE "books" (
    "isbn" TEXT NOT NULL,
    "title" TEXT,
    "author_id" BIGINT,
    CONSTRAINT "books_pkey" PRIMARY KEY ("isbn"),
    CONSTRAINT "fk_author" FOREIGN KEY(author_id)
        REFERENCES authors(id)
);
```

---

## Requirements

* Java 11+
* Maven
* Spring Boot
* Lombok plugin enabled in the IDE

---

## Getting Started

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/dao-jdbc-crud.git
   cd dao-jdbc-crud
   ```

2. **Build and run tests:**

   ```bash
   ./mvnw clean test
   ```

3. **Run the application** *(if applicable)*:
   While this project doesn't include a REST API, the application context will load with DAOs configured and ready for integration.

---

## Utility: Test Data

The `TestDataUtil` class provides reusable methods for generating test entities, making tests cleaner and reducing duplication.

---

## Key Concepts Demonstrated

* **DAO Pattern** with Spring's `JdbcTemplate`
* Clean separation of concerns
* Unit testing with **Mockito**
* Integration testing with **Spring Boot**
* Builder pattern with **Lombok**