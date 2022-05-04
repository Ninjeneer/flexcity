package energy.flexcity.energytest.crud.domain.activation.dto;


public class CreateActivationDto {
    public long duration;
    public double power;

    public CreateActivationDto(long duration, double power) {
        this.duration = duration;
        this.power = power;
    }

    public CreateActivationDto() {

    }
}
