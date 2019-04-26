import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class signup_connect
{
	public void connection(String getname,String getpassword,String getemail)throws ClassNotFoundException, SQLException 
	{
		String url="jdbc:mysql://localhost:3307/student";
		String uname="root";
		String pass="abhishek1999";
		
		String query1="insert into signup1 values (?,?,?)";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn=DriverManager.getConnection(url,uname,pass);
		
		PreparedStatement ps=conn.prepareStatement(query1);
		
		ps.setString(1,getname);
		ps.setString(2,getpassword);
		ps.setString(3,getemail);
		
		int count=ps.executeUpdate();
		
		System.out.println(count+" row/s affected");
		conn.close();
	}
}
