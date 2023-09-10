package token;

public class Token {

	private int riga;
	private TokenType tipo;
	private String val;
	
	/**
	 * Crea un nuovo Token comprensivo di valore
	 * 
	 * @param tipo - Tipo Token
	 * @param riga - Riga alla quale compare
	 * @param val - Valore contenuto
	 */
	public Token(TokenType tipo, int riga, String val) {
		this.tipo = tipo;
		this.riga = riga;
		this.val = val;
	}
	
	/**
	 * Crea un nuovo Token sprovvisto di valore
	 * 
	 * @param tipo - Tipo Token
	 * @param riga - Riga alla quale compare
	 */
	public Token(TokenType tipo, int riga) {
		this.tipo = tipo;
		this.riga = riga;
		this.val = "";
	}

    /**
     * Restituisce il numero di riga nella quale compare il Token
     * 
     * @return numero riga
     */
    public int getRiga() {
    	return riga;
    }
    
    /**
     * Restituisce la tipologia alla quale appartiene il Token
     * 
     * @return tipologia Token
     */
    public TokenType getTipo() {
    	return tipo;
    }
    
    /**
     * Restituisce il valore contenuto dal Token
     * 
     * @return valore Token
     */
    public String getVal() {
    	return val;
    }
    
    /**
     * Ritorna il Token in formato stringa
     * 
     * @return stringa rappresentante il Token
     */
	public String toString() {
		String tipo = "", s;
		Integer riga = this.riga;
		
		switch(this.tipo) {
			case TYINT: 	tipo = "TYINT"; 	break;
			case TYFLOAT: 	tipo = "TYFLOAT"; 	break;
			case PRINT: 	tipo = "PRINT"; 	break;
			case ID: 		tipo = "ID"; 		break;
			case INT: 		tipo = "INT"; 		break;
			case FLOAT: 	tipo = "FLOAT"; 	break;
			case ASSIGN: 	tipo = "ASSIGN"; 	break;
			case PLUS: 		tipo = "PLUS"; 		break;
			case MINUS: 	tipo = "MINUS"; 	break;
			case TIMES: 	tipo = "TIMES"; 	break;
			case DIV: 		tipo = "DIV"; 		break;
			case SEMI: 		tipo = "SEMI"; 		break;
			case EOF: 		tipo = "EOF"; 		break;
		}
		
		if(this.val.equals(""))
			s = "<"+tipo+",r:"+riga.toString()+">";
		else
			s = "<"+tipo+",r:"+riga.toString()+","+this.val+">";
		
		return s;
	}
}
