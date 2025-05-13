# Software Requirements Specification (SRS) for Task Management App

## 1. Introduction

### 1.1 Purpose
This document outlines the scope and requirements for a Task Management App that allows users to manage tasks and users. The app provides functionality for creating, updating, retrieving, and deleting tasks and users.

### 1.2 Scope
The app includes two main components: **TaskController** (manages tasks) and **UserController** (manages users). It uses **Spring Boot** to provide a RESTful API and integrates **Swagger** for documentation.

## 2. System Overview
The app includes:

- **TaskController**: Manages task operations.
- **UserController**: Manages user operations.
- **DTOs**: TaskModel and UserModel represent task and user data.
- **ResponseApi**: Standardizes API responses.

## 3. Functional Requirements

### 3.1 Task Management
- **Create Task**: Create a new task with task details.
- **Get All Tasks**: Retrieve all tasks in the system.
- **Get Task by ID**: Retrieve a task by its ID.
- **Get Tasks by User ID**: Retrieve tasks assigned to a specific user.
- **Update Task**: Update an existing task.
- **Delete Task**: Delete a task by its ID.

### 3.2 User Management
- **Create User**: Create a new user.
- **Get All Users**: Retrieve all users in the system.
- **Get User by ID**: Retrieve a user by their ID.
- **Update User**: Update an existing user.
- **Delete User**: Delete a user by their ID.

## 4. Non-Functional Requirements

- **Maintainability**: Follow SOLID principles and provide clear documentation.

## 5. API Specifications

### Task API
- **Get All Tasks**: `GET /api/tasks` → List of tasks.
- **Get Task by ID**: `GET /api/tasks/{id}` → Task details.
- **Create Task**: `POST /api/tasks` → Task creation.
- **Update Task**: `PUT /api/tasks/{id}` → Update task.
- **Delete Task**: `DELETE /api/tasks/{id}` → Delete task.

### User API
- **Get All Users**: `GET /api/users` → List of users.
- **Get User by ID**: `GET /api/users/{id}` → User details.
- **Create User**: `POST /api/users` → User creation.
- **Update User**: `PUT /api/users/{id}` → Update user.
- **Delete User**: `DELETE /api/users/{id}` → Delete user.
- 
## Tech Stack

The following technologies are used to build the Task Management App:

- **JDK**: 17
- **Spring Boot**: Version 3.4.3
- **Swagger**: For API documentation (using Springdoc OpenAPI for Swagger UI integration)
- **Maven**: For dependency management and project build

## Swagger Integration

Swagger is integrated into the project using **Springdoc OpenAPI** to automatically generate API documentation and provide an interactive UI.

- **API Documentation URL**:  
  After running the app, you can access the Swagger UI at: http://localhost:8080/swagger-ui.html

## Security Overview

Features:

Login & Registration with password

Google OAuth2 login

2FA via Email (OTP)

JWT (access & refresh tokens)

Swagger API documentation

Auth Flow:

POST /api/auth/register – Register new user

POST /api/auth/login – Validate password & send OTP to email

POST /api/auth/verify-otp – Submit OTP & receive tokens

POST /api/auth/refresh – Get new access token with refresh token

