package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ast.NodeProgram;
import compilerexception.SyntacticException;
import parser.Parser;
import scanner.Scanner;

class TestParser {

	@Test
	void testDeclarations() {
		
		assertDoesNotThrow(() -> {
			Parser p = new Parser(new Scanner("src/test/data/testParser/testParserDich.txt"));
			assertDoesNotThrow(() -> p.parse());
		});
	}
	
	@Test
	void testAssign() {
		
		assertDoesNotThrow(() -> {
			Parser p = new Parser(new Scanner("src/test/data/testParser/testParserAssegnazione.txt"));
			assertDoesNotThrow(() -> p.parse());
		});
	}
	
	@Test
	void testExceptions() {
		
		assertDoesNotThrow(() -> {
			Parser p0 = new Parser(new Scanner("src/test/data/testParser/testParserEcc_0.txt"));
			assertThrows(SyntacticException.class, () -> p0.parse());
			
			Parser p1 = new Parser(new Scanner("src/test/data/testParser/testParserEcc_1.txt"));
			assertThrows(SyntacticException.class, () -> p1.parse());
			
			Parser p2 = new Parser(new Scanner("src/test/data/testParser/testParserEcc_2.txt"));
			assertThrows(SyntacticException.class, () -> p2.parse());
			
			Parser p3 = new Parser(new Scanner("src/test/data/testParser/testParserEcc_3.txt"));
			assertThrows(SyntacticException.class, () -> p3.parse());
			
			Parser p4 = new Parser(new Scanner("src/test/data/testParser/testParserEcc_4.txt"));
			assertThrows(SyntacticException.class, () -> p4.parse());
			
			Parser p5 = new Parser(new Scanner("src/test/data/testParser/testParserEcc_5.txt"));
			assertThrows(SyntacticException.class, () -> p5.parse());
		});
	}
	
	@Test
	void testCorrect() {
		
		assertDoesNotThrow(() -> {
			Parser p0, p1;
			
			p0 = new Parser(new Scanner("src/test/data/testParser/testParserCorretto1.txt"));
			assertDoesNotThrow(() -> p0.parse());
			
			p1 = new Parser(new Scanner("src/test/data/testParser/testParserCorretto2.txt"));
			assertDoesNotThrow(() -> p1.parse());
		});
	}
	
	@Test
	void testASTDecl() {
		
		assertDoesNotThrow(() -> {
			Parser p = new Parser(new Scanner("src/test/data/testParser/testParserDich.txt"));
			NodeProgram prg = p.parse();
			assertEquals(prg.toString(), "Program: [int a;, int b;, int sdsd;, float c;, int a;, print aaa;]");
		});
	}
	
	@Test
	void testASTAssign() {
		
		assertDoesNotThrow(() -> {
			Parser p = new Parser(new Scanner("src/test/data/testParser/testParserAssegnazione.txt"));
			
			NodeProgram prg = p.parse();
			assertEquals(prg.toString(), "Program: [a = NodeBinOp [op=+, left=b, right=(INTy)3], c = NodeBinOp [op=/, left=(INTy)3, right=(INTy)23], a = NodeBinOp [op=+, left=(INTy)12, right=NodeBinOp [op=-, left=b, right=NodeBinOp [op=/, left=(INTy)24, right=NodeBinOp [op=*, left=(INTy)2, right=(INTy)67]]]], a = (INTy)2, c = NodeBinOp [op=+, left=NodeBinOp [op=*, left=a, right=(INTy)32], right=NodeBinOp [op=-, left=(INTy)23, right=NodeBinOp [op=+, left=(INTy)11, right=f]]]]");
		});
	}
	
	@Test
	void testASTCorrect() {
		
		assertDoesNotThrow(() -> {
			Parser p;
			NodeProgram prg;
			
			p = new Parser(new Scanner("src/test/data/testParser/testParserCorretto1.txt"));
			
			prg = p.parse();
			assertEquals(prg.toString(), "Program: [print stampa;, float numberfloat;, int floati;, a = NodeBinOp [op=+, left=(INTy)5, right=(INTy)3], b = NodeBinOp [op=+, left=a, right=(INTy)5]]");
			
			p = new Parser(new Scanner("src/test/data/testParser/testParserCorretto2.txt"));
			prg = p.parse();
			assertEquals(prg.toString(), "Program: [int num;, num = (INTy)5, num = id, num = NodeBinOp [op=+, left=id, right=(FLOATy)5.0], num = NodeBinOp [op=*, left=id, right=(INTy)5], num = NodeBinOp [op=*, left=id, right=id], num = NodeBinOp [op=+, left=id, right=NodeBinOp [op=-, left=(INTy)5, right=NodeBinOp [op=*, left=(INTy)8, right=NodeBinOp [op=/, left=(FLOATy)6.0, right=(INTy)2]]]], num = NodeBinOp [op=-, left=NodeBinOp [op=*, left=id, right=(INTy)5], right=NodeBinOp [op=+, left=NodeBinOp [op=*, left=(FLOATy)8.0, right=(INTy)6], right=(INTy)2]], print ok;]");
		});
	}
	
}
