package it.univpm.ProgettoOOP.utils;

import java.util.Iterator;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.exception.StateNotValid;

/**
 * @author Paolo, Maurizio
 *
 * Classe che implementa il filtraggio in base agli stati
 */
public class StateFilter implements Filter{

	/**
	 *Metodo che implementa il filtraggio
	 * @param data JSONArray contenente il database da filtrare
	 * @param filter JSONObject contenente il filtro in formato json
	 * @return JSONArray contenente il  database filtrato
	 * @throws StateNotValid Errore restituito se gli stati non sono stati inseriti correttamente nel filtro
	 */
	@Override
	public  JSONArray filter(JSONArray data, JSONObject filter) throws StateNotValid{
		JSONObject states=(JSONObject) filter.get("state");
		if(states.equals(null))
			return data;
		else
		{
			JSONArray output=new JSONArray();
			
			Vector<String> in= (Vector<String>) states.get("$in");
			if(in.equals(null))
				throw new StateNotValid();
			else {
				for(String s:in) {
					Iterator i=data.iterator();
					while(i.hasNext()) {
						JSONObject event=(JSONObject) i.next();
						JSONObject embedded=(JSONObject) event.get("_embedded");
						JSONArray venues=(JSONArray) embedded.get("venues");
						JSONObject temp=(JSONObject) venues.get(0);
						JSONObject state=(JSONObject) temp.get("state");
						String stateCode=(String) state.get("stateCode");
						if(s.equals(stateCode))
							output.add(event);
					}
				}
				return output;
			}
		}
		
	}

}
