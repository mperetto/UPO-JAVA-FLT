package ast;

import java.util.ArrayList;

public class NodeProgram extends NodeAST {
	private final ArrayList<NodeDecSt> decSts;
	
	/**
	 * Inizializza un nuovo programma
	 * 
	 * @param decSts
	 */
	public NodeProgram(ArrayList<NodeDecSt> decSts) {
		this.decSts = decSts;
	}
	
	/**
	 * Restituisce tutti i nodi del programma
	 * 
	 * @return istruzioni programma
	 */
	public ArrayList<NodeDecSt> getDecSts() {
		return decSts;
	}
	
	@Override
	public String toString() {
		return "Program: " + decSts.toString();
	}

	@Override
	public void calResType() {
		
		boolean error = false;
		for(int i = 0; i < decSts.size(); i++) {
			decSts.get(i).calResType();
			if(decSts.get(i).getResType() == TypeDescriptor.Error) {
				error = true;
			}
		}
		
		if(error) {
			this.setResType(TypeDescriptor.Error);
		}
		else {
			this.setResType(TypeDescriptor.Void);
		}
	}

	@Override
	public void calCodice() {
		
		if(this.getResType() == TypeDescriptor.Error) {
			this.setCodice("");
			return;
		}
		
		String codice = "";
		for(int i = 0; i < decSts.size(); i++) {
			decSts.get(i).calCodice();
			codice = codice + decSts.get(i).getCodice();
		}
		
		this.setCodice(codice);
	}
}
