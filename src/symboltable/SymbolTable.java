package symboltable;
import java.util.HashMap;

/**
 * La classe genera una tabella dei simboli
 * 
 * @author jackx
 *
 */
public class SymbolTable {
	private static HashMap<String, Attributes> table;
	
	/**
	 * Inizializza una nuova tabella dei simboli
	 */
	public static void init() {
		table = new HashMap<String, Attributes>();
	}
	
	/**
	 * Inserisce un nuovo elemento nella tabella.
	 * Se la tabella contiene già l'elemento restituisce false, true altrimenti
	 * 
	 * @param id
	 * @param entry
	 * @return esito operazione
	 */
	public static boolean enter(String id, Attributes entry) {
		return table.put(id, entry) == null;
	}
	
	/**
	 * Cerca e restituisce gli attributi associati ad un identificatore
	 * 
	 * @param id
	 * @return attributi associati
	 */
	public static Attributes lookup(String id) {
		return table.get(id);
	}
	
	/**
	 * Restituisce la tabella in formato stringa
	 * 
	 * @return stringa
	 */
	public static String toStr() {
		String str = table.toString();
		
		return str;
	}
	
	/**
	 * Restituisce la grandezza della tabella
	 * 
	 * @return grandezza
	 */
	public static int size() {
		return (table.size());
	}
}
