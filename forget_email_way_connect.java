import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class forget_email_way_connect
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
		String query="select * from signup1";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn=DriverManager.getConnection(url,uname,pass);
		
		Statement st=conn.createStatement();
		
		ResultSet rs=st.executeQuery(query); //DQL->data query language(used to fire query)
		
		String password=null;
		int flag=0;
		while(rs.next())
		{
			if(username.equals(rs.getString(1)) && email.equals(rs.getString(3)))
			{
				flag=1;
				password=rs.getString(2); 
				
				
			    Authenticator auth = new SMTPAuthenticator();
			    Session session = Session.getInstance(props, auth);
			    session.setDebug(true);
			    MimeMessage msg = new MimeMessage(session);
			    msg.setText("Your password is " + password);
			    msg.setSubject("Password for your account");
			    msg.setFrom(new InternetAddress("abhiksingh1999@gmail.com"));
			    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			    msg.saveChanges();
			    Transport.send(msg);
	
                System.out.println("Your password mailed to you");
			}
		}
		
		st.close();
		conn.close();
		
		if(flag==1)
			System.out.println("Found Password");
		else
			System.out.println("Not found");
		
		return password;
    } 
}
class SMTPAuthenticator extends javax.mail.Authenticator
{
    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication("abhiksingh1999@gmail.com","abhishek1999");
    }
}


