package energy.flexcity.energytest.refactoring.readers;

/**
 * Object used to read values from components
 *
 * @param <T> Expected type
 */
public abstract class Reader<T> {
    private T value;

    public abstract void readValue();

    public T getValue() {
        return value;
    }

    protected void setValue(T value) {
        this.value = value;
    }
}
