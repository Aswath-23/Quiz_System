#  Online Quiz & Assessment System

A console-based Java application that allows an **admin** to manage quiz questions and **users** to take timed quizzes. Built as a final-year college project to demonstrate object-oriented programming, file handling, and real-time quiz features.

---

## Features

- **Admin Module**
  - Secure login (default: `admin` / `admin123`)
  - Add new questions with category, difficulty, and four options
  - Delete existing questions from the question bank
- **User Quiz Module**
  - Randomized question order (optional – can be extended)
  - **15-second timer** per question
  - Automatic skip on timeout
  - Final score, percentage, and performance feedback
  - Detailed answer review showing correct/incorrect responses
- **Clean Code Structure**
  - Separate classes for `Question`, `AdminService`, `QuizManager`, `Result`, `FileHandler`
  - Exception handling and input validation

---

## Technologies Used

- **Java** (JDK 8 or higher)
- **File I/O** (BufferedReader, PrintWriter)
- **Object-Oriented Principles** (encapsulation, separation of concerns)

