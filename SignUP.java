import java.awt.Color;
import java.awt.Container;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

public class SignUP implements ActionListener
{
	JFrame f;
	JButton b1;
	JButton b2;
	JLabel un;
	JLabel pw;
	JLabel em;
	JTextField t1;
	JTextField t2;
	JTextField t3;
	Container c;
	
	SignUP()
	{
		f=new JFrame("SignUP");
		
		b1=new JButton("Register");
		b2=new JButton("Login");
		
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
		c.setBackground(Color.PINK);
		
		
		un.setBounds(50,14,100,200);
		pw.setBounds(50,113,100,200);
		em.setBounds(50,212,100,200);
		
		t1.setBounds(150,100,300,30);
		t2.setBounds(150,199,300,30);
		t3.setBounds(150,298,300,30);
		
		b1.setBounds(100,340,100,30);
		b2.setBounds(320,340,100,30);
		
		f.add(un);
		f.add(pw);
		f.add(em);
		f.add(b1);
		f.add(b2);
		f.add(t1);
		f.add(t2);
		f.add(t3);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String username=t1.getText();
		String password=t2.getText();
		String email=t3.getText();
		
		signup_connect obj=new signup_connect();
		
		if(username.length()!=0 && password.length()!=0 && email.length()!=0)
		{
			if(ae.getSource()==b1)
			{
					try 
					{
						obj.connection(username,password,email);
					} catch (ClassNotFoundException e) 
					{
						e.printStackTrace();
					} catch (SQLException e) 
					{
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(f,"Successfully Registered",
							"", JOptionPane.PLAIN_MESSAGE);
					t1.setText("");
					t2.setText("");
					t3.setText("");
			}
		}
		else if(username.length()==0 || password.length()==0 || email.length()==0)
		{
			if(ae.getSource()==b1)
			{
				JOptionPane.showMessageDialog(f,"Enter UserName or Password or Email",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(ae.getSource()==b2)
			{
				new Login();
				f.setVisible(false);
			}
		}	
	}
}
