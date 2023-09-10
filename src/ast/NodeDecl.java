package ast;

import symboltable.Attributes;
import symboltable.SymbolTable;

public class NodeDecl extends NodeDecSt {
	private NodeId id;
	private LangType type;
	private NodeExpr init;
	
	/**
	 * Inizializza un nodo di tipo dichiarazione
	 * 
	 * @param id
	 * @param lt
	 * @param expr
	 */
	public NodeDecl(NodeId id, LangType lt, NodeExpr expr) {
		this.id = id;
		this.type = lt;
		this.init = expr;
	}
	
	/**
	 * Restituisce il nodo identificatore
	 * 
	 * @return id
	 */
	public NodeId getId() {
		return id;
	}

	/**
	 * Restituisce il tipo di dichiarazione
	 * 
	 * @return tipo
	 */
	public LangType getType() {
		return type;
	}

	/**
	 * Restituisce l'espressione di destra
	 * 
	 * @return espressione
	 */
	public NodeExpr getInit() {
		return init;
	}
	
	public String toString() {
		String lt = "";
		
		switch(type) {
			case INTy: lt = "int"; break;
			case FLOATy: lt = "float"; break;
		}
		
		String expr = "";
		if(init != null) {
			expr = " = " + init.toString();
		}
		
		return lt + " " + id.toString() + expr + ";";
	}
	
	/**
	 * Verifia se è necessario ed è possibile eseguire una conversione automatica sull'espressione
	 * 
	 * @return {true} se la conversione
	 */
	private boolean verifyConversionExpr() {
		if(type == LangType.FLOATy && init.getResType() == TypeDescriptor.Int) {
			init = new NodeConvert(init);
			init.calResType();
			return true;
		}
		else if(type == LangType.INTy && init.getResType() == TypeDescriptor.Float) {
			return false;
		}
		
		return true;
	}

	@Override
	public void calResType() {
		
		boolean exprResTypeErr = false;
		if(init != null) {
			init.calResType();
			if(init.getResType() == TypeDescriptor.Error || !verifyConversionExpr()) {
				exprResTypeErr = true;
			}
		}
		
		Attributes attr = new Attributes(this.type);
		attr.setRegistro(Registri.newRegister());
		id.setDefinition(attr);
		boolean resEnterST = SymbolTable.enter(id.getName(), attr);
		
		this.setResType(TypeDescriptor.Void);
		
		if(!resEnterST || exprResTypeErr) {
			this.setResType(TypeDescriptor.Error);
		}
	}

	@Override
	public void calCodice() {
		if(init != null) {
			init.calCodice();
			
			char registro = id.getDefinition().getRegistro();
			
			String codice = init.getCodice() + " s" + registro + " 0 k";
			
			this.setCodice(codice);
		}
		else {
			this.setCodice("");
		}
	}
}
