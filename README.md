
# JDBC Code Project

<p align="center">
  <img src="Images/project-demo.png" alt="JDBC Demo" width="600"/>
</p>

<p align="center"><b>Figure:</b> Screenshot of JDBC Student Management System</p>

## Overview
This project demonstrates the use of Java Database Connectivity (JDBC) to interact with a PostgreSQL database. It includes various Java classes that perform operations such as creating databases, creating tables, inserting data, updating records, deleting records, and viewing data.

## Folder Structure
```
MultipleFiles/
├── org.eclipse.jdt.core.prefs              # Eclipse IDE preferences for Java Development
├── org.eclipse.m2e.core.prefs              # Eclipse IDE preferences for Maven integration
├── jdbc_demo.java                          # Java class for basic JDBC operations
├── Student.java                            # Java class for managing student records
├── Student_Data_Management.java            # Java class for student data management
├── pom.xml                                 # Maven Project Object Model file for dependencies
├── pom.properties                          # Maven properties file
├── jdbc_demo.class                         # Compiled class file for jdbc_demo
├── Student.class                           # Compiled class file for Student
├── Student_Data_Management.class           # Compiled class file for Student_Data_Management
├── Images/                                 # Directory for storing images (if any)
└── README.md                               # This README file
```

## Features
- **Database Operations**: Create databases and tables, insert, update, delete, and view records.
- **Student Management**: The `Student` and `Student_Data_Management` classes offer interfaces to manage student data.
- **JDBC Integration**: Demonstrates connectivity with PostgreSQL using standard JDBC.

## Technologies Used
- **Java**: Primary language used for development.
- **PostgreSQL**: Backend database for storing information.
- **JDBC**: For Java-based database operations.
- **Maven**: Dependency management and project build tool.

## PostgreSQL Requirements
- **PostgreSQL Version**: 12 or above is recommended.
- **Default Port**: `5432`
- **Database Name**: You can create a database manually, e.g., `studentdb`
- **JDBC Connection Example**:
   ```java
   static String url = "jdbc:postgresql://localhost:5432/";
   static String user = "postgres";
   static String password = "1234";
   ```
- If your database is named `studentdb`, then your connection string should be:
   ```java
   static String url = "jdbc:postgresql://localhost:5432/studentdb";
   ```

- Ensure your PostgreSQL server is running, and that user credentials are correctly set up.

## Getting Started

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   ```

2. **Set Up PostgreSQL**:  
   Ensure PostgreSQL is installed and running. Create a database as required by the application.

3. **Configure Database Connection**:  
   Update the connection string, username, and password in the Java source files as shown above.

4. **Build the Project** using Maven:
   ```bash
   mvn clean install
   ```

5. **Run the Application**:  
   Execute the main class (e.g., `jdbc_demo` or `Student`) to start the program.

## Usage
- Use `jdbc_demo` to perform simple database operations.
- Use the `Student` class for menu-driven student management.
- Use `Student_Data_Management` for extended functionality and better data handling.

## Contributing
Contributions are welcome! Feel free to fork the repo and submit a pull request or open an issue with improvements or suggestions.

## License
This project is licensed under the MIT License - see the LICENSE file for more details.

## Contact
For any inquiries, please contact **Aryan Mevada** at [aryanmevda04@gmail.com].
