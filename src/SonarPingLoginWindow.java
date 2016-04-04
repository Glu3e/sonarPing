import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SonarPingLoginWindow extends JFrame {
	public static void main(String[] args) {
		SonarPingLoginWindow frameTable = new SonarPingLoginWindow();
	}
	
	JButton btnLogin = new JButton("Login");
	JPanel panel = new JPanel();
	JTextField txtUserName = new JTextField();
	JPasswordField pwdUser = new JPasswordField();
	JLabel lblUserName = new JLabel("Username: ");
	JLabel lblPass = new JLabel("Password: ");
	
	SonarPingLoginWindow(){
		super("User Login Authentication");
		setSize(350, 200);
		setLocation(500, 300);
		panel.setLayout(null);
		//panel.setBackground(Color.lightGray);
		
		txtUserName.setBounds(170,30,150,25);
		lblUserName.setBounds(70, 30, 150, 25);
		lblPass.setBounds(70, 70, 150, 25);
		pwdUser.setBounds(170,70,150,25);
		btnLogin.setBounds(200,120,80,20);
		
		panel.add(btnLogin);
		panel.add(txtUserName);
		panel.add(pwdUser);
		panel.add(lblUserName);
		panel.add(lblPass);
		
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		//loginAction();
	}
	
	/*public void loginAction() {
		btnLogin.addActionListener(new ActionListener() {
			public void authentication(ActionEvent ae) {
				String uName = txtUserName.getText();
				String password = pwdUser.getText();
				
				if (uName.equals ("Akan") && password.equals("1234"))
				{
					SonarPingView sPingView = new SonarPingView();
					sPingView.setVisible(true);
					dispose();
				}
				
				else
				{
					JOptionPane.showMessageDialog(null,"Incorrect Password/Username");
					txtUserName.setText("");
					pwdUser.setText("");
					txtUserName.requestFocus();
				}
			}

		});
	} */
}
