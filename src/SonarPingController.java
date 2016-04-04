import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author Jupiter
 *
 */
public class SonarPingController {
	SonarPingModel sonarPingModel;
	SonarPingEmailModel sonarPingEmailModel;
	SonarPingView sonarPingView;
	int toggle;
	
	SonarPingController(SonarPingModel model, SonarPingView view, SonarPingEmailModel emailModel){
		this.sonarPingModel = model;
		this.sonarPingView = view;
		this.sonarPingEmailModel = emailModel;
		
		toggle = 0;
		sonarPingView.armListener(new SystemArmingListener());
		sonarPingView.disarmListener(new SystemArmingListener());
		
		this.sonarPingModel.toggle(0);
	}
	
	public class SystemArmingListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() == sonarPingView.ArmJButton){

				sonarPingModel.toggle(1);
				sonarPingView.colorPanel.setBackground(Color.RED);
				sonarPingView.ArmJButton.setEnabled(false);
				sonarPingView.DisarmJButton.setEnabled(true);
				sonarPingModel.disarmButtonPresses = false;
				
			}else if(arg0.getSource() == sonarPingView.DisarmJButton){
				
				sonarPingView.colorPanel.setBackground(Color.GREEN);
				sonarPingModel.toggle(0);
				sonarPingView.ArmJButton.setEnabled(true);
				sonarPingView.DisarmJButton.setEnabled(false);
				sonarPingModel.disarmButtonPresses = true;
			}
		}
	}
}
