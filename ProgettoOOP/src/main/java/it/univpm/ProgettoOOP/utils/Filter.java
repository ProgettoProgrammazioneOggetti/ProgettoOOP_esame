package it.univpm.ProgettoOOP.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.exception.*;

public interface Filter {
	public  JSONArray filter(JSONArray data, JSONObject filter) throws DateNotValid, RangeNotValid, GenreNotValid, KeywordNotValid, StateNotValid;

}
