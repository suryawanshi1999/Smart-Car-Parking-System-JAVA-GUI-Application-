import java.awt.Color;
import java.awt.Container;
import java.awt.event.*;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.swing.*;

public class Login extends login_connect implements ActionListener 
{
	JFrame f;
	
	JButton b1; //Login
	JButton b2;	//Sign Up
	JButton b3; //forgotpassword
	JButton admin;
	
	JLabel un;	//Username
	JLabel pw;  //Password
	
	JTextField t1; //userfield
	JPasswordField t2; //passfield
	
	Container c;
	
	Login()
	{
		f=new JFrame("Login");
		
		b1=new JButton("Login");
		b2=new JButton("Sign Up");
		b3=new JButton("Forgot Password");
		admin=new JButton("Admin");
		
		un=new JLabel("UserName");
		pw=new JLabel("Password");
		
		t1=new JTextField();
		t2=new JPasswordField();
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(400,180,500,500);
		f.setResizable(false);
		f.setVisible(true);
		f.setLayout(null);
		c=f.getContentPane();
		c.setBackground(Color.yellow);
		
		un.setBounds(50,14,100,200);
		pw.setBounds(50,113,100,200);
		
		t1.setBounds(150,100,300,30);
		t2.setBounds(150,199,300,30);
		
		b1.setBounds(100,340,100,30);
		b1.setBackground(Color.GREEN);
		
		b2.setBounds(320,340,100,30);
		b2.setBackground(Color.MAGENTA);
		
		b3.setBounds(190,380,150,30);
		b3.setBackground(Color.RED);
		
		admin.setBounds(350,0,150,30);
		
		f.add(un);
		f.add(pw);

		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(admin);
		
		f.add(t1);
		f.add(t2);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		admin.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{	
		String username=t1.getText();
		String password=t2.getText();
		
		login_connect obj=new login_connect();
		
		//Admin page connection
		
		if(e.getSource()==admin)
		{
			try
			{
				new AdminPage();
				f.setVisible(false);
			} 
			catch (ClassNotFoundException | SQLException | MessagingException e1) 
			{
				e1.printStackTrace();
			}
		}
		if(username.length()!=0 && password.length()!=0)
		{
			if(e.getSource()==b1)
			{
				int success = 0;
				
				try 
				{
					success=obj.connection(username,password);
				} 
				catch (ClassNotFoundException e1)
				{
					e1.printStackTrace();
				}
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
				
				if(success==1)
				{
					JOptionPane.showMessageDialog(f,"Successfully Login",
							"Success", JOptionPane.PLAIN_MESSAGE);
					f.setVisible(false);
					try 
					{
						new Parking_Slot(username,password);
					}
					catch (ClassNotFoundException | SQLException e1)
					{
						e1.printStackTrace();
					}	
				}
				else
				{
					JOptionPane.showMessageDialog(f,"UserName or Password is/are incorrect",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				t1.setText("");
				t2.setText("");
				
			}
		}
		else if(username.length()==0 || password.length()==0)
		{
			if(e.getSource()==b1)
			{
				JOptionPane.showMessageDialog(f,"Enter UserName or Password",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(e.getSource()==b2)
			{
				new SignUP();
				f.setVisible(false);
			}
			else if(e.getSource()==b3)
			{
				new forget();
				f.setVisible(false);
			}
		}
		
	}
	public static void main(String[]args)throws Exception
	{
		new ReminderNotification().start();
		new Login();	
	}
}
