package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {
    public MapSchema() {
        Predicate<Object> predicateInit = map -> map == null || map instanceof Map<?, ?>;
        modifySchema("Init", predicateInit);
    }

    public MapSchema required() {
        Predicate<Object> requiredPredicate = map -> map instanceof Map<?, ?>;
        modifySchema("requiredMap", requiredPredicate);
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Object> sizeofPredicate = map -> map == null || map instanceof Map && ((Map) map).size() == size;
        modifySchema("sizeofMap", sizeofPredicate);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        Predicate<Object> shapePredicate = map -> map == null || map instanceof Map && schemas.entrySet().stream()
                .allMatch(schema -> schema.getValue().isValid(((Map<?, ?>) map).get(schema.getKey())));
        modifySchema("shape", shapePredicate);
        return this;
    }
}
