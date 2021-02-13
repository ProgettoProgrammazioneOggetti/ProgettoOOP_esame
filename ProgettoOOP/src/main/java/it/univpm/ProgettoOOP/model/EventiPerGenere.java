package it.univpm.ProgettoOOP.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EventiPerGenere extends Statistic {
	JSONArray output;
	
	public EventiPerGenere(JSONObject genfilter) {
		super(genfilter);
	}
	
	public JSONArray getOutput() {
		return output;
	}

	@Override
	public void statCalculator() {
		// TODO Auto-generated method stub
		
	}

}
