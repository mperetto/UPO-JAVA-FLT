package symboltable;
import ast.LangType;

/**
 * Classe contenente gli attributi di un identificatore
 * 
 * @author jackx
 *
 */
public class Attributes {
	private LangType tipo;
	private char registro;
	
	/**
	 * Inizializza un nuovo contenitore per gli attributi
	 * 
	 * @param type
	 */
	public Attributes(LangType type) {
		this.tipo = type;
	}

	/**
	 * Restituisce il tipo
	 * 
	 * @return tipo
	 */
	public LangType getTipo() {
		return tipo;
	}

	/**
	 * Setta il tipo
	 * 
	 * @param tipo
	 */
	public void setTipo(LangType tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Restituisce il registro associato
	 * 
	 * @return registro
	 */
	public char getRegistro() {
		return registro;
	}
	
	/**
	 * Setta il registro
	 * 
	 * @param registro
	 */
	public void setRegistro(char registro) {
		this.registro = registro;
	}
	
	/**
	 * Restituisce gli attributi in formato stringa
	 */
	public String toString() {
		return " | " + tipo.toString() + " | " + registro + " | ";
	}
}
