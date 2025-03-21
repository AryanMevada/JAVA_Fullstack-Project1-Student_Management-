package jdbc_code;


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

public class Student {
	 
	static Scanner sc = new Scanner(System.in);
	
	public  static String dbname="studentdb";
	public static String tbname="Student_info";
	
	static String url = "jdbc:postgresql://localhost:5432/"; 
    static String user = "postgres";
    static String password = "1234";
	
    
	public static   void CreateDb() {
		
		 
		try (Connection conn=DriverManager.getConnection(url,user,password);){
			Statement stmt=conn.createStatement();
		    String query = "CREATE DATABASE " + dbname;
		     stmt.executeLargeUpdate(query);
				
			System.out.println("Database "+dbname+" created sucessfully");
			
		}catch (SQLException e) {e.printStackTrace();}
	}
	
	
	
	
	
	
	//sql query from user
	
	
	
	
	
	
	
    public static void CreateTb() {

    System.out.println("Enter the database name: ");
		String dbname=sc.next();
	System.out.println("Enter the Table name: ");
		String tbname=sc.next();
		
    
    
    
		try (Connection conn=DriverManager.getConnection(url+dbname,user,password);){
			Statement stmt =conn.createStatement();
			
			/*String query="Create table "+tbname+"( 
			 				Std_ENRID SERIAL PRIMARY KEY,"
							+"Std_ROLLNO Int,"
							+"Std_Name Char,"
							+"Std_Course Varchar(100),"
			 			+"Std_ContactNo int()" ;
			*/
			String query = "CREATE TABLE IF NOT EXISTS " + tbname + " ("
	                + "Std_ROLLNO INT PRIMARY KEY, "
	                + "Std_ENRID  VARCHAR(6), "
	                + "Std_Name VARCHAR(50), "
	                + "Std_Course VARCHAR(100), "
	                + "Std_ContactNo Varchar)";
			stmt.execute(query);
			
			//PreparedStatement pstmt=conn.prepareStatement(query);
			System.out.println("Table "+tbname+" created sucessfully ");
				
			
		}catch (SQLException e) {e.printStackTrace();}
	}
	
	public static void Insert() {
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
		System.out.println("Enter the database name: ");
		String dbname=sc.next();
	System.out.println("Enter the Table name: ");
		String tbname=sc.next();
		*/
	System.out.println();
	System.out.println("____________________Enter the vlaues into respective order____________________\n"
			+ "   ROLL NO : \n"
			+ "   ENROLLMENT ID : \n"
			+ "   Student Name : \n"
			+ "   Course :  \n"
			+ "   Contact No Varchar\n "
			+"-----------------------------------------------------------------------------------------------"			
			);
	int rollno=sc.nextInt();
	String ernid=sc.next();
	String name=sc.next();
	String course=sc.next();
	String contact=sc.next();
	
	

		try(Connection conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,"postgres","1234");){
			String query =" insert into "+tbname+" values(?,?,?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,rollno);
			pstmt.setString(2,ernid);                  // Std_ROLLNO
            pstmt.setString(3,name);   // Std_Name
            pstmt.setString(4,course);         // Std_Course
            pstmt.setString(5,contact); //_ContactNo (Fixed Data Type)
            int i=pstmt.executeUpdate();	
            
            
            System.out.println("values Inserted ");
			}catch (SQLException e) {e.printStackTrace();}
	}
	
	public static void Delete() {
		//System.out.println("Enter the database name: ");
		//String dbname=sc.next();
		//System.out.println("Enter the Table name: ");
		//String tbname=sc.next();
		System.out.println("Enter the where id = ");
		String id=sc.next();
		
		
		
	
		try(Connection conn=DriverManager.getConnection(url+tbname,user,password);){
			String query =" DELETE FROM "+tbname+" WHERE Std_ROLLNO ="+ id;
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.executeUpdate();
			
			System.out.println("deletedd");
			
			
		}catch (SQLException e) {e.printStackTrace();}
	}
	
	public static void Update () {
		System.out.println("Enter the database name: ");
		String dbname=sc.next();
	System.out.println("Enter the Table name: ");
		String tbname=sc.next();
		
		System.out.println("____________________Enter the vlaues into respective order____________________"
				+ "   Student Name : \n"
				+ "   Course :  \n"
				+ "   Contact No \n "
				+ "   ENROLLMENT ID : \n"
				+"-----------------------------------------------------------------------------------------------"			
				);
		
		String name=sc.next();
		String course=sc.next();
		String contact=sc.next();
		String ernid=sc.next();
		
		try(Connection conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,"postgres","1234");){
			
			String query = "UPDATE " + tbname + " SET Std_Name = ?, Std_Course = ?, Std_ContactNo = ? WHERE Std_ENRID = ?";
		    
		    PreparedStatement pstmt = conn.prepareStatement(query);
		    
		    // Setting new values
		    pstmt.setString(1,name);   // Updating Name
		    pstmt.setString(2, course);         // Updating Course
		    pstmt.setString(3, contact);    // Updating Contact Number
		    pstmt.setString(4, ernid);        // Condition: Matching Roll Number

		    int i = pstmt.executeUpdate();
		    if (i > 0) {
		        System.out.println("Record Updated Successfully");
		    } else {
		        System.out.println("No record found with the given Std_ROLLNO");
		    }
            
            
            
            System.out.println("values Inserted ");
			}catch (SQLException e) {e.printStackTrace();}
		
		
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

	            // Display Data
	            for (Map<String, String> student : studentRecords) {
	                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	                System.out.println();
	                System.out.println("Std_RollNo : " + student.get("Std_RollNo"));
	                System.out.println("Std_Name : " + student.get("Std_Name"));
	                System.out.println("Std_EnrId : " + student.get("Std_EnrId"));
	                System.out.println("Std_Course : " + student.get("Std_Course"));
	                System.out.println("Std_Contact : " + student.get("Std_Contact"));
	                System.out.println();
	                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    
	    }
	    
	

	
	
	
	public static void main(String[] args) {
		//CreateDb();
		//CreateTb();
		//Insert();
		//Delete();
	   //Update();
		
		
			Scanner sc = new Scanner(System.in);
		
	while (true) 
		{
			System.out.println("_____________________Student Data System _________________________");
			System.out.println();
			System.out.println("Select the option form the menu below : ");
			System.out.println("1. Insert Student Details");
			System.out.println("2. Update  Student Details");
			System.out.println("3. Delete Student Details");
			System.out.println("4. View Student Details");
			int choice=sc.nextInt();
			
				
			
		switch (choice) 
		{
			case 1:	Insert(); break;
			case 2 : Update(); break;
			case 3 : Delete(); break;
			case 4 : view(); break;
			case 5:
                System.out.println("Exiting... Thank you!");
                sc.close();
                return;  
            default:
                System.out.println("Invalid choice! Enter again.");
		  }
	   }
	}
}
	
		
	
