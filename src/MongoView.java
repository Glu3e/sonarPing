
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MongoView extends JFrame{
	
	private JLabel     firstNameLabel = new JLabel("Firstname: ");
	JTextField firstName = new JTextField(10);
	private JLabel     lastNameLabel = new JLabel("Lastname: ");
	JTextField lastName = new JTextField(10);
	private JLabel     EmailLabel = new JLabel("Email: ");
	JTextField email = new JTextField(10);
	private JLabel     PasswordLabel = new JLabel("Password: ");
	JTextField password = new JTextField(10);
	private JLabel browseUserInfo = new JLabel("Browse User Info :");
	JTextField type = new JTextField(10);
	private JLabel userType = new JLabel("Type :");
	
	JButton 	createButton = new JButton("Create");
	JButton 	deleteButton = new JButton("Delete");
	JButton 	updateButton = new JButton("Update");
	JButton     browseButton = new JButton("Browse");
	
	//private JButton     removeallButton = new JButton("Remove All");
 
	public JPanel panelMain;
	public JPanel panelCenter;
	public JPanel calcPanel;
	
	MongoView(){
		
		panelMain = new JPanel();
		
		this.setSize(600,180);
		this.setResizable(false);
		this.setTitle("MONGO DB TEST");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		panelMain.setLayout(new BorderLayout());
		this.add(panelMain);
		
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
		panelCenter.add(userType,gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panelCenter.add(type,gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		panelCenter.add(createButton,gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;		
		panelCenter.add(deleteButton,gbc);
		gbc.gridx = 2;
		gbc.gridy = 5;		
		panelCenter.add(updateButton,gbc);
		gbc.gridx = 3;
		gbc.gridy = 5;		
		panelCenter.add(browseButton,gbc);	
		gbc.gridx = 5;
		gbc.gridy = 5;		
		//panelCenter.add(removeallButton,gbc);		
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
	
	public String getUserType()
	{
		return type.getText();
	}

	void addCreateButtonListener(ActionListener listener){
		createButton.addActionListener(listener);
	}
	
	void addDeleteButtonListener(ActionListener listener){
		deleteButton.addActionListener(listener);
	}
	
	void addUpdateButtonListener(ActionListener listener){
		updateButton.addActionListener(listener);
	}
	
	void addBrowseButtonListener(ActionListener listener){
		browseButton.addActionListener(listener);
	}
	
	
	void displayErrorMessage(String errorMessage)
	{
		JOptionPane.showMessageDialog(this, errorMessage);
	}
}
