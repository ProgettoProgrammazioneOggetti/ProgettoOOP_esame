package it.univpm.ProgettoOOP.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.exception.*;

public class DateFilter implements Filter{

	//@Override
	public  JSONArray filter(JSONArray data, JSONObject filter) throws DateNotValid, RangeNotValid{
		JSONObject dates=(JSONObject) filter.get("date");
		if(dates.equals(null))
			return data;
		else {
			JSONArray output=new JSONArray();
			Iterator i=data.iterator();
			String gte=(String) dates.get("gte");
			if(gte.equals(null)) {
				Vector<String> bt=(Vector<String>) dates.get("bt");
				if(bt.equals(null))
					throw new DateNotValid();
				else {
					//Filter by date between two input
					if(isAfter(bt.get(1), bt.get(0))) {	
						while(i.hasNext()) {
							JSONObject event=(JSONObject) i.next();
							JSONObject date=(JSONObject) event.get("dates");
							JSONObject start=(JSONObject) date.get("start");
							if(isBetween((String) start.get("localDate"), bt.get(0), bt.get(1))) 
								output.add(event);
							}
						return output;}
					else{
						throw new RangeNotValid();
						}
					}
				}
			else {
				//Filter by date greater than one in input
				while(i.hasNext()) {
				JSONObject event=(JSONObject) i.next();
				JSONObject date=(JSONObject) event.get("dates");
				JSONObject start=(JSONObject) date.get("start");
				if(isAfter((String) start.get("localDate"), gte)) 
					output.add(event);
				}
				return output;
				}
				}
			}
	
	private static boolean isAfter(String date, String reference) {
		Calendar data=Calendar.getInstance();
		data.set(Integer.parseInt(date.substring(0, 4)),Integer.parseInt(date.substring(5, 7)),Integer.parseInt(date.substring(8, 10)));
		Calendar refer=Calendar.getInstance();
		refer.set(Integer.parseInt(reference.substring(0, 4)), Integer.parseInt(reference.substring(5,7)), Integer.parseInt(reference.substring(8,10)));
		if(data.after(refer))
			return true;
		else
			return false;
	}
	private static boolean isBetween(String date, String before, String after) {
		Calendar data=Calendar.getInstance();
		data.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5,7)),Integer.parseInt(date.substring(8, 10)));
		Calendar bef=Calendar.getInstance();
		bef.set(Integer.parseInt(before.substring(0, 4)), Integer.parseInt(before.substring(5,7)),Integer.parseInt(before.substring(8, 10)));
		Calendar aft=Calendar.getInstance();
		aft.set(Integer.parseInt(after.substring(0, 4)), Integer.parseInt(after.substring(5,7)),Integer.parseInt(after.substring(8, 10)));
		if(data.after(bef) && data.before(aft))
		return true;
		else
			return false;
	}

}
