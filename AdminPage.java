import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class AdminPage implements ActionListener
{
	JFrame f;
	Container c;
	
	JLabel name;
	JLabel otp;
	JLabel email;
	JLabel title;
	
	JTextArea tname;
	JTextArea totp;
	JTextArea temail;
	
	JButton back;
	JButton confirm;
	JButton send;
	String otp_from_email=null;
	String username_=null;
	String email_=null;
	
	AdminPage() throws ClassNotFoundException, SQLException, MessagingException
	{
		f=new JFrame("Admin Verfication Page");
		
		title=new JLabel("Admin Verfication Page");
		name=new JLabel("NAME");
		otp=new JLabel("OTP");
		email=new JLabel("EMAIL");
		
		tname=new JTextArea();
		totp=new JTextArea();
		temail=new JTextArea();
		
		confirm=new JButton("Confirm");
		send=new JButton("Send");
		back=new JButton("<< Back");
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(400,180,500,500);
		f.setResizable(false);
		f.setVisible(true);
		f.setLayout(null);
		c=f.getContentPane();
		c.setBackground(Color.GREEN);
		
		title.setBounds(200,-70,300,200);
		
		name.setBounds(50,10,300,200);
		email.setBounds(50,110,300,200);
		otp.setBounds(50,200,300,200);
		
		tname.setBounds(200,100,200,20);
		temail.setBounds(200,200,200,20);
		totp.setBounds(200,300,200,20);
		
		back.setBounds(0,0,100,30);
		confirm.setBounds(210,400,100,50);
		send.setBounds(400,197,70,30);
		
		f.add(name);
		f.add(otp);
		f.add(email);
		f.add(tname);
		f.add(totp);
		f.add(temail);
		f.add(confirm);
		f.add(send);
		f.add(back);
		f.add(title);
		
		confirm.addActionListener(this);
		send.addActionListener(this);
		back.addActionListener(this);
	}
	public void msg_from_email() throws ClassNotFoundException, SQLException, MessagingException
	{
		otp_from_email=new Admin_connect_otp().connection(tname.getText(),temail.getText());
	}
	public void actionPerformed(ActionEvent e) 
	{
		username_=tname.getText();
		email_=temail.getText();
		
		if(e.getSource()==back)
		{
			new Login();
			f.setVisible(false);
		}
		if(username_.length()!=0 && email_.length()!=0)
		{
			if(e.getSource()==send)
			{
				try 
				{
					msg_from_email();
					JOptionPane.showMessageDialog(f,"OTP is send to your registered email id [If u r admin]",
							"", JOptionPane.PLAIN_MESSAGE);
				}
				catch (ClassNotFoundException | SQLException | MessagingException e1) 
				{
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource()==confirm)
		{
			if(otp_from_email!=null)
			{
				if(totp.getText().equals(otp_from_email))
				{
					System.out.println("You r admin");
					JOptionPane.showMessageDialog(f,"You r admin",
							"", JOptionPane.PLAIN_MESSAGE);
					try 
					{
						new AdminPage_showingAllDetails(email_);
					} 
					catch (ClassNotFoundException | SQLException | MessagingException e1) 
					{
						e1.printStackTrace();
					}
					f.setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(f,"Warning",
							"Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	/*public static void main(String[] args) throws ClassNotFoundException, SQLException, MessagingException 
	{
		new AdminPage();
	}*/
}
