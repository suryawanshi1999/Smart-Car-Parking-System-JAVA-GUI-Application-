import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Remove_Registered_email
{
	public Remove_Registered_email(String email)throws ClassNotFoundException, SQLException 
	{
		String url="jdbc:mysql://localhost:3307/student";
		String uname="root";
		String pass="abhishek1999";
		
		String query1="delete from signup1 where email=(?)";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn1=DriverManager.getConnection(url,uname,pass);
		
		PreparedStatement ps=conn1.prepareStatement(query1);
		
		ps.setString(1,email);
		
		int count=ps.executeUpdate();
		
		System.out.println(count+" row/s affected");
		conn1.close();
	}
}
