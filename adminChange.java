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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class adminChange implements ActionListener
{
	JFrame f1;
	Container c1;
	
	JLabel name;
	JLabel pass;
	JLabel email;
	
	JButton change;
	
	JTextField n;
	JPasswordField p;
	JTextField e;
	
	String emailComingFromAdminPage=null;
	adminChange(String emailComingFromAdminPage)
	{
		this.emailComingFromAdminPage=emailComingFromAdminPage;
		
		f1=new JFrame("Admin Change");
		name=new JLabel("Name");
		pass=new JLabel("Password");
		email=new JLabel("Email");
		
		change=new JButton("Change");
		
		n=new JTextField();
		p=new JPasswordField();
		e=new JTextField();
		
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setBounds(400,180,500,300);
		f1.setResizable(false);
		f1.setVisible(true);
		f1.setLayout(null);
		c1=f1.getContentPane();
		c1.setBackground(Color.PINK);
		
		name.setBounds(40,50,200,50);
		pass.setBounds(40,100,200,50);
		email.setBounds(40,150,200,50);
		
		n.setBounds(150,50,200,30);
		p.setBounds(150,100,200,30);
		e.setBounds(150,150,200,30);
		
		change.setBounds(40,200,100,30);
		
		f1.add(n);
		f1.add(e);
		f1.add(p);
		f1.add(name);
		f1.add(email);
		f1.add(pass);
		f1.add(change);
		
		change.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		String tname=n.getText();
		String tpass=p.getText();
		String temail=e.getText();
		
		if(ae.getSource()==change)
		{
			try
			{
				int flag=new adminChange_connect().updateAdmin(tname,tpass,temail);
				if(flag!=0)
				{
					JOptionPane.showMessageDialog(f1,"Updated",
							"", JOptionPane.PLAIN_MESSAGE);
					f1.setVisible(false);
					new Login();
				}
				else
				{
					JOptionPane.showMessageDialog(f1,"Not Updated",
							"Error", JOptionPane.ERROR_MESSAGE);
					f1.setVisible(false);
					try 
					{
						new AdminPage_showingAllDetails(emailComingFromAdminPage);
					}
					catch (ClassNotFoundException | SQLException | MessagingException e1) 
					{
						e1.printStackTrace();
					} 
				}
			} 
			catch (ClassNotFoundException | SQLException e1)
			{
				e1.printStackTrace();
			}
		}
		
	}
	/*public static void main(String[]args)
	{
		new adminChange("abhiksingh1999@gmail.com");
	}*/
}
