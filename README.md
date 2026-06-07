# PRODIGY_BD_01 -> User Management REST API

📦This repository contains "Task 1" for my Software Engineering Internship at "Prodigy InfoTech"🚀.
🎯The goal of this project is to build a robust backend REST API using Java and Spring Boot that securely manages user records using an in-memory architecture like an hashmap with thread-safe sequential ID generation.

🌟Current Progress --> Day 1 -> Created the `pom.xml` configuration file and built the `User.java` model class to define user fields (ID, Name, Email, Age).
Day 2 --> Added `Controller.java` to handle the routing logic (POST, GET, PUT, DELETE) and `Application.java` to start the Spring Boot app. Used a `ConcurrentHashMap` to store users in memory and an `AtomicLong` to handle auto-incrementing user IDs safely.
