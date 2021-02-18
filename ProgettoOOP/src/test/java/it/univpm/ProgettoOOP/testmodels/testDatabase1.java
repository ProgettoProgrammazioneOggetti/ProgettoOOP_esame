package it.univpm.ProgettoOOP.testmodels;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.ProgettoOOP.database.Database;
import it.univpm.ProgettoOOP.model.Ricerca;

public class testDatabase1 {
	
	private Database d1;
	
	@BeforeEach
	void setUp() throws Exception{
		d1 = new Database();
	}
	
	@AfterEach 
	void tearDown() throws Exception{
		
	}
	
	@Test
	void Test1() {
		assertNotNull(Database.getDatabaseFromFile());
	}
	
	@Test
	void Test2() {
		assertTrue(!Database.getDatabaseFromFile().isEmpty());
	}

}