package compilerexception;

public class LexicalException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4834822409861314783L;
	private int riga;
	
	public LexicalException(String message, int riga) {
		super(message);
		this.riga = riga;
	}
	public LexicalException(String message, int riga, Throwable ex) {
		super(message, ex);
		this.riga = riga;
	}
	
	public int getRiga() {
		return riga;
	}
}