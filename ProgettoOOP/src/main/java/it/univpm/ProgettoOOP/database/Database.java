package it.univpm.ProgettoOOP.database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.ProgettoOOP.exception.DatabaseNotValid;
import it.univpm.ProgettoOOP.utils.APIConnection;

/**
 * @author Paolo, Maurizio
 *
 *Classe che gestisce il database sia nel contatto con l'API che con il file di backup
 */
public class Database {
	
	/**
	 * Costruttore della classe in assenza di parametri 
	 */
	public Database() {
		APIConnection data=new APIConnection();
		data.openConnection();
		Database.setDatabaseInFile(data.getData());
	}
	/**
	 * Costruttore con database da verificare
	 * @param datab JSONArray contenente il database impostato dall'utente
	 * @throws DatabaseNotValid Errore restistuito nel caso in cui il database non sia corretto, viene dato questo errore in output
	 */
	public Database(JSONArray datab) throws DatabaseNotValid{
		this();
		boolean correct=true;
		Iterator i=datab.iterator();
		while(i.hasNext()) {
			JSONObject temp=(JSONObject) i.next();
			//Controllo della struttura e della presenza delle informazioni essenziali
			if(temp.get("name")==null || temp.get("info")!=null || temp.get("dates")==null || temp.get("classification")==null ||temp.get("_embedded")==null)
				correct=false;
			else {
				JSONObject dates=(JSONObject)temp.get("dates");
				JSONArray classifications=(JSONArray) temp.get("classifications");
				JSONObject embedded=(JSONObject) temp.get("_embedded");
				JSONArray venues=(JSONArray) embedded.get("venues");
				JSONObject firstEl=(JSONObject) venues.get(0);
				JSONObject state=(JSONObject) firstEl.get("state");
				if(dates.get("start")==null || dates.get("end")==null || classifications.get(0)==null || state.get("stateCode")==null)
					correct=false;
				else {
					firstEl=(JSONObject) classifications.get(0);
					JSONObject genre=(JSONObject) firstEl.get("genre");
					if(genre.get("name")==null)
						correct=false;
				}
				
			}
		}
		if(correct) {
		Database.setDatabaseInFile(datab);}
		else
			{Database data=new Database();
			throw new DatabaseNotValid(); }
		
	}
	/**
	 * Metodo che importa il database dal file
	 * @return JSONArray contenente il database usabile per statistiche e filtri
	 */
	public static JSONArray getDatabaseFromFile()  {
		JSONArray output=new JSONArray();
		JSONParser parser=new JSONParser();
		try {
		    output =(JSONArray) parser.parse("database.json");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
		
	}
	/**
	 * Classe che scrive il database generato nel file
	 * @param data JSONArray contenente il database da salvare in file
	 */
	private static void setDatabaseInFile(JSONArray data) {
		try {
			BufferedWriter writer=new BufferedWriter(new FileWriter("database.json"));
			writer.write(data.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
