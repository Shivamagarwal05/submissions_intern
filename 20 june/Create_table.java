import java.sql.*;
class Create_table
{
	public static void main(String[] args) throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","inc0rrect#");
		PreparedStatement pstmt = null ;//conn.prepareStatement("alter table teacher modify schoolname varchar(20) not null");
		//pstmt.executeUpdate();
		//pstmt = conn.prepareStatement("CREATE SEQUENCE seq_person MINVALUE 3 START WITH 3 INCREMENT BY 1 CACHE 10");
		//pstmt.executeUpdate();
		pstmt = conn.prepareStatement("insert into teacher values(seq_person.nextval,?,?,?)");//sequence already created in previous executions.
		
		pstmt.setString(1,"rama");
		pstmt.setString(2,"java");
		pstmt.setString(3,"SRMS");
		pstmt.executeUpdate();
		
		conn.close();
	}
}