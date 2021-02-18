package it.univpm.ProgettoOOP.testmodels;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.ProgettoOOP.database.Database;
import it.univpm.ProgettoOOP.model.Ricerca;

/**
 * @author Maurizio, Paolo
 *
 *Classe che contiene i test per la classe Database
 */
public class testDatabase1 {
	
	private Database d1;
	
	@BeforeEach
	void setUp() throws Exception{
		d1 = new Database();
	}
	
	@AfterEach 
	void tearDown() throws Exception{
		
	}
	
	/**
	 * Test che prova che il database sia diverso da null
	 * @throws IOException
	 */
	@Test
	void Test1() throws IOException {
		assertNotNull(Database.getDatabaseFromFile());
	}
	
	/**
	 * Test che prova che il database non sia vuoto
	 * @throws IOException
	 */
	@Test
	void Test2() throws IOException {
		assertTrue(!Database.getDatabaseFromFile().isEmpty());
	}

}