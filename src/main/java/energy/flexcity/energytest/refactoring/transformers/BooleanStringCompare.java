package energy.flexcity.energytest.refactoring.transformers;

public class BooleanStringCompare implements Transformer<String, Boolean>{
    private final String toCompare;

    public BooleanStringCompare(String toCompare) {
        this.toCompare = toCompare;
    }

    @Override
    public Boolean transform(String valueToTransform) {
        return valueToTransform.equals(this.toCompare);
    }
}
