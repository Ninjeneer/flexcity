package energy.flexcity.energytest.crud.domain.activation.service;

import energy.flexcity.energytest.crud.domain.activation.adapters.ActivationService;
import energy.flexcity.energytest.crud.domain.activation.dto.CreateActivationDto;
import energy.flexcity.energytest.crud.domain.activation.dto.UpdateActivationDto;
import energy.flexcity.energytest.crud.domain.activation.entity.Activation;
import energy.flexcity.energytest.crud.domain.activation.exceptions.ActivationDoesNotExist;
import energy.flexcity.energytest.crud.domain.activation.ports.ActivationRepository;

import java.util.List;

public class ActivationServiceImpl implements ActivationService {
    private final ActivationRepository activationRepository;

    public ActivationServiceImpl(ActivationRepository activationRepository) {
        this.activationRepository = activationRepository;
    }

    @Override
    public Activation create(CreateActivationDto dto) {
        Activation activation = new Activation(dto.duration, dto.power);
        activation = this.activationRepository.save(activation);
        return activation;
    }

    @Override
    public Activation getById(String id) {
        return this.activationRepository.getById(id);
    }

    @Override
    public List<Activation> getAll() {
        return this.activationRepository.getAll();
    }

    @Override
    public void delete(String id) throws ActivationDoesNotExist {
        Activation activation = this.activationRepository.getById(id);
        if (activation == null) {
            throw new ActivationDoesNotExist();
        }
        this.activationRepository.delete(activation);
    }

    @Override
    public Activation update(String id, UpdateActivationDto dto) throws ActivationDoesNotExist {
        Activation activation = this.activationRepository.getById(id);
        if (activation == null) {
            throw new ActivationDoesNotExist();
        }

        activation.setDuration(dto.duration);
        activation.setPower(dto.power);
        this.activationRepository.save(activation);
        return activation;
    }
}
