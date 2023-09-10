package ast;

public class NodeBinOp extends NodeExpr {

	private LangOper op;
	private NodeExpr left;
	private NodeExpr right;
	
	/**
	 * Inizzializza un nuovo nodo operatore binario
	 * 
	 * @param op il valore della costante
	 * @param left espressione di sinistra
	 * @param right espressione di destra
	 */
	public NodeBinOp(LangOper op, NodeExpr left, NodeExpr right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Restituisce l'operatore dell'operazione
	 * 
	 * @return operatore
	 */
	public LangOper getOp() {
		return op;
	}

	/**
	 * Restituisce l'espressione di sinistra
	 * 
	 * @return espressione sinistra
	 */
	public NodeExpr getLeft() {
		return left;
	}

	/**
	 * Restituisce l'espressione di destra
	 * 
	 * @return espressione destra
	 */
	public NodeExpr getRight() {
		return right;
	}
	
	/**
	 * Restituisce l'operatore
	 * 
	 * @return operatore
	 */
	private String getOper() {
		String oper = "";
		switch(op) {
		case PLUS: oper = "+"; break;
		case MINUS: oper = "-"; break;
		case DIV: oper = "/"; break;
		case TIMES: oper = "*"; break;
		}
		
		return oper;
	}

	@Override
	public String toString() {
		
		String oper = getOper();
		
		return "NodeBinOp [op=" + oper + ", left=" + getLeft().toString() + ", right=" + getRight().toString() + "]";
	}

	@Override
	public void calResType() {
		left.calResType();
		right.calResType();
		
		if(left.getResType() == TypeDescriptor.Int && right.getResType() == TypeDescriptor.Int) {
			this.setResType(TypeDescriptor.Int);
			return;
		}
		
		if(left.getResType() == TypeDescriptor.Float && right.getResType() == TypeDescriptor.Float) {
			this.setResType(TypeDescriptor.Float);
			return;
		}
		
		if(left.getResType() != right.getResType()) {
			if(left.getResType() == TypeDescriptor.Int) {
				left = new NodeConvert(left);
				left.calResType();
			}
			else {
				right = new NodeConvert(right);
				right.calResType();
			}
			
			this.setResType(TypeDescriptor.Float);
		}
		else {
			this.setResType(TypeDescriptor.Error);
		}
	}

	@Override
	public void calCodice() {
		left.calCodice();
		right.calCodice();
		
		String changePrecision = " ";
		if(left.getResType() == TypeDescriptor.Float || right.getResType() == TypeDescriptor.Float) {
			changePrecision = " 5 k ";
		}
		
		String codice = left.getCodice() + right.getCodice() + changePrecision + getOper();
		
		this.setCodice(codice);	
	}

}
