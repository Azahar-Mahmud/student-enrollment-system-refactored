# Student Course & Enrollment Management System

A comprehensive command-line-based student management system demonstrating advanced software architecture principles, design patterns, and systematic refactoring from legacy code to enterprise-grade architecture.

---

## Refactoring Team

**Group 2 - CSE423 Software Architecture**

| Name | Student ID |
|------|------------|
| Azahar Mahmud Chowdhury Rafi | 2021-3-60-019 |
| Sheikh Md Rakibul Hasan | 2022-3-60-009 |
| Ahnaf Anan | 2023-1-60-089 |

**Course:** CSE423 - Software Architecture  
**Instructor:** Ahmed Adnan, Lecturer  
**Institution:** East West University, Department of Computer Science and Engineering  
**Semester:** Spring 2026

---

## Complete Commit History

- **Initial Legacy Code:** [661e1dd](https://github.com/Azahar-Mahmud/student-enrollment-system-refactored/commit/661e1dd) - Before Refactoring
- **Phase 1 - SOLID Principles Implementation (Final):** [0bc2f19](https://github.com/Azahar-Mahmud/student-enrollment-system-refactored/commit/0bc2f19) - February 11, 2026
- **Phase 2 - Code Smell Refactoring (Final):** [1091089](https://github.com/Azahar-Mahmud/student-enrollment-system-refactored/commit/1091089) - March 14, 2026
- **Phase 3 - Design Patterns Integration (Final):** [2cca3b2](https://github.com/Azahar-Mahmud/student-enrollment-system-refactored/commit/2cca3b2) - April 7, 2026
- **Phase 4 - Architectural Patterns Documentation:** [72c3bd6](https://github.com/Azahar-Mahmud/student-enrollment-system-refactored/commit/72c3bd6) - May 1, 2026

---

## Original Project

**Base Project:** Student Enrollment Management System  
**Initial Commit:** [661e1dd](https://github.com/Azahar-Mahmud/student-enrollment-system-refactored/commit/661e1dd) - Legacy Codebase (Before Refactoring)

This project serves as the foundation for demonstrating evolutionary software design through three major refactoring phases.

---

## Setup & Run Instructions

### Prerequisites
* Java Development Kit (JDK): Version 11 or higher
* Git: To clone the repository
* (Optional) IDE: Eclipse or IntelliJ IDEA

### Command Line

```bash
# Clone the repository
git clone https://github.com/Azahar-Mahmud/student-enrollment-system-refactored.git
cd student-enrollment-system-refactored

# Create data directory with sample CSV files
mkdir data

# Compile all source files
mkdir bin
javac -d bin $(find src -name "*.java")

# Run the application
java -cp bin edu.ccrm.cli.CliManager
```

### IDE (Eclipse/IntelliJ)

1. Clone the repository
2. Import as Java project
3. Create `data` folder in project root with sample CSV files
4. Run `CliManager.java` as Java Application

---

## Project Evolution

This system underwent four systematic phases, transforming from a monolithic legacy codebase to a well-architected enterprise system following industry-standard principles, patterns, and architectural documentation.

### Phase 1: SOLID Principles Implementation (Final)

**Commit:** [0bc2f19](https://github.com/Azahar-Mahmud/student-enrollment-system-refactored/commit/0bc2f19)  
**Date:** February 11, 2026

Established clean architecture foundation by applying SOLID principles across all layers.

**Service Layer Refactoring:**
- **Dependency Inversion Principle (DIP):** Introduced repository abstraction layer (`IStudentRepository`, `ICourseRepository`, `IInstructorRepository`) to decouple services from concrete data storage
- **Single Responsibility Principle (SRP):** Extracted validation logic from `EnrollmentService` into dedicated validation methods (`validateNoDuplicate`, `validateCreditLimit`)

**CLI Layer Refactoring:**
- **SRP & OCP:** Decomposed monolithic `CliManager` (7+ responsibilities) into focused command classes:
  - `ManageStudentsCommand` - Student operations
  - `ManageCoursesCommand` - Course management
  - `ManageEnrollmentsCommand` - Enrollment & grading
  - `ManageDataCommand` - Import/export/backup
  - `ShowReportsCommand` - Report generation
- **DIP:** Introduced `MenuCommand` interface for dependency injection through map-based dispatcher

**I/O Layer Refactoring:**
- **OCP & SRP:** Replaced monolithic `ImportExportService` (6+ responsibilities) with interface-based design
- Created `ICsvImporter`/`ICsvExporter` interfaces for abstraction
- Implemented specialized handlers: `StudentCsvImporter`, `CourseCsvImporter`, `StudentCsvExporter`, `CourseCsvExporter`, `EnrollmentCsvExporter`
- System now open for extension (new CSV types) but closed for modification

---

### Phase 2: Code Smell Refactoring (Final)

**Commit:** [1091089](https://github.com/Azahar-Mahmud/student-enrollment-system-refactored/commit/1091089)  
**Date:** March 14, 2026

Systematically eliminated code smells and applied classic refactoring techniques to improve code quality.

**Code Smells Addressed:**

1. **Inappropriate Naming**
   - Renamed cryptic variable names to meaningful identifiers:
     - `e` → `enrollment`
     - `en` → `enrollment`
     - `s` → `student`
     - `c` → `course`
     - `ss` → `studentService`
     - `cs` → `courseService`
   - Impact: Improved code readability and self-documentation

2. **Comments Smell (Deodorant Comments)**
   - Removed comments that explained bad code by extracting methods with self-documenting names
   - Files refactored: `BackupService`, `CourseManagementHelper`
   - Comments explaining complex logic replaced with descriptive method names

3. **Long Method**
   - Broke down `getStudentTranscript()` in `StudentService` into 7 focused methods
   - Each method has single, clear responsibility
   - Improved testability, maintainability, and code readability through logical decomposition

4. **Primitive Obsession**
   - Replaced magic numbers with named constants:
     - `VALID_GRADE_THRESHOLD = 0.0` (instead of hardcoded `0`)
   - Extracted inline lambda expressions into descriptive methods:
     - `getNoFilterPredicate()` - Clear intent for "no filtering"
     - `hasValidGrade()` - Explicit grade validation logic
   - Files improved: `CourseManagementHelper`, `ReportService`

5. **Duplicated Code**
   - Extracted duplicated GPA calculation logic into dedicated `GpaCalculator` utility class
   - Consolidated recursive file operations from `BackupService` into `RecursiveFileUtils`
   - Moved transcript formatting logic into `TranscriptFormatter` class
   - Impact: Eliminated code duplication across service layer

**Refactoring Techniques Applied:**

- **Extract Method:** Applied to decompose long methods (`getStudentTranscript`), eliminate deodorant comments (`BackupService`), and replace primitive lambda expressions with named methods (`CourseManagementHelper`, `ReportService`)
- **Rename Method/Variable:** Systematically improved naming throughout the codebase
- **Introduce Constants:** Replaced magic numbers with named constants (`VALID_GRADE_THRESHOLD = 0.0`) for maintainability

---

### Phase 3: Design Patterns Integration (Final)

**Commit:** [2cca3b2](https://github.com/Azahar-Mahmud/student-enrollment-system-refactored/commit/2cca3b2)  
**Date:** April 7, 2026

Integrated 9 industry-standard Gang of Four design patterns to achieve enterprise-grade flexibility, extensibility, and maintainability.

**Creational Patterns:**

1. **Singleton (Thread-Safe)**
   - **Problem:** Original `AppConfig` was not thread-safe; multiple threads could create multiple instances
   - **Solution:** Implemented double-checked locking with `volatile` keyword
   - **Benefit:** Guaranteed single instance even in multi-threaded environment

2. **Factory Method**
   - **Problem:** Object creation (`new Student()`, `new Instructor()`) scattered across codebase; constructor changes required hunting down multiple files
   - **Solution:** Created `PersonFactory` (abstract), `StudentFactory`, `InstructorFactory`
   - **Benefit:** Centralized creation logic; single point of modification

**Structural Patterns:**

3. **Adapter**
   - **Problem:** CSV formatting logic hardcoded in export services; switching to JSON/XML would require rewriting services
   - **Solution:** Introduced `ExportFormatAdapter` interface, `CsvExportAdapter` implementation
   - **Benefit:** Export service format-agnostic; easy to add new formats

4. **Decorator**
   - **Problem:** Adding audit logging to `EnrollmentService` would violate Open-Closed Principle
   - **Solution:** Created `EnrollmentServiceInterface`, `LoggingEnrollmentDecorator` wrapping concrete service
   - **Benefit:** Non-invasive feature addition; preserves original service integrity

5. **Bridge**
   - **Problem:** Report generation logic and display logic (System.out) tightly coupled in `ReportService`
   - **Solution:** Separated abstraction (`Report`, `GpaDistributionReport`) from implementor (`ReportRenderer`, `ConsoleReportRenderer`)
   - **Benefit:** Reports can be rendered to console, GUI, PDF independently

6. **Proxy**
   - **Problem:** `DataStore` accepted invalid data (nulls, empty strings) with no validation
   - **Solution:** Created `DataStoreProxy` acting as validation gatekeeper before real store
   - **Benefit:** Access control and validation without bloating storage layer

**Behavioral Patterns:**

7. **Strategy**
   - **Problem:** Grading formula hardcoded in `Grade` enum; couldn't support curved or lenient grading
   - **Solution:** Extracted `GradingStrategy` interface, `StandardGradingStrategy`, `RelativeGradingStrategy`
   - **Benefit:** Grading algorithms swappable at runtime

8. **Observer**
   - **Problem:** Enrollment success printed directly with `System.out.println`; adding email notifications would require modifying service
   - **Solution:** Implemented `EnrollmentObserver` pattern with `EnrollmentNotifier`, `EnrollmentLogger`
   - **Benefit:** Multiple observers can react to enrollment events asynchronously

9. **Mediator**
   - **Problem:** `EnrollmentService` tightly coupled to both `StudentService` and `CourseService`; adding more services would create tangled dependencies
   - **Solution:** Introduced `ServiceMediator` interface, `EnrollmentMediator`
   - **Benefit:** Services communicate through central mediator; fully decoupled

---

### Phase 4: Architectural Patterns Documentation

**Commit:** [72c3bd6](https://github.com/Azahar-Mahmud/student-enrollment-system-refactored/commit/72c3bd6)  
**Date:** May 1, 2026

Documented comprehensive system architecture using industry-standard architectural patterns and diagrams to provide clear visualization of system structure and component interactions.

**Architecture Diagrams Created:**

1. **System Context Diagram**
   - Identified system boundaries and external actors (Administrator, Instructor, Student)
   - Mapped interactions with File System Storage (CSV Data Files, Backups Directory)
   - Established System under Consideration (SuC) scope

2. **Archetype 1: Layered Architecture**
   - **Presentation Archetype:** CliManager, Command Registry, Management Helpers
   - **Business Logic Archetype:** StudentService, CourseService, EnrollmentService, ReportService with integrated design patterns (Observer, Mediator, Decorator, Strategy, Bridge, Proxy)
   - **Data Access Archetype:** ImportExportService, BackupService, CSV Services with Strategy and Adapter patterns
   - **Data Storage Archetype:** CSV files (students, courses, enrollments) and timestamped backup folders

3. **Archetype 2: Domain Model**
   - Abstract Person with concrete Student and Instructor implementations
   - Factory Method pattern hierarchy (PersonFactory → StudentFactory/InstructorFactory)
   - Value objects (CourseCode) and enums (Semester, Grade)
   - Domain entities with clear relationships (Enrollment references Student, Course, Semester, Grade)

4. **Archetype 3: Design Pattern Integration**
   - **Creational Patterns:** Singleton (AppConfig), Factory Method (PersonFactory)
   - **Structural Patterns:** Adapter (ExportAdapter), Decorator (LoggingEnrollment), Bridge (Reporting), Proxy (DataStoreProxy)
   - **Behavioral Patterns:** Strategy (Grading), Observer (EnrollmentObserver), Mediator (EnrollmentMediator)

**Complete Layered Architecture (5 Layers):**

- **Layer 1 - Presentation:** CliManager, CommandRegistry, 5 Management Helpers
- **Layer 2 - Business Logic:** Core services with 7 design pattern implementations
- **Layer 3 - Data Access:** Import/Export services, CSV handlers, Strategy and Adapter patterns
- **Layer 4 - Domain & Utilities:** Domain models, Factory pattern, custom exceptions, utilities, Singleton configuration
- **Layer 5 - Data Storage:** CSV files in data/ directory, timestamped backups in backup/ directory

**Component Interaction Diagram:**
- Documented data flow from User → ManagementHelper → Business Service → DataStore
- Illustrated Observer pattern notification flow (Enrollment Logger/Notifier)
- Mapped Mediator pattern coordination between services

**Event-Driven Sub-Architecture:**
- Event Producer: EnrollmentService emitting ENROLLMENT_CREATED/REMOVED events
- Event Channel: Observer list with attach/detach/notify methods
- Event Consumers: EnrollmentNotifier (console), EnrollmentLogger (audit), future consumers (email/SMS)

---

**Acknowledgments:** Special thanks to our instructor Ahmed Adnan for guidance on software architecture principles and design pattern implementation that made this comprehensive refactoring journey possible.