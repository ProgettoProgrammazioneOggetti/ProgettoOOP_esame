package it.univpm.ProgettoOOP.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProgettoOOP.exception.*;

/**
 * @author Paolo, Maurizio
 *
 * Interfaccia per la gestione dei filtri
 */
public interface Filter {
	
	/**
	 * Metodo astratto per il filtraggio dei dati
	 * @param data JSONArray contenente il database da filtrare
	 * @param filter JSONObject contenente il filtro in formato json
	 * @return JSONArray contenente il  database filtrato
	 * @throws DateNotValid Errore restituito se la data non è stata inserita correttamente nel filtro
	 * @throws RangeNotValid Errore restituito se si è inserito un intervallo di date non corretto nel filtro
	 * @throws GenreNotValid Errore restituito se i generi non sono stati inseriti correttamente nel filtro
	 * @throws KeywordNotValid Errore restituito se le parole chiave non sono state inserite correttamente nel filtro
	 * @throws StateNotValid Errore restituito se gli stati non sono stati inseriti correttamente nel filtro
	 */
	public JSONArray filter(JSONArray data, JSONObject filter) throws DateNotValid, RangeNotValid, GenreNotValid, KeywordNotValid, StateNotValid;

}
