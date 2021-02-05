package it.univpm.ProgettoOOP.model;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class EventiTotale extends Statistic {
	HashMap output;
	
	public EventiTotale(JSONObject filter) {
		super(filter);
		
	}

	public HashMap getOutput() {
		return output;
	}

	@Override
	public void statCalculator() {
		// TODO Auto-generated method stub
		
	}

}
