package energy.flexcity.energytest.crud.domain.activation.exceptions;

public class ActivationDoesNotExist extends Exception {
    public ActivationDoesNotExist() {
        super("Activation does not exist");
    }
}
