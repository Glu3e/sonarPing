import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;  
import javax.swing.event.ListSelectionListener;
import javax.swing.UIManager;

import org.junit.Test;
//import static org.junit.Assert.assertEquals;
/**
 * This class implement a view which interacts with the users
 * @author Zhaoduan, Akanimoh, Keitha
 * @version 2.0
 * @since 2016-03-10
 */
public class MongoView extends JFrame{
	
	private JLabel useridLabel = new JLabel("UserID: ");
	JTextField userid = new JTextField(40);
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
	String[] petStrings = { "Homeowner", "Dependant", "Time-Sensitive" };


	
	private JButton 	createButton = new JButton("Create");
	private JButton 	deleteButton = new JButton("Delete");
	private JButton 	updateButton = new JButton("Update");
	private JButton     browseButton = new JButton("Browse");
	private JButton     removeallButton = new JButton("Remove All");
	private JButton 	btnView1;
 
	public JPanel panelMain;
	public JPanel panelCenter;
	//public JPanel calcPanel;
	
	public JPanel panelView;
	public JScrollPane srlpaneView;
	public JTable dbTableView;
	public DefaultTableModel tblmodelView;
	
	Color color4 = new Color(54, 56, 92);
	Color color5 = new Color(1, 2, 20);
	Color color6 = new Color(24, 26, 62);
	Color color7 = new Color(1, 2, 20, 100);
	
	Color color1 = new Color(79, 87, 121);
	Color color2 = new Color(30, 37, 72, 180);
	Color color3 = new Color(22, 28, 57,130);
	/**
	 * This method build View Panel
	 */
	public void buildViewPanel()
	{
		panelView = new JPanel();
		panelView.setLayout(new BorderLayout());
		//panelView.setBackground(color1);

		btnView1 = new JButton("Refresh");
		btnView1.setBackground(color6);
		btnView1.setForeground(Color.lightGray);
		
		panelView.add("South",btnView1);
		
		Object[] columns = {"FirstName", "LastName", "Email", "Password", "Type"/*, "Id"*/};
		
		tblmodelView = new DefaultTableModel();
		tblmodelView.setColumnIdentifiers(columns);
		
		dbTableView = new JTable(tblmodelView);
		//dbTableView.getcomp
		dbTableView.setBackground(color1);
		dbTableView.setForeground(Color.lightGray);
		
		
		srlpaneView = new JScrollPane(dbTableView);
		//srlpaneView.setBackground(Color.black);
		panelView.add("North",srlpaneView);
		
		
		dbTableView.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    //@Override
		    public void valueChanged(ListSelectionEvent event) 
		    {
		        if (dbTableView.getSelectedRow() > -1) 
		        {
		            firstName.setText(dbTableView.getValueAt(dbTableView.getSelectedRow(), 0).toString());
		            lastName.setText(dbTableView.getValueAt(dbTableView.getSelectedRow(), 1).toString());
		            email.setText(dbTableView.getValueAt(dbTableView.getSelectedRow(), 2).toString());
		            password.setText(dbTableView.getValueAt(dbTableView.getSelectedRow(), 3).toString());
		            type.setText(dbTableView.getValueAt(dbTableView.getSelectedRow(), 4).toString());
		        }
		    }
		});		
		//panelView.getContentPane().setBackground(color4);
		//dbTableView.getContentPane().setBackground(color4);
		//((RootPaneContainer) srlpaneView).getContentPane().setBackground(color4);
		this.getContentPane().setBackground(color4);

	}	
	
	/**
	 * This constructs MongoView
	 */
	MongoView()
	{
		
		panelMain = new JPanel();
		this.setSize(600,680);
		this.setResizable(false);
		this.getContentPane().setBackground(color3);
		this.setTitle("Users Information Management");
		panelMain.setLayout(new BorderLayout());
		panelMain.setBackground(color1);
		getContentPane().add(panelMain);
		//change panelMain BorderLAyout to another layout so I can chnage Refresh button width and add Browse button next to it
		buildViewPanel();
		panelMain.add("North",panelView);
		
		panelCenter = new JPanel();
		panelCenter.setLayout(new GridBagLayout());
		panelCenter.setBackground(color1);
		buildMongoGBC(panelCenter);	//chnage gridbag unit lengths so that the buttons are longer in width and spaced out from the text fields a bit 
		
		//setVisible(true);
		panelMain.add("Center",panelCenter);
		
		
	}
	
	/**
	 * For SonarPingView - in buildMongoPanel method - to build gridbag contraints for MongoView
	 */
	public void buildMongoGBC(JPanel panelCenter)
	{
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.insets = new Insets(0, 0, 5, 5);
		GridBagConstraints gbc3 = new GridBagConstraints();
		gbc3.insets = new Insets(0, 0, 5, 5);
		GridBagConstraints gbc4 = new GridBagConstraints();
		gbc4.insets = new Insets(0, 0, 5, 5);
		GridBagConstraints gbc5 = new GridBagConstraints();
		gbc5.insets = new Insets(0, 0, 5, 5);
		GridBagConstraints gbc6 = new GridBagConstraints();
		gbc6.insets = new Insets(0, 0, 5, 5);
		GridBagConstraints gbc7 = new GridBagConstraints();
		gbc7.insets = new Insets(0, 0, 5, 5);
		GridBagConstraints gbc8 = new GridBagConstraints();
		gbc8.insets = new Insets(0, 0, 5, 5);
		GridBagConstraints gbc9 = new GridBagConstraints();
		gbc9.insets = new Insets(0, 0, 0, 5);
		GridBagConstraints gbc11 = new GridBagConstraints();
		gbc11.insets = new Insets(0, 0, 0, 5);

		createButton.setBackground(color6);
		createButton.setForeground(Color.lightGray);
		updateButton.setBackground(color6);
		updateButton.setForeground(Color.lightGray);
		browseButton.setBackground(color6);
		browseButton.setForeground(Color.lightGray);
		deleteButton.setBackground(color6);
		deleteButton.setForeground(Color.lightGray);

		firstNameLabel.setForeground(Color.lightGray);
		lastNameLabel.setForeground(Color.lightGray);
		EmailLabel.setForeground(Color.lightGray);
		PasswordLabel.setForeground(Color.lightGray);
		userType.setForeground(Color.lightGray);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelCenter.add(firstNameLabel,gbc);
		gbc2.gridx = 1;
		gbc2.gridy = 0;
		panelCenter.add(firstName,gbc2);
		GridBagConstraints gbc12 = new GridBagConstraints();
		gbc12.fill = GridBagConstraints.BOTH;
		gbc12.insets = new Insets(0, 0, 5, 5);
		gbc12.gridx = 3;
		gbc12.gridy = 0;
		panelCenter.add(createButton,gbc12);
		gbc3.gridx = 0;
		gbc3.gridy = 1;
		panelCenter.add(lastNameLabel,gbc3);
		gbc4.gridx = 1;
		gbc4.gridy = 1;
		panelCenter.add(lastName,gbc4);
		GridBagConstraints gbc14 = new GridBagConstraints();
		gbc14.fill = GridBagConstraints.BOTH;
		gbc14.insets = new Insets(0, 0, 5, 5);
		gbc14.gridx = 3;
		gbc14.gridy = 1;		
		panelCenter.add(updateButton,gbc14);
		gbc5.gridx = 0;
		gbc5.gridy = 2;
		panelCenter.add(EmailLabel,gbc5);
		gbc6.gridx = 1;
		gbc6.gridy = 2;
		panelCenter.add(email,gbc6);
		GridBagConstraints gbc15 = new GridBagConstraints();
		gbc15.fill = GridBagConstraints.BOTH;
		gbc15.insets = new Insets(0, 0, 5, 5);
		gbc15.gridx = 3;
		gbc15.gridy = 2;		
		panelCenter.add(browseButton,gbc15);
		gbc7.gridx = 0;
		gbc7.gridy = 3;		
		panelCenter.add(PasswordLabel,gbc7);
		gbc8.gridx = 1;
		gbc8.gridy = 3;	
		panelCenter.add(password,gbc8);
		GridBagConstraints gbc13 = new GridBagConstraints();
		gbc13.fill = GridBagConstraints.BOTH;
		gbc13.insets = new Insets(0, 0, 5, 5);
		gbc13.gridx = 3;
		gbc13.gridy = 3;		
		panelCenter.add(deleteButton,gbc13);
		gbc9.gridx = 0;
		gbc9.gridy = 4;
		panelCenter.add(userType,gbc9);
		gbc11.gridx = 1;
		gbc11.gridy = 4;
		panelCenter.add(type,gbc11);
	}
	
	/**
	 * This method gets firstname
	 * @return firstname
	 */
	public String getFirstName()
	{
		return firstName.getText();
	}
	
	/**
	 * This method gets lastname
	 * @return lastname
	 */
	public String getLastName()
	{
		return lastName.getText();
	}
	
	/**
	 * This method gets email address
	 * @return email address
	 */
	public String getEmail()
	{
		return email.getText();
	}
	
	/**
	 * This method gets password
	 * @return password
	 */
	public String getPassword()
	{
		return password.getText();
	}
	
	/**
	 * This method gets user type
	 * @return user type
	 */
	public String getUserType()
	{
		return type.getText();
	}

	/**
	 * This method adds listener
	 * @param listenForButton
	 */
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
		btnView1.setActionCommand("browseButton");
		btnView1.addActionListener(listenForButton);
	}
	
	/**
	 * This method displays a error message
	 * @param errorMessage
	 */
	void displayErrorMessage(String errorMessage)
	{
		UIManager.put("OptionPane.background", color5);
		UIManager.put("Panel.background", color5);
		JOptionPane.showMessageDialog(this, errorMessage);
	}
}
