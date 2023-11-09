import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class AppTest {
    @Test
    void test1() {
        boolean expected = true;
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
        schema.contains("wh");
        actual = schema.isValid("what does the fox say");
        assertEquals(true, actual);
        schema.contains("what");
        actual = schema.isValid("what does the fox say");
        assertEquals(true, actual);
        schema.contains("whatthe");
        actual = schema.isValid("what does the fox say");
        assertEquals(false, actual);
        actual = schema.isValid("what does the fox say");
        assertEquals(false, actual);
    }
    @Test
    void test2() {
        boolean expected = true;
        Validator v = new Validator();
        StringSchema schema = v.string();
        boolean actual = schema.isValid(null); // true
        assertEquals(expected, actual);
    }
}
