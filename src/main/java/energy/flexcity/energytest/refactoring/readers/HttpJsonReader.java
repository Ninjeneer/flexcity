package energy.flexcity.energytest.refactoring.readers;

public class HttpJsonReader extends Reader<Double> {
    @Override
    public void readValue() {
        this.setValue(3.5);
    }
}
