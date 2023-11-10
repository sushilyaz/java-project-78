package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {
    public StringSchema() {
        Predicate<Object> predicateInit = s -> s == null || s instanceof String;
        modifySchema("Init", predicateInit);
    }

    public StringSchema required() {
        Predicate<Object> predicateRequired = s ->  s instanceof String && !((String) s).isEmpty();
        modifySchema("requiredString", predicateRequired);
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<Object> predicateMinLength = s -> s == null || s instanceof String && ((String) s).length() >= length;
        modifySchema("minLengthString", predicateMinLength);
        return this;
    }

    public StringSchema contains(String str) {
        Predicate<Object> predicateContains = s -> s == null || s instanceof String && ((String) s).contains(str);
        modifySchema("containsString", predicateContains);
        return this;
    }
}
