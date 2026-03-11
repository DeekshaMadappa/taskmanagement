# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Run the application
./mvnw spring-boot:run

# Build (skip tests)
./mvnw clean package -DskipTests

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=TaskmanagementApplicationTests
```

## Tech Stack

- **Spring Boot 4.0.3**, Java 21
- **Spring Data JPA** with Hibernate (DDL auto: `update`)
- **Spring Security** (included but not yet configured)
- **PostgreSQL** driver (Lombok for boilerplate reduction)

## Database Setup

Requires a running PostgreSQL instance:
- Host: `localhost:5430` (non-standard port)
- Database: `task_management`
- Username: `task_user`
- Password: `password123`

## Architecture

This is an early-stage REST API backend for a task management system. The current codebase only has the data layer — no controllers or services exist yet.

**Domain model:**
- `User` — has `name`, `email` (unique), `password`, `role` (USER/ADMIN)
- `Project` — has a `name`, `description`, `owner` (User), `createdAt`
- `Task` — has `title`, `description`, `status` (TODO/IN_PROGRESS/DONE), `dueDate`, belongs to a `Project` and has an `assignedUser`

**Relationships:** `Task` → `Project` (many-to-one), `Task` → `User` (assignedUser, many-to-one), `Project` → `User` (owner, many-to-one)

**Custom repository queries:**
- `UserRepository.findByEmail(String email)`
- `TaskRepository.findByProjectId(Long projectId)`

## Known Package Inconsistency

There is a split between two base packages that must be resolved before the app will compile correctly:
- `TaskmanagementApplication` and `User` entity use `com.example.taskmanagement`
- `Task`, `Project`, and all repositories use `com.company.taskmanagement`

All classes should be unified under one package (likely `com.example.taskmanagement` to match the `pom.xml` `groupId`).