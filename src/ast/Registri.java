package ast;

import java.util.ArrayList;
import java.util.Arrays;

public class Registri {
	private static ArrayList<Character> registri;
	
	/**
	 * Inizializza i registri
	 */
	public static void init() {
		Character[] regs = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		
		registri = new ArrayList<Character>();
		registri.addAll(Arrays.asList(regs));
	}
	
	/**
	 * Riserva un nuovo registro
	 * 
	 * @return registro riservato
	 */
	public static Character newRegister() {
		
		if(registri.size() == 0) return null;
		
		return registri.remove(0);
	}
	
	/**
	 * Mostra i registri in formato stringa
	 * 
	 * @return stringa registri
	 */
	public static String toStr() {
		return registri.toString();
	}
}
