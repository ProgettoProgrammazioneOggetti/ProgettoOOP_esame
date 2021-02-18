package it.univpm.ProgettoOOP.model;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.database.Database;
import it.univpm.ProgettoOOP.exception.DateNotValid;
import it.univpm.ProgettoOOP.exception.GenreNotValid;
import it.univpm.ProgettoOOP.exception.KeywordNotValid;
import it.univpm.ProgettoOOP.exception.RangeNotValid;
import it.univpm.ProgettoOOP.exception.StateNotValid;
import it.univpm.ProgettoOOP.utils.*;

/**
 * @author Maurizio, Paolo
 *
 *Classe che gestisce la rotta "/genre"
 */
public class EventiPerGenere extends Statistic {
	/**
	 * Hashmap contenente l'output della rotta
	 */
	HashMap output;
	
	/**
	 * Costruttore della classe che richiama quello della super classe
	 * @param genfilter JSONObject contenente il filtro
	 * @throws StateNotValid
	 * @throws GenreNotValid
	 * @throws DateNotValid
	 * @throws RangeNotValid
	 * @throws KeywordNotValid
	 */
	public EventiPerGenere(JSONObject genfilter) throws StateNotValid, GenreNotValid, DateNotValid, RangeNotValid, KeywordNotValid {
		super(genfilter);
		this.statCalculator();
	}
	
	/**
	 * Metodo che produce l'output e lo carica nell'Hashmap output
	 */
	@Override
	public void statCalculator() throws StateNotValid, GenreNotValid, DateNotValid, RangeNotValid, KeywordNotValid {
		JSONArray database = new JSONArray();
		database = Database.getDatabaseFromFile();
		JSONObject genre=(JSONObject) filter.get("genre");
		
		
		Vector<String> in=(Vector<String>) genre.get("in");
		if(in.equals(null))
			throw new StateNotValid();
		else {
			for(String s:in) {
				JSONObject tempFilter = new JSONObject();
				JSONObject body = new JSONObject();
				
				body.put("$in", s);
				tempFilter.put("genre", body);
				
				StateFilter filter1 = new StateFilter();
				database = filter1.filter(database, tempFilter);
				GenreFilter filter2 = new GenreFilter();
				database = filter2.filter(database, filter);
				DateFilter filter3 = new DateFilter();
				database = filter3.filter(database, filter);
				KeywordFilter filter4 = new KeywordFilter();
				database = filter4.filter(database, filter);
				
				
				this.output.put(genre, database.size());
			}
			
		}
	}

	/**
	 * Metodo get di output
	 * @return Hashmap contenente l'output
	 */
	public HashMap getOutput() {
		return output;
	}

}
