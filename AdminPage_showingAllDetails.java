import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminPage_showingAllDetails implements ActionListener
{
	JFrame f;
	Container c;
	
	JPasswordField admin;
	
	JLabel choice;
	JLabel label;
	JLabel changeadmin;
	        
    JComboBox cb;
    JComboBox cb1;
    
	JButton remove;
	JButton remove_register;
	JButton exit;
	JButton change;
	
	String slot=null;
	String slotinfo=null;
	String email=null;
	String blocked_slot[]=new String[6];
	String registered_email[]=new String[100];
	String emailComingFromAdminPage=null;
	
	public void SlotandEmail() throws ClassNotFoundException, SQLException
	{
		blocked_slot=new Parking_slot_finding_booked_slot().connection();
		cb=new JComboBox(blocked_slot);
		
		//===========
		registered_email=new RegisteredEmailId_find().connection();
		cb1=new JComboBox(registered_email);
	}
	public AdminPage_showingAllDetails(String email_) throws ClassNotFoundException, SQLException, MessagingException
	{
		this.emailComingFromAdminPage=email_;
		
		f=new JFrame("Update/Remove");
		
		choice=new JLabel("Select the Slot No :-");
		label=new JLabel("Select Registered Email-Id :-");
		
		//blocked_slot=new Parking_slot_finding_booked_slot().connection();
		//cb=new JComboBox(blocked_slot);
		SlotandEmail();
		//===========
		//registered_email=new RegisteredEmailId_find().connection();
		//cb1=new JComboBox(registered_email);
		
		remove=new JButton("Remove");
		remove_register=new JButton("Remove");
		exit=new JButton("Exit");
		change=new JButton("Change");
		changeadmin=new JLabel("Change Admin(Enter password) :");
		admin=new JPasswordField();
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(400,180,500,500);
		f.setResizable(false);
		f.setVisible(true);
		f.setLayout(null);
		c=f.getContentPane();
		c.setBackground(Color.GREEN);
		
		choice.setBounds(40,18,200,100);
		cb.setBounds(190,55,200,30);
		remove.setBounds(40,90,100,30);
		
		label.setBounds(40,115,200,100);
		cb1.setBounds(210,150,200,30);
		remove_register.setBounds(40,190,100,30);
		
		change.setBounds(50,400,80,30);
		exit.setBounds(410,400,80,30);
		
		changeadmin.setBounds(43,340,200,50);
		admin.setBounds(250,350,200,30);
		
		f.add(choice);
		f.add(cb);
		f.add(remove);
		
		f.add(label);
		f.add(cb1);
		f.add(remove_register);
		
		f.add(exit);
		f.add(change);

		f.add(changeadmin);
		f.add(admin);
		
		remove.addActionListener(this);
		remove_register.addActionListener(this);
		exit.addActionListener(this);
		change.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		slot=(String) cb.getSelectedItem();
		email=(String) cb1.getSelectedItem();
		
		String pass=null;
		
		if(admin.getText().length()!=0)
		{
			if(e.getSource()==change)
			{
				try
				{
					pass=new adminChange_connect().adminChange_connect(admin.getText(),emailComingFromAdminPage);
					if(pass.equals(admin.getText()))
					{
						JOptionPane.showMessageDialog(f,"Password Match",
								"", JOptionPane.PLAIN_MESSAGE);
						f.setVisible(false);
						new adminChange(emailComingFromAdminPage);
					}
					else
					{
						if(pass.equals(admin.getText()))
						{
							JOptionPane.showMessageDialog(f,"Incorrect Password",
									"", JOptionPane.WARNING_MESSAGE);
							f.setVisible(false);
							
							new AdminPage_showingAllDetails(emailComingFromAdminPage);
						}
					}
				} 
				catch (ClassNotFoundException | SQLException | MessagingException e1) 
				{
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource()==exit)
		{
			new Login();
			f.setVisible(false);
		}
		if(e.getSource()==remove)
		{
			//System.out.println(cb.getSelectedItem());
			try 
			{
				new Remove_Slot_connect(slot);
			} 
			catch (ClassNotFoundException | SQLException e1)
			{
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(f,slot+" Deleted",
					"", JOptionPane.PLAIN_MESSAGE);
			f.setVisible(false);
			try 
			{
				new AdminPage_showingAllDetails(emailComingFromAdminPage);
			}
			catch (ClassNotFoundException | SQLException | MessagingException e1) 
			{
				e1.printStackTrace();
			} 
		}
		if(e.getSource()==remove_register)
		{
			try
			{
				new Remove_Registered_email(email);
			} 
			catch (ClassNotFoundException | SQLException e1)
			{
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(f,email+" Deleted",
					"", JOptionPane.PLAIN_MESSAGE);
			f.setVisible(false);
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
	public static void main(String[] args) throws ClassNotFoundException, SQLException, MessagingException
	{
		new AdminPage_showingAllDetails("abhiksingh1999@gmail.com");
	}
}
