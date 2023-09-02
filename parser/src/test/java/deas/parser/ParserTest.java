package deas.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.ParameterizedTest;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

   @DisplayName("Should pass through basic integers to the AST")
   @ParameterizedTest
   @CsvSource({"1,1", "3,3", "5,5", "-3,-3","7777777,7777777", "15,15", "2147483647,2147483647"})
   void testBasicNumericalValue(int number, String string){
    ASTNode expected = new ASTNode(new Token(number));

    Parser parser = new Parser();
    ASTNode actual = parser.parse(string);

    assertEquals(expected.getValue(), actual.getValue());
   }

   @DisplayName("Should pass through Java Integer.MAX_VALUE integer to the AST")
   void testMaxIntNumericalValue(){

    ASTNode expected = new ASTNode(new Token(Integer.MAX_VALUE));

    Parser parser = new Parser();
    ASTNode actual = parser.parse("2147483647");

    assertEquals(expected.getValue(), actual.getValue());
   }

   @DisplayName("Should pass through Java Integer.MIN_VALUE to the AST")
   void testMinIntNumericalValue(){

    ASTNode expected = new ASTNode(new Token(Integer.MIN_VALUE));

    Parser parser = new Parser();
    ASTNode actual = parser.parse("-2147483648");

    assertEquals(expected.getValue(), actual.getValue());
   }
}
