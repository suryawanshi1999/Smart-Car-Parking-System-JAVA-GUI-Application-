import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class login_connect 
{
	public int connection(String username,String password)throws ClassNotFoundException, SQLException 
	{
		String url="jdbc:mysql://localhost:3307/student";
		String uname="root";
		String pass="abhishek1999";
		String query="select * from signup1";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn=DriverManager.getConnection(url,uname,pass);
		
		Statement st=conn.createStatement();
		
		ResultSet rs=st.executeQuery(query); //DQL->data query language(used to fire query)
		
		int flag=0;
		while(rs.next())
		{
			if(username.equals(rs.getString(1)) && password.equals(rs.getString(2)))
			{
				System.out.println("Successfully Login");
				flag=1;
			}
		}
		
		st.close();
		conn.close();
		
		return flag;
	}
}
