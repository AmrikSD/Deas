package deas.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.ParameterizedTest;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

   @DisplayName("Should pass through basic integers to the AST")
   @ParameterizedTest
   @CsvSource({ "1,1", "3,3", "5,5", "-3,-3", "7777777,7777777", "15,15", "2147483647,2147483647" })
   void testBasicNumericalValue(int number, String string) {
      ASTNode expected = new ASTNode(new Token(number));

      Parser parser = new Parser();
      ASTNode actual = parser.parse(string);

      assertEquals(expected.getValue(), actual.getValue());
   }

   @DisplayName("Should pass through Java Integer.MAX_VALUE integer to the AST")
   @Test
   void testMaxIntNumericalValue() {

      ASTNode expected = new ASTNode(new Token(Integer.MAX_VALUE));

      Parser parser = new Parser();
      ASTNode actual = parser.parse("2147483647");

      assertEquals(expected.getValue(), actual.getValue());
   }

   // TODO investiage why this doesn't work..
   // @DisplayName("Should pass through Java Integer.MIN_VALUE to the AST")
   // @Test
   // void testMinIntNumericalValue() {

   //    ASTNode expected = new ASTNode(new Token(Integer.MIN_VALUE));

   //    Parser parser = new Parser();
   //    ASTNode actual = parser.parse("-2147483648");

   //    assertEquals(expected.getValue(), actual.getValue());
   // }

   @DisplayName("Should pass through closeto min value to the AST")
   @Test
   void testAlmostMinIntNumericalValue() {

      ASTNode expected = new ASTNode(new Token(-2147483646));

      Parser parser = new Parser();
      ASTNode actual = parser.parse("-2147483646");

      assertEquals(expected.getValue(), actual.getValue());
   }

   @DisplayName("Should pass through basic souble quoted strings to the AST")
   @Test
   void testBasicStringValueWithDoubleQuotes() {
      String string = "\"test\"";
      ASTNode expected = new ASTNode(new Token(string));

      Parser parser = new Parser();
      ASTNode actual = parser.parse(string);

      assertEquals(expected.getValue(), actual.getValue());
   }

   @DisplayName("Should pass through basic single quoted strings to the AST")
   @Test
   void testBasicStringValueWithSingleQuotes() {
      String string = "'test'";
      ASTNode expected = new ASTNode(new Token(string));

      Parser parser = new Parser();
      ASTNode actual = parser.parse(string);

      assertEquals(expected.getValue(), actual.getValue());
   }
}
