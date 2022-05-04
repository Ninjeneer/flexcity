package energy.flexcity.energytest.refactoring.readers;

public class FtpReader extends Reader<String> {
    @Override
    public void readValue() {
        this.setValue("UP");
    }
}
