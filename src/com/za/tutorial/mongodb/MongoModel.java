package com.za.tutorial.mongodb;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class MongoModel 
{
	/*private int calculationValue;
	
	public void addTwoNumbers(int firstNumber, int secondNumber)
	{
		calculationValue = firstNumber + secondNumber;
	}
	
	public int getCalculationValue()
	{
		return calculationValue;
	}*/
	private DB dB;
	private DBCollection dBCollection;
	private BasicDBObject basicDBObject;
	
	public MongoModel()
	{
		dB = (new MongoClient("localhost", 27017)).getDB("SonarPingDatabase");
		dBCollection = dB.getCollection("UserDB");
		
		basicDBObject = new BasicDBObject();
		//basicDBObject.put("Firstname","Danny");
		//basicDBObject.put("Lastname","Wang");
		//basicDBObject.put("Email","Dannywang0619@gmail.com");
		//basicDBObject.put("Password","123456");
		
		//dBCollection.insert(basicDBObject);
	}	
	
	public void insertMongoDB(String key, String value)	
	{
		basicDBObject.put(key, value);	
		dBCollection.insert(basicDBObject);		
	}
	
	public void deleteMongoDB(String key, String value)
	{
		basicDBObject.put(key, value);
		dBCollection.remove(basicDBObject);
	}
	
	public void updateMongoDB(String key, String value)
	{
	
	}
	
	public String selectMongoDB(String key, String value)
	{
		String str = "";
		
		basicDBObject.put(key, value);
		DBCursor dbCursor = dBCollection.find(basicDBObject);		
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
		
		dBCollection.remove(new BasicDBObject());
		return i;
	}

}
