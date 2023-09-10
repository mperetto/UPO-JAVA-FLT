package ast;

import symboltable.Attributes;
import symboltable.SymbolTable;

public class NodeId extends NodeAST {
	private String name;
	private Attributes definition;
	
	/**
	 * Inizializza un nodo identificatore
	 * 
	 * @param name
	 */
	public NodeId(String name) {
		this.name = name;
	}
	
	/**
	 * Restituisce l'etichetta dell'identificatore
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Restituisce la definizione del nodo (registro e tipo)
	 * 
	 * @return definizione
	 */
	public Attributes getDefinition() {
		return this.definition;
	}
	
	/**
	 * Setta la definizione del nodo identificatore
	 * 
	 * @param definition
	 */
	public void setDefinition(Attributes definition) {
		this.definition = definition;
	}
	
	public String toString() {
		return getName();
	}

	@Override
	public void calResType() {
		
		Attributes attr = SymbolTable.lookup(name);
		
		if(attr == null) {
			this.setResType(TypeDescriptor.Error);
		}
		else {
			switch(attr.getTipo()) {
			case INTy: this.setResType(TypeDescriptor.Int); break;
			case FLOATy: this.setResType(TypeDescriptor.Float); break;
			}
			
			this.definition = attr;
		}
	}

	@Override
	public void calCodice() {
		this.setCodice("");
	}
}
