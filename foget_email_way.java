import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class foget_email_way implements ActionListener
{
	JFrame f;
	JButton b1; //showpassword
	JButton b2;	//Login
	JButton b3; //Sign Up
	JLabel un;	//Username
	JLabel pw;  //Password
	JLabel em;  //email
	JTextField t1; //userfield
	JTextField t2; //passfield
	JTextField t3; //emailfield
	Container c;
	
	foget_email_way()
	{
		f=new JFrame("Foget Password");
		
		b1=new JButton("Show Password");
		b2=new JButton("Login");
		b3=new JButton("Sign Up");
		
		un=new JLabel("UserName");
		pw=new JLabel("Password");
		em=new JLabel("Email");
		
		t1=new JTextField();
		t2=new JTextField();
		t3=new JTextField();
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(400,180,500,500);
		f.setResizable(false);
		f.setVisible(true);
		f.setLayout(null);
		c=f.getContentPane();
		c.setBackground(Color.GREEN);
		
		un.setBounds(50,-40,100,200);
		em.setBounds(50,45,100,200);
		
		pw.setBounds(50,150,100,200);
		
		t1.setBounds(150,50,300,30);//name
		t3.setBounds(150,135,300,30); //email
		t2.setBounds(150,234,300,30);//pass
		
		b1.setBounds(200,190,150,30);
		b2.setBounds(100,300,100,30);
		b3.setBounds(320,300,100,30);
		
		f.add(un);
		f.add(pw);
		f.add(em);
		
		f.add(b1);
		f.add(b2);
		f.add(b3);
		
		f.add(t1);
		f.add(t2);
		f.add(t3);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		String username=t1.getText();
		String email=t3.getText();
		
		String password=null;
		
		forget_email_way_connect obj=new forget_email_way_connect();
		
		if(e.getSource()==b1)
		{
			
			try 
			{
				password=obj.connection(username,email);
			} 
			catch (ClassNotFoundException e1)
			{
				e1.printStackTrace();
			}
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			} catch (MessagingException e1)
			{
				e1.printStackTrace();
			}
			t2.setText(password);
			
		}
		else if(e.getSource()==b2)
		{
			new Login();
			f.setVisible(false);
		}
		else if(e.getSource()==b3)
		{
			new SignUP();
			f.setVisible(false);
		}
	}
	public static void main(String[]args)
	{
		new foget_email_way();
	}
}
