package it.univpm.ProgettoOOP.database;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.exception.DatabaseNotValid;
import it.univpm.ProgettoOOP.utils.APIConnection;

public class Database {
	private JSONArray database;
	public JSONArray getDatabase() {
		return database;
	}
	public Database() {
		APIConnection data=new APIConnection();
		data.openConnection();
		this.database=data.getData();
		
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
		if(correct)
		this.database=datab;
		else
			throw new DatabaseNotValid(); 
		
	}
	

}
