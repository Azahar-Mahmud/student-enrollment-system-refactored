# Student Course & Enrollment Management System

This project is a comprehensive, command-line-based student management system developed in Java. It demonstrates a wide range of core Java features, from fundamental syntax to advanced topics like the Stream API and NIO.2.

**Note on Origin:** This project is a refactored and architecturally enhanced version of the Student Enrollment System originally created by [yhcb21](https://github.com/yhcb21/student-enrollment-system).

## 🏗️ System Architecture & Design

This application has been engineered using a **Layered Architecture** to ensure separation of concerns, maintainability, and scalability.

### Architectural Flow
The system follows a strict flow of data and control:
`[CLI Layer] -> [Service Layer] -> [Repository Layer] -> [Data Source]`

1.  **CLI Layer (Presentation):** Handles user input/output. Uses the **Command Pattern** to delegate specific menu actions to separate command classes.
2.  **Service Layer (Business Logic):** Contains the core business rules (e.g., calculating GPA, preventing duplicate enrollments). It relies on Repository interfaces (DIP).
3.  **Repository Layer (Data Access):** Abstracts the data storage. We use in-memory Maps currently, but this layer allows switching to a Database without changing the Service code.
4.  **I/O Layer (Infrastructure):** Handles file persistence using the **Template Method Pattern** to standardizing importing/exporting logic.

---

## 🛠️ Engineering Principles & Design Patterns

This project applies **SOLID Principles** to remove "Code Smells" and improve structural integrity.

### 1. Design Patterns Implemented
| Pattern | Location | Purpose |
| :--- | :--- | :--- |
| **Repository** | `edu.ccrm.repository` | Decouples business logic from data access code. |
| **Command** | `edu.ccrm.cli.command` | Encapsulates UI actions as objects, fixing the "God Class" issue in `CliManager`. |
| **Template Method** | `edu.ccrm.io.template` | Defines the skeleton of CSV Import/Export algorithms, allowing subclasses to define specific parsing logic. |
| **Builder** | `Course.Builder` | Provides a readable way to construct complex `Course` objects. |
| **Singleton** | `AppConfig` | Ensures only one configuration instance exists globally. |

### 2. SOLID Principles Applied
* **Single Responsibility Principle (SRP):**
    * *Before:* `CliManager` handled UI, logic, and navigation. `ImportExportService` handled 3 types of files for both input and output.
    * *After:* `CliManager` only routes commands. Specific commands (e.g., `ManageStudentsCommand`) handle specific menus. `StudentCsvExporter` handles only student exports.
* **Open/Closed Principle (OCP):**
    * The system is open for extension but closed for modification. New menu options can be added by creating a new `Command` class without altering the main loop. New export formats can be added by extending `CsvExportHandler`.
* **Dependency Inversion Principle (DIP):**
    * High-level modules (`StudentService`) do not depend on low-level modules (`DataStore` or `HashMap`). Both depend on abstractions (`IStudentRepository`).

### 3. Code Smells Removed
* **God Class:** `CliManager` was reduced from ~230 lines to ~60 lines.
* **Rigidity:** Hardcoded `switch` statements for menus were replaced with a flexible Command Map.
* **Duplication (DRY):** Repeated `try-catch` blocks and file creation logic in I/O services were consolidated into abstract base classes.

---

## 🚀 How to Run

### Prerequisites
* **Java Development Kit (JDK):** Version 11 or higher.
* **Git:** To clone the repository.

### 1. From the Command Line
1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/Azahar-Mahmud/student-enrollment-system-refactored.git](https://github.com/Azahar-Mahmud/student-enrollment-system-refactored.git)
    cd student-enrollment-system-refactored
    ```
2.  **Create the data directory:**
    ```bash
    mkdir data
    ```
    *(Note: The application will auto-generate sample data if files are missing, but you can populate `students.csv` and `courses.csv` manually if preferred).*

3.  **Compile all source files:**
    ```bash
    # Create a directory for compiled classes
    mkdir bin

    # Compile (for Windows) - Updated to include new packages
    javac -d bin src\edu\ccrm\cli\*.java src\edu\ccrm\cli\command\*.java src\edu\ccrm\config\*.java src\edu\ccrm\domain\*.java src\edu\ccrm\exception\*.java src\edu\ccrm\io\*.java src\edu\ccrm\io\template\*.java src\edu\ccrm\repository\*.java src\edu\ccrm\service\*.java src\edu\ccrm\util\*.java

    # Compile (for macOS/Linux)
    javac -d bin $(find src -name "*.java")
    ```

4.  **Run the application:**
    ```bash
    java -cp bin edu.ccrm.cli.CliManager
    ```

---

## 📚 Syllabus Topic to Code Mapping (Updated)

| Syllabus Topic | File/Class/Method Where Demonstrated |
| :--- | :--- |
| **Packages & `main` class** | Project structure (`edu.ccrm.*`), `CliManager.main()` |
| **Interfaces (New)** | `IStudentRepository`, `MenuCommand` |
| **Abstract Classes** | `Person`, `CsvExportHandler`, `CsvImportHandler` |
| **Polymorphism** | `MenuCommand.execute()` (Dynamic dispatch), `Repository` implementations |
| **Decision Structures** | `Command Map` (replaces complex switch), `InputValidator` |
| **Loops (for, while)** | `CliManager.main()` (Game Loop), `Stream.forEach` |
| **File I/O (NIO.2)** | `ImportExportService`, `BackupService` |
| **Generics** | `CsvExportHandler<T>` (Generic class), `List<Student>` |
| **Functional Interfaces** | `Predicate<Course>` in `CourseService` |
| **Exception Handling** | Custom exceptions (`DuplicateEnrollmentException`), Multi-catch blocks |

## 🧪 Notes

### Enabling Assertions
Assertions are used in this project (e.g., in `CourseCode.java`) to check for internal invariants.
* **Command Line:** `java -ea -cp bin edu.ccrm.cli.CliManager`
* **Eclipse:** Run Configurations > Arguments > VM arguments: `-ea`

### Sample Workflow
1.  **Import Data:** `4` (Data Mgmt) -> `1` (Export/Import) -> Auto-load happens on start.
2.  **Add Student:** `1` (Manage Students) -> `1` (Add).
3.  **Enroll:** `3` (Manage Enrollments) -> `1` (Enroll).
4.  **Grade:** `3` (Manage Enrollments) -> `2` (Record Grade).
5.  **Report:** `5` (Reports) -> `1` (GPA Distribution) -> *Requires graded students*.
