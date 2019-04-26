import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import com.fazecast.jSerialComm.SerialPort;

public class ReadingandWriting
{
	static SerialPort s1;
	static PrintWriter outPut;
	
	public static void main(String[] args) throws IOException,InterruptedException 
	{
		SerialPort[] s=SerialPort.getCommPorts();
		for(SerialPort port:s)
		{
			System.out.println(""+port.getSystemPortName());;
			s1=SerialPort.getCommPort(port.getSystemPortName());
			if(s1.openPort())
			{
				System.out.println("Successfully port opened");
				outPut = new PrintWriter(s1.getOutputStream());
			}
			else
			{
				System.out.println("Failed to open port");
			}
		}
		
		s1.setBaudRate(9600);
		InputStream is=s1.getInputStream();
		Thread.sleep(1000);
		
		
		for(int i=0;true;i++)
		{
			String x=""+(char)is.read();
			System.out.print(x);
			
			if(x.equals("1"))
			{
				System.out.println(""+(char)is.read());	
				//System.out.println("Status: LED on");
				//outPut.print("1");
				//outPut.flush();
			}
			Thread.sleep(200);
		}
	}
}
