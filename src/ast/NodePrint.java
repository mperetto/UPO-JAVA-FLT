package ast;

import symboltable.Attributes;

public class NodePrint extends NodeStm {
	
	private NodeId id;
	
	/**
	 * Inizializza un nodo di tipo stampa
	 * 
	 * @param id
	 */
	public NodePrint(NodeId id) {
		this.id = id;
	}
	
	/**
	 * Restituisce il nodo identificatore
	 * 
	 * @return id
	 */
	public NodeId getId() {
		return id;
	}

	@Override
	public String toString() {
		return "print " + id.toString() + ";";
	}

	@Override
	public void calResType() {
		id.calResType();
		if(id.getResType() != TypeDescriptor.Error) {
			this.setResType(TypeDescriptor.Void);
		}
		else {
			this.setResType(TypeDescriptor.Error);
		}
	}

	@Override
	public void calCodice() {
		Attributes def = id.getDefinition();
		
		char reg = def.getRegistro();
		
		String code = " l" + reg + " p P";
		
		this.setCodice(code);
	}
}
