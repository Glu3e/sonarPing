//import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;



import java.awt.*;
import java.awt.Window.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
 * This class deals with the user GUI
 * @author Joshua 
 * @version 2.0
 * @since 2016-01-30
 */
public class SonarPingView extends JFrame
{
	
	public JTabbedPane tabPane = new JTabbedPane();
	public JPanel panelMain, panelUser;
	public JPanel panelNorth;
	public JPanel panelCenter;
	
	/* panelNorth */
	public JTextField txtNorth1;
	public JButton btnNorth1;
	public JButton btnNorth2;
	
	/* panelCenter */
	public JButton btnCenter1, btnCenter2;
	GridBagConstraints gbc = new GridBagConstraints();
	
	
	/* panelSouth */
	private JPanel armPanel;
	private JPanel disarmPanel;
	private JPanel main;
		
	JPanel colorPanel;
	JButton ArmJButton;
	JButton DisarmJButton;
	
	JMenuBar menuBar;
	JMenu menuDecleration;
	
	JMenuItem configureItem;
	JMenuItem closeItem;
	
	
	static int SLOTIDEL = 0;
	static int SLOTUSED = 1;
	static int SLOTREMOVED =2;
	public int[] SensorList = {SLOTUSED,SLOTIDEL,SLOTIDEL,SLOTIDEL,SLOTIDEL,SLOTIDEL,SLOTIDEL,SLOTIDEL,SLOTIDEL,SLOTIDEL,SLOTIDEL,SLOTIDEL,SLOTIDEL,SLOTIDEL,SLOTIDEL,SLOTIDEL}; 
	public static int MaxSensor = 8;
	public static JButton[] SensorArm = new JButton[MaxSensor];
	public static JButton[] SensorDisarm = new JButton[MaxSensor];
	//public static JLabel[] SensorFlag = new JLabel[MaxSensor];
	public static JTextField[] SensorFlag = new JTextField[MaxSensor];
	
	/**
	 * This constructs a SonarPingView
	 */
	public SonarPingView()
	{
		Init();
		buildMainPanel();
		buildTabPane();
		if(MyLoginDialog.homeowner)
		{
			//JOptionPane.showMessageDialog(null,"homeowner is true.");
			buildMenu();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640,680);
		setVisible(true);
				
		addListeners();
	}
	/**
	 * This method builds a menu
	 */
	public void buildMenu()
	{
		configureItem = new JMenuItem("Configure");
		configureItem.setToolTipText("Configure Data");
		configureItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MongoMVC mongo = new MongoMVC();
				mongo.main(null);
			}
		});
		
		menuDecleration = new JMenu("File");		
		menuDecleration.add(configureItem);
		menuBar.add(menuDecleration);
		setJMenuBar(menuBar);
	}
	
	/**
	 * This method builds north panel in the view
	 */
	public void buildNorthPanel()
	{
		btnNorth1 = new JButton("Simulate adding a new sensor");
		panelNorth.add(btnNorth1);
		btnNorth2 = new JButton("Simulate removing a existed sensor");
		panelNorth.add(btnNorth2);
	}
	
	/**
	 * This method removes label and button from the center panel
	 */
	public void removefromCenterPanel()
	{
		int i;
		
		for(i=1; i<MaxSensor; i++)
		{
			if(SensorList[i]==SLOTREMOVED)
			{
				//JOptionPane.showMessageDialog(null,"Remove sensor "+Integer.toString(i));
				GridBagConstraints gbc = new GridBagConstraints();
				panelCenter.remove(SensorFlag[i]);
				panelCenter.remove(SensorArm[i]);
				panelCenter.remove(SensorDisarm[i]);
				SensorList[i]=SLOTIDEL;
				panelCenter.invalidate();
				panelCenter.repaint();
		
			}
		}	
	}
	
	/**
	 * This method builds center panel
	 */
	
	public void buildCenterPanel()
	{
		int i;
				
		gbc.insets = new Insets(5,5,5,5);		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelCenter.add(SensorFlag[0], gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelCenter.add(SensorArm[0], gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		panelCenter.add(SensorDisarm[0], gbc);
		
		int y = 1;		
		
		for(i=1; i<MaxSensor; i++)
		{
			if(SensorList[i]==SLOTUSED)
			{
				gbc.gridx = 0;
				gbc.gridy = y;
				panelCenter.add(SensorFlag[i], gbc);
				gbc.gridx = 1;
				gbc.gridy = y;
				panelCenter.add(SensorArm[i], gbc);
				gbc.gridx = 2;
				gbc.gridy = y;
				panelCenter.add(SensorDisarm[i], gbc);
				y++;				
			}
		}
		panelCenter.invalidate();
		panelCenter.repaint();
	}
	
	/**
	 * This method builds south panel
	 */
	public void buildSouthPanel()
	{
		armPanel = new JPanel(new GridLayout(1,1));
		disarmPanel = new JPanel(new GridLayout(1,1));
		
		colorPanel = new JPanel();
		menuBar = new JMenuBar();
		
		main = new JPanel();
		main.setLayout(new GridLayout(2,2));
		
		ArmJButton = new JButton("Arm"); 
		armPanel.add(ArmJButton);

		DisarmJButton = new JButton("Disarm"); 
		disarmPanel.add(DisarmJButton);

		main.add(armPanel);
		main.add(disarmPanel);
		main.add(colorPanel);
	}

	/**
	 * This method build main panel
	 */
	public void buildMainPanel()
	{			
		panelMain = new JPanel();
		panelMain.setSize(680,480);
		panelMain.setLayout(new BorderLayout());		
		panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout());
		buildNorthPanel();
		panelMain.add("North",panelNorth);
		
		panelCenter = new JPanel();
		panelCenter.setLayout(new GridBagLayout());		
		buildCenterPanel();
		panelMain.add("Center",panelCenter);
		
		buildSouthPanel();
		panelMain.add("South",main);
		
		setVisible(true);
	}
	
	/**
	 * This method builds tab pane
	 */
	public void buildTabPane()
	{
		//tabPane.add("First Panel", panelView);
		tabPane.add("Operation Panel", panelMain);	
		add(tabPane);
	}
	
	
/**
 * This method inits the view
 */
	public static void Init() 
	{		
		// TODO Auto-generated method stub	
		int i;
		
		//MyUI ui = new MyUI();
		for(i=0;i<MaxSensor;i++)
		{
			//SensorFlag[i] = new JLabel("Status"+Integer.toString(i));
			SensorFlag[i] = new JTextField("    SENSOR   "+Integer.toString(i)+"    ");
			SensorArm[i] = new JButton("ARM("+Integer.toString(i)+")");
			SensorDisarm[i] = new JButton("DISARM("+Integer.toString(i)+")");
		}		
	}
	
	/**
	 * This method adds a listener
	 * @param armListener
	 */
	void armListener(ActionListener armListener){
		ArmJButton.addActionListener(armListener);
	}
	
	/**
	 * This method adds a listener
	 * @param disarmListener
	 */
	void disarmListener(ActionListener disarmListener){
		DisarmJButton.addActionListener(disarmListener);
	}
	
	/**
	 * This method adds listeners for buttons
	 */
	public void addListeners() 
	{
		btnNorth1.setActionCommand("btnNorth1");
		btnNorth1.addActionListener(new btnListener());
		btnNorth2.setActionCommand("btnNorth2");
		btnNorth2.addActionListener(new btnListener());
		
		SensorArm[0].setActionCommand("SensorArm0");
		SensorArm[0].addActionListener(new btnListener());
		SensorDisarm[0].setActionCommand("SensorDisarm0");
		SensorDisarm[0].addActionListener(new btnListener());
	}
	
	/**
	 * This method adds listenser for sensor
	 * @param i
	 */
	private void addSensorListeners(int i)
	{
		String strarm = "SensorArm" + Integer.toString(i);
		SensorArm[i].setActionCommand(strarm);
		SensorArm[i].addActionListener(new btnListener());
		
		String strdisarm = "SensorDisarm" + Integer.toString(i);
		SensorDisarm[i].setActionCommand(strdisarm);
		SensorDisarm[i].addActionListener(new btnListener());
	}
	
	/**
	 * This method removes listenser for sensor
	 * @param i
	 */
	private void removeSensorListeners(int i)
	{
		String s = "SensorArm" + Integer.toString(i);
		SensorArm[i].setActionCommand(s);
		SensorArm[i].removeActionListener(new btnListener());
		
		String strdisarm = "SensorDisarm" + Integer.toString(i);
		SensorDisarm[i].setActionCommand(strdisarm);
		SensorDisarm[i].removeActionListener(new btnListener());
	}
	
	
	Object[] row = new Object[4];
	/**
	 * This class implements button listeners
	 * @author Joshua 
	 *
	 */
	public class btnListener implements ActionListener
	{
		int i;
		int id;
		String strAuthor;
		int pubYear;
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			try
			{
				if(e.getActionCommand().equals("btnNorth1"))
				{
					
					for(i=1;i<MaxSensor;i++)
					{
						if( (SensorList[i]==SLOTIDEL) )
						{
							SensorList[i] = SLOTUSED;
							buildCenterPanel();
							addSensorListeners(i);							
							break;
						}
					}
					if(i==MaxSensor)
						JOptionPane.showMessageDialog(null,"Archive the maximum sensors");
					
					setVisible(true);
										
				}
				else if(e.getActionCommand().equals("btnNorth2"))
				{
					
					for(i=MaxSensor-1;i>0;i--)
					{
						if(SensorList[i]==SLOTUSED)
						{
							SensorList[i] = SLOTREMOVED;
							removeSensorListeners(i);
							removefromCenterPanel();
							setVisible(true);
							break;
						}
					}
					if(i==0)
						JOptionPane.showMessageDialog(null,"You can't remove sensor 0");
					
					
										
				}
				else if(e.getActionCommand().equals("SensorArm0"))
				{		
					//JOptionPane.showMessageDialog(null,"SensorArm 0 CLICK!");
					SensorFlag[0].setBackground(Color.green);
				}
				else if(e.getActionCommand().equals("SensorArm1"))
				{		
					//JOptionPane.showMessageDialog(null,"SensorArm 1 CLICK!");
					SensorFlag[1].setBackground(Color.green);
				}
				else if(e.getActionCommand().equals("SensorArm2"))
				{		
					//JOptionPane.showMessageDialog(null,"SensorArm 2 CLICK!");
					SensorFlag[2].setBackground(Color.green);
				}
				else if(e.getActionCommand().equals("SensorArm3"))
				{		
					//JOptionPane.showMessageDialog(null,"SensorArm 3 CLICK!");
					SensorFlag[3].setBackground(Color.green);
				}
				else if(e.getActionCommand().equals("SensorArm4"))
				{		
					//JOptionPane.showMessageDialog(null,"SensorArm 4 CLICK!");
					SensorFlag[4].setBackground(Color.green);
				}
				else if(e.getActionCommand().equals("SensorArm5"))
				{		
					//JOptionPane.showMessageDialog(null,"SensorArm 5 CLICK!");
					SensorFlag[5].setBackground(Color.green);
				}
				else if(e.getActionCommand().equals("SensorArm6"))
				{		
					//JOptionPane.showMessageDialog(null,"SensorArm 6 CLICK!");
					SensorFlag[6].setBackground(Color.green);
				}
				else if(e.getActionCommand().equals("SensorArm7"))
				{		
					//JOptionPane.showMessageDialog(null,"SensorArm 7 CLICK!");
					SensorFlag[7].setBackground(Color.green);
				}
				
				else if(e.getActionCommand().equals("SensorDisarm0"))
				{		
					//JOptionPane.showMessageDialog(null,"SensorArm 7 CLICK!");
					SensorFlag[0].setBackground(Color.red);
				}
				else if(e.getActionCommand().equals("SensorDisarm1"))
				{		
					//JOptionPane.showMessageDialog(null,"SensorArm 7 CLICK!");
					SensorFlag[1].setBackground(Color.red);
				}
				else if(e.getActionCommand().equals("SensorDisarm2"))
				{		
					//JOptionPane.showMessageDialog(null,"SensorArm 7 CLICK!");
					SensorFlag[2].setBackground(Color.red);
				}
				else if(e.getActionCommand().equals("SensorDisarm3"))
				{		
					//JOptionPane.showMessageDialog(null,"SensorArm 7 CLICK!");
					SensorFlag[3].setBackground(Color.red);
				}
				else if(e.getActionCommand().equals("SensorDisarm4"))
				{		
					//JOptionPane.showMessageDialog(null,"SensorArm 7 CLICK!");
					SensorFlag[4].setBackground(Color.red);
				}
				else if(e.getActionCommand().equals("SensorDisarm5"))
				{		
					//JOptionPane.showMessageDialog(null,"SensorArm 7 CLICK!");
					SensorFlag[5].setBackground(Color.red);
				}
				else if(e.getActionCommand().equals("SensorDisarm6"))
				{		
					//JOptionPane.showMessageDialog(null,"SensorArm 7 CLICK!");
					SensorFlag[6].setBackground(Color.red);
				}
				else if(e.getActionCommand().equals("SensorDisarm7"))
				{		
					//JOptionPane.showMessageDialog(null,"SensorArm 7 CLICK!");
					SensorFlag[7].setBackground(Color.red);
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}			
		}		
	}

}