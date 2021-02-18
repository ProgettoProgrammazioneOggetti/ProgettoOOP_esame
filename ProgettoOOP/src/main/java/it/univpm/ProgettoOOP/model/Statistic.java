package it.univpm.ProgettoOOP.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.exception.DateNotValid;
import it.univpm.ProgettoOOP.exception.GenreNotValid;
import it.univpm.ProgettoOOP.exception.KeywordNotValid;
import it.univpm.ProgettoOOP.exception.RangeNotValid;
import it.univpm.ProgettoOOP.exception.StateNotValid;

public abstract class Statistic {
	public JSONArray database;
	public JSONObject filter;
	
	public Statistic (JSONObject filter) {
		this.filter = filter;
	}
	
	public abstract void statCalculator() throws StateNotValid, GenreNotValid, DateNotValid, RangeNotValid, KeywordNotValid;

}
