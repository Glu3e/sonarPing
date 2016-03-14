
import java.net.UnknownHostException;

import com.mongodb.*;

public class MongoModel 
{
	private DB dB;
	private DBCollection dBCollection;
	private BasicDBObject basicDBObject;
	
	public MongoModel()
	{
		try {
			this.dB = (new MongoClient("localhost", 27017)).getDB("SonarPingDatabase");
			
			this.dBCollection = dB.getCollection("UserDB");
			this.basicDBObject = new BasicDBObject();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	public void insertMongoDB(String first, String last, String email, String pass)	
	{	
		this.basicDBObject.append("First Name",first);
		this.basicDBObject.append("Last Name",last);
		this.basicDBObject.append("Email",email);
		this.basicDBObject.append("Password",pass);
		//this.basicDBObject.append(key, value);
		this.dBCollection.insert(new DBObject[] {basicDBObject});		
	}
	
	public void deleteMongoDB(String identifier)
	{
		this.basicDBObject.put("First Name", identifier);
		this.dBCollection.remove(basicDBObject);
	}
	
	public void updateMongoDB(String key, String value)
	{
	
	}
	
	public String selectMongoDB(String key, String value)
	{
		String str = "";
		
		this.basicDBObject.put(key, value);
		DBCursor dbCursor = this.dBCollection.find(basicDBObject);		
		while (dbCursor.hasNext()) 
			//System.out.println(dbCursor.next());
			str = str + " | " + dbCursor.next();
		
		return str;
	}
	
	public String browseMongoDB()
	{
		String str = "";
		DBCursor dbCursor = dBCollection.find();
		while (dbCursor.hasNext()) 
			//System.out.println(dbCursor.next());
			str = str + " | " + dbCursor.next();	
		return str;
	}
	
	public int removeallMongo()
	{
		int i=0;
		
		this.dBCollection.remove(new BasicDBObject());
		return i;
	}

}
