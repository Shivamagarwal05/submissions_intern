import java.sql.*;
class Database
{	
	
	public static void main(String[] args) throws ClassNotFoundException
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","inc0rrect#");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Employees");
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
  
			//step5 close the connection object  
			con.close();  
		
		 }catch(Exception e){ System.out.println(e);}  
	}
  
	 
}  