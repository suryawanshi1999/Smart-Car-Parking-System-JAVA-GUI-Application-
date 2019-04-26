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


public class Parking_Slot implements ActionListener
{
	JFrame f;
	
	JButton refresh;
	
	JButton b1;
	JButton b2;	
	JButton b3;
	JButton b4;
	JButton b5;
	JButton b6;
	
	JButton back;
	
	JLabel label; 
	JLabel user_label; 
	
	Container c;
	
	String username=null;
	String slot=null;	//new slot
	String blocked_slot[]=new String[6];
	//String all_slot[]=null;
	String password=null;
	int f1=0,f2=0,f3=0,f4=0,f5=0,f6=0;
	
	Parking_Slot(String username,String password) throws ClassNotFoundException, SQLException
	{
		this.username=username;
		this.password=password;
		
		f=new JFrame("Parking Slot");
		
		label=new JLabel("Welcome to Parking Slot".toUpperCase());
		user_label=new JLabel("'"+username.toUpperCase()+"'"+"    Choose one Slot");
		
		refresh=new JButton("Refresh");
		b1=new JButton("Slot 1");
		b2=new JButton("Slot 2");
		b3=new JButton("Slot 3");
		b4=new JButton("Slot 4");
		b5=new JButton("Slot 5");
		b6=new JButton("Slot 6");
		back=new JButton("<< BACK");
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(400,180,500,500);
		f.setResizable(false);
		f.setVisible(true);
		f.setLayout(null);
		c=f.getContentPane();
		c.setBackground(Color.GREEN);
		
		label.setBounds(170,-115,300,300);
		user_label.setBounds(5,-90,300,300);
		
		refresh.setBounds(400,0,100,30);
		
		b1.setBounds(50,100,100,100);
		b2.setBounds(200,100,100,100);
		b3.setBounds(350,100,100,100);
		b4.setBounds(50,300,100,100);
		b5.setBounds(200,300,100,100);
		b6.setBounds(350,300,100,100);
		
		back.setBounds(0,0,100,30);
		
		f.add(label);
		f.add(user_label);
		
		f.add(refresh);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(b5);
		f.add(b6);
		f.add(back);
		
		
		int i=0;
		/*all_slot=new String[6];
		for(i=0;i<6;i++)
		{
			all_slot[i]="Slot "+(i+1);
		}*/
		//System.out.println(all_slot[3]);
		
		blocked_slot=new Parking_slot_finding_booked_slot().connection();
		
		/*for(i=0;i<6;i++)
		{
			if(blocked_slot[i]!=null)
			{
				for(j=0;j<6;j++)
				{
					if(blocked_slot[i].equals(all_slot[j]))
					{
						System.out.println(all_slot[j]);
					}
				}	
			}		
		}*/
		
		refresh.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		back.addActionListener(this);
		
		
		f1=f2=f3=f4=f5=f6=0;
		for(i=0;i<6;i++)
		{
			if(blocked_slot[i]!=null)
			{
					if(blocked_slot[i].equals(b1.getText()))
					{
						f1=1;
						b1.removeActionListener(this);
						b1.setBackground(Color.RED);
					}
					else if(blocked_slot[i].equals(b2.getText()))
					{
						f2=1;
						b2.removeActionListener(this);
						b2.setBackground(Color.RED);
					}
					else if(blocked_slot[i].equals(b3.getText()))
					{
						f3=1;
						b3.removeActionListener(this);
						b3.setBackground(Color.RED);
					}
					else if(blocked_slot[i].equals(b4.getText()))
					{
						f4=1;
						b4.removeActionListener(this);
						b4.setBackground(Color.RED);
					}
					else if(blocked_slot[i].equals(b5.getText()))
					{
						f5=1;
						b5.removeActionListener(this);
						b5.setBackground(Color.RED);
					}
					else if(blocked_slot[i].equals(b6.getText()))
					{
						f6=1;
						b6.removeActionListener(this);
						b6.setBackground(Color.RED);
					}
			}		
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==refresh)
		{
			try 
			{
				new Parking_Slot(username,password);
			}
			catch (ClassNotFoundException | SQLException e1)
			{
				e1.printStackTrace();
			}
			f.setVisible(false);
		}
		if(e.getSource()==back)
		{
			new Login();
			f.setVisible(false);
		}
		else if(e.getSource()==b1)
		{
			slot="Slot 1";
			System.out.println("Slot 1 Booked");
			b1.setBackground(Color.RED);
			main_fn(username,password,slot);
		}
		else if(e.getSource()==b2)
		{
			slot="Slot 2";
			System.out.println("Slot 2 Booked");
			b2.setBackground(Color.RED);
			main_fn(username,password,slot);
		}
		else if(e.getSource()==b3)
		{
			slot="Slot 3";
			System.out.println("Slot 3 Booked");
			b3.setBackground(Color.RED);
			main_fn(username,password,slot);
		}
		else if(e.getSource()==b4)
		{
			slot="Slot 4";
			System.out.println("Slot 4 Booked");
			b4.setBackground(Color.RED);
			main_fn(username,password,slot);
		}
		else if(e.getSource()==b5)
		{
			slot="Slot 5";
			System.out.println("Slot 5 Booked");
			b5.setBackground(Color.RED);
			main_fn(username,password,slot);
		}
		else if(e.getSource()==b6)
		{
			slot="Slot 6";
			System.out.println("Slot 6 Booked");
			b6.setBackground(Color.RED);
			main_fn(username,password,slot);
		}	
	}
	void main_fn(String Username,String Password,String Slot)
	{
		this.username=Username;
		this.password=Password;
		this.slot=Slot;
		
		JOptionPane.showMessageDialog(f,slot+" Booked\n\""+username+"\"  Proceed further",
				"SUCCESS", JOptionPane.PLAIN_MESSAGE);
		
		//**********************
		new ChooseDateAndTimeForParkingSlot(username,password,slot);
		//**********************
		
		/*try
		{
			new Parking_Slot_connect().connection(username,password,slot);
		} 
		catch (ClassNotFoundException | SQLException | MessagingException e1)
		{
			e1.printStackTrace();
		}
		JOptionPane.showMessageDialog(f,slot+" Booked\n THANK YOU",
				"SUCCESS", JOptionPane.PLAIN_MESSAGE);*/
		
		f.setVisible(false);
	}

	/*public static void main(String[]args) throws ClassNotFoundException, SQLException
	{
		new Parking_Slot("anita","anita");
	}*/
}
