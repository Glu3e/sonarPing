
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
		
				
		this.theView.addCreateButtonListener(new MongoCreateButtonListener());
		this.theView.addDeleteButtonListener(new MongoDeleteButtonListener());
		this.theView.addUpdateButtonListener(new MongoUpdateButtonListener());
		this.theView.addBrowseButtonListener(new MongoBrowseButtonListener());
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
	
	class MongoCreateButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			
			
			firstName = theView.getFirstName();
			lastName = theView.getLastName();
			password = theView.getPassword();
			email = theView.getEmail();
			type = theView.getUserType();
			
			
			if(e.getSource() == theView.createButton &&  !firstName.equals("")){
				theModel.insertMongoDB(firstName,lastName,email,password,type);
			}else{
				JOptionPane.showMessageDialog(null, "Enter a First Name");
			}
			
			
			clearFields();
			
		}
		
	}
	
	class MongoDeleteButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			String key;
			firstName = theView.getFirstName();
			lastName = theView.getLastName();
			password = theView.getPassword();
			email = theView.getEmail();
			type = theView.getUserType();
			
			
			if(e.getSource() == theView.deleteButton && !firstName.equals("")){
				key = theView.getFirstName();
				theModel.deleteMongoDB(key);
				return;
			}else{
				JOptionPane.showMessageDialog(null, "Enter a First Name");
			}
			
			clearFields();
			return;
		}
		
	}
	
	class MongoUpdateButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			firstName = theView.getFirstName();
			lastName = theView.getLastName();
			password = theView.getPassword();
			email = theView.getEmail();
			type = theView.getUserType();
			
			if(validateFields()){
				theModel.updateMongoDB(firstName, lastName, email, password,type);
			}
			clearFields();
			return;
		}
		
	}

	class MongoBrowseButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			String str;
			
			firstName = theView.getFirstName();
			lastName = theView.getLastName();
			password = theView.getPassword();
			email = theView.getEmail();
			type = theView.getUserType();
			
			if(e.getSource() == theView.browseButton){
				str = theModel.browseMongoDB();
				JOptionPane.showMessageDialog(null,str);
			}
			
		}
		
	}
	
}
