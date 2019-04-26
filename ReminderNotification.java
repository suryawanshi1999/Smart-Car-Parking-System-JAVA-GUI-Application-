import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


class SMTPAuthenticator4 extends javax.mail.Authenticator
{
    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication("abhiksingh1999@gmail.com","abhishek1999");
    }
}
class MyThread
{
	public MyThread() throws ClassNotFoundException, SQLException, MessagingException, InterruptedException
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
		String query="select * from parkingslot2";
		//String query1="insert into parkingslot2 values (?)";
		String query1="UPDATE parkingslot2 SET RemainderMailSent = 1 "
				+ "WHERE email = (?)";
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn=DriverManager.getConnection(url,uname,pass);
		Connection conn1=DriverManager.getConnection(url,uname,pass);
		
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(query); 
		
		String currentDate []= new String[100];
		String currentTime []= new String[100];
		String selectedDate []= new String[100];
		String selectedTime []= new String[100];
		
		String username []= new String[100];
		String slotname []= new String[100];
		String email []= new String[100];
		
		int remainder []= new int[100];
		
		int i=0,count=0;
		
		while(rs.next())
		{
			username[i]=rs.getString(1);
			email[i]=rs.getString(3);
			slotname[i]=rs.getString(4);
			
			currentDate[i]=rs.getString(5);
			currentTime[i]=rs.getString(6);
			selectedDate[i]=rs.getString(7);
			selectedTime[i]=rs.getString(8);
			
			remainder[i]=rs.getInt(9);
			
			i++;
			count++;
		}

		
		
		for(int j=0;j<count;j++)
		{
		System.out.println("username : "+username[j]);
		System.out.println("email : "+email[j]);
		System.out.println("Slot : "+slotname[j]);
		
		System.out.println("currentDate : "+currentDate[j]);
		System.out.println("currentTime : "+currentTime[j]);
		System.out.println("selectedDate : "+selectedDate[j]);
		System.out.println("selectedTime : "+selectedTime[j]);
		
		System.out.println("RemainderEmailSent : "+remainder[j]);
		
		System.out.println("********************************");
		
		//************
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");
		
		Date curr = new Date();
		String nowDate = dateFormat.format(curr);
		String nowTime = timeFormat.format(curr);
		
		System.out.println("NowDate : "+nowDate+"\nNowTime : "+nowTime);
		//********************
		
		int Totalhr =Integer.parseInt((String)currentTime[j].subSequence(0,2))
											+
					 Integer.parseInt((String)selectedTime[j].subSequence(0,2));
		
		int Totalmin =Integer.parseInt((String)currentTime[j].subSequence(3,5))
											+
					 Integer.parseInt((String)selectedTime[j].subSequence(3,5));
		
		String calculatedTime=null;
		
		/*if(Totalmin==60)	//10:59 -> 11:00
		{
			Totalmin=00;
			Totalhr=Totalhr+1;
			calculatedTime=Totalhr+":"+Totalmin;
		}
		else*/
		//{
			calculatedTime=Totalhr+":"+Totalmin;
		//}
			
		System.out.println("Calculated Time : "+calculatedTime);
		
		PreparedStatement ps=conn1.prepareStatement(query1);
		
		if(selectedDate[j].equals(nowDate))
		{	
		if(calculatedTime.equals(nowTime))
		{
			System.out.println("Time Match");
			
			if(remainder[j]==0)
			{
			Authenticator auth = new SMTPAuthenticator2();
		    Session session = Session.getInstance(props, auth);
		    session.setDebug(true);
		    MimeMessage msg = new MimeMessage(session);
		    msg.setText("Time Limit for Parking slot for your vechile is 'OVER'\n "
		    		+username[j]+" you booked Slot No:- "+slotname[j]+" for \n"
		    				+ "Time Duration : "+selectedTime[j]+"\n"
		    					+"Booked Slot Time : "+currentTime[j]+ "Date Duration : "+selectedDate[j]
		    							+ "Booked Slot Date : "+currentDate[j]);
		    msg.setSubject("Slot Booked");
		    msg.setFrom(new InternetAddress("abhiksingh1999@gmail.com"));
		    msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email[j]));
		    msg.saveChanges();
		    Transport.send(msg);
		  
	        System.out.println("Remainder mailed sent to you");
	        
	        
	        
			ps.setString(1,email[j]);
			int count1=ps.executeUpdate();
			System.out.println(count1+" row/s affected");
	        
			}
	        
		}	
		else
		{

			if(calculatedTime.compareTo(nowTime)>1)
			{
				System.out.println("Extra Charges will be taken");
			}
		}
			
		System.out.println("####################################");
		}
		}
		
		st.close();
		conn.close();
		conn1.close();
		
    } 
}
class ReminderNotification extends Thread
{
	public void run()
	{
		try 
		{
			while(true)
			{
				Thread.sleep(10000);//10s
				new MyThread();
			}
			
		} 
		catch (ClassNotFoundException | SQLException | MessagingException e) 
		{
			e.printStackTrace();
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	/*public static void main(String[] args) throws ClassNotFoundException, SQLException, MessagingException
	{
		new ReminderNotification().start();
		
	}*/
}
