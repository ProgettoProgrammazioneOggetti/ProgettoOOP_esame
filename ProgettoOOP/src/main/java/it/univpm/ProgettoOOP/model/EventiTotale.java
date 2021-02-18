package it.univpm.ProgettoOOP.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

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
	
	public EventiTotale(JSONObject filter) throws StateNotValid, DateNotValid, RangeNotValid, GenreNotValid, KeywordNotValid {
		super(filter);
		this.statCalculator();
	}

	public HashMap getOutput() {
		return output;
	}
	
	@Override
	public void statCalculator() throws StateNotValid, GenreNotValid, DateNotValid, RangeNotValid, KeywordNotValid {
		
		JSONArray database = new JSONArray();
		database = Database.getDatabaseFromFile();
		JSONObject states=(JSONObject) filter.get("state");
		
		
		Vector<String> in=(Vector<String>) states.get("in");
		if(in.equals(null))
			throw new StateNotValid();
		else {
			for(String s:in) {
				JSONObject tempFilter = new JSONObject();
				JSONObject body = new JSONObject();
				
				body.put("$in", s);
				tempFilter.put("state", body);
				
				StateFilter filter1 = new StateFilter();
				database = filter1.filter(database, tempFilter);
				GenreFilter filter2 = new GenreFilter();
				database = filter2.filter(database, filter);
				DateFilter filter3 = new DateFilter();
				database = filter3.filter(database, filter);
				KeywordFilter filter4 = new KeywordFilter();
				database = filter4.filter(database, filter);
				
				
				this.output.put(states, database.size());
			}
			
		}
	}

}
