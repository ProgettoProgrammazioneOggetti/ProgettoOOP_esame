package it.univpm.ProgettoOOP.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.database.Database;
import it.univpm.ProgettoOOP.exception.DateNotValid;
import it.univpm.ProgettoOOP.exception.GenreNotValid;
import it.univpm.ProgettoOOP.exception.KeywordNotValid;
import it.univpm.ProgettoOOP.exception.RangeNotValid;
import it.univpm.ProgettoOOP.exception.StateNotValid;
import it.univpm.ProgettoOOP.utils.*;

public class EventiPerGenere extends Statistic {
	JSONArray output;
	
	public EventiPerGenere(JSONObject genfilter) throws StateNotValid, GenreNotValid, DateNotValid, RangeNotValid, KeywordNotValid {
		super(genfilter);
		this.statCalculator();
	}
	
	public JSONArray getOutput() {
		return output;
	}
	
	@Override
	public void statCalculator() {
		JSONArray database = new JSONArray();
		database = Database.getDatabaseFromFile();
		
	}

}
