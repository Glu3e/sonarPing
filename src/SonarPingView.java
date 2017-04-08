//import java.awt.BorderLayout;
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
import javax.swing.plaf.ColorUIResource;

//import java.awt.GridBagLayout;
import java.awt.*;
import java.awt.Window.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import com.sun.javafx.scene.control.skin.ColorPalette;
import java.awt.Color;



/**
 * This class deals with the user GUI
 * @author Joshua 
 * @version 2.0
 * @since 2016-01-30
 */
public class SonarPingView extends JFrame implements KeyListener
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
	private JPanel mongoViewPanel;	
	
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
	public static String [] sensorList = new String[MaxSensor];
	//MongoView mongoMVC = new MongoView();

	/*Color color1 = new Color(58, 61, 126);
	Color color2 = new Color(30, 33, 95);
	Color color3 = new Color(12, 13, 67);
	*/Color color4 = new Color(54, 56, 92);
	Color color5 = new Color(1, 2, 20);
	Color color6 = new Color(4, 6, 42, 120);
	Color color7 = new Color(160, 162, 180);
	
	Color color1 = new Color(39, 47, 81, 217);
	Color color2 = new Color(30, 37, 72, 180);
	Color color3 = new Color(22, 28, 57,130);

	
	/**
	 * This constructs a SonarPingView
	 */
	public SonarPingView()
	{
		Init();
		buildMainPanel();
		buildMongoPanel();
		buildTabPane("Operation Panel", panelMain);
		buildTabPane("MVC Panel", mongoViewPanel);
		if(MyLoginDialog.homeowner)
		{
			//JOptionPane.showMessageDialog(null,"homeowner is true.");
			buildMenu();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640,680);
		setVisible(true);
		getContentPane().setBackground(color4);
		addKeyListener(this);
		setFocusable(true);
	    setFocusTraversalKeysEnabled(false);
		addListeners();
	}
	/**
	 * This method builds a menu
	 */
	public void buildMenu()
	{
		configureItem = new JMenuItem("Configure");
		configureItem.setToolTipText("Configure Data");
//		configureItem.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//				MongoMVC mongo = new MongoMVC();
//				mongo.main(null);
//			}
//		});
		
		menuDecleration = new JMenu("File");		
		menuDecleration.add(configureItem);
		menuBar.add(menuDecleration);
		setJMenuBar(menuBar);
		
		// Add an Quit JMenuItem
		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.setMnemonic(KeyEvent.VK_Q);
		mntmClose.setAccelerator(KeyStroke.getKeyStroke(
		             KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		mntmClose.setToolTipText("Exit application");
		mntmClose.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent event) {
		            System.exit(0);
		        }

		    });
		menuDecleration.add(mntmClose);
	}
	
	/**
	 * This method builds north panel in the view
	 */
	public void buildNorthPanel()
	{
		/*btnNorth1 = new JButton("Simulate adding a new sensor");
		panelNorth.add(btnNorth1);
		btnNorth2 = new JButton("Simulate removing a existed sensor");
		panelNorth.add(btnNorth2);*/
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
		//armPanel.setBackground(color6);
		
		disarmPanel = new JPanel(new GridLayout(1,1));
		
		colorPanel = new JPanel();
		colorPanel.setBackground(color7);
		menuBar = new JMenuBar();
		//menuBar.setBackground(color1);
		//menuBar.setForeground(color1);

		
		main = new JPanel();
		main.setLayout(new GridLayout(1,3));
		//main.setBackground(color1);
		//main.setForeground(color4);

		//Icon i=new ImageIcon("button.png");
		
		ArmJButton = new JButton("Arm");
		//ArmJButton.setIcon(i);
		//ArmJButton.setSelectedIcon(i);
		ArmJButton.setBackground(color6);
		ArmJButton.setForeground(Color.lightGray);

		armPanel.add(ArmJButton);

		DisarmJButton = new JButton("Disarm");
		DisarmJButton.setBackground(color6);
		DisarmJButton.setForeground(Color.lightGray);
		disarmPanel.add(DisarmJButton);
		
		main.add(colorPanel);
		main.add(armPanel);
		main.add(disarmPanel);

	}

	/**
	 * This method build main panel
	 */
	public void buildMainPanel()
	{			
		panelMain = new JPanel();
		panelMain.setSize(680,480);
		panelMain.setLayout(new BorderLayout());
		//panelMain.setBackground(Color.black);
		//panelMain.setForeground(Color.black);
		panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout());
		panelNorth.setBackground(color1);
		buildNorthPanel();
		panelMain.add("South",panelNorth);
		
		panelCenter = new JPanel();
		panelCenter.setLayout(new GridBagLayout());
		panelCenter.setBackground(color1);
		buildCenterPanel();
		panelMain.add("Center",panelCenter);
		
		buildSouthPanel();
		panelMain.add("North",main);
		

		panelMain.setFocusable(true);
		panelMain.requestFocusInWindow();
		
		setVisible(true);
	}
	
	/**
	 * This method builds panel for MongoMVC
	 */
	public void buildMongoPanel()
	{
		
		MongoView mongoMVC = new MongoView();
//		MongoView mongoMVC2 = new MongoView();

		//String[] args = new String[] {"123"};
		//MongoMVC.main(args);
		//mongoMVC.getContentPane().setBackground(color4);

		mongoViewPanel = new JPanel();
		mongoViewPanel.setBackground(color1);
		this.setSize(600,680);
		this.setResizable(false);
		this.setTitle("Users Information Management");
		//this.getContentPane().setBackground(color4);

		mongoViewPanel.setLayout(new BorderLayout());
		this.add(mongoViewPanel);
		mongoMVC.getContentPane().setBackground(color4);

		mongoMVC.buildViewPanel();
		mongoMVC.getContentPane().setBackground(color4);
		//mongoMVC.panelView.setBackground(color1);
		mongoViewPanel.add("North",mongoMVC.panelView);
		
		panelCenter = new JPanel();
		panelCenter.setBackground(color1);
		//panelCenter.setForeground(Color.lightGray);
		panelCenter.setLayout(new GridBagLayout());		
		mongoMVC.buildMongoGBC(panelCenter);
		setVisible(true);
		mongoViewPanel.add("Center",panelCenter);
		
		MongoModel theModel = new MongoModel();
		MongoControl theControl = new MongoControl(mongoMVC, theModel);
	}
	
	/**
	 * This method builds tab pane
	 */
	public void buildTabPane(String tabName, JPanel panel)
	{
		//tabPane.add("First Panel", panelView);
		tabPane.setBackground(color7);
		tabPane.setForeground(color2);
		tabPane.add(tabName, panel);	
		add(tabPane);
	}
	
	
/**
 * This method inits the view
 */
	public static void Init() 
	{		
		// TODO Auto-generated method stub	
		int i;
		Color color6 = new Color(4, 6, 42, 120);

		//MyUI ui = new MyUI();
		for(i=0;i<MaxSensor;i++)
		{
			//SensorFlag[i] = new JLabel("Status"+Integer.toString(i));
			SensorFlag[i] = new JTextField("    SENSOR   "+Integer.toString(i)+"    ");
			SensorArm[i] = new JButton("ARM("+Integer.toString(i)+")");
			SensorArm[i].setBackground(color6);
			SensorArm[i].setForeground(Color.lightGray);
			SensorDisarm[i] = new JButton("DISARM("+Integer.toString(i)+")");
			SensorDisarm[i].setBackground(color6);
			SensorDisarm[i].setForeground(Color.lightGray);
			sensorList[i]="";
		}
		sensorList[0] = "Sensor " + Integer.toString(0);
		
		String str;
		int k;
		java.util.Date date = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		str = "Current sensolList situation at " + formattedDate +"\r\n";
		System.out.print(str);
		for (k = 0; k < MaxSensor; k++)
		{
			str = "["+Integer.toString(k)+"]="+sensorList[k]+"; ";
			System.out.print(str);
		}
		System.out.print("\r\n");
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
			
		/*btnNorth1.setActionCommand("btnNorth1");
		btnNorth1.addActionListener(new btnListener());
		btnNorth2.setActionCommand("btnNorth2");
		btnNorth2.addActionListener(new btnListener());*/
		
		
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
		
		/*String str;
		int k;
		java.util.Date date = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		str = "Current sensolList situation at " + formattedDate +"\r\n";
		System.out.print(str);
		
		
		for (k=0;k<MaxSensor;k++)
		{
			str = "["+Integer.toString(k)+"]="+sensorList[k]+"; ";
			System.out.print(str);
		}
		System.out.print("\r\n\r\n");*/
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
		//System.out.print("SENSOR DELETED");
		sensorList[i] = "";
		
		String strdisarm = "SensorDisarm" + Integer.toString(i);
		SensorDisarm[i].setActionCommand(strdisarm);
		SensorDisarm[i].removeActionListener(new btnListener());
		
		/*String str;
		
		for (int k=0;k<MaxSensor;k++)
		{
			str = sensorList[k] + "  ";
			System.out.print(str);
		}
		System.out.println();*/
	}
	
	private void AddToArrayList(int i)
	{
		String str;
		int k;
		
		sensorList[i] = "Sensor " + Integer.toString(i);
		
		java.util.Date date = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		str = "Current sensolList situation at " + formattedDate +"\r\n";
		System.out.print(str);
		
		
		for (k=0;k<MaxSensor;k++)
		{
			str = "["+Integer.toString(k)+"]="+sensorList[k]+"; ";
			System.out.print(str);
		}
		System.out.print("\r\n\r\n");
	}
	
	private void RemoveFromArrayList(int i)
	{
		String str;
		sensorList[i] = "";
		
		java.util.Date date = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		str = "Current sensolList situation at " + formattedDate +"\r\n";
		System.out.print(str);
		
		for (int k=0;k<MaxSensor;k++)
		{
			str = "["+Integer.toString(k)+"]="+sensorList[k]+"; ";
			System.out.print(str);
		}
		System.out.print("\r\n\r\n");
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
					AddToArrayList(0);
					SensorFlag[0].setBackground(Color.red);
					SensorArm[0].setFocusable(false);
				}
				else if(e.getActionCommand().equals("SensorArm1"))
				{		
					AddToArrayList(1);
					SensorFlag[1].setBackground(Color.red);
					SensorArm[1].setFocusable(false);
				}
				else if(e.getActionCommand().equals("SensorArm2"))
				{		
					AddToArrayList(2);
					SensorFlag[2].setBackground(Color.red);
				}
				else if(e.getActionCommand().equals("SensorArm3"))
				{		
					AddToArrayList(3);
					SensorFlag[3].setBackground(Color.red);
				}
				else if(e.getActionCommand().equals("SensorArm4"))
				{		
					AddToArrayList(4);
					SensorFlag[4].setBackground(Color.red);
				}
				else if(e.getActionCommand().equals("SensorArm5"))
				{		
					AddToArrayList(5);
					SensorFlag[5].setBackground(Color.red);
				}
				else if(e.getActionCommand().equals("SensorArm6"))
				{		
					AddToArrayList(6);
					SensorFlag[6].setBackground(Color.red);
				}
				else if(e.getActionCommand().equals("SensorArm7"))
				{		
					AddToArrayList(7);
					SensorFlag[7].setBackground(Color.red);
				}
				
				else if(e.getActionCommand().equals("SensorDisarm0"))
				{	
					RemoveFromArrayList(0);
					SensorFlag[0].setBackground(Color.green);
				}
				else if(e.getActionCommand().equals("SensorDisarm1"))
				{	
					RemoveFromArrayList(1);
					SensorFlag[1].setBackground(Color.green);
				}
				else if(e.getActionCommand().equals("SensorDisarm2"))
				{	
					RemoveFromArrayList(2);
					SensorFlag[2].setBackground(Color.green);
				}
				else if(e.getActionCommand().equals("SensorDisarm3"))
				{	
					RemoveFromArrayList(3);
					SensorFlag[3].setBackground(Color.green);
				}
				else if(e.getActionCommand().equals("SensorDisarm4"))
				{	
					RemoveFromArrayList(4);
					SensorFlag[4].setBackground(Color.green);
				}
				else if(e.getActionCommand().equals("SensorDisarm5"))
				{	
					RemoveFromArrayList(5);
					SensorFlag[5].setBackground(Color.green);
				}
				else if(e.getActionCommand().equals("SensorDisarm6"))
				{	
					RemoveFromArrayList(6);
					SensorFlag[6].setBackground(Color.green);
				}
				else if(e.getActionCommand().equals("SensorDisarm7"))
				{	
					RemoveFromArrayList(7);
					SensorFlag[7].setBackground(Color.green);
				}
				
				panelMain.setFocusable(true);
				panelMain.requestFocusInWindow();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}			
		}		
	}
	
    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    	int i;
    	
    	if( (e.getKeyCode()== KeyEvent.VK_A) || (e.getKeyCode()== KeyEvent.VK_INSERT) )
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
        if( (e.getKeyCode()== KeyEvent.VK_D) || (e.getKeyCode()== KeyEvent.VK_DELETE) )
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
    }
    public void keyTyped(KeyEvent e) {
        //System.out.println("keyTyped");
    }

}