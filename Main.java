import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class Main
{
   /* String  d_email = "abc@gmail.com",
            d_password = "pass",
            d_host = "smtp.gmail.com",
            d_port  = "465",
            m_to = "abc@gmail.com",
            m_subject = "Testing",
            m_text = "testing email.";*/

    public Main()
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

        try
        {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            session.setDebug(true);
            MimeMessage msg = new MimeMessage(session);
            msg.setText("testing email.");
            msg.setSubject("Testing");
            msg.setFrom(new InternetAddress("abhiksingh1999@gmail.com"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress("bharatsingh1may@gmail.com"));
            Transport.send(msg);
        }
        catch (Exception mex)
        {
            mex.printStackTrace();
        } 
    }

    public static void main(String[] args)
    {
        Main blah = new Main();
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication("abhiksingh1999@gmail.com","abhishek1999");
        }
    }
}