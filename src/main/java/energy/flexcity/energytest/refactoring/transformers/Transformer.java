package energy.flexcity.energytest.refactoring.transformers;

/**
 * Transform a given value to another following customizable rules
 *
 * @param <T> Source type
 * @param <U> Destination type
 */
public interface Transformer<T, U> {
    U transform(T valueToTransform);
}
