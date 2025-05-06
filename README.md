# 👷‍♂️ Worker Management System – Spring Boot Backend

🔗 **Visit us:** [www.officialshivam.in](https://www.officialshivam.in)

---

## 📘 Overview

A robust and scalable **Spring Boot Backend** for managing workers, their attendance, extra tasks, and dues. Includes automated scheduling for attendance entries.

---

## 🧱 Schemas

- **👤 Workers**
- **📅 Attendance**
- **💰 Dues**
- **🛠️ Extra_Tasks**

---

## 📡 API Endpoints

### 👤 Worker

- ✅ `POST /workers` – Add new Worker  
- ❌ `DELETE /workers/{id}` – Delete Worker  
- ♻️ `PUT /workers/{id}` – Update Worker Details  

### 📅 Attendance

- ✅ `POST /attendance` – Add Attendance  
- ♻️ `PUT /attendance/{id}` – Update Attendance  
- ❌ `DELETE /attendance/{id}` – Delete Attendance  

### 🛠️ Extra Tasks

- ✅ `POST /extra-tasks` – Add Task  
- ❌ `DELETE /extra-tasks/{id}` – Remove Task  
- ♻️ `PUT /extra-tasks/{id}` – Update Task  

### ⏰ Scheduler

- 🕒 Automatically creates a daily attendance record for **all active workers**  
  _(Default status: `false`)_

---

## 🧾 Sample Request Body

```json
{
  "worker_uuid": "worker-1234",
  "name": "John Doe",
  "is_active": true,
  "worker_data": {
    "worker_task": "Plumber",
    "worker_phone": "9876543210",
    "worker_joining_date": "2023-01-15",
    "worker_base_salary": 15000.00,
    "worker_per_day_salary": 500.00,
    "worker_emergency_contact_number": "9123456789",
    "worker_address": "123, Main Street, City"
  },
  "attendance_data": {
    "attendance_date": "2024-05-06",
    "attendance_status": "Present",
    "is_extra_task_assigned": false
  },
  "due_data": {
    "due_date": "2024-05-01",
    "due_amount": 1000.00,
    "due_reason": "Advance Payment"
  },
  "extra_task_data": {
    "task_id": "task-5678",
    "is_task_completed": false,
    "description": "Fixing water pipe",
    "task_amount": 300.00
  }
}
```

## 🧾 Sample Response Body

```json
{
  "ResponseCode": "200",
  "ResponseMessage": "SUCCESS"
}
```

---

## 🚀 Tech Stack

- 🧰 **Spring Boot** – Backend framework
- 🗃️ **MySQL / PostgreSQL** – Relational Database (customizable)
- 🧪 **Spring Data JPA / Hibernate** – ORM for database access
- 🔁 **Spring Scheduler** – For daily automated tasks
- 📦 **Maven** – Dependency and build management

---

## 👨‍💻 Author

Developed by [**Shivam**](https://www.officialshivam.in)

---

## 📜 License

MIT License. Feel free to use, modify, and distribute.

---
