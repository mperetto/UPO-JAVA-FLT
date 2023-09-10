package compilerexception;

import token.Token;

public class SyntacticException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6769918482921655689L;
	private int riga;
	private Token token;
	
	public SyntacticException(String message, Token tk, int riga) {
		super(message);
		this.token = tk;
		this.riga = riga;
	}
	
	public SyntacticException(String message, Throwable ex) {
		super(message, ex);
	}
	
	public Token getToken() {
		return token;
	}
	
	public int getRiga() {
		return riga;
	}
}
