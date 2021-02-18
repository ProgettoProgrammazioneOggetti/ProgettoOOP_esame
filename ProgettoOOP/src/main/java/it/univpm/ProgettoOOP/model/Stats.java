package it.univpm.ProgettoOOP.model;

import java.util.Iterator;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.database.Database;
import it.univpm.ProgettoOOP.exception.DateNotValid;
import it.univpm.ProgettoOOP.exception.GenreNotValid;
import it.univpm.ProgettoOOP.exception.KeywordNotValid;
import it.univpm.ProgettoOOP.exception.RangeNotValid;
import it.univpm.ProgettoOOP.exception.StateNotValid;
import it.univpm.ProgettoOOP.utils.DateFilter;
import it.univpm.ProgettoOOP.utils.GenreFilter;
import it.univpm.ProgettoOOP.utils.KeywordFilter;
import it.univpm.ProgettoOOP.utils.StateFilter;

public class Stats extends Statistic {
	JSONArray output;
	
	public Stats(JSONObject stats) throws StateNotValid, DateNotValid, RangeNotValid, GenreNotValid, KeywordNotValid {
		super(stats);
		this.statCalculator();
	}

	public JSONArray getOutput() {
		return output;
	}


	@Override
	public void statCalculator() throws StateNotValid, DateNotValid, RangeNotValid, GenreNotValid, KeywordNotValid {
		JSONArray database = new JSONArray();
		database = Database.getDatabaseFromFile();
		JSONObject state=(JSONObject) filter.get("state");
		
		Vector<String> in=(Vector<String>) state.get("in");
		if(in.equals(null))
			throw new StateNotValid();
		else {
			for(String s:in) {
				JSONObject tempFilter1 = new JSONObject();
				JSONObject body1 = new JSONObject();
				body1.put("$in", s);
				tempFilter1.put("state", body1);
				JSONArray temp = new JSONArray();
				for(int i=0; i<12; i++) {
					JSONObject tempFilter = new JSONObject();
					JSONObject body = new JSONObject();
					String mese = new String();
					String dataini = new String();
					String datafin = new String();
					switch(i) {
					case 0:
						mese = "Gennaio";
						dataini = "2021:01:01";
						datafin = "2021:01:31";
						break;
					case 1:
						mese = "Febbraio";
						dataini = "2021:02:01";
						datafin = "2021:02:28";
						break;
					case 2:
						mese = "Marzo";
						dataini = "2021:03:01";
						datafin = "2021:03:31";
						break;
					case 3:
						mese = "Aprile";
						dataini = "2021:04:01";
						datafin = "2021:04:30";
						break;
					case 4:
						mese = "Maggio";
						dataini = "2021:05:01";
						datafin = "2021:05:31";
						break;
					case 5:
						mese = "Giugno";
						dataini = "2021:06:01";
						datafin = "2021:06:30";
						break;
					case 6:
						mese = "Luglio";
						dataini = "2021:07:01";
						datafin = "2021:07:31";
						break;
					case 7:
						mese = "Agosto";
						dataini = "2021:08:01";
						datafin = "2021:08:31";
						break;
					case 8:
						mese = "Settembre";
						dataini = "2021:09:01";
						datafin = "2021:09:30";
						break;
					case 9:
						mese = "Ottobre";
						dataini = "2021:10:01";
						datafin = "2021:10:31";
						break;
					case 10:
						mese = "Novembre";
						dataini = "2021:11:01";
						datafin = "2021:11:30";
						break;
					case 11:
						mese = "Dicembre";
						dataini = "2021:12:01";
						datafin = "2021:12:31";
						break;
					}
					Vector<String> dates = new Vector<String>();
					dates.add(dataini);
					dates.add(datafin);
					body.put("$bt", dates);
					tempFilter.put("date", body);
					
					StateFilter filter1 = new StateFilter();
					database = filter1.filter(database, tempFilter1);
					GenreFilter filter2 = new GenreFilter();
					database = filter2.filter(database, filter);
					DateFilter filter3 = new DateFilter();
					database = filter3.filter(database, tempFilter);
					KeywordFilter filter4 = new KeywordFilter();
					database = filter4.filter(database, filter);
					
					JSONObject month = new JSONObject();
					month.put("name", mese);
					month.put("value", database.size());
					temp.add(month);
				}
				
				JSONObject render = new JSONObject();
				render.put("name", s);
				render.put("min", this.getMin(temp));
				render.put("max", this.getMax(temp));
				render.put("avg", this.getMedia(temp));
				output.add(render);
			}
		}
		
	}
	
	private JSONObject getMin(JSONArray database) {
		Iterator i =  database.iterator();
		int pos = 0;
		int valMin = 0;
		int j = 0;
		while(i.hasNext()) {
			JSONObject temp = (JSONObject) i.next();
			int value = (int) temp.get("value");
			if(j == 0) {
				valMin = value;
			}
			else {
				if(value < valMin) {
					pos = j;
					valMin = value;
				}
			}
			j++;
		}
		return (JSONObject) database.get(j);
	}
	
	private JSONObject getMax(JSONArray database) {
		Iterator i =  database.iterator();
		int pos = 0;
		int valMax = 0;
		int j = 0;
		while(i.hasNext()) {
			JSONObject temp = (JSONObject) i.next();
			int value = (int) temp.get("value");
			if(j == 0) {
				valMax = value;
			}
			else {
				if(value > valMax) {
					pos = j;
					valMax = value;
				}
			}
			j++;
		}
		return (JSONObject) database.get(j);
	}
	
	private double getMedia(JSONArray database) {
		Iterator i = database.iterator();
		int somma = 0;
		while(i.hasNext()) {
			JSONObject temp = (JSONObject) i.next();
			somma += (int) temp.get("value");
		}
		return ((double)somma / database.size());
	}

}
