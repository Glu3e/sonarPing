/*
 * Fix: delete and update function
 */
import java.net.UnknownHostException;


import javax.swing.JOptionPane;

import com.mongodb.*;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
 * This class deals with the all the requests related with Mongo Database
 * @author Zhaoduan, Akanimoh, Keitha
 * @vision v2.0
 * @since 2016-03-10
 */

public class MongoModel 
{
	private DB dB;
	private DBCollection dBCollection;
	//private BasicDBObject basicDBObject;
	
	/**
	 * This constructs MongoModel
	 */
	public MongoModel()
	{
		try {
			this.dB = (new MongoClient("localhost", 27017)).getDB("SonarPingDatabase");
			
			this.dBCollection = dB.getCollection("UserDB");
			//this.basicDBObject = new BasicDBObject();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method insert a record in the Mongo Database
	 * @param first firstname
	 * @param last  lastname
	 * @param email email address
	 * @param pass  password
	 * @param type  type of the user, Home owner or dependent
	 */
	public void insertMongoDB(String first, String last, String email, String pass, String type)	
	{	
		BasicDBObject basicDBObject;
		
		try{
			basicDBObject = new BasicDBObject(); 
			basicDBObject.append("First Name",first);
			basicDBObject.append("Last Name",last);
			basicDBObject.append("Email",email);
			basicDBObject.append("Password",pass);
			basicDBObject.append("Type",type);
			this.dBCollection.insert(new DBObject[] {basicDBObject});		
		}
		
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		close();
	
	}
	
	/**
	 * This method deletes a record from Mongo Database
	 * @param identifier
	 */
	public void deleteMongoDB(String identifier)
	{
		BasicDBObject basicDBObject;
		
		try
		{	
			basicDBObject = new BasicDBObject();		
			basicDBObject.append("First Name", identifier);
			this.dBCollection.remove(basicDBObject);
		}		
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, identifier);
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		close();
	}
	
	/**
	 * This mothod updates a record which is in the Mongo Database
	 * @param first firstname
	 * @param last  lastname
	 * @param email email address
	 * @param pass  password
	 * @param type  type of user, Homeowner or dependent
	 */
	public void updateMongoDB(String first, String last, String email, String pass, String type)
	{
		BasicDBObject basicDBObject;
		try{
			basicDBObject = new BasicDBObject();
			basicDBObject.put("First Name", first);
			this.dBCollection.remove(basicDBObject);
			
			basicDBObject = null;
			basicDBObject = new BasicDBObject(); 
			
			basicDBObject.append("First Name",first);
			basicDBObject.append("Last Name",last);
			basicDBObject.append("Email",email);
			basicDBObject.append("Password",pass);
			basicDBObject.append("Type",type);
			
			this.dBCollection.insert(new DBObject[] {basicDBObject});	
		}
		
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		close();
	}
	
	/**
	 * This method inquires the Mongo Database with a key value
	 * @param key   Key for the Mongo DB
	 * @param value Value for the Mongo DB
	 * @return
	 */
	public String selectMongoDB(String key, String value)
	{
		BasicDBObject basicDBObject;
		String str = "";
		
		basicDBObject = new BasicDBObject();
		basicDBObject.put(key, value);
		DBCursor dbCursor = this.dBCollection.find(basicDBObject);		
		while (dbCursor.hasNext()) 
			str = str + " | " + dbCursor.next();
		
		
		close();
		return str;
	}
	
	
	/**
	 * This mothod browses all the user information that are stored in the Mongo Database
	 * @param theView  the view which calls the method
	 * @param theModel the model which relates the method
	 * @return
	 */
	public String browseMongoDB(MongoView theView, MongoModel theModel)
	{
		String str = "";
		String firstn, lastn, email, passwd, type/*, id*/;
		DBCursor dbCursor = dBCollection.find();
		DBObject obj;
		
		int rowCount = theView.tblmodelView.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			theView.tblmodelView.removeRow(i);
		}
		
		while (dbCursor.hasNext()) {
			//System.out.println(dbCursor.next());
			//str = str + "\n" + dbCursor.next();
			obj = dbCursor.next();
			str = str +	"\n" + obj;
			//theView.userid.setText("");
        	//theView.userid.setText(obj.get("_id").toString());
        	//JOptionPane.showMessageDialog(null, obj.get("_id").toString());
			firstn = (String)obj.get("First Name");
			lastn  = (String)obj.get("Last Name");
			email  = (String)obj.get("Email");
			passwd = (String)obj.get("Password");
			type   = (String)obj.get("Type");
			//id     = obj.get("_id").toString();
			theView.tblmodelView.addRow(new Object[] {firstn, lastn, email, passwd, type/*, id*/});			
		}
		
		close();
		return str;
	}
	
	/**
	 * This method inquires all the email information and return it as a string
	 * @return
	 */
	public String getAllEmails()
	{
		String str = "";
		String placeHolderString = "";
		String finalEmailString = "";
		
		
		BasicDBObject userTypeQuery = new BasicDBObject();
		BasicDBObject emailQuery = new BasicDBObject();
		
		
		userTypeQuery.append("Type", "DEPENDENT");
		emailQuery.append("Email","1");
		
		
		
		DBCursor dbCursor = dBCollection.find(userTypeQuery,emailQuery);
		
		while (dbCursor.hasNext()){ 
			str = ""+dbCursor.next();
			placeHolderString = (str.substring(str.indexOf(","), str.length()-1));
			finalEmailString += placeHolderString.substring(placeHolderString.indexOf(":"), placeHolderString.length()-1).substring(3) + ",";
		}
		
		
		close();
		
		return finalEmailString.substring(0, finalEmailString.length()-1);
	}
	
	/**
	 * This method removes the Mongo Database connection
	 * @return
	 */
	public int removeallMongo()
	{
		int i=0;
		
		this.dBCollection.remove(new BasicDBObject());
		return i;
	}
	
	/**
	 * This methods is a empty method.
	 */
	public void close(){
		//this.basicDBObject = null;
	}

}
