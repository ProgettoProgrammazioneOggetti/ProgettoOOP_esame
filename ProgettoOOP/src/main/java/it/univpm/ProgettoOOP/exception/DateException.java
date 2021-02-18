package it.univpm.ProgettoOOP.exception;

/**
 * @author Paolo, Maurizio
 *
 *Errore generico per problemi con le date nel filtro
 */
public abstract class DateException extends Exception{

	/**
	 * Numero seriale della classe
	 */
	private static final long serialVersionUID = 0;
	/**
	 * Costruttore della classe DateException
	 */
	public DateException() {
		super();
	}

}
