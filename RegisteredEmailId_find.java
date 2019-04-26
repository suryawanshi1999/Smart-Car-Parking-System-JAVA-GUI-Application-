import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisteredEmailId_find
{
	public String[] connection()throws ClassNotFoundException, SQLException 
	{
		String url="jdbc:mysql://localhost:3307/student";
		String uname="root";
		String pass="abhishek1999";
		String query="select * from signup1";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn=DriverManager.getConnection(url,uname,pass);
		
		Statement st=conn.createStatement();
		
		ResultSet rs=st.executeQuery(query); //DQL->data query language(used to fire query)
		
		String email[]=new String[100];
		int i=0;
		while(rs.next())
		{
			email[i]=rs.getString(3);
			i++;
		}
		st.close();
		conn.close();
		
		return email;
	}
}
