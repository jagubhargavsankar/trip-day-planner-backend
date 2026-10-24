# 🌍 Trip Day Description Management System

## 📌 Project Objective
The **Trip Day Description Management System** is a full-featured **Java Spring Boot + Hibernate** backend application that helps users create, manage, and organize **trip itineraries**.  
Each trip can contain multiple **Day Descriptions**, and each description can also be saved in a **Library** for reuse in future trips.  

The goal is to simplify trip planning by allowing detailed daily planning — including activities, timing, packing suggestions, and optional images.

---

## 🛠️ Tech Stack
| Layer | Technology |
|-------|-------------|
| Language | Java |
| Framework | Spring Boot |
| ORM | Hibernate / JPA |
| Database | MySQL |
| Build Tool | Maven |
| JSON Handling | Jackson |
| IDE (Suggested) | IntelliJ IDEA / STS / VS Code |
| Version Control | Git + GitHub |

---

## 🚀 Features

### 🏖 Trip Management
- Create new trips  
- Fetch all trips  
- Get trip by ID  
- Delete trip (with cascading day descriptions)

### 📅 Day Description Management
- Create new day description under a trip  
- Fetch all day descriptions for a trip  
- Fetch reusable library items  
- Fetch single day description by ID  
- Update (PUT) and partial update (PATCH)  
- Soft delete (mark as deleted, not removed)  
- Copy an existing library item into a new trip  

---

## 📂 Database Design

### **Trip Table**
| Field | Type | Description |
|-------|------|-------------|
| id | BIGINT | Primary Key |
| tripName | VARCHAR | Trip name |
| dayDescriptions | OneToMany | Linked list of day descriptions |

### **DayDescription Table**
| Field | Type | Description |
|--------|------|-------------|
| id | BIGINT | Primary Key |
| title | VARCHAR(70) | Title of the day |
| description | VARCHAR(800) | Activity details |
| location | VARCHAR | Location name |
| time | VARCHAR | Time of the activity |
| packingSuggestions | VARCHAR(800) | Packing or clothing advice |
| images | List<String> | Image URLs |
| saveInLibrary | BOOLEAN | True = reusable in library |
| deleted | BOOLEAN | Soft delete flag |
| trip_id | BIGINT | Foreign key (Trip) |

---

## ⚡ API Endpoints

### 🧭 TripController
| Method | Endpoint | Description |
|--------|-----------|-------------|
| `GET` | `/api/trips` | Get all trips |
| `POST` | `/api/trips` | Create a new trip |
| `GET` | `/api/trips/{id}` | Get trip by ID |
| `DELETE` | `/api/trips/{id}` | Delete a trip by ID |

### 📋 DayDescriptionController
| Method | Endpoint | Description |
|--------|-----------|-------------|
| `POST` | `/api/trips/{tripId}/day-descriptions` | Create a new day description for a trip |
| `GET` | `/api/trips/{tripId}/day-descriptions` | Get all day descriptions for a trip |
| `GET` | `/api/library/day-descriptions` | Get reusable library items |
| `GET` | `/api/day-descriptions/{id}` | Get a single day description by ID |
| `PUT` | `/api/day-descriptions/{id}` | Update a day description completely |
| `PATCH` | `/api/day-descriptions/{id}` | Partial update of fields |
| `DELETE` | `/api/day-descriptions/{id}` | Soft delete a day description |
| `POST` | `/api/trips/{tripId}/day-descriptions/copy/{libraryId}` | Copy an item from library into a trip |

---

## 🧠 Example JSON Bodies

### ➕ Create a Trip
**POST** `/api/trips`
```json
{
  "tripName": "Goa Vacation"
}
➕ Create Day Description for a Trip
POST /api/trips/1/day-descriptions

json
Copy code
{
  "title": "Dinner Under the Stars",
  "description": "Romantic candlelight dinner with live music",
  "location": "Calangute Beach",
  "time": "Evening",
  "packingSuggestions": "Smart casual wear",
  "images": [
    "https://example.com/dinner1.jpg",
    "https://example.com/dinner2.jpg"
  ],
  "saveInLibrary": true
}
✏️ Update Day Description
PUT /api/day-descriptions/3

json
Copy code
{
  "title": "Evening Dinner",
  "description": "Dinner with live beach band",
  "time": "20:00",
  "packingSuggestions": "Evening wear",
  "saveInLibrary": false
}
🧩 Partial Update
PATCH /api/day-descriptions/3

json
Copy code
{
  "title": "Updated Dinner Plan"
}
🧱 Project Structure
swift
Copy code
src/
 ├── main/java/com/example/trip_day_description/
 │   ├── controller/
 │   │   ├── TripController.java
 │   │   └── DayDescriptionController.java
 │   ├── model/
 │   │   ├── Trip.java
 │   │   └── DayDescription.java
 │   ├── repository/
 │   │   ├── TripRepository.java
 │   │   └── DayDescriptionRepository.java
 │   ├── service/
 │   │   ├── TripService.java
 │   │   └── DayDescriptionService.java
 │   └── service/impl/
 │       ├── TripServiceImpl.java
 │       └── DayDescriptionServiceImpl.java
 ├── resources/
 │   └── application.properties
💡 Key Highlights
✅ Clean separation of controller, service, and repository layers
✅ Soft delete implemented using a boolean flag
✅ Partial updates supported via PATCH
✅ Reusable Library system for day descriptions

🧑‍💻 Author
Bhargav Sankar
Java Developer | Spring Boot Enthusiast
📧 jagubhargavasankar2005@gmail.com
💻 GitHub: @jagubhargavasankar2005-lgtm
