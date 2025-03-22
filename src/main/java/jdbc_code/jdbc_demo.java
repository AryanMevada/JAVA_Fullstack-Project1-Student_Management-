package jdbc_code;

import java.sql.Statement;



import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class jdbc_demo {
	 static String url = "jdbc:postgresql://localhost:5432/"; // Connect to the default 'postgres' database
     static String user = "postgres";
     static String password = "1234";
	
	private static void fetchtbl (String dbname,String  tbname) {
		
		try(Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,"postgres","1234");){
			Statement stmt= con.createStatement();
			String query ="Select * from "+tbname;
			ResultSet res=stmt.executeQuery(query);
			
			while(res.next()) {
				System.out.println("Emp Id : "+ res.getInt("id"));
				System.out.println("Emp Name : "+ res.getString("name"));
				System.out.println("Emp Age : "+ res.getInt("age"));
				System.out.println("Emp Salary : "+ res.getString("salary"));
				//con.close();
				//stm.colse();
				
				}
			
		}catch (SQLException e) {e.printStackTrace();}
	}
	public static void deletTbl(String dbname,String tbname) {
		try(Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,"postgres","1234");){
			String query =" DELETE FROM "+tbname+" WHERE id=3";
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.executeUpdate();
			
			System.out.println("deletedd");
			
			
		}catch (SQLException e) {e.printStackTrace();}
	}
	
	private static void insertdata(String dbname,String tbname) {
		
		try(Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,"postgres","1234");){
			String query =" insert into "+tbname+" values(?,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setInt(1, 6);
			pstmt.setString(2, "bhide");
			pstmt.setInt(3,35);
			pstmt.setString(4, "40000");
			int i=pstmt.executeUpdate();
			
			}catch (SQLException e) {e.printStackTrace();}
	}
	
		
		
	 public static void main(String[] args) {
		//fetchtbl("mydatabase","Employee");
		 //deletTbl("mydatabase","Employee");
		 insertdata("mydatabase","Employee");
		 int choices=1;
		 
		 switch (choices) {
		case 1:insertdata("mydatabase","Employee");
		
		
			
			break;

		default:
			break;
		}
	}
}
/////

