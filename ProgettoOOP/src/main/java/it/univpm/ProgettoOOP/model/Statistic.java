package it.univpm.ProgettoOOP.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.exception.DateNotValid;
import it.univpm.ProgettoOOP.exception.GenreNotValid;
import it.univpm.ProgettoOOP.exception.KeywordNotValid;
import it.univpm.ProgettoOOP.exception.RangeNotValid;
import it.univpm.ProgettoOOP.exception.StateNotValid;

/**
 * @author Maurizio, Paolo
 *
 *Super-classe astratta con le caratteristiche che accomunano le sotto classi
 */
public abstract class Statistic {
	/**
	 * Database interno alla classe
	 */
	public JSONArray database;
	/**
	 * JSONObject contenente il filtro
	 */
	public JSONObject filter;
	
	/**
	 * Costruttore della classe
	 * @param filter JSONObject contenente il filtro
	 */
	public Statistic (JSONObject filter) {
		this.filter = filter;
	}
	
	/**
	 * Metodo astratto per l'elaborazione dell'output
	 * @throws StateNotValid
	 * @throws GenreNotValid
	 * @throws DateNotValid
	 * @throws RangeNotValid
	 * @throws KeywordNotValid
	 */
	public abstract void statCalculator() throws StateNotValid, GenreNotValid, DateNotValid, RangeNotValid, KeywordNotValid;

}
