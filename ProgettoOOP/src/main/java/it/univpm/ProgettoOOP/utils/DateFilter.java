package it.univpm.ProgettoOOP.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.exception.*;

/**
 * @author Paolo, Maurizio
 *
 * Classe che gestisce il filtaggio in base alla data
 */
public class DateFilter implements Filter{

	//@Override
	/**
	 * Metodo che implementa il filtraggio
	 * @param data JSONArray contenente il database da filtrare
	 * @param filter JSONObject contenente il filtro
	 * @return JSONArray contenente il database filtrato
	 * @throws DateNotValid Errore restituito se la data non è stata inserita correttamente nel filtro
	 * @throws RangeNotValid Errore restituito se si è inserito un intervallo di date non corretto nel filtro
	 */
	public  JSONArray filter(JSONArray data, JSONObject filter) throws DateNotValid, RangeNotValid{
		JSONObject dates=(JSONObject) filter.get("date");
		if(dates.equals(null))
			return data;
		else {
			JSONArray output=new JSONArray();
			Iterator i=data.iterator();
			String gte=(String) dates.get("$gte");
			if(gte.equals(null)) {
				Vector<String> bt=(Vector<String>) dates.get("$bt");
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
	
	/**
	 *  Metodo che stabilisce se una data è successiva a una di riferimento
	 * @param date String contenente la data da valutare
	 * @param reference String contenente la data di riferimento
	 * @return boolean che dice se la data viene prima (true) o dopo (false)
	 */
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
	/**
	 * Metodo che stabilisce se una data si trova tra due di riferimento
	 * @param date String contenente la data da valutare
	 * @param before String contenente la data da considerare come iniziale
	 * @param after String contenente la data da considerare come finale
	 * @return boolean se la data si trova tra le due di riferimento (true) o no (false)
	 */
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
