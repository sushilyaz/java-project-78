package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    public NumberSchema() {
        Predicate<Object> predicateInit = num -> num == null || num instanceof Integer;
        modifySchema("Init", predicateInit);
    }

    public NumberSchema required() {
        Predicate<Object> requiredPredicate = num -> num instanceof Integer;
        modifySchema("requiredNumber", requiredPredicate);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Object> positivePredicate = num -> num instanceof Integer && (Integer) num > 0 || num == null;
        modifySchema("positiveNumber", positivePredicate);
        return this;
    }

    public NumberSchema range(int begin, int end) {
        Predicate<Object> rangePredicate = num -> ((Integer) num >= begin && (Integer) num <= end)
                && num instanceof Integer || num == null;
        modifySchema("rangeNumber", rangePredicate);
        return this;
    }
}
