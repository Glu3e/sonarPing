
import java.net.UnknownHostException;
import javax.swing.JOptionPane;
import com.mongodb.*;



public class MongoModel 
{
	private static DB dB;
	private static DBCollection dBCollection;
	private static BasicDBObject basicDBObject;
	
	public MongoModel()
	{
		try {
			this.dB = (new MongoClient("localhost", 27017)).getDB("SonarPingDatabase");
			
			initialize();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	public void initialize(){
		this.dBCollection = dB.getCollection("UserDB");
		this.basicDBObject = new BasicDBObject();
	}
	
	public void detached(){
		this.dBCollection = null;
		this.basicDBObject = null;
	}
	
	
	public void insertMongoDB(String first, String last, String email, String pass, String type)	
	{	
		try{
			initialize(); 
			this.basicDBObject.append("First Name",first);
			this.basicDBObject.append("Last Name",last);
			this.basicDBObject.append("Email",email);
			this.basicDBObject.append("Password",pass);
			this.basicDBObject.append("Type",type);
			this.dBCollection.insert(new DBObject[] {basicDBObject});
			detached();
			JOptionPane.showMessageDialog(null, "Record Inserted Successfully");
		}
		
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
	
	}
	
	public void deleteMongoDB(String identifier)
	{
		try{
			initialize();
			this.basicDBObject.put("First Name", identifier);
			this.dBCollection.remove(basicDBObject);
			JOptionPane.showMessageDialog(null, "Record Deleted Successfully");
			detached();
		}
		
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	public void updateMongoDB(String first, String last, String email, String pass, String type)
	{
		try{
			initialize();
			this.basicDBObject.put("First Name", first);
			this.dBCollection.remove(basicDBObject);
			detached();
			
			initialize();
			this.basicDBObject = new BasicDBObject(); 
			
			this.basicDBObject.append("First Name",first);
			this.basicDBObject.append("Last Name",last);
			this.basicDBObject.append("Email",email);
			this.basicDBObject.append("Password",pass);
			this.basicDBObject.append("Type",type);
			
			this.dBCollection.insert(new DBObject[] {basicDBObject});
			
			JOptionPane.showMessageDialog(null, "Record Updated Successfully");
			
			detached();
		}
		
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	public String selectMongoDB(String key, String value)
	{
		String str = "";
		
		initialize();
		
		this.basicDBObject.put(key, value);
		DBCursor dbCursor = this.dBCollection.find(basicDBObject);
		
		while (dbCursor.hasNext()){ 
			str = str + " | " + dbCursor.next();
		}
		
		dbCursor.close();
		detached();
		
		
		return str;
	}
	
	public String browseMongoDB()
	{
		initialize();
		String str = "";
		DBCursor dbCursor = dBCollection.find();
		while (dbCursor.hasNext()){ 
			str = str + "\n" + dbCursor.next();
		}
		
		dbCursor.close();
		detached();
		
		return str;
	}
	
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
		
		
		dbCursor.close();
		
		return finalEmailString.substring(0, finalEmailString.length()-1);
	}
	
	
	
	public int removeallMongo()
	{
		initialize();
		int i=0;
		
		this.dBCollection.remove(new BasicDBObject());
		detached();
		
		return i;
	
	}

}
