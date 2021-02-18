package it.univpm.ProgettoOOP.exception;

/**
 * @author Paolo, Maurizio
 *
 *Errore dato in output se il database inserito manualmente non è corretto
 */
public class DatabaseNotValid extends Exception{
	
	/**
	 * Numero seriale della classe
	 */
	private static final long serialVersionUID=1;

	/**
	 * Costruttore della classe DatabaseNotValid
	 */
	public DatabaseNotValid() {
		super();
		}

}
