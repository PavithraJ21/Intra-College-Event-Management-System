
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
class DBHandler {
	Connection con;
	Statement st;
	public DBHandler() {
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
	 int insertEvent(String name,String date,String sTime,String eTime,String club,String venue) {
		int x=0;
		 try {
		
		String query="insert into event3 values('"+name+"','"+date+"','"+sTime+"','"+eTime+"','"+club+"','"+venue+"')";
		x=st.executeUpdate(query);}
		 catch(Exception ex) {
			 System.out.println(ex);}
		return x;
	 }
	 int verifyPassword(String id,String pwd) {
		  int x=0;
		 try {
			 String query="select Password from Student where Student_Id='"+id+"'";
			 ResultSet rs=st.executeQuery(query);
			 while(rs.next()) {
				 String pass=String.valueOf(rs);
				 System.out.println(pass);
			 if(rs.getString(1).equals(pwd)) 
			 {
				 System.out.println("verified");
				 x=1;
			 }
			 else {System.out.println("Unverified");}}
		 }
		 catch(Exception ex) {
			 System.out.println(ex);
		 }
		 return x;
	 }
	 int deleteEvent(String name) {
	 int x=0;
	 try {
	 String query="delete from event3 where E_Name='"+name+"'";
	 x=st.executeUpdate(query);
	 }
	 catch(Exception ex) {
		 System.out.println(ex);
		 }
	 return x;
	 }
	 int insertParticipation(String id,String event) {
	 int x=0;
	 try {
	
	String query="insert into Participates values('"+id+"','"+event+"')";
	x=st.executeUpdate(query);}
	 catch(Exception ex) {
		 System.out.println(ex);}
	return x;
 }
	 int insertStudent(String id,String name,String dept,int year,String section,String pwd) {
			int x=0;
			 try {
			
			String query="insert into Student values('"+id+"','"+name+"','"+dept+"',"+year+",'"+section+"','"+pwd+"')";
			x=st.executeUpdate(query);}
			 catch(Exception ex) {
				 System.out.println(ex);}
			return x;
		 }
	public String[][] displayEvent(){
		try {
			String query="select * from event3";
			ResultSet rs=st.executeQuery(query);
		    String[][] events=new String[10][6];
		    int i=0;
			while(rs.next()) {
				System.out.println(rs.getString(1));
				events[i][0]=rs.getString(1);
				events[i][1]=rs.getString(2);
				events[i][2]=rs.getString(3);
				events[i][3]=rs.getString(4);
				events[i][4]=rs.getString(5);
				events[i][5]=rs.getString(6);
				i=i+1;
			}
			rs.close();
			return events;
		}
		catch(Exception ex) {
			System.out.println(ex);
			}
		return null;
	}
	public String[][] viewEvent(){
		try {
			String query="select * from participates";
			ResultSet rs=st.executeQuery(query);
		    String[][] events=new String[10][2];
		    int i=0;
			while(rs.next()) {
				System.out.println(rs.getString(1));
				events[i][0]=rs.getString(1);
				events[i][1]=rs.getString(2);
				
				i=i+1;
			}
			rs.close();
			return events;
		}
		catch(Exception ex) {
			System.out.println(ex);
			}
		return null;
	}
}