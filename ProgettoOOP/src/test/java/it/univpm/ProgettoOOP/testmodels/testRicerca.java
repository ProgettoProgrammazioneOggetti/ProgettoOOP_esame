package it.univpm.ProgettoOOP.testmodels;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.ProgettoOOP.model.Ricerca;

/**
 * @author Maurizio, Paolo
 *
 *Classe che controlla le funzionalità della classe Ricerca
 */
public class testRicerca {
	
	private Ricerca r1;
	private Ricerca r2;
	
	
	@BeforeEach
	void setUp() throws Exception{
		r1 = new Ricerca("FL",  "Hamilton");
	}
	
	@AfterEach
	void tearDown() throws Exception {
		
	}
	
	/**
	 * Test che controlla che l'output non sia nullo
	 */
	@Test
	void testJSONObject1() {
		assertNotNull(r1.getOutput());
	}

	/**
	 * Test che controlla che l'output non sia vuoto
	 */
	@Test 
	void test3() {
		assertTrue(!r1.getOutput().isEmpty());
	}


}
