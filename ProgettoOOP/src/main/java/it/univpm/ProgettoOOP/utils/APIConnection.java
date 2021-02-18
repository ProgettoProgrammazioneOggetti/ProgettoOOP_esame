package it.univpm.ProgettoOOP.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.univpm.ProgettoOOP.exception.KeywordNotValid;

/**
 * @author Paolo, Maurizio
 *
 * Classe per la gestione della comunicazione con l'API esterna
 */
public class APIConnection {
	/**
	 * Contenuto della chiamata all'API
	 */
	private JSONArray data;
	/**
	 * Chiave necessaria per la chiamata all'API
	 */
	private final String APIkey="xqGfLV1EpP1kD4akgep0Yq75bh4gh8fZ";
	/**
	 * Primo metodo per la connessione all'API senza parametri che carica la risposta dell'API nel JSONArray data
	 */
	public void openConnection() {
		JSONParser parser=new JSONParser();
		JSONObject embedded=null;
		try {
			String url="https://app.ticketmaster.com/discovery/v2/events?startDateTime=2021-01-01T00:00:00Z&endDateTime=2021-12-31T23:59:59Z&apikey="+APIkey;
			URLConnection URL=new URL(url).openConnection();
			BufferedReader reader=new BufferedReader(new InputStreamReader(URL.getInputStream()));
			String input=reader.readLine();
			JSONObject download=(JSONObject) parser.parse(input);
			embedded=(JSONObject) download.get("_embedded");				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!embedded.equals(null)) {
			this.data=(JSONArray) embedded.get("events");
			
		}
		
			
		
	}
	/**
	 * Secondo metodo per la connessione all'API che carica la risposta nel JSONArray data
	 * @param state String contenente il codice dello stato
	 * @param keyword Vettore contenente le keyword
	 */
	public void openConnection(String state, Vector<String> keyword)  {
		JSONObject ffilter= new JSONObject();
		JSONParser parser=new JSONParser();
		JSONObject embedded=null;
		try {
			String url="https://app.ticketmaster.com/discovery/v2/events?startDateTime=2021-01-01T00:00:00Z&endDateTime=2021-12-31T23:59:59Z&stateCode="+state+"&apikey="+APIkey;
			URLConnection URL=new URL(url).openConnection();
			BufferedReader reader=new BufferedReader(new InputStreamReader(URL.getInputStream()));
			String input=reader.readLine();
			JSONObject download=(JSONObject) parser.parse(input);
			embedded=(JSONObject) download.get("_embedded");   		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{if(!embedded.equals(null)) {
			JSONArray database=(JSONArray) embedded.get("events");
			ffilter.put("keyword", keyword);
			KeywordFilter filt=new KeywordFilter();
			this.data=filt.filter(database, ffilter); }
		}
		catch(KeywordNotValid k) {
			//to complete
		}		
	}
	public JSONArray getData() {
		return data;
	}

}
