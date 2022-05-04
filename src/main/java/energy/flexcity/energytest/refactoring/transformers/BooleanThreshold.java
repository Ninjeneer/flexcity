package energy.flexcity.energytest.refactoring.transformers;

public class BooleanThreshold implements Transformer<Double, Boolean> {
    private final Double reference;

    public BooleanThreshold(Double reference) {
        this.reference = reference;
    }

    @Override
    public Boolean transform(Double valueToTransform) {
        return valueToTransform > this.reference;
    }
}
