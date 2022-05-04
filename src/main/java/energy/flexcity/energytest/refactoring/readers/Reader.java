package energy.flexcity.energytest.refactoring.readers;

import energy.flexcity.energytest.refactoring.transformers.Transformer;

public abstract class Reader<T> {
    private T value;
    public abstract void readValue();

    public T getValue() {
        return value;
    }

    protected void setValue(T value) {
        this.value = value;
    }

    public Object transform(Transformer transformer) {
        return transformer.transform(value);
    }
}
