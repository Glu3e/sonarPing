
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MongoControl {
	private MongoView theView;
	private MongoModel theModel;
	
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	
	public MongoControl(MongoView theView, MongoModel theModel)
	{
		this.theView = theView;
		this.theModel = theModel;
		
		this.theView.addMongoButtonListener(new MongoButtonListener());		
	}
	
	public void clearFields(){
		theView.firstName.setText("");
		theView.lastName.setText("");
		theView.password.setText("");
		theView.email.setText("");
	}
	
	class MongoButtonListener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e) 
		{
			String str;
			int rt;
			String key, value;
			
			firstName = theView.getFirstName();
			lastName = theView.getLastName();
			password = theView.getPassword();
			email = theView.getEmail();
			
						
			try
			{
				if(e.getActionCommand().equals("CreateButton"))
				{
					theModel.insertMongoDB(firstName,lastName,email,password);
				}
				else if(e.getActionCommand().equals("deleteButton"))
				{
					key = theView.getFirstName();
					theModel.deleteMongoDB(key);
				}
				else if(e.getActionCommand().equals("updateButton"))
				{
					theModel.updateMongoDB(firstName, lastName, email, password);				
				}
				else if(e.getActionCommand().equals("browseButton"))
				{
					str = theModel.browseMongoDB();
					JOptionPane.showMessageDialog(null,str);
				}	
				else if(e.getActionCommand().equals("removeallButton"))
				{
					rt = theModel.removeallMongo();
					JOptionPane.showMessageDialog(null,"Remove all the data in MongoDB");	
				}				
				
				
				clearFields();
			}
			catch(Exception ex)
			{
				theView.displayErrorMessage("Mongo DB operation fail.");;
			}
		}
	}
}
