import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.swing.JOptionPane;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SonarPingModel {

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
		}
		
		catch(Exception ex){
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
	
	public static class Serial implements SerialPortEventListener{
		@Override
		public void serialEvent(SerialPortEvent event) {
			try{
				
				if(event.getNewValue()){
					
					System.out.println("Joshua");
					try{
						Thread.sleep(30000);
					}
					
					catch(Exception ex){
						
					}
					 
						
						
				}else if(event.getEventType() == SerialPortEvent.OUTPUT_BUFFER_EMPTY){
					System.out.println("asdf");

				}
				
				
			}catch(Exception ex){
				ex.printStackTrace();
				
				//JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
	}
}
