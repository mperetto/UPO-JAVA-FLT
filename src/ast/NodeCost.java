package ast;

public class NodeCost extends NodeExpr {

	private String value;
	private LangType type;
	
	/**
	 * Inizzializza un nuovo nodo costante
	 * 
	 * @param value il valore della costante
	 * @param type il tipo di costante
	 */
	public NodeCost(String value, LangType type) {
		this.value = value;
		this.type = type;
	}
	
	/**
	 * Restituisce il valore della costante
	 * 
	 * @return valore
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Restituisce il tipo della costante
	 * 
	 * @return tipo
	 */
	public LangType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "(" + getType().toString() + ")" + getValue();
	}

	@Override
	public void calResType() {
		
		if(type == LangType.INTy) {
			this.setResType(TypeDescriptor.Int);
		}
		else {
			this.setResType(TypeDescriptor.Float);
		}		
	}

	@Override
	public void calCodice() {
		String codice = " " + value;
		
		this.setCodice(codice);
	}

}
