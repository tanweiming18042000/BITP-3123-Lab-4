package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MyDatabase {
	static Connection conn=null;
	public static Connection doConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/distributed_lab4_ex5","root","");
		return conn;
	}
	

	
	public static void main(String[] args) {
		try {
			System.out.println(MyDatabase.doConnection()+" Sucessful");
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		
	}

}
	
	