package model;
/**
 * 
 * @author Dries Janse, Marie Verdonck, Bram Van Asschodt
 *
 */
@SuppressWarnings("serial")
public class DomainException extends RuntimeException {

	public DomainException(String message, Exception exception) {
		super(message, exception);
	}

	public DomainException(String message) {
		super(message);
	}

	public DomainException() {
		super();
	}

}
