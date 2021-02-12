/**
 * 
 */
package it.univpm.ProgettoOOP.controller;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.web.bind.annotation.*;

import it.univpm.ProgettoOOP.model.EventiPerGenere;
import it.univpm.ProgettoOOP.model.EventiTotale;
import it.univpm.ProgettoOOP.model.Ricerca;
import it.univpm.ProgettoOOP.model.Statistic;
/**
 * @author Maurizio
 * @author Paolo
 *
 */
class ControllerClass {
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public JSONArray cercaEvento(@RequestParam(name = "name") String name,@RequestParam(name= "keyword") String keyword) {
		JSONArray output;
		Ricerca search = new Ricerca(name, keyword);
		output = search.getOutput();
		return output;
	}
	
	@RequestMapping(value ="/events" , method=RequestMethod.POST)
	public HashMap eventiTotale(@RequestBody JSONObject filter) {
		HashMap output;
		EventiTotale events = new EventiTotale(filter);
		output = events.getOutput();
		return output;
		
	}
	
	@RequestMapping(value ="/genevents", method=RequestMethod.POST)
	public JSONArray eventiPerGenere(@RequestBody JSONObject genfilter) {
		JSONArray output;
		EventiPerGenere genevents = new EventiPerGenere(genfilter);
		output = genevents.getOutput();
		return output;
	}
}
