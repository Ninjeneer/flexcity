package energy.flexcity.energytest.casestudy.domain.activation.adapters;

import energy.flexcity.energytest.casestudy.domain.activation.dto.ActivationRequestDto;

public interface ActivationService {
    void handleActivationRequest(ActivationRequestDto activationRequest);
}
