//import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SonarPingView extends JFrame {
	private JPanel panel1;
	private JPanel panel2;
	JPanel colorPanel;
	private JPanel main;
	JButton ArmJButton;
	JButton DisarmJButton;

	public SonarPingView(){

		//setLayout( new BorderLayout());
		panel1 = new JPanel(new GridLayout(1,1));
		panel2 = new JPanel(new GridLayout(1,1));
		colorPanel = new JPanel();
		main = new JPanel();
		main.setLayout(new GridLayout(2,2));


		ArmJButton = new JButton("Arm"); 
		panel1.add(ArmJButton);

		DisarmJButton = new JButton("Disarm"); 
		panel2.add(DisarmJButton);

		//colorPanel.setBackground(Color.RED);
		
		main.add(panel1);
		main.add(panel2);
		main.add(colorPanel);
		main.add(colorPanel);

		//ArmJButton.setPreferredSize(new Dimension(200, 200));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 200);
		this.setResizable(false);
		this.add(main);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible( true );
	}

	
	void armListener(ActionListener armListener){
		ArmJButton.addActionListener(armListener);
	}
	
	void disarmListener(ActionListener disarmListener){
		DisarmJButton.addActionListener(disarmListener);
	}
}
