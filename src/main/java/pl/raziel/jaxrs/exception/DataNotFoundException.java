package pl.raziel.jaxrs.exception;

public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -16308581690687761L;

	public DataNotFoundException(String message) {
		super(message);
	}

}
