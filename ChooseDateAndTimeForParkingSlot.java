import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class ChooseDateAndTimeForParkingSlot implements ActionListener 
{
	JFrame f;
	JLabel chooseDate;
	JLabel chooseTime;
	JLabel currDate;
	JLabel currTime;
	JLabel slot;
	JDateChooser date;
	Choice time;
	JTextField slotText;
	
	JButton confirm;
	JButton back;
	
	Container c;
	
	String username;
	String password;
	String slotno;
	
	ChooseDateAndTimeForParkingSlot(String username,String password,String slotno)
	{
		this.username=username;
		this.password=password;
		this.slotno=slotno;
		
		f=new JFrame("Choose Date And Time For Parking Slot");
		
		chooseDate=new JLabel("Choose Date");
		confirm=new JButton("Confirm");
		back=new JButton("<< Back");
		slot=new JLabel("Slot No ");
		slotText=new JTextField(slotno);
		
		chooseTime=new JLabel("Choose Time");
		time=new Choice();
		time.add("00:01");
		time.add("00:02");
		time.add("00:03");
		time.add("00:04");
		time.add("00:05");
		time.add("00:10");
		time.add("00:30");
		time.add("01:00");
		time.add("02:00");
		time.add("03:00");
		time.add("04:00");
		
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm a");
		
		Date curr = new Date();
		currDate=new JLabel(dateFormat.format(curr));
		currTime=new JLabel(timeFormat.format(curr));
		//System.out.println(dateFormat.format());
		
		date=new JDateChooser();
		date.setDateFormatString("yyyy-MM-dd");
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(400,180,500,300);
		f.setResizable(false);
		f.setVisible(true);
		f.setLayout(null);
		c=f.getContentPane();
		c.setBackground(Color.yellow);
		
		back.setBounds(0,0,100,30);
		
		slot.setBounds(50,50,100,30);
		slotText.setBounds(200,50,100,30);
		
		chooseDate.setBounds(50,100,100,30);
		
		chooseTime.setBounds(50,150,100,30);
		time.setBounds(200,150,100,30);
		
		confirm.setBounds(50,180,100,30);
		confirm.setBackground(Color.GREEN);
		
		date.setBounds(200,100,200,30);
		currDate.setBounds(410,0,200,30);
		currTime.setBounds(410,30,200,30);
		
		f.add(chooseDate);
		f.add(confirm);
		f.add(back);
		f.add(date);
		f.add(currDate);
		f.add(currTime);
		f.add(slot);
		f.add(slotText);
		
		f.add(chooseTime);
		f.add(time);
		
		confirm.addActionListener(this);
		back.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		String choosegetdate=((JTextField)date.getDateEditor().getUiComponent()).getText();
		String currentDate=currDate.getText();
		String currentTime=currTime.getText();
		String choosegettime=time.getSelectedItem();
			
		if(e.getSource()==confirm)
		{
			try 
			{
				DateFormat timeFormat = new SimpleDateFormat("HH:mm a");
				Date curr = new Date();
				String latestTime=timeFormat.format(curr);
				
				new Parking_Slot_connect().connection(username,password,
				slotno,currentDate,latestTime,choosegetdate,choosegettime);
				
			} 
			catch (ClassNotFoundException | SQLException | MessagingException e1) 
			{
				e1.printStackTrace();
			}
			
			JOptionPane.showMessageDialog(f,slotno+" Booked\n THANK YOU",
					"SUCCESS", JOptionPane.PLAIN_MESSAGE);
			new Login();
			f.setVisible(false);
				
		}
		
		if(e.getSource()==back)
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
		
	}
	/*public static void main(String[] args) 
	{
		new ChooseDateAndTimeForParkingSlot("anita", "anita", "Slot 3" );
	}*/
}
