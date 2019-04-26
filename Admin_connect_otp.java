import java.sql.Connection;
import java.sql.DriverManager;
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

public class Admin_connect_otp
{
	public String connection(String username,String email)throws ClassNotFoundException, SQLException, MessagingException 
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
		String query="select * from admin";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn=DriverManager.getConnection(url,uname,pass);
		
		Statement st=conn.createStatement();
		
		ResultSet rs=st.executeQuery(query); //DQL->data query language(used to fire query)
		
		//generating random no. for otp
		
		int random =(int) (Math.random()* 999999);
		String otp_password=Integer.toString(random);
		
		int flag=0;
		while(rs.next())
		{
			if(username.equals(rs.getString(1)) && email.equals(rs.getString(2)))
			{
				flag=1;
				
			    Authenticator auth = new SMTPAuthenticator3();
			    Session session = Session.getInstance(props, auth);
			    session.setDebug(true);
			    MimeMessage msg = new MimeMessage(session);
			    msg.setText("Your OTP password is " + otp_password+" please Don't Share");
			    msg.setSubject("OTP Password for Admin");
			    msg.setFrom(new InternetAddress("abhiksingh1999@gmail.com"));
			    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			    msg.saveChanges();
			    Transport.send(msg);
			       
                System.out.println("Your OTP password mailed to you");
			}
		}
		
		st.close();
		conn.close();
		
		if(flag==1)
			System.out.println("Username and email Found ");
		else
			System.out.println("Username and email Not found");
		
		return otp_password;
    } 
	/*public static void main() throws ClassNotFoundException, SQLException, MessagingException
	{
		String otp=new Admin_connect_otp().connection("admin","abhiksingh1999@gmail.com");
	}*/
}
class SMTPAuthenticator3 extends javax.mail.Authenticator
{
    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication("abhiksingh1999@gmail.com","abhishek1999");
    }
}


