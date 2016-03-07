import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.swing.JOptionPane;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SonarPingModel implements Runnable{

	SerialPort port;
	CommPortIdentifier basicPort;
	static BufferedReader input;
	static OutputStream output;

	SonarPingModel(String comPortName){
		try{
			this.basicPort = CommPortIdentifier.getPortIdentifier(comPortName);
			this.port = (SerialPort) this.basicPort.open("", 1);

			port.setSerialPortParams(9600, 
					SerialPort.DATABITS_8, 
					SerialPort.STOPBITS_1, 
					SerialPort.PARITY_NONE);

			output = port.getOutputStream();
			input = new BufferedReader(new InputStreamReader(this.port.getInputStream()));
			//this.port.addEventListener(new Serial());
			//this.port.notifyOnDataAvailable(true);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void toggle(int x){
		if(x == 1){
			this.createConnection();
		}else if(x == 0){
			this.breakConnection();
		}
	}

	private void createConnection(){
		try{
			this.port.addEventListener(new Serial());
			this.port.notifyOnDataAvailable(true);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private void breakConnection(){
		try{
			this.port.removeEventListener();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void readEverything(){
		try{
			System.out.println(input.read() + "Joshua");
		}
		
		catch(Exception ex){
			
		}
	}
	
	
	public static class Serial implements SerialPortEventListener{
		@Override
		public void serialEvent(SerialPortEvent event) {
			try{
				switch(event.getEventType()){
					case SerialPortEvent.DATA_AVAILABLE:
						
						break;
				}
				
				
				
			}catch(Exception ex){
				ex.printStackTrace();
				//JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
	}

	@Override
	public void run() {
		while(true){
			readEverything();
		}
		
	}
}
