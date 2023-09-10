package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ast.NodeProgram;
import ast.Registri;
import ast.TypeDescriptor;
import parser.Parser;
import scanner.Scanner;
import symboltable.SymbolTable;

class TestTypeChecking {
	
	@BeforeEach
	void init() {
		SymbolTable.init();
		Registri.init();
	}

	@Test
	void testDichRipetute() {
		
		assertDoesNotThrow(() -> {
			Parser p;
			NodeProgram prg;
			
			p = new Parser(new Scanner("src/test/data/testTypeCheck/1_dicRipetute.txt"));
			
			prg = p.parse();
			prg.calResType();
			
			assertEquals(prg.getResType(), TypeDescriptor.Error);
		});
	}
	
	@Test
	void testCorretto() {
		
		assertDoesNotThrow(() -> {
			Parser p;
			NodeProgram prg;
			
			p = new Parser(new Scanner("src/test/data/testTypeCheck/2_fileCorrect.txt"));
			
			prg = p.parse();
			prg.calResType();
			
			assertEquals(prg.getResType(), TypeDescriptor.Void);
		});
	}
	
	@Test
	void testIdNoDich() {
		
		assertDoesNotThrow(() -> {
			Parser p;
			NodeProgram prg;
			
			p = new Parser(new Scanner("src/test/data/testTypeCheck/3_IdNotDeclare.txt"));
			
			prg = p.parse();
			prg.calResType();
			
			assertEquals(prg.getResType(), TypeDescriptor.Error);
		});
	}
	
	@Test
	void testErrorAssConvert() {
		
		assertDoesNotThrow(() -> {
			Parser p;
			NodeProgram prg;
			
			p = new Parser(new Scanner("src/test/data/testTypeCheck/errorAssignConvert.txt"));
			
			prg = p.parse();
			prg.calResType();
			
			assertEquals(prg.getResType(), TypeDescriptor.Error);
		});
	}
	
	@Test
	void testErrorOp() {
		
		assertDoesNotThrow(() -> {
			Parser p;
			NodeProgram prg;
			
			p = new Parser(new Scanner("src/test/data/testTypeCheck/errorOp.txt"));
			
			prg = p.parse();
			prg.calResType();
			
			assertEquals(prg.getResType(), TypeDescriptor.Error);
		});
	}
	
	@Test
	void testFileCorrect2() {
		
		assertDoesNotThrow(() -> {
			Parser p;
			NodeProgram prg;
			
			p = new Parser(new Scanner("src/test/data/testTypeCheck/fileCorrect2.txt"));
			
			prg = p.parse();
			prg.calResType();
			
			assertEquals(prg.getResType(), TypeDescriptor.Void);
		});
	}
	
	@Test
	void testGenerale() {
		
		assertDoesNotThrow(() -> {
			Parser p;
			NodeProgram prg;
			
			p = new Parser(new Scanner("src/test/data/testTypeCheck/testGenerale.txt"));
			
			prg = p.parse();
			prg.calResType();
			
			assertEquals(prg.getResType(), TypeDescriptor.Void);
		});
	}
	
	@Test
	void testGenerale2() {
		
		assertDoesNotThrow(() -> {
			Parser p;
			NodeProgram prg;
			
			p = new Parser(new Scanner("src/test/data/testTypeCheck/testGenerale2.txt"));
			
			prg = p.parse();
			prg.calResType();
			
			assertEquals(prg.getResType(), TypeDescriptor.Void);
		});
	}

}
