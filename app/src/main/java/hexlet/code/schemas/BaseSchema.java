package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {
    private final Map<String, Predicate<Object>> req = new HashMap<>();

    protected final void modifySchema(String key, Predicate<Object> descPredic) {
        req.put(key, descPredic);
    }

    public boolean isValid(Object element) {
        List<String> keys = new ArrayList<>(req.keySet());
        for (String key : keys) {
            if (!req.get(key).test(element)) {
                return false;
            }
        }
        return true;
    }
}
