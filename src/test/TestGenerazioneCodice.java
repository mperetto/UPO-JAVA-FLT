package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ast.NodeProgram;
import ast.Registri;
import parser.Parser;
import scanner.Scanner;
import symboltable.SymbolTable;

class TestGenerazioneCodice {
	
	@BeforeEach
	void init() {
		Registri.init();
		SymbolTable.init();
	}
	
	@Test
	void testDecl() {
		
		Parser p;
		NodeProgram prg;
		try {
			p = new Parser(new Scanner("src/test/data/testGenCod/testDecl.txt"));
			
			prg = p.parse();
			prg.calResType();
			prg.calCodice();
			
			assertEquals(prg.getCodice().trim(), "".trim());
		}
		catch (Exception e) {
			fail("Il codice non doveva generare eccezzioni");
		}
	}

	@Test
	void testAssign() {
		
		Parser p;
		NodeProgram prg;
		try {
			p = new Parser(new Scanner("src/test/data/testGenCod/testAssign.txt"));
			
			prg = p.parse();
			prg.calResType();
			prg.calCodice();
			
			assertEquals(prg.getCodice().trim(), "2 sa 0 k  2 3 la / + sb 0 k lb p P".trim());
		}
		catch (Exception e) {
			fail("Il codice non doveva generare eccezzioni");
		}
	}
	
	@Test
	void testPrint() {
		
		Parser p;
		NodeProgram prg;
		try {
			p = new Parser(new Scanner("src/test/data/testGenCod/testPrint.txt"));
			
			prg = p.parse();
			prg.calResType();
			prg.calCodice();
			
			assertEquals(prg.getCodice().trim(), "3 2 + sa 0 k la p P".trim());
		}
		catch (Exception e) {
			fail("Il codice non doveva generare eccezzioni");
		}
	}
	
	@Test
	void testGenerale() {
		
		Parser p;
		NodeProgram prg;
		try {
			p = new Parser(new Scanner("src/test/data/testGenCod/testGenerale.txt"));
			
			prg = p.parse();
			prg.calResType();
			prg.calCodice();
			
			assertEquals(prg.getCodice().trim(), "1.0 6 5 k / sb 0 k lb p P  1 6 / sa 0 k la p P".trim());
		}
		catch (Exception e) {
			fail("Il codice non doveva generare eccezzioni");
		}
	}
}
