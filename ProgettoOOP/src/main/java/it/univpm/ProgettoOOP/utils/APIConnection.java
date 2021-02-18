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

public class APIConnection {
	private JSONArray data;
	private static String APIkey="B1DdF0t3tyWSZywL0iAvB1pZmXN2m833";
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
	public void openConnection(String state, Vector<String> keyword)  {
		JSONObject ffilter= new JSONObject();
		JSONParser parser=new JSONParser();
		JSONObject embedded= new JSONObject();
		try {
			String url="https://app.ticketmaster.com/discovery/v2/events?startDateTime=2021-01-01T00:00:00Z&endDateTime=2021-12-31T23:59:59Z&stateCode=FL&apikey=B1DdF0t3tyWSZywL0iAvB1pZmXN2m833"; //correggere "+state+" e "+APIkey"
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
		try{
			if(!embedded.equals(null)) {
			JSONArray database=(JSONArray) embedded.get("events");
			JSONObject temp = new JSONObject();
			temp.put("$in", keyword);
			ffilter.put("keyword", temp);
			KeywordFilter filt=new KeywordFilter();
			this.data=filt.filter(database, ffilter);
					}
		}
		catch(KeywordNotValid k) {
			k.printStackTrace();
		}		
	}
	public JSONArray getData() {
		return data;
	}

}
