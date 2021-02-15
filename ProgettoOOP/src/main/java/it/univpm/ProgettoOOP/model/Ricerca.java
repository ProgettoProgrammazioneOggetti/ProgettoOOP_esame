package it.univpm.ProgettoOOP.model;

import java.util.Vector;
import org.json.simple.JSONArray;
import it.univpm.ProgettoOOP.utils.APIConnection;

public class Ricerca {
	private String name;
	private String keyword;
	private JSONArray output;
	
	public Ricerca(String name, String keyword) {
		APIConnection connect = new APIConnection();
		Vector<String> change = cambioVar(keyword);
		connect.openConnection(name, change);
		this.output= connect.getData();
	}
	
	public Vector<String> cambioVar(String keyword) {
		Vector<String> changed= new Vector<String>();
			for(int i = 0; i<keyword.length();i++) {
			int temp = 0;
			if(keyword.substring(i)==",") {
				changed.add(keyword.substring(temp, i));
				temp = i;
			}
		}
		return changed;
	}

	public JSONArray getOutput() {
		return output;
	}

}
