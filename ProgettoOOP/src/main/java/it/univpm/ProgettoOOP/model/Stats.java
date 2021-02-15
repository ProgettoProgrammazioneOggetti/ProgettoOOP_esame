package it.univpm.ProgettoOOP.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.database.Database;
import it.univpm.ProgettoOOP.exception.DateNotValid;
import it.univpm.ProgettoOOP.exception.GenreNotValid;
import it.univpm.ProgettoOOP.exception.KeywordNotValid;
import it.univpm.ProgettoOOP.exception.RangeNotValid;
import it.univpm.ProgettoOOP.exception.StateNotValid;
import it.univpm.ProgettoOOP.utils.DateFilter;
import it.univpm.ProgettoOOP.utils.GenreFilter;
import it.univpm.ProgettoOOP.utils.KeywordFilter;
import it.univpm.ProgettoOOP.utils.StateFilter;

public class Stats extends Statistic {
	JSONArray output;
	
	public Stats(JSONObject stats) throws StateNotValid, DateNotValid, RangeNotValid, GenreNotValid, KeywordNotValid {
		super(stats);
		JSONArray database = new JSONArray();
		Database data = new Database();
		database = data.getDatabaseFromFile();
		StateFilter filter1 = new StateFilter();
		database = filter1.filter(database, stats);
		GenreFilter filter2 = new GenreFilter();
		database = filter2.filter(database, stats);
		DateFilter filter3 = new DateFilter();
		database = filter3.filter(database, stats);
		KeywordFilter filter4 = new KeywordFilter();
		database = filter4.filter(database, stats);
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
