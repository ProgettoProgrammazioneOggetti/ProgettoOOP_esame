package it.univpm.ProgettoOOP.utils;

import java.util.Iterator;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.exception.StateNotValid;

public class StateFilter implements Filter{

	@Override
	public  JSONArray filter(JSONArray data, JSONObject filter) throws StateNotValid{
		JSONObject states=(JSONObject) filter.get("state");
		if(states.equals(null))
			return data;
		else
		{
			JSONArray output=new JSONArray();
			
			Vector<String> in=(Vector<String>) states.get("in");
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
