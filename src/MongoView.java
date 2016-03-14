
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MongoView extends JFrame{
	
	private JTextField firstNumber = new JTextField(10);
	private JLabel additionLable = new JLabel("+");
	private JTextField secondNumber = new JTextField(10);
	private JButton calculateButton = new JButton("Calculate");
	private JTextField calcSolution = new JTextField(10);
	
	private JLabel     firstNameLabel = new JLabel("Firstname: ");
	private JTextField firstName = new JTextField(10);
	private JLabel     lastNameLabel = new JLabel("Lastname: ");
	private JTextField lastName = new JTextField(10);
	private JLabel     EmailLabel = new JLabel("Email: ");
	private JTextField email = new JTextField(10);
	private JLabel     PasswordLabel = new JLabel("Password: ");
	private JTextField password = new JTextField(10);
	private JLabel browseUserInfo = new JLabel("Browse User Info :");
	
	private JButton 	createButton = new JButton("Create");
	private JButton 	deleteButton = new JButton("Delete");
	private JButton 	updateButton = new JButton("Update");
	private JButton     browseButton = new JButton("Browse");
	private JButton     removeallButton = new JButton("Remove All");
 
	public JPanel panelMain;
	public JPanel panelCenter;
	public JPanel calcPanel;
	
	MongoView(){
		
		panelMain = new JPanel();
		
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,480);
		this.setTitle("MONGO DB TEST");
		panelMain.setLayout(new BorderLayout());
		this.add(panelMain);
		
		/*calcPanel = new JPanel();
		calcPanel.setLayout(new FlowLayout());
		calcPanel.add(firstNumber);
		calcPanel.add(additionLable);
		calcPanel.add(secondNumber);
		calcPanel.add(calculateButton);
		calcPanel.add(calcSolution);
		panelMain.add("North",calcPanel);*/
		
		panelCenter = new JPanel();
		panelCenter.setLayout(new GridBagLayout());		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelCenter.add(firstNameLabel,gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelCenter.add(firstName,gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelCenter.add(lastNameLabel,gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelCenter.add(lastName,gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelCenter.add(EmailLabel,gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panelCenter.add(email,gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;		
		panelCenter.add(PasswordLabel,gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;	
		panelCenter.add(password,gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		panelCenter.add(createButton,gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;		
		panelCenter.add(deleteButton,gbc);
		gbc.gridx = 2;
		gbc.gridy = 4;		
		panelCenter.add(updateButton,gbc);
		gbc.gridx = 3;
		gbc.gridy = 4;		
		panelCenter.add(browseButton,gbc);	
		gbc.gridx = 5;
		gbc.gridy = 4;		
		panelCenter.add(removeallButton,gbc);		
		setVisible(true);
		panelMain.add("Center",panelCenter);
		
		
	}
	
	public String getFirstName()
	{
		return firstName.getText();
	}
	
	public String getLastName()
	{
		return lastName.getText();
	}
	
	public String getEmail()
	{
		return email.getText();
	}
	
	public String getPassword()
	{
		return password.getText();
	}
	

	
	void addMongoButtonListener(ActionListener listenForButton)
	{
		createButton.setActionCommand("CreateButton");
		createButton.addActionListener(listenForButton);
		deleteButton.setActionCommand("deleteButton");
		deleteButton.addActionListener(listenForButton);
		updateButton.setActionCommand("updateButton");
		updateButton.addActionListener(listenForButton);	
		browseButton.setActionCommand("browseButton");
		browseButton.addActionListener(listenForButton);
		removeallButton.setActionCommand("removeallButton");
		removeallButton.addActionListener(listenForButton);		
		
	}
	
	void displayErrorMessage(String errorMessage)
	{
		JOptionPane.showMessageDialog(this, errorMessage);
	}
}
