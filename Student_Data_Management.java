package jdbc_code2;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class Student_Data_Management {
    static Scanner sc = new Scanner(System.in);
    public static String dbname = "student_mangement";
    public static String tbname = "studnet_data";
    
    static String url = "jdbc:postgresql://localhost:5432/";
    static String user = "postgres";
    static String password = "1234";

    public static void CreateDb() {
       
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conn.createStatement();
            String query = "CREATE DATABASE " + dbname;
            stmt.executeUpdate(query);
            System.out.println("Database " + dbname + " created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void CreateTb() {
        try (Connection conn = DriverManager.getConnection(url + dbname, user, password)) {
            Statement stmt = conn.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS " +tbname + " ("
                    + "Std_ROLLNO INT PRIMARY KEY, "
                    + "Std_ENRID VARCHAR(10) UNIQUE, "
                    + "Std_Name VARCHAR(50), "
                    + "Std_Course VARCHAR(100), "
                    + "Std_ContactNo VARCHAR(15))";
            stmt.execute(query);
            System.out.println("Table " + tbname + " created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Insert() {
        Map<String, Object> studentData = new HashMap<>();

        System.out.println("\nEnter Student Details:");
        System.out.print("ROLL NO: ");
        studentData.put("rollno", sc.nextInt());
        sc.nextLine(); // Consume newline

        System.out.print("ENROLLMENT ID: ");
        studentData.put("ernid", sc.nextLine());

        System.out.print("Student Name: ");
        studentData.put("name", sc.nextLine());

        System.out.print("Course: ");
        studentData.put("course", sc.nextLine());

        System.out.print("Contact No: ");
        studentData.put("contact", sc.nextLine());
/*String query="Create table "+tbname+"( 
			 				Std_ENRID SERIAL PRIMARY KEY,"
							+"Std_ROLLNO Int,"
							+"Std_Name Char,"
							+"Std_Course Varchar(100),"
			 			+"Std_ContactNo int()" ;
			*/
        try (Connection conn = DriverManager.getConnection(url + dbname, user, password)) {
            String query = "INSERT INTO " + tbname + " VALUES(?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, (int) studentData.get("rollno"));
            pstmt.setString(2, (String) studentData.get("ernid"));
            pstmt.setString(3, (String) studentData.get("name"));
            pstmt.setString(4, (String) studentData.get("course"));
            pstmt.setString(5, (String) studentData.get("contact"));
            pstmt.executeUpdate();
            System.out.println("Values Inserted Successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Delete() {
        System.out.print("Enter the ROLL NO to delete: ");
        int id = sc.nextInt();

        try (Connection conn = DriverManager.getConnection(url + dbname, user, password)) {
            String query = "DELETE FROM " + tbname + " WHERE Std_ROLLNO = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("No record found with the given Roll No.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Update() {
        Map<String, Object> updatedData = new HashMap<>();

        System.out.println("\nEnter Enrollment ID to Update Record:");
        System.out.print("ENROLLMENT ID: ");
        updatedData.put("ernid", sc.next());

        sc.nextLine(); // Consume newline
        System.out.print("New Student Name: ");
        updatedData.put("name", sc.nextLine());

        System.out.print("New Course: ");
        updatedData.put("course", sc.nextLine());

        System.out.print("New Contact No: ");
        updatedData.put("contact", sc.nextLine());

        try (Connection conn = DriverManager.getConnection(url + dbname, user, password)) {
            String query = "UPDATE " + tbname + " SET Std_Name = ?, Std_Course = ?, Std_ContactNo = ? WHERE Std_ENRID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, (String) updatedData.get("name"));
            pstmt.setString(2, (String) updatedData.get("course"));
            pstmt.setString(3, (String) updatedData.get("contact"));
            pstmt.setString(4, (String) updatedData.get("ernid"));
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Record Updated Successfully.");
            } else {
                System.out.println("No record found with the given Enrollment ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void view() {
        List<Map<String, String>> studentRecords = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url + dbname, user, password)) {
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM " + tbname;
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Map<String, String> student = new HashMap<>();
                student.put("Std_RollNo", rs.getString("Std_ROLLNO"));
                student.put("Std_Name", rs.getString("Std_Name"));
                student.put("Std_EnrId", rs.getString("Std_ENRID"));
                student.put("Std_Course", rs.getString("Std_Course"));
                student.put("Std_Contact", rs.getString("Std_ContactNo"));
                studentRecords.add(student);
            }

            if (studentRecords.isEmpty()) {
                System.out.println("No records found.");
            } else {
                for (Map<String, String> student : studentRecords) {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("Std_RollNo : " + student.get("Std_RollNo"));
                    System.out.println("Std_Name : " + student.get("Std_Name"));
                    System.out.println("Std_EnrId : " + student.get("Std_EnrId"));
                    System.out.println("Std_Course : " + student.get("Std_Course"));
                    System.out.println("Std_Contact : " + student.get("Std_Contact"));
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       
    	
    	while (true) {
            System.out.println("\n_____________________Student Data System_______________________");
            System.out.println("1. Insert Student Details");
            System.out.println("2. Update Student Details");
            System.out.println("3. Delete Student Details");
            System.out.println("4. View Student Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    Insert();
                    break;
                case 2:
                    Update();
                    break;
                case 3:
                    Delete();
                    break;
                case 4:
                    view();
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please enter again.");
            }
        }
        
    }
}
