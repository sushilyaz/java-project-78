package hexlet.code.schemas;

import hexlet.code.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MapSchema extends BaseSchema {
    private boolean req = false;
    private boolean size = false;
    private int n = 0;
    private boolean shapeTurnOn = false;
    private Map<String, BaseSchema> schemasGlobal = new HashMap<>();

    public MapSchema required() {
        this.req = true;
        return this;
    }

    public MapSchema sizeof(int count) {
        this.n = count;
        this.size = true;
        return this;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        this.shapeTurnOn = true;
        schemasGlobal.putAll(schemas);
    }

    @Override
    public boolean isValid(Object validationElement) {
        if (validationElement == null || validationElement instanceof Map<?, ?>) {
            if (req) {
                if (validationElement == null) {
                    return false;
                }
            }
            if (size) {
                try {
                    if (((Map<?, ?>) validationElement).size() != n) {
                        return false;
                    }
                } catch (NullPointerException e) {
                    return false;
                }
            }
            if (shapeTurnOn) {
                Validator v = new Validator();
                BaseSchema base;
                List<Object> keys;
                try {
                    keys = new ArrayList<>(((Map<?, ?>) validationElement).keySet());
                } catch (NullPointerException e) {
                    return false;
                }
                for (Object key : keys) {
                    try {
                        base = schemasGlobal.get(key);
                    } catch (Exception e) {
                        continue;
                    }
                    if (!base.isValid(((Map<?, ?>) validationElement).get(key))) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

}
