
package funlang;

public class BoolValue
        extends Value {

    private static final BoolValue FALSE = new BoolValue(false);

    private static final BoolValue TRUE = new BoolValue(true);

    private boolean value;

    private BoolValue(
            boolean b) {
        this.value = b;
    }

    public static BoolValue get(
            boolean b) {

        if (b) {
            return TRUE;
        }

        return FALSE;
    }

    @Override
    public boolean isSame(
            Value value) {

        return this.value == ((BoolValue) value).value;
    }

    public boolean getValue() {

        return this.value;
    }

}
