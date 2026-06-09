# PRODIGY_BD_01 -> User Management REST API

📦This repository contains "Task 1" for my Software Engineering Internship at "Prodigy InfoTech"🚀.

🎯The goal of this project is to build a robust backend REST API using Java and Spring Boot that securely manages user records using an in-memory architecture like an hashmap with thread-safe unique id generation.

🌟Current Progress --> Day 1 🚀 -> Created the `pom.xml` configuration file and built the `User.java` model class to define user fields (ID, Name, Email, Age).

Day 2 🔄-> Added `Controller.java` to handle the routing logic (POST, GET, PUT, DELETE) and `Application.java` to start the Spring Boot app. Used a `ConcurrentHashMap` to store users in memory and implement `UUID` to handle unique id generation safely.


Day 3 🔥-> Implemented a `ExceptHandler.java` file to automatically catch validation errors and return clean error responses.


Task Completion🎯 Summary:-
Task 1 is 100% complete and fully verified. All CRUD operations run flawlessly, inputs are securely validated. Ready for final review!
