package it.univpm.ProgettoOOP.model;

import java.util.Vector;
import org.json.simple.JSONArray;
import it.univpm.ProgettoOOP.utils.APIConnection;

/**
 * @author Maurizio, Paolo
 *
 *Classe che gestisce la rotta "/search"
 */
public class Ricerca {
	private String name;
	private String keyword;
	private JSONArray output;
	
	/**
	 * Costruttore della classe
	 * @param name String contenente il codice dello stato
	 * @param keyword String che contiene le parole chiave
	 */
	public Ricerca(String name, String keyword) {
		APIConnection connect = new APIConnection();
		Vector<String> change = cambioVar(keyword);
		connect.openConnection(name, change);
		this.output= connect.getData();
		this.name = name;
		this.keyword=keyword;
	}
	
	/**
	 * Metodo che permette di trasformare una stringa in un vettore di stringa
	 * @param keyword String contenente le parole chiave separate da virgola
	 * @return Vettore contenente le parole chiave
	 */
	private Vector<String> cambioVar(String keyword) {
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

	/**
	 * Metodo get di output
	 * @return JSONArray contenente l'output
	 */
	public JSONArray getOutput() {
		return output;
	}


}
