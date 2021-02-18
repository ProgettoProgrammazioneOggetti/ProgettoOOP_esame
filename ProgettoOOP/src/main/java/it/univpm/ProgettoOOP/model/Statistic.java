package it.univpm.ProgettoOOP.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public abstract class Statistic {
	public JSONArray database;
	public JSONObject filter;
	
	public Statistic (JSONObject filter) {
		this.filter = filter;
	}
	
	public abstract void statCalculator();

}
