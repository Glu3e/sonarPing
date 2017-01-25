import java.io.BufferedReader;
import java.io.IOException;
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
		//@Override
		public synchronized void serialEvent(SerialPortEvent event) {
			try{
				
				if(event.getNewValue()){
					
					try{
						String x = input.readLine();
						if(x.equals("Motion Comming From Main Door!")){
							
							SonarPingEmailModel runner = new SonarPingEmailModel("a.collaco@hotmail.com", 
									"john.orion.ray@gmail.com", "john.orion.ray@gmail.com", "phantom1237");
							runner.sessionInitialize();
							runner.run();
							
						}
					
						
					}
					
					catch(Exception ex){
						
					}
					 
					
					
						
						
				}else if(event.getEventType() == SerialPortEvent.OUTPUT_BUFFER_EMPTY){
					

				}
				
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
}
