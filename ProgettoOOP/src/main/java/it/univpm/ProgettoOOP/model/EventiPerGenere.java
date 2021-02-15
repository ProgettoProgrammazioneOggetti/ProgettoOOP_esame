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
		JSONArray database = new JSONArray();
		Database data = new Database();
		database = data.getDatabaseFromFile();
		StateFilter filter1 = new StateFilter();
		database = filter1.filter(database, genfilter);
		GenreFilter filter2 = new GenreFilter();
		database = filter2.filter(database, genfilter);
		DateFilter filter3 = new DateFilter();
		database = filter3.filter(database, genfilter);
		KeywordFilter filter4 = new KeywordFilter();
		database = filter4.filter(database, genfilter);
		this.output = statCalculator(database);
	}
	
	public JSONArray getOutput() {
		return output;
	}

	public JSONArray statCalculator(JSONArray database) {		
		return output;
	}

	@Override
	public void statCalculator() {
		// TODO Auto-generated method stub
		
	}

}
