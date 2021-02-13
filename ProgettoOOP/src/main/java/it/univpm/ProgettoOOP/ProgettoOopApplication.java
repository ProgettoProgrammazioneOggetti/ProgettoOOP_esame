package it.univpm.ProgettoOOP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.univpm.ProgettoOOP.database.Database;

@SpringBootApplication
public class ProgettoOopApplication {

	public static void main(String[] args) {
		Database data=new Database();
		SpringApplication.run(ProgettoOopApplication.class, args);
	}

}
