# 🧩 What is DAO?

**DAO** = **Data Access Object**

- It’s a **design pattern**.
- It’s a **class** whose **only job** is to **talk to the database**.

---

A **DAO** (Data Access Object) is a design pattern used to separate database interaction logic from the rest of your application. It acts as a bridge between your **service layer** and the **persistence layer** (the database).

Imagine a domain where you have **books** and **authors**:
- A **book** has attributes like ISBN (unique identifier), title, and an author ID (linking it to an author).
- An **author** has attributes like a unique ID, name, and age.

If multiple services in your application need to interact with books and authors, and you use **JDBC** directly, each service would have to:
- Know the structure of the database,
- Write SQL queries,
- Handle the mapping between **SQL results** and **Java objects**.

This approach would lead to **lots of duplicate code** and **tight coupling** between services and the database.

The solution is to **centralize** all database operations related to a specific entity (e.g., `Book` or `Author`) into their own **DAO classes** (`BookDao`, `AuthorDao`).

Each DAO:
- Knows how to **query** and **update** the database for its entity,
- **Maps** database rows to Java objects and vice versa,
- Provides a **clean interface** for services to call without worrying about SQL details.

Thus, services simply **inject** the appropriate DAO(s) and call high-level methods like `findAll()`, `save()`, `deleteById()`, etc.

Using DAOs leads to:
- **Separation of concerns** (services don't deal with SQL),
- **Less code duplication**,
- **Better modularity**, making the app easier to develop, maintain, and test.

In short:
> **DAO = clean separation between the service layer and the database, organized around your entities.**

---

# 🎯 What DAO does:

| Action | Meaning |
|:------|:--------|
| **Retrieve** | Get data from the database |
| **Insert** | Save new data into the database |
| **Update** | Modify existing data |
| **Delete** | Remove data |

---

## 🛠 Why use DAO?

- Separates **business logic** (your app's rules) from **database logic** (SQL stuff).
- Makes your code **cleaner**, **easier to test**, and **easier to maintain**.

Instead of putting SQL directly inside your service or controller, you say:

👉 "Hey DAO, can you please go to the database and get/save/update this for me?"

---

## A tiny real example:

```java
@Repository
public class StudentDao {

    private final JdbcTemplate jdbcTemplate;

    public StudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> findAll() {
        return jdbcTemplate.query(
            "SELECT * FROM student",
            (rs, rowNum) -> new Student(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email")
            )
        );
    }
}
```
---

# 📦 In short:

| Term | Meaning |
|-----|---------|
| DAO | A class that **hides** all the database code. |
| Goal | Help your app **talk to** the database cleanly. |
| Benefit | **Separation of concerns** = clean architecture! |

---