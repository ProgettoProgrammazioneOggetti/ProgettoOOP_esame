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
 * @author Maurizio, Paolo
 * 
 *Classe contenente le rotte dell'applicazione
 */
class ControllerClass {
	
	
	/**
	 * Rotta che permette di ottenere tutti gli eventi dato uno stato e delle parole chiave
	 * @param name String contenente il codice dello stato
	 * @param keyword String contenente le parole chiave separate da virgola
	 * @return JSONArray con gli eventi corrispondenti all'input
	 */
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public JSONArray cercaEvento(@RequestParam(name = "name", defaultValue= "") String name,@RequestParam(name= "keyword") String keyword) {
		JSONArray output;
		Ricerca search = new Ricerca(name, keyword);
		output = search.getOutput();
		return output;
	}
	
	/**
	 * Rotta che permette di ottenere il numero di eventi per stato dato un filtro in input
	 * @param filter JSONObject contenente il filtro
	 * @return Hashmap contenente gli stati presenti nel filtro e il rispettivo numero di eventi
	 * @throws DateNotValid 
	 * @throws RangeNotValid
	 * @throws StateNotValid
	 * @throws KeywordNotValid
	 * @throws GenreNotValid
	 */
	@RequestMapping(value ="/events" , method=RequestMethod.POST)
	public HashMap eventiTotale(@RequestBody JSONObject filter) throws DateNotValid, RangeNotValid, StateNotValid, KeywordNotValid, GenreNotValid {
		HashMap output;
		EventiTotale events = new EventiTotale(filter);
		output = events.getOutput();
		return output;
		
	}
	
	/**
	 * Rotta che permette di ottenere il numero di eventi raggruppati per evento dato un filtro in input
	 * @param genfilter JSONObject contenente il filtro
	 * @return Hashmap contenente i generi presenti nel filtro e il rispettivo numero di eventi
	 * @throws DateNotValid
	 * @throws RangeNotValid
	 * @throws StateNotValid
	 * @throws GenreNotValid
	 * @throws KeywordNotValid
	 */
	@RequestMapping(value ="/genevents", method=RequestMethod.POST)
	public HashMap eventiPerGenere(@RequestBody JSONObject genfilter) throws DateNotValid, RangeNotValid, StateNotValid, GenreNotValid, KeywordNotValid {
		HashMap output;
		EventiPerGenere genevents = new EventiPerGenere(genfilter);
		output = genevents.getOutput();
		return output;
	}
	
	/**
	 * Rotta che permette di ottenere, per ogni stato, il mese con minimo di eventi (e relativo valore), il mese con il massimo numero di eventi (e relativo valore) e la media
	 * @param stats JSONObject contenente il filtro
	 * @return JSONArray con l'output descritto
	 * @throws DateNotValid
	 * @throws RangeNotValid
	 * @throws StateNotValid
	 * @throws GenreNotValid
	 * @throws KeywordNotValid
	 */
	@RequestMapping(value="/statistics", method=RequestMethod.POST)
	public JSONArray stats(@RequestBody JSONObject stats) throws DateNotValid, RangeNotValid, StateNotValid, GenreNotValid, KeywordNotValid {
		JSONArray output;
		Stats statistics = new Stats(stats);
		output = statistics.getOutput();
		return output;
	}
	
	/**
	 * Rotta che permette di ottenere in output il database utilizzato
	 * @return JSONarray contenente il database
	 */
	@RequestMapping(value="/database", method=RequestMethod.GET)
	public JSONArray getDatabase() {
		JSONArray output = Database.getDatabaseFromFile();
		return output;
	}
	
	/**
	 * Rotta che permette di resettare o aggiornare il database
	 * @return String contenente il successo o fallimento dell'operazione
	 */
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
	
	/**
	 * Rotta che permette di controllare il database in uso dopo modifiche dell'utente
	 * @return Stringa contenente il successo dell'operazione
	 */
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
