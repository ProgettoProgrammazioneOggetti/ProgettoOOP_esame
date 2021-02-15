/**
 * 
 */
package it.univpm.ProgettoOOP.controller;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.web.bind.annotation.*;

import it.univpm.ProgettoOOP.database.Database;
import it.univpm.ProgettoOOP.exception.DatabaseNotValid;
import it.univpm.ProgettoOOP.exception.DateNotValid;
import it.univpm.ProgettoOOP.exception.GenreNotValid;
import it.univpm.ProgettoOOP.exception.KeywordNotValid;
import it.univpm.ProgettoOOP.exception.RangeNotValid;
import it.univpm.ProgettoOOP.exception.StateNotValid;
import it.univpm.ProgettoOOP.model.EventiPerGenere;
import it.univpm.ProgettoOOP.model.EventiTotale;
import it.univpm.ProgettoOOP.model.Ricerca;
import it.univpm.ProgettoOOP.model.Statistic;
import it.univpm.ProgettoOOP.model.Stats;
/**
 * @author Maurizio
 * @author Paolo
 *
 */
class ControllerClass {
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public JSONArray cercaEvento(@RequestParam(name = "name", defaultValue= "") String name,@RequestParam(name= "keyword") String keyword) {
		JSONArray output;
		Ricerca search = new Ricerca(name, keyword);
		output = search.getOutput();
		return output;
	}
	
	@RequestMapping(value ="/events" , method=RequestMethod.POST)
	public HashMap eventiTotale(@RequestBody JSONObject filter) throws DateNotValid, RangeNotValid, StateNotValid, KeywordNotValid, GenreNotValid {
		HashMap output;
		EventiTotale events = new EventiTotale(filter);
		output = events.getOutput();
		return output;
		
	}
	
	@RequestMapping(value ="/genevents", method=RequestMethod.POST)
	public JSONArray eventiPerGenere(@RequestBody JSONObject genfilter) {
		JSONArray output;
		EventiPerGenere genevents = new EventiPerGenere(genfilter);
		output = genevents.getOutput();
		return output;
	}
	
	@RequestMapping(value="/statistics", method=RequestMethod.POST)
	public JSONArray stats(@RequestBody JSONObject stats) {
		JSONArray output;
		Stats statistics = new Stats(stats);
		output = statistics.getOutput();
		return output;
	}
	
	@RequestMapping(value="/database", method=RequestMethod.GET)
	public JSONArray getDatabase() {
		JSONArray output = Database.getDatabaseFromFile();
		return output;
	}
	
	@RequestMapping(value="/resetDatabase", method=RequestMethod.GET)
	public String resetDatabase() {
		String done = "Reset riuscito con successo";
		String notDone="Il database non si è aggiornato";
		JSONArray oldVersion = Database.getDatabaseFromFile();
		Database data= new Database();
		JSONArray newVersion = Database.getDatabaseFromFile();
		if(newVersion.equals(oldVersion))
			return notDone;
		else
			return done;
	}
	
	@RequestMapping(value="/setDatabase" , method=RequestMethod.GET)
	public String setDatabase() {
		JSONArray data = Database.getDatabaseFromFile();
		try {
			Database database= new Database(data);
		} catch(DatabaseNotValid e) {
			e.printStackTrace();
		}
		return "Database correttamente caricato";
	}
}
