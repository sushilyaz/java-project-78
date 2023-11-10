package hexlet.code.schemas;

public class NumberSchema extends BaseSchema{
    private boolean req = false;
    private boolean pos = false;
    private boolean rangeX = false;
    private int begin = -1000000;
    private int end = 1000000;

    public NumberSchema required() {
        this.req = true;
        return this;
    }

    public NumberSchema positive() {
        this.pos = true;
        return this;
    }

    public NumberSchema range(int start, int finish) {
        this.rangeX = true;
        this.begin = start;
        this.end = finish;
        return this;
    }
    @Override
    public boolean isValid(Object validationElement) {
        if (validationElement == null || validationElement instanceof Integer) {
            if (req) {
                if (validationElement == null) return false;
            }
            if (pos) {
                if (!(validationElement == null || (Integer) validationElement > 0)) return false;
            }
            if (rangeX) {
                if ((Integer) validationElement < begin || (Integer) validationElement > end) return false;
            }
            return true;
        }
        return false;
    }
}
