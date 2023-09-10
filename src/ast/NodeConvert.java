package ast;

public class NodeConvert extends NodeExpr {
	private NodeExpr expression;
	
	/**
	 * Inizializza un nodo di tipo conversione
	 * 
	 * @param expr
	 */
	public NodeConvert(NodeExpr expr) {
		this.expression = expr;
	}

	@Override
	public void calResType() {
		this.setResType(TypeDescriptor.Float);		
	}
	
	@Override
	public String toString() {
		return expression.toString();
	}

	@Override
	public void calCodice() {
		expression.calCodice();
		
		String codice = expression.getCodice();
		
		this.setCodice(codice);
	}

}
