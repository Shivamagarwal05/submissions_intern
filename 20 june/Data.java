import java.sql.*;
class Data
{
	public static void main(String[] args)	throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","inc0rrect#");
		PreparedStatement stmt = conn.prepareStatement("select * from shool ");
		//stmt.setInt(1,207);
		//stmt.setString(2,"LBVM");
		//stmt.executeUpdate();
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+" "+rs.getString(2));
		}
		conn.close();
	}
}

