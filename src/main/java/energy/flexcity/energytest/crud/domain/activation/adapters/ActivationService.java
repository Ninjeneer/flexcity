package energy.flexcity.energytest.crud.domain.activation.adapters;

import energy.flexcity.energytest.crud.domain.activation.dto.CreateActivationDto;
import energy.flexcity.energytest.crud.domain.activation.dto.UpdateActivationDto;
import energy.flexcity.energytest.crud.domain.activation.entity.Activation;
import energy.flexcity.energytest.crud.domain.activation.exceptions.ActivationDoesNotExist;

import java.util.List;

public interface ActivationService {
    Activation create(CreateActivationDto dto);

    Activation getById(String id);

    List<Activation> getAll();

    void delete(String id) throws ActivationDoesNotExist;

    Activation update(String id, UpdateActivationDto dto) throws ActivationDoesNotExist;
}
