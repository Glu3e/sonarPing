import javax.swing.JButton;
import javax.swing.JFrame;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestJUnit 
{	
	//Integration Test
	public static void main1(String [] args)
	{
        final JFrame frame = new JFrame("");
        final JButton btnLogin = new JButton("");        

        MyLoginDialog loginDlg = new MyLoginDialog(frame);
        loginDlg.setVisible(true);
        // if logon successfully
        if(loginDlg.isSucceeded())
        {
        	 btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
        }
        else
        {
        	 btnLogin.setText("Sorry your user/password are not match!");
        	 System.exit(0);
        }
		
        
        SonarPingModel model = new SonarPingModel("COM4");	
		SonarPingView view = new SonarPingView();
		SonarPingController controller = new SonarPingController(model, view,null);			
	}
}
