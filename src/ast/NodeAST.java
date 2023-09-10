package ast;

public abstract class NodeAST {
	
	private TypeDescriptor resType;
	private String codice;
	
	/**
	 * Calcola il tipo risultante
	 */
	public abstract void calResType();
	
	/**
	 * Genera il codice eseguibile
	 */
	public abstract void calCodice();
	
	/**
	 * Restituisce il tipo risultante
	 * 
	 * @return tipo risultante
	 */
	public TypeDescriptor getResType() {
		return resType;
	}
	
	/**
	 * Imposta il tipo risultante del nodo
	 * 
	 * @param resType
	 */
	public void setResType(TypeDescriptor resType) {
		this.resType = resType;
	}
	
	/**
	 * Setta il codice eseguibile
	 * 
	 * @param codice
	 */
	protected void setCodice(String codice) {
		this.codice = codice;
	}
	
	/**
	 * Restituisce il codice eseguibile
	 * 
	 * @return il codice sorgente
	 */
	public String getCodice() {
		return codice;
	}
	
	/**
	 * Restituisce il nodo in formato stringa
	 * 
	 * @return rappresentazione del nodo
	 */
	public abstract String toString();
}
