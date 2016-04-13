
package funlang;

public class IntValue
        extends Value {

    private int value;

    public IntValue(
            int value) {
        this.value = value;
    }

    @Override
    public boolean isSame(
            Value value) {

        return this.value == ((IntValue) value).value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
