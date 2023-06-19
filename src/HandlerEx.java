import java.sql.Connection;
		import java.sql.DriverManager;
		import java.sql.Statement;
		import java.sql.*;
public class HandlerEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Connection con;
			Statement st;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/event_management","root","pavi");
				System.out.println("Connected successfully.");
				st=con.createStatement();
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			
			}
	}

