
package funlang;

public class StringValue
        extends Value {

    private String value;

    public StringValue(
            String value) {
        this.value = value;
    }

    @Override
    public boolean isSame(
            Value value) {

        return this.value == ((StringValue) value).value;
    }

    public String getValue() {

        return this.value;
    }
}
