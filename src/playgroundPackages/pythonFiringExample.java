package playgroundPackages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class pythonFiringExample {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);  
		System.out.println("Enter a number: ");
		int scriptSelector = reader.nextInt(); 
		
		
		
		
		reader.close();
		
		if(scriptSelector == 1){
		
			try {
				 String s;
				 Process p = Runtime.getRuntime().exec("python " + System.getProperty("user.dir") + "\\pythonScripts\\tester.py");
		            
		            BufferedReader stdInput = new BufferedReader(new 
		                 InputStreamReader(p.getInputStream()));

		            BufferedReader stdError = new BufferedReader(new 
		                 InputStreamReader(p.getErrorStream()));

		            // read the output from the command
		            System.out.println("Here is the standard output of the command:\n");
		            while ((s = stdInput.readLine()) != null) {
		                System.out.println(s);
		            }
		            
		            // read any errors from the attempted command
		            System.out.println("Here is the standard error of the command (if any):\n");
		            while ((s = stdError.readLine()) != null) {
		                System.out.println(s);
		            }
		            
		            System.exit(0);
		            
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}else if(scriptSelector == 2){
			try {
				 String s;
				 Process p = Runtime.getRuntime().exec("python " + System.getProperty("user.dir") + "\\pythonScripts\\helloIfs.py");
		            
		            BufferedReader stdInput = new BufferedReader(new 
		                 InputStreamReader(p.getInputStream()));

		            BufferedReader stdError = new BufferedReader(new 
		                 InputStreamReader(p.getErrorStream()));

		            // read the output from the command
		            System.out.println("Here is the standard output of the command:\n");
		            while ((s = stdInput.readLine()) != null) {
		                System.out.println(s);
		            }
		            
		            // read any errors from the attempted command
		            System.out.println("Here is the standard error of the command (if any):\n");
		            while ((s = stdError.readLine()) != null) {
		                System.out.println(s);
		            }
		            
		            System.exit(0);
		            
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}else if(scriptSelector == 3){
			try {
				 String s;
				 Process p = Runtime.getRuntime().exec("python " + System.getProperty("user.dir") + "\\pythonScripts\\tester.py");
		            
		            BufferedReader stdInput = new BufferedReader(new 
		                 InputStreamReader(p.getInputStream()));

		            BufferedReader stdError = new BufferedReader(new 
		                 InputStreamReader(p.getErrorStream()));

		            // read the output from the command
		            System.out.println("Here is the standard output of the command:\n");
		            while ((s = stdInput.readLine()) != null) {
		                System.out.println(s);
		            }
		            
		            // read any errors from the attempted command
		            System.out.println("Here is the standard error of the command (if any):\n");
		            while ((s = stdError.readLine()) != null) {
		                System.out.println(s);
		            }
		            
		            System.exit(0);
		            
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}
		

	}

}
