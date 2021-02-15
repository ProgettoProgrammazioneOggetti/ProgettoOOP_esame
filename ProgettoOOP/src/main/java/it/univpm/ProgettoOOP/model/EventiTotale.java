package it.univpm.ProgettoOOP.model;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.univpm.ProgettoOOP.database.Database;
import it.univpm.ProgettoOOP.exception.DateNotValid;
import it.univpm.ProgettoOOP.exception.GenreNotValid;
import it.univpm.ProgettoOOP.exception.KeywordNotValid;
import it.univpm.ProgettoOOP.exception.RangeNotValid;
import it.univpm.ProgettoOOP.exception.StateNotValid;
import it.univpm.ProgettoOOP.utils.*;

public class EventiTotale extends Statistic {
	HashMap output;
	
	public EventiTotale(JSONObject filter) throws StateNotValid, KeywordNotValid, GenreNotValid, DateNotValid, RangeNotValid {
		super(filter);
		JSONArray database = new JSONArray();
		JSONObject fulldata = new JSONObject();
		Database data = new Database();
		database = data.getDatabaseFromFile();
		StateFilter filter1 = new StateFilter();
		database = filter1.filter(database, filter);
		KeywordFilter filter2 = new KeywordFilter();
		database = filter2.filter(database, filter);
		GenreFilter filter3 = new GenreFilter();
		database = filter3.filter(database, filter);
		DateFilter filter4 = new DateFilter();
		database = filter4.filter(database, filter);
		this.output = statCalculator(database);
	}

	public HashMap getOutput() {
		return output;
	}

	public HashMap statCalculator(JSONArray database) {
		
		return output;		
	}

	@Override
	public void statCalculator() {
		// TODO Auto-generated method stub
		
	}

}
