import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class adminChange_connect
{
	public String adminChange_connect(String password,String email_)throws ClassNotFoundException, SQLException 
	{
		String url="jdbc:mysql://localhost:3307/student";
		String uname="root";
		String pass="abhishek1999";
		
		String query="select * from admin";
		String query1="delete from admin where email=(?)";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn=DriverManager.getConnection(url,uname,pass);
		Connection conn1=DriverManager.getConnection(url,uname,pass);
		
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(query); 
		
		String pass1=null;
		int flag=0;
		while(rs.next())
		{
			if(password.equals(rs.getString(3)) && email_.equals(rs.getString(2)))
			{
				pass1=rs.getString(3);
				System.out.println("Email and Password Match");
				flag=1;
			}
		}
		
		PreparedStatement ps=conn1.prepareStatement(query1);
		
		ps.setString(1,email_);
		
		
		int count=ps.executeUpdate();
		
		System.out.println(count+" row/s affected");
		
		st.close();
		conn.close();
		conn1.close();
		
		return pass1;
	}
	public int updateAdmin(String name,String password,String email) throws ClassNotFoundException, SQLException
	{
		String url="jdbc:mysql://localhost:3307/student";
		String uname="root";
		String pass="abhishek1999";
		
		String query1="INSERT INTO admin (name,email,password) VALUES (?,?,?)";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn1=DriverManager.getConnection(url,uname,pass);
		
		
		PreparedStatement ps=conn1.prepareStatement(query1);
		
		ps.setString(1,name);
		ps.setString(2,email);
		ps.setString(3,password);
		
		int count=ps.executeUpdate();
		
		System.out.println(count+" row/s affected");
		
		conn1.close();
		
		return count;
	}
}
