import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class AppTest {
    @Test
    void test1() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        boolean actual = schema.isValid("");
        assertEquals(true, actual);// true
        actual = schema.isValid(null);
        assertEquals(true, actual);
        schema.required();
        actual = schema.isValid(null);
        assertEquals(false, actual);
        actual = schema.isValid("");
        assertEquals(false, actual);
        actual = schema.isValid(5);
        assertEquals(false, actual);
        actual = schema.isValid("what does the fox say");
        assertEquals(true, actual);
        actual = schema.isValid("hexlet");
        assertEquals(true, actual);
        actual = schema.contains("wh").isValid("what does the fox say");
        assertEquals(true, actual);
        schema.contains("what").isValid("what does the fox say");
        actual = schema.isValid("what does the fox say");
        assertEquals(true, actual);
        actual = schema.contains("whatthe").isValid("what does the fox say");
        assertEquals(false, actual);
        actual = schema.isValid("what does the fox say");
        assertEquals(false, actual);
    }
    @Test
    void test2() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        boolean actual = schema.isValid(null);
        assertEquals(true, actual);
        actual = schema.positive().isValid(null);
        assertEquals(true, actual);
        schema.required();
        actual = schema.isValid(null);
        assertEquals(false, actual);
        actual = schema.isValid("5");
        assertEquals(false, actual);
        actual = schema.isValid(10);
        assertEquals(true, actual);
        actual = schema.isValid(-10);
        assertEquals(false, actual);
        actual = schema.isValid(0);
        assertEquals(false, actual);
        schema.range(5, 10);
        actual = schema.isValid(5);
        assertEquals(true, actual);
        actual = schema.isValid(10);
        assertEquals(true, actual);
        actual = schema.isValid(4);
        assertEquals(false, actual);
        actual = schema.isValid(11);
        assertEquals(false, actual);
    }
}
