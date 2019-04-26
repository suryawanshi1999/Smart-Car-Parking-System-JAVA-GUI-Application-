import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Remove_Slot_connect
{
	public Remove_Slot_connect(String slot)throws ClassNotFoundException, SQLException 
	{
		String url="jdbc:mysql://localhost:3307/student";
		String uname="root";
		String pass="abhishek1999";
		
		//String query="select * from parkingslot1";
		//String query1="delete from parkingslot1 where email=(?)";
		
		String query="select * from parkingslot2";
		String query1="delete from parkingslot2 where email=(?)";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn=DriverManager.getConnection(url,uname,pass);
		Connection conn1=DriverManager.getConnection(url,uname,pass);
		
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(query); 
		
		String email=null;
		int flag=0;
		while(rs.next())
		{
			if(slot.equals(rs.getString(4)))
			{
				email=rs.getString(3);
				System.out.println("Email Found");
				flag=1;
			}
		}
		
		PreparedStatement ps=conn1.prepareStatement(query1);
		
		ps.setString(1,email);
		
		
		int count=ps.executeUpdate();
		
		System.out.println(count+" row/s affected");
		st.close();
		conn.close();
		conn1.close();
	}
}
