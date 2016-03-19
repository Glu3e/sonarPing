
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
	private String type;
	
	public MongoControl(MongoView theView, MongoModel theModel)
	{
		this.theView = theView;
		this.theModel = theModel;
		
		this.theView.addMongoButtonListener(new MongoButtonListener());		
		this.theView.addCreateButtonListener(new MongoCreateButtonListener());
		this.theView.addDeleteButtonListener(new MongoDeleteButtonListener());
	}
	
	public boolean validateFields(){
		
		firstName = theView.getFirstName();
		lastName = theView.getLastName();
		password = theView.getPassword();
		email = theView.getEmail();
		type = theView.getUserType();
		
		if(firstName.equals("")){
			JOptionPane.showMessageDialog(null, "Enter A First Name!");
			return false;
		}
		
		if(lastName.equals("")){
			JOptionPane.showMessageDialog(null, "Enter A Last Name!");
			return false;
		}
		
		if(password.equals("")){
			JOptionPane.showMessageDialog(null, "Enter A Password!");
			return false;
		}
		
		if(email.equals("")){
			JOptionPane.showMessageDialog(null, "Enter A Email!");
			return false;
		}
		
		if(type.equals("") || !type.equals("DEPENDENT")){
			JOptionPane.showMessageDialog(null, "Enter A Status!");
			return false;
		}
		
		return true;
		
	}
	
	
	public void clearFields(){
		theView.firstName.setText("");
		theView.lastName.setText("");
		theView.password.setText("");
		theView.email.setText("");
		theView.type.setText("");
	}
	
	class MongoButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String str;
			
			String key, value;
			
			firstName = theView.getFirstName();
			lastName = theView.getLastName();
			password = theView.getPassword();
			email = theView.getEmail();
			type = theView.getUserType();
						
			try
			{
				if(e.getActionCommand().equals("deleteButton") && !firstName.equals(""))
				{
					key = theView.getFirstName();
					theModel.deleteMongoDB(key);
					return;
				}
				
				if(e.getActionCommand().equals("browseButton") && !firstName.equals(""))
				{
					str = theModel.browseMongoDB();
					JOptionPane.showMessageDialog(null,str);
					return;
				}
				
				
				if(validateFields()){
					if(e.getActionCommand().equals("CreateButton"))
					{
						theModel.insertMongoDB(firstName,lastName,email,password,type);
					}
					else if(e.getActionCommand().equals("updateButton"))
					{
						theModel.updateMongoDB(firstName, lastName, email, password,type);				
					}				
				}
				
				
				
				clearFields();
				return;
			}
			catch(Exception ex)
			{
				theView.displayErrorMessage("Mongo DB operation fail.");;
			}
		}
	}
	
	
	class MongoCreateButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			String str;
			String key, value;
			
			firstName = theView.getFirstName();
			lastName = theView.getLastName();
			password = theView.getPassword();
			email = theView.getEmail();
			type = theView.getUserType();
			
			if(validateFields()){
				if(e.getSource() == theView.createButton){
					theModel.insertMongoDB(firstName,lastName,email,password,type);
				}
			}
			
			clearFields();
			
		}
		
	}
	
	class MongoDeleteButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			String str;
			
			String key, value;
			
			firstName = theView.getFirstName();
			lastName = theView.getLastName();
			password = theView.getPassword();
			email = theView.getEmail();
			type = theView.getUserType();
			
			
			if(e.getSource() == theView.deleteButton){
				key = theView.getFirstName();
				theModel.deleteMongoDB(key);
				return;
				
			}
			
			clearFields();
			return;
		}
		
	}
	
	class MongoUpdateButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	class MongoBrowseButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
