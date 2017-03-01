import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * This is the driver class for the system
 * @author Joshua, Zhaoduan
 * @version 2.0
 * @since 2016-01-30
 */
public class StartingPoint 
{
	public static void main(String [] args){
       // final JFrame frame = new JFrame("");
        //final JButton btnLogin = new JButton("");        

//        MyLoginDialog loginDlg = new MyLoginDialog(frame);
//        loginDlg.setVisible(true);
//        // if logon successfully
//        if(loginDlg.isSucceeded())
//        {
//        	 btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
//        }
//        else
//        {
//        	 btnLogin.setText("Sorry your user/password are not match!");
//        	 System.exit(0);
//        }
		
        
        SonarPingModel model = new SonarPingModel("COM3");	
		SonarPingView view = new SonarPingView();
		SonarPingController controller = new SonarPingController(model, view,null);
			
	}
}
