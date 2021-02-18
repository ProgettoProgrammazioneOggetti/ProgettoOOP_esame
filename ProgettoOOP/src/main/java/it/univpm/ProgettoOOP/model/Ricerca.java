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
		this.name = name;
		this.keyword=keyword;
	}
	
	public Vector<String> cambioVar(String keyword) {
		Vector<String> changed= new Vector<String>();
		int temp =0;
		while(keyword.indexOf(',')!= -1) {
			changed.add(keyword.substring(temp, keyword.indexOf(',')));
			temp = keyword.indexOf(',') + 1;
			keyword= keyword.substring(temp);
		}
			changed.add(keyword);

		return changed;
	}

	public JSONArray getOutput() {
		return output;
	}


}
