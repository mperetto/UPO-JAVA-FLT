package parser;

import java.util.ArrayList;

import ast.LangOper;
import ast.LangType;
import ast.NodeAssign;
import ast.NodeBinOp;
import ast.NodeCost;
import ast.NodeDecSt;
import ast.NodeDecl;
import ast.NodeDeref;
import ast.NodeExpr;
import ast.NodeId;
import ast.NodePrint;
import ast.NodeProgram;
import ast.NodeStm;
import compilerexception.LexicalException;
import compilerexception.SyntacticException;
import scanner.Scanner;
import token.Token;
import token.TokenType;

/**
 * La classe gestisce il parsing del programma
 * 
 * @author jackx
 *
 */
public class Parser {
	private Scanner scanner;
	
	/**
	 * Inizializza una nuova istanza del parser
	 * 
	 * @param scanner
	 */
	public Parser(Scanner scanner) {
		this.scanner = scanner;
	}
	
	private Token match(TokenType type) throws SyntacticException {
		
		try {
			Token tk = scanner.peekToken();
			
			if(type.equals(tk.getTipo())) return scanner.nextToken();
			throw new SyntacticException("Atteso token di tipo: " + type.toString() + " Ottenuto: " + tk.getTipo().toString(), tk, tk.getRiga());
		}
		catch (LexicalException e) {
			throw new SyntacticException("Errore sintattico: ", e);
		}
		
	}
	
	/**
	 * Avvia il processo di parsing
	 * 
	 * @return
	 * @throws SyntacticException
	 */
	public NodeProgram parse() throws SyntacticException {
		try {
			return parsePrg();
		}
		catch (LexicalException e) {
			throw new SyntacticException("Errore lessicale: " + e.getMessage(), e);
		}
		
	}
	
	private NodeProgram parsePrg() throws LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		
		switch(tk.getTipo()) {
			case TYFLOAT:
			case TYINT:
			case PRINT:
			case ID:
			case EOF:
				ArrayList<NodeDecSt> prg = parseDSs();
				match(TokenType.EOF);
				return new NodeProgram(prg);
			default: throw new SyntacticException("Programma non corretto sintatticamente", tk, tk.getRiga());
		}
	}
	
	private ArrayList<NodeDecSt> parseDSs() throws LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		
		switch(tk.getTipo()) {
			case TYFLOAT:
			case TYINT:
			{
				NodeDecl dec = parseDcl();
				ArrayList<NodeDecSt> decSt = parseDSs();
				decSt.add(0, dec);
				return decSt;
			}
			case PRINT:
			case ID:
			{
				NodeStm stm = parseStm();
				ArrayList<NodeDecSt> decSt = parseDSs();
				decSt.add(0, stm);
				return decSt;
			}
			case EOF:
				return new ArrayList<NodeDecSt>();
			default: throw new SyntacticException("Errore sintattico dichiarazione", tk, tk.getRiga());
				
		}
	}
	
	private NodeDecl parseDcl() throws LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		
		switch(tk.getTipo()) {
			case TYFLOAT:
			{
				LangType type = parseTy();
				String id = match(TokenType.ID).getVal();
				NodeExpr expr = parseDclP();
				return new NodeDecl(new NodeId(id), type, expr);
			}
			case TYINT:
			{
				LangType type = parseTy();
				String id = match(TokenType.ID).getVal();
				NodeExpr expr = parseDclP();
				return new NodeDecl(new NodeId(id), type, expr);
			}
			default: throw new SyntacticException("Errore dichiarazione", tk, tk.getRiga());
		}
	}
	
	private NodeExpr parseDclP() throws LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		
		switch(tk.getTipo()) {
			case SEMI:
				match(TokenType.SEMI);
				return null;
			case ASSIGN:
				match(TokenType.ASSIGN);
				NodeExpr exp = parseExp();
				match(TokenType.SEMI);
				return exp;
				
			default: throw new SyntacticException("Manca (;) al termine dell'istruzione", tk, tk.getRiga());
		}
	}
	
	private NodeStm parseStm() throws LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		
		switch(tk.getTipo()) {
			case PRINT:
			{
				match(TokenType.PRINT);
				String id = match(TokenType.ID).getVal();
				match(TokenType.SEMI);
				return new NodePrint(new NodeId(id));
			}
			case ID:
			{
				String id = match(TokenType.ID).getVal();
				match(TokenType.ASSIGN);
				NodeExpr exp = parseExp();
				match(TokenType.SEMI);
				return new NodeAssign(new NodeId(id), exp);
			}
			default: throw new SyntacticException("Errore sintattico dichiarazione", tk, tk.getRiga());
		}
	}
	
	private NodeExpr parseExp() throws LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		
		switch(tk.getTipo()) {
			case INT:
			case FLOAT:
			case ID:
				NodeExpr left = parseTr();
				NodeExpr exp = parseExpP(left);
				return exp;
			default: throw new SyntacticException("Errore dichiarazione espressione", tk, tk.getRiga());
		}
	}
	
	private NodeExpr parseExpP(NodeExpr left) throws LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		
		switch(tk.getTipo()) {
			case PLUS:
			{
				match(TokenType.PLUS);
				NodeExpr exp1 = parseTr();
				NodeExpr exp2 = parseExpP(exp1);
				return new NodeBinOp(LangOper.PLUS, left, exp2);
			}
			case MINUS:
			{
				match(TokenType.MINUS);
				NodeExpr exp1 = parseTr();
				NodeExpr exp2 = parseExpP(exp1);
				return new NodeBinOp(LangOper.MINUS, left, exp2);
			}
			case SEMI:
				return left;
			default: throw new SyntacticException("Errore dichiarazione espressione", tk, tk.getRiga());
		}
	}
	
	private NodeExpr parseTr() throws LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		
		switch(tk.getTipo()) {
			case INT:
			case FLOAT:
			case ID:
				NodeExpr left = parseVal();
				NodeExpr exp = parseTrP(left);
				return exp;
			default: throw new SyntacticException("Errore dichiarazione espressione matematica", tk, tk.getRiga());
				
		}
	}
	
	private NodeExpr parseTrP(NodeExpr left) throws LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		
		switch(tk.getTipo()) {
			case TIMES:
			{
				match(TokenType.TIMES);
				NodeExpr exp1 = parseVal();
				NodeExpr exp2 = parseTrP(exp1);
				return new NodeBinOp(LangOper.TIMES, left, exp2);
			}
			case DIV:
			{
				match(TokenType.DIV);
				NodeExpr exp1 = parseVal();
				NodeExpr exp2 = parseTrP(exp1);
				return new NodeBinOp(LangOper.DIV, left, exp2);
			}
			case PLUS:
			case MINUS:
			case SEMI:
				return left;
			default: throw new SyntacticException("Errore dichiarazione espressione matematica", tk, tk.getRiga());
		}
	}
	
	private LangType parseTy() throws LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		
		switch(tk.getTipo()) {
			case TYINT:
				match(TokenType.TYINT);
				return LangType.INTy;
			case TYFLOAT:
				match(TokenType.TYFLOAT);
				return LangType.FLOATy;
			default: throw new SyntacticException("Errore dichiarazione espressione matematica", tk, tk.getRiga());
		}
	}
	
	private NodeExpr parseVal() throws LexicalException, SyntacticException {
		Token tk = scanner.peekToken();
		
		switch(tk.getTipo()) {
			case INT:
			{
				String val = match(TokenType.INT).getVal();
				return new NodeCost(val, LangType.INTy);
			}
			case FLOAT:
			{
				String val = match(TokenType.FLOAT).getVal();
				return new NodeCost(val, LangType.FLOATy);
			}
			case ID:
			{
				String id = match(TokenType.ID).getVal();
				return new NodeDeref(new NodeId(id));
			}
			default: throw new SyntacticException("Errore, valore non riconosciuto", tk, tk.getRiga());
		}
	}
}
