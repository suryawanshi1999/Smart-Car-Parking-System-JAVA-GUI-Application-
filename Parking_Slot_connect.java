import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Parking_Slot_connect
{
	//username,password,
	//slotno,currentDate,currentTime,choosegetdate,choosegettime
	public String connection(String username,String password,String slotno,
		  String currentDate,String currentTime,String choosegetdate,
		  String choosegettime)
			throws ClassNotFoundException, SQLException, MessagingException 
	{
		Properties props = new Properties();
        props.put("mail.smtp.user", "abhiksingh1999@gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        
        SecurityManager security = System.getSecurityManager();
	    
		String url="jdbc:mysql://localhost:3307/student";
		String uname="root";
		String pass="abhishek1999";
		String query="select * from signup1";	//finding email wala query
		
		//String query1="insert into parkingslot1 values (?,?,?,?,?,?,?,?)";
		String query1="insert into parkingslot2 values (?,?,?,?,?,?,?,?,?)";
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn=DriverManager.getConnection(url,uname,pass);
		Connection conn1=DriverManager.getConnection(url,uname,pass);
		
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(query); //DQL->data query language(used to fire query)
		
		String email=null;
		int flag=0;
		
		while(rs.next())
		{
			if(username.equals(rs.getString(1)) && password.equals(rs.getString(2)))
			{
				flag=1;
				email=rs.getString(3); 
				break;
			}
		}
		
		PreparedStatement ps=conn1.prepareStatement(query1);
		ps.setString(1,username);
		ps.setString(2,password);
		ps.setString(3,email);
		ps.setString(4,slotno);
		ps.setString(5,currentDate);
		ps.setString(6,currentTime);
		ps.setString(7,choosegetdate);
		ps.setString(8,choosegettime);
		ps.setInt(9,0);
		
		int count=ps.executeUpdate();
		System.out.println(count+" row/s affected");
		
		st.close();
		conn1.close();
		conn.close();
		
		if(flag==1)
			System.out.println("Found Email");
		else
			System.out.println("Not found");
		
		Authenticator auth = new SMTPAuthenticator2();
	    Session session = Session.getInstance(props, auth);
	    session.setDebug(true);
	    MimeMessage msg = new MimeMessage(session);
	    msg.setText("Thank You for choosing 'My Slot Booked Software'\n "
	    		+username+" you have booked Slot No:- "+slotno+" for \n"
	    				+ "Time Duration : "+choosegettime+"\n"
	    						+ "Date Duration : "+choosegetdate);
	    msg.setSubject("Slot Booked");
	    msg.setFrom(new InternetAddress("abhiksingh1999@gmail.com"));
	    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
	    msg.saveChanges();
	    Transport.send(msg);
	  
        System.out.println("Slot Booked Info Mailed to you");
                
		return password;
    } 
}
class SMTPAuthenticator2 extends javax.mail.Authenticator
{
    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication("abhiksingh1999@gmail.com","abhishek1999");
    }
}


