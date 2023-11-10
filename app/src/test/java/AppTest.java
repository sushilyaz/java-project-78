import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    void testStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        boolean actual = schema.isValid("");
        assertEquals(true, actual);
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
    void testNumberSchema() {
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

    @Test
    void testMapSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        boolean actual = schema.isValid(null);
        assertEquals(true, actual);
        schema.required();
        actual = schema.isValid(null);
        assertEquals(false, actual);
        actual = schema.isValid(4);
        assertEquals(false, actual);
        actual = schema.isValid(new HashMap());
        assertEquals(true, actual);
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        actual = schema.isValid(data);
        assertEquals(true, actual);
        schema.sizeof(2);
        actual = schema.isValid(data);
        assertEquals(false, actual);
        data.put("key2", "value2");
        actual = schema.isValid(data);
        assertEquals(true, actual);
    }

    @Test
    void testMapSchemaNPE() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        schema.sizeof(2);
        boolean actual = schema.isValid(null);
        assertEquals(false, actual);
    }

    @Test
    void testShapeMapSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        boolean actual = schema.isValid(human1); // true
        assertEquals(true, actual);

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        actual = schema.isValid(human2);
        assertEquals(true, actual);

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        actual = schema.isValid(human3);
        assertEquals(false, actual);

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        actual = schema.isValid(human4);
        assertEquals(false, actual);
    }
}
