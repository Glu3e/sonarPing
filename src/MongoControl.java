
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MongoControl {
	private MongoView theView;
	private MongoModel theModel;
	
	public MongoControl(MongoView theView, MongoModel theModel)
	{
		this.theView = theView;
		this.theModel = theModel;
		
		this.theView.addMongoButtonListener(new MongoButtonListener());		
	}
	
	class MongoButtonListener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e) 
		{
			String str;
			int rt;
			String key, value;
			
			// TODO Auto-generated method stub			
			try
			{
				if(e.getActionCommand().equals("CreateButton"))
				{
					key = theView.getFirstName()+' '+theView.getLastName();
					value = theView.getPassword()+' '+theView.getEmail();
					theModel.insertMongoDB(key, value);
					//JOptionPane.showMessageDialog(null,"Click Create Button");						
				}
				else if(e.getActionCommand().equals("deleteButton"))
				{
					key = theView.getFirstName()+' '+theView.getLastName();
					value = theView.getPassword()+' '+theView.getEmail();
					theModel.deleteMongoDB(key, value);
					//JOptionPane.showMessageDialog(null,"Click Delete Button");				
				}
				else if(e.getActionCommand().equals("updateButton"))
				{
					JOptionPane.showMessageDialog(null,"Click Update Button");				
				}
				else if(e.getActionCommand().equals("browseButton"))
				{
					str = theModel.browseMongoDB();
					//JOptionPane.showMessageDialog(null,"Click Browse Button");	
					JOptionPane.showMessageDialog(null,str);
				}	
				else if(e.getActionCommand().equals("removeallButton"))
				{
					rt = theModel.removeallMongo();
					JOptionPane.showMessageDialog(null,"Remove all the data in MongoDB");	
				}				
			}
			catch(Exception ex)
			{
				theView.displayErrorMessage("Mongo DB operation fail.");;
			}
		}
	}
}
