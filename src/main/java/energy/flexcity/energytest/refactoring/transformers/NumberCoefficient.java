package energy.flexcity.energytest.refactoring.transformers;

public class NumberCoefficient implements Transformer<Double, Double> {
    private final double coefficient;

    public NumberCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public Double transform(Double valueToTransform) {
        return this.coefficient * valueToTransform;
    }
}
