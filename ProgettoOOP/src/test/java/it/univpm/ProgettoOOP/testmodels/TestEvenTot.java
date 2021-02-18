package it.univpm.ProgettoOOP.testmodels;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.ProgettoOOP.exception.DateNotValid;
import it.univpm.ProgettoOOP.exception.GenreNotValid;
import it.univpm.ProgettoOOP.exception.KeywordNotValid;
import it.univpm.ProgettoOOP.exception.RangeNotValid;
import it.univpm.ProgettoOOP.exception.StateNotValid;
import it.univpm.ProgettoOOP.model.EventiTotale;

public class TestEvenTot {
	
	private EventiTotale tot;
	
	@BeforeEach
	void setUp() throws DateNotValid, RangeNotValid, StateNotValid, GenreNotValid, KeywordNotValid {
		tot = new EventiTotale(null);
	}
	
	@AfterEach
	void tearDown() {
		
	}
	
	@Test
	void Test1() {
		assertNotNull(tot.getOutput());
	}
	
	@Test
	void Test2() {
		assertTrue(tot.getOutput().isEmpty());
	}

}
