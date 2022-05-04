package energy.flexcity.energytest.refactoring.transformers;

public interface Transformer<T, U> {
    U transform(T valueToTransform);
}
