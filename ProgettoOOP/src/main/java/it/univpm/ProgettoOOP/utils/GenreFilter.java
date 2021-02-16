package it.univpm.ProgettoOOP.utils;

import java.util.Iterator;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.exception.GenreNotValid;

public class GenreFilter implements Filter{

	@Override
	public  JSONArray filter(JSONArray data, JSONObject filter) throws GenreNotValid {
		JSONObject genres=(JSONObject) filter.get("genre");
		JSONArray output=new JSONArray();
		if(genres.equals(null))
			return data;
		else {
			Vector<String> in=(Vector<String>) genres.get("$in");
			if(in.equals(null))
				throw new GenreNotValid();
			else {
				//Filter by genre
				for(String s:in) {
					Iterator i=data.iterator();
					while(i.hasNext()) {
						JSONObject event=(JSONObject) i.next();
						JSONArray classification=(JSONArray) event.get("classification");
						JSONObject temp=(JSONObject) classification.get(0);
						JSONObject genre=(JSONObject) temp.get("genre");
						String name=(String) genre.get("name");
						if(name==s)
							output.add(event);
						
					}
				}
				return output;
			}
			
		}
			
	}

}
