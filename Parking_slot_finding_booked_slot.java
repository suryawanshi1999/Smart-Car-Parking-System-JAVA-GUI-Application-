import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Parking_slot_finding_booked_slot
{
	public String[] connection()throws ClassNotFoundException, SQLException 
	{
		String url="jdbc:mysql://localhost:3307/student";
		String uname="root";
		String pass="abhishek1999";
		//String query="select * from parkingslot1";
		String query="select * from parkingslot2";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn=DriverManager.getConnection(url,uname,pass);
		
		Statement st=conn.createStatement();
		
		ResultSet rs=st.executeQuery(query); //DQL->data query language(used to fire query)
		
		String slot[]=new String[6];
		int i=0;
		while(rs.next())
		{
			slot[i]=rs.getString(4);
			//System.out.println(slot[i]+" Booked");
			i++;
		}
		
		st.close();
		conn.close();
		
		
		return slot;
	}
	public static void main(String[]args) throws ClassNotFoundException, SQLException
	{
		new Parking_slot_finding_booked_slot().connection();
	}
}
