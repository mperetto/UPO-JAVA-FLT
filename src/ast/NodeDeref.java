package ast;

public class NodeDeref extends NodeExpr {
	
	private NodeId id;
	
	/**
	 * Inizializza un nodo di riferimento a id
	 * 
	 * @param id
	 */
	public NodeDeref(NodeId id) {
		this.id = id;
	}

	/**
	 * Restituisce l'identificatore
	 * 
	 * @return id
	 */
	public NodeId getId() {
		return id;
	}

	@Override
	public String toString() {
		return getId().toString();
	}

	@Override
	public void calResType() {
		id.calResType();
		
		this.setResType(id.getResType());
	}

	@Override
	public void calCodice() {
		char registro = id.getDefinition().getRegistro();
		
		String codice = " l" + registro;
		
		this.setCodice(codice);
	}

}
