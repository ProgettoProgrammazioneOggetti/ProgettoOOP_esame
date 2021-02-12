package it.univpm.ProgettoOOP.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Stats extends Statistic {
	JSONArray output;
	
	public Stats(JSONObject stats) {
		super(stats);
	}

	public JSONArray getOutput() {
		return output;
	}

	@Override
	public void statCalculator() {
		// TODO Auto-generated method stub
		
	}

}
