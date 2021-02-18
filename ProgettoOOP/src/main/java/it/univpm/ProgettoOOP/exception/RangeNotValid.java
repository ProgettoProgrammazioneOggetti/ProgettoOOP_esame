package it.univpm.ProgettoOOP.exception;

/**
 * @author Paolo, Maurizio
 * 
 * Errore dato se la data finale viene prima di quella iniziale
 */
public class RangeNotValid extends DateException{

	/**
	 * Numero seriale della classe
	 */
	private static final long serialVersionUID = 5;
	/**
	 * Costruttore della classe
	 */
	public RangeNotValid() {
		super();
	}

}
