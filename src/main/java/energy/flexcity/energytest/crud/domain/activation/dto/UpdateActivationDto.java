package energy.flexcity.energytest.crud.domain.activation.dto;

public class UpdateActivationDto {
    public long duration;
    public double power;

    public UpdateActivationDto(long duration, double power) {
        this.duration = duration;
        this.power = power;
    }

    public UpdateActivationDto() {

    }
}
