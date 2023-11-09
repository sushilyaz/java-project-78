package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class StringSchema {
    private boolean req = false;
    private int minL = 0;

    private List<String> subs = new ArrayList<>();

    public void required() {
        req = true;
    }

    public void minLength(int n) {
        minL = n;
    }

    public void contains(String substr) {
        subs.add(substr);
    }

    public boolean isValid(Object validationElement) {
        if (validationElement == null || validationElement instanceof String) {
            if (req) {
                if (validationElement == null || validationElement.equals("")) {
                    return false;
                }
            }
            if (validationElement != null) {
                if ((((String) validationElement).length() < minL)) {
                    return false;
                }
            }
            if (!subs.isEmpty()) {
                for (String some : subs) {
                    if (!((String) validationElement).contains(some)) return false;
                }
            }
            return true;
        }
        return false;
    }
}
