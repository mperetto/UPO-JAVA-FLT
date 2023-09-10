package scanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import token.*;
import compilerexception.LexicalException;

public class Scanner {
	final char EOF = (char) -1; // int 65535
	private int riga;
	private PushbackReader buffer;
	private Token currentToken;
	
	private List<Character> skipChars; // ' ', '\n', '\t', '\r', EOF
	private List<Character> letters; // 'a',...'z'
	private List<Character> digits; // '0',...'9'
	
	private HashMap<Character, TokenType> operatorsMap;  //'+', '-', '*', '/', '=', ';'
	private HashMap<String, TokenType> keyWordsMap;  //"print", "float", "int"

	/**
	 * Inizizializza un nuovo scanner per la scansione di un file
	 * 
	 * @param fileName - percorso del file da elaborare
	 * */
	public Scanner(String fileName) throws LexicalException {

		try {
			this.buffer = new PushbackReader(new FileReader(fileName));
			riga = 1;
			currentToken = null;
			init();
		}
		catch (FileNotFoundException e) {
			throw new LexicalException("File non trovato", 0, e);
		}
	}
	
	private void init() {
		
		//inizializzo caratteri da skippare
		skipChars = new LinkedList<>();
		skipChars.add(' ');
		skipChars.add('\n');
		skipChars.add('\t');
		skipChars.add('\r');
		skipChars.add(EOF);
		
		//inizializzo lettere minuscole
		letters = new LinkedList<>();
		for(int i = 97; i <= 122; i++){
            letters.add((char)i);
        }
		//inizializzo lettere maiuscole
		for(int i = 65; i <= 90; i++){
            letters.add((char)i);
        }
		
		//inizializzo numeri
		digits = new LinkedList<>();
		for(int i = 48; i <= 57; i++){
			digits.add((char)i);
		}
		
		//inizializzo gli operatori
		operatorsMap = new HashMap<>();
		operatorsMap.put('+', TokenType.PLUS);
		operatorsMap.put('-', TokenType.MINUS);
		operatorsMap.put('*', TokenType.TIMES);
		operatorsMap.put('/', TokenType.DIV);
		operatorsMap.put('=', TokenType.ASSIGN);
		operatorsMap.put(';', TokenType.SEMI);
		
		//inizializzo le parole chiave
		keyWordsMap = new HashMap<>();
		keyWordsMap.put("print", TokenType.PRINT);
		keyWordsMap.put("float", TokenType.TYFLOAT);
		keyWordsMap.put("int", TokenType.TYINT);
		
	}
	
	/**
	 * Restituisce il prossimo Token senza consumare l'input
	 * 
	 * @return Token
	 * @throws LexicalException 
	 * */
	public Token peekToken() throws LexicalException {
		
		if(currentToken == null)
			currentToken = nextToken();		
		
		return currentToken;
	}

	/**
	 * Restituisce il Token successivo
	 * 
	 * @return Token
	 * @throws LexicalException 
	 * */
	public Token nextToken() throws LexicalException {
		
		if(currentToken != null){
			Token tk = currentToken;
			currentToken = null;
			return tk;
		}

		// nextChar contiene il prossimo carattere dell'input.
		char nextChar = peekChar();

		// Scorro i caratteri di skip
		while(skipChars.contains(nextChar)) {
			
			if(nextChar == '\n')
				riga++;
			
			if(nextChar == EOF) {
				readChar();
				Token tk = new Token(TokenType.EOF, riga);
				return tk;
			}
			readChar();
			nextChar = peekChar();
		}
		
		// Scansiono un numero
		if(digits.contains(nextChar))
			return scanNumber();

		// Scansiono un id
		if(letters.contains(nextChar))
			return scanId();

		if(operatorsMap.containsKey(nextChar)) {
			readChar();
			return new Token(operatorsMap.get(nextChar), riga);
		}

		return nextToken();

	}

	private Token scanNumber() throws LexicalException {
		
		String number = "";
		
		while(digits.contains(peekChar())) {
			number = number + readChar();
		}
		
		if(peekChar() == '.') {// Il numero potrebbe essere float
			int numBeforeDot = number.length();
			number = number + readChar();
			int cont = 0;
			
			while(digits.contains(peekChar())) {
				number = number + readChar();
				cont++;
			}
			
			if(cont > 5 || cont == 0)
				throw new LexicalException("I numeri float devono avere minimo 1 e massimo 5 numeri dopo la virgola", riga);
			else if(letters.contains(peekChar()))
				throw new LexicalException("Un numero float non può contenere lettere", riga);
			else if(numBeforeDot > 1 && number.startsWith("0")) {
				throw new LexicalException("Un numero float non può iniziare con 0", riga);
			}
			
			return new Token(TokenType.FLOAT, riga, number);
		}
		
		if(letters.contains(peekChar()))
			throw new LexicalException("Un numero intero non può contenere lettere", riga);
		
		if(number.charAt(0) == '0' && number.length() > 1)
			throw new LexicalException("un numero intero non può iniziare per 0", riga);
		
		return new Token(TokenType.INT, riga, number);
	}

	private Token scanId() throws LexicalException {
		String id = "";
		
		while(letters.contains(peekChar())) {
			id = id + readChar();
		}
		
		if(digits.contains(peekChar()))
			throw new LexicalException("Un identificatore non può contenere numeri", riga);
		
		if(keyWordsMap.containsKey(id)) {
			return new Token(keyWordsMap.get(id), riga);
		}
		else {
			return new Token(TokenType.ID, riga, id);
		}
	}

	private char readChar() throws LexicalException {
		
		try {
			return ((char) this.buffer.read());
		}
		catch (IOException e) {
			throw new LexicalException("Errore lettura file", riga, e);
		}
	}

	private char peekChar() throws LexicalException {
		
		try {
			char c = (char) buffer.read();
			buffer.unread(c);
			return c;
		}
		catch (IOException e) {
			throw new LexicalException("Errore lettura file", riga, e);
		}
		
	}
}
