package it.univpm.ProgettoOOP.testmodels;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.ProgettoOOP.model.Ricerca;

public class testRicerca {
	
	private Ricerca r1;
	private Ricerca r2;
	
	
	@BeforeEach
	void setUp() throws Exception{
		r1 = new Ricerca("FL",  "Hamilton");
		r2 = new Ricerca("","");
	}
	
	@AfterEach
	void tearDown() throws Exception {
		
	}
	
	@Test
	void testJSONObject1() {
		assertNotNull(r1.getOutput());
	}
	
	@Test
	void testJSONObject2() {
		assertTrue(r1.getOutput().isEmpty());
	}

	@Test 
	void test3() {
		assertTrue(!r1.getOutput().isEmpty());
	}


}
