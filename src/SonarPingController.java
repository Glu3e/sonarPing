import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JButton;

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
				//JOptionPane.showMessageDialog(sonarPingView, "Activated");
				sonarPingModel.toggle(1);
				sonarPingView.colorPanel.setBackground(Color.RED);
			}else if(arg0.getSource() == sonarPingView.DisarmJButton){
				//JOptionPane.showMessageDialog(sonarPingView, "Deactivated");
				sonarPingView.colorPanel.setBackground(Color.GREEN);
				sonarPingModel.toggle(0);
			}
			
		}
	}
}
