import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fazecast.jSerialComm.SerialPort;

public class arduinoLDR
{
	static SerialPort s1;
	
	public static void main(String[] args) throws IOException,InterruptedException, SQLException, ClassNotFoundException 
	{
		SerialPort[] s=SerialPort.getCommPorts();
		for(SerialPort port:s)
		{
			System.out.println(""+port.getSystemPortName());;
			s1=SerialPort.getCommPort(port.getSystemPortName());
			if(s1.openPort())
			{
				System.out.println("Successfully port opened");
			}
			else
			{
				System.out.println("Failed to open port");
			}
		}
		
		s1.setBaudRate(9600);
		InputStream is=s1.getInputStream();
		//
		OutputStream os=s1.getOutputStream();
		//
		Thread.sleep(1000);
		//
		os.write(1);
		//
		
		String url="jdbc:mysql://localhost:3307/student";
		String uname="root";
		String pass="abhishek1999";
		
		String query="select * from arduino";
		String query1="insert into arduino values (?,?)";
		
		String x=null;
		
		for(;true;)
		{
			int flag=0;
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection(url,uname,pass);
			Connection conn1=DriverManager.getConnection(url,uname,pass);
			
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			
			x=(char)is.read()+"";
			System.out.print(x);
			
			while(rs.next())
			{	
				if(x.equals(rs.getString(2)))
				{
					System.out.println("Booked");
					flag=1;
				}
			}
			if(flag==0 && x.equals("4"))
			{
				PreparedStatement ps=conn1.prepareStatement(query1);
				ps.setString(1,"slot1");
				ps.setString(2,x);
				
				int count=ps.executeUpdate();
				System.out.println(count+" row/s affected");
			}
			
			
			System.out.println();
			Thread.sleep(500);
		}
	}
}
