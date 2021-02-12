package it.univpm.ProgettoOOP.database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.ProgettoOOP.exception.DatabaseNotValid;
import it.univpm.ProgettoOOP.utils.APIConnection;

public class Database {
	private JSONArray database;
	
	public Database() {
		APIConnection data=new APIConnection();
		data.openConnection();
		this.database=data.getData();
		Database.setDatabaseInFile(data.getData());
	}
	public Database(JSONArray datab) throws DatabaseNotValid{
		this();
		boolean correct=true;
		Iterator i=datab.iterator();
		while(i.hasNext()) {
			JSONObject temp=(JSONObject) i.next();
			if(temp.get("i")==null)
				correct=false;
		}
		if(correct) {
		this.database=datab;
		Database.setDatabaseInFile(datab);}
		else
			throw new DatabaseNotValid(); 
		
	}
	public JSONArray getDatabase() {
		return database;
	}
	public static JSONArray getDatabaseFromFile()  {
		JSONArray output=new JSONArray();
		JSONParser parser=new JSONParser();
		try {
			output=(JSONArray) parser.parse("database.json");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
		
	}
	private static void setDatabaseInFile(JSONArray data) {
		try {
			BufferedWriter writer=new BufferedWriter(new FileWriter("database.java"));
			writer.write(data.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
