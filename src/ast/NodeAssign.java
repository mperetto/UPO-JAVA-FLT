package ast;

public class NodeAssign extends NodeStm {
	
	private NodeId id;
	private NodeExpr expr;
	
	/**
	 * Inizializza un nuovo nodo assegnamento
	 * 
	 * @param id identificatore di sinistra
	 * @param expr espressione da assegnare
	 */
	public NodeAssign(NodeId id, NodeExpr expr) {
		this.id = id;
		this.expr = expr;
	}

	/**
	 * Restituisce l'espressione assegnata
	 * 
	 * @return espressione
	 */
	public NodeExpr getExpr() {
		return expr;
	}

	/**
	 * Restituisce l'identificatore dell'assegnamento
	 * 
	 * @return identificatore
	 */
	public NodeId getId() {
		return id;
	}

	@Override
	public String toString() {
		return getId().toString() + " = " + getExpr().toString();
	}

	@Override
	public void calResType() {
		id.calResType();
		expr.calResType();
		
		if(id.getResType() == TypeDescriptor.Error || expr.getResType() == TypeDescriptor.Error) {
			this.setResType(TypeDescriptor.Error);
			return;
		}
		
		if(id.getResType() == expr.getResType()) {
			this.setResType(id.getResType());
			return;
		}
		
		if(id.getResType() == TypeDescriptor.Float && expr.getResType() == TypeDescriptor.Int) {
			expr = new NodeConvert(expr);
			expr.calResType();
			this.setResType(TypeDescriptor.Float);
			return;
		}
		
		this.setResType(TypeDescriptor.Error);
	}

	@Override
	public void calCodice() {
		char registro = id.getDefinition().getRegistro();
		expr.calCodice();
		
		String codice = " " + expr.getCodice() + " s" + registro + " 0 k";
		
		this.setCodice(codice);
	}

}
