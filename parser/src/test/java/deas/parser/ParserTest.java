package deas.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
   @Test void testBasicNumericalValue(){
    ASTNode expected = new ASTNode(42);

    Parser parser = new Parser();
    ASTNode actual = parser.parse("42");

    assertEquals(expected.value, actual.value);
   }
}
