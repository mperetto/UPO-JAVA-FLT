package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import compilerexception.LexicalException;
import scanner.*;
import token.Token;
import token.TokenType;

class TestScanner {
	
	@Test
	void testEOF() {
		
		assertDoesNotThrow(() -> {
			Scanner s = new Scanner("src/test/data/testScanner/testEOF.txt");
			Token tk = s.nextToken();
			
			assertEquals(tk.getTipo(), TokenType.EOF);
			assertEquals(tk.toString(), "<EOF,r:3>");
		});			
	}
	
	@Test
	void testID() {
		
		assertDoesNotThrow(() -> {
			Scanner s = new Scanner("src/test/data/testScanner/testID.txt");
			Token tk = s.nextToken();
			
			assertEquals(tk.getTipo(), TokenType.ID);
			assertEquals(tk.toString(), "<ID,r:1,jskjdsfhkjdshkf>");
			
			tk = s.nextToken();
			assertEquals(tk.getTipo(), TokenType.ID);
			assertEquals(tk.toString(), "<ID,r:2,printl>");
			
			tk = s.nextToken();
			assertEquals(tk.getTipo(), TokenType.ID);
			assertEquals(tk.toString(), "<ID,r:4,hhhjj>");
		});		
	}
	
	@Test
	void testScanId() {
		
		assertDoesNotThrow(() -> {
			Scanner s = new Scanner("src/test/data/testScanner/testScanId.txt");
			Token tk = s.nextToken();
			
			assertEquals(tk.getTipo(), TokenType.TYINT);
			assertEquals(tk.toString(), "<TYINT,r:1>");
			
			tk = s.nextToken();
			assertEquals(tk.getTipo(), TokenType.TYFLOAT);
			assertEquals(tk.toString(), "<TYFLOAT,r:2>");
			
			tk = s.nextToken();
			assertEquals(tk.getTipo(), TokenType.PRINT);
			assertEquals(tk.toString(), "<PRINT,r:3>");
			
			tk = s.nextToken();
			assertEquals(tk.getTipo(), TokenType.ID);
			assertEquals(tk.toString(), "<ID,r:4,nome>");
			
			tk = s.nextToken();
			assertEquals(tk.getTipo(), TokenType.ID);
			assertEquals(tk.toString(), "<ID,r:5,intnome>");
			
			tk = s.nextToken();
			assertEquals(tk.getTipo(), TokenType.TYINT);
			assertEquals(tk.toString(), "<TYINT,r:6>");
			
			tk = s.nextToken();
			assertEquals(tk.getTipo(), TokenType.ID);
			assertEquals(tk.toString(), "<ID,r:6,nome>");
		});
	}
	
	@Test
	void testKeyWords() {
		
		assertDoesNotThrow(() -> {
			Scanner s = new Scanner("src/test/data/testScanner/testKeywords.txt");
			Token tk = s.nextToken();
			
			assertEquals(tk.getTipo(), TokenType.PRINT);
			assertEquals(tk.toString(), "<PRINT,r:2>");
			
			tk = s.nextToken();
			assertEquals(tk.getTipo(), TokenType.TYFLOAT);
			assertEquals(tk.toString(), "<TYFLOAT,r:2>");
			
			tk = s.nextToken();
			assertEquals(tk.getTipo(), TokenType.TYINT);
			assertEquals(tk.toString(), "<TYINT,r:5>");
		});
	}
	
	@Test
	void testOperators() {
		
		assertDoesNotThrow(() -> {
			Scanner s = new Scanner("src/test/data/testScanner/testOperators.txt");
			Token tk = s.nextToken();
			
			assertEquals(tk.getTipo(), TokenType.PLUS);
			assertEquals(tk.toString(), "<PLUS,r:1>");
			
			tk = s.nextToken();
			assertEquals(tk.getTipo(), TokenType.MINUS);
			assertEquals(tk.toString(), "<MINUS,r:2>");
			
			tk = s.nextToken();
			assertEquals(tk.getTipo(), TokenType.TIMES);
			assertEquals(tk.toString(), "<TIMES,r:2>");
			
			tk = s.nextToken();
			assertEquals(tk.getTipo(), TokenType.DIV);
			assertEquals(tk.toString(), "<DIV,r:3>");
			
			tk = s.nextToken();
			assertEquals(tk.getTipo(), TokenType.ASSIGN);
			assertEquals(tk.toString(), "<ASSIGN,r:8>");
			
			tk = s.nextToken();
			assertEquals(tk.getTipo(), TokenType.SEMI);
			assertEquals(tk.toString(), "<SEMI,r:10>");
		});
	}
	
	@Test
	void testInt() {
		
		assertDoesNotThrow(() -> {
			Scanner s = new Scanner("src/test/data/testScanner/testINT.txt");
			Token tk = s.nextToken();
			
			assertEquals(tk.getTipo(), TokenType.INT);
			assertEquals(tk.toString(), "<INT,r:2,698>");
		});
	}
	
	@Test
	void testFloat() {
		
		assertDoesNotThrow(() -> {
			Scanner s = new Scanner("src/test/data/testScanner/testFLOAT.txt");
			Token tk;
			
			assertThrows(LexicalException.class, () -> s.nextToken());
			
			assertThrows(LexicalException.class, () -> s.nextToken());
			
			assertThrows(LexicalException.class, () -> s.nextToken());
			
			tk = s.nextToken();
			assertEquals(tk.getTipo(), TokenType.FLOAT);
			assertEquals(tk.toString(), "<FLOAT,r:6,2.34>");
		});
	}
	
	@Test
	void testGenerale() {
		
		assertDoesNotThrow(() -> {
			Scanner s = new Scanner("src/test/data/testScanner/testGenerale.txt");
			Token tk;
			
			String[] expectedResult = new String[19];
			
			expectedResult[0] = "<TYINT,r:1>";
			expectedResult[1] = "<ID,r:1,temp>";
			expectedResult[2] = "<SEMI,r:1>";
			expectedResult[3] = "<ID,r:2,temp>";
			expectedResult[4] = "<ASSIGN,r:2>";
			expectedResult[5] = null;
			expectedResult[6] = "<SEMI,r:2>";
			expectedResult[7] = "<TYFLOAT,r:4>";
			expectedResult[8] = "<ID,r:4,b>";
			expectedResult[9] = "<SEMI,r:4>";
			expectedResult[10] = "<ID,r:5,b>";
			expectedResult[11] = "<ASSIGN,r:5>";
			expectedResult[12] = "<ID,r:5,temp>";
			expectedResult[13] = "<PLUS,r:5>";
			expectedResult[14] = "<FLOAT,r:5,3.2>";
			expectedResult[15] = "<SEMI,r:5>";
			expectedResult[16] = "<PRINT,r:6>";
			expectedResult[17] = "<ID,r:6,b>";
			expectedResult[18] = "<SEMI,r:6>";
			
			for(int i = 0; i < expectedResult.length; i++) {
				
				if(expectedResult[i] == null) {
					assertThrows(LexicalException.class, () -> s.nextToken());
				}
				else {
					tk = s.nextToken();
					assertEquals(tk.toString(), expectedResult[i]);
				}
			}
		});
	}
	
	@Test
	void testPeekToken() {
		
		assertDoesNotThrow(() -> {
			Scanner s = new Scanner("src/test/data/testScanner/testGenerale.txt");
			Token tk = s.peekToken();
			
			assertEquals(tk.toString(), "<TYINT,r:1>");
			tk = s.peekToken();
			assertEquals(tk.toString(), "<TYINT,r:1>");
			
			s.nextToken();
			tk = s.peekToken();
			assertEquals(tk.toString(), "<ID,r:1,temp>");
		});
	}

}
