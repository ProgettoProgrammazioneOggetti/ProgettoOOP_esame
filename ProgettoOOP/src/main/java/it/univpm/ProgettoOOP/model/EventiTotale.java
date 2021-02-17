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
	
	public EventiTotale(JSONObject filter) throws StateNotValid {
		super(filter);
		this.statCalculator();
	}

	public HashMap getOutput() {
		return output;
	}
	
	@Override
	public void statCalculator() throws StateNotValid {
		
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
			}
		}
	}

}
