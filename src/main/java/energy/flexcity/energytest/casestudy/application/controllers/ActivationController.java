package energy.flexcity.energytest.casestudy.application.controllers;

import energy.flexcity.energytest.casestudy.domain.activation.adapters.ActivationService;
import energy.flexcity.energytest.casestudy.domain.activation.dto.ActivationRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activations")
public class ActivationController {

    private final ActivationService activationService;

    public ActivationController(ActivationService activationService) {
        this.activationService = activationService;
    }

    @PostMapping()
    public void activation(@RequestBody() ActivationRequestDto activationRequestDto) {
        this.activationService.handleActivationRequest(activationRequestDto);
    }
}
