package it.univpm.ProgettoOOP.utils;

import java.util.Iterator;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.exception.KeywordNotValid;

public class KeywordFilter implements Filter{

	@Override
	public  JSONArray filter(JSONArray data, JSONObject filter) throws KeywordNotValid {
		JSONObject keywords=(JSONObject) filter.get("keyword");
		if(keywords.equals(null))
			return data;
		else {
			JSONArray output=new JSONArray();
			Vector<String> in=(Vector<String>) keywords.get("in");
			if(in.equals(null))
				throw new KeywordNotValid();
			else {
				for(String s:in) {
					Iterator i=data.iterator();
					while(i.hasNext()) {
						JSONObject event=(JSONObject)i.next();
						String name=(String) event.get("name");
						if(name.contains(s))
							output.add(event);
						else {
							String info=(String) event.get("info");
							if(info.contains(s))
								output.add(event);
						}
					}
				}
				return output;
			}
			
		}
	}

}
