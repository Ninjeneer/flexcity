package energy.flexcity.energytest.crud.infrastructure.activation;

import energy.flexcity.energytest.crud.domain.activation.entity.Activation;
import energy.flexcity.energytest.crud.domain.activation.ports.ActivationRepository;

import java.util.ArrayList;
import java.util.List;

public class ActivationRepositoryImpl implements ActivationRepository {
    private final List<Activation> activations;

    public ActivationRepositoryImpl() {
        this.activations = new ArrayList<>();
    }

    @Override
    public Activation save(Activation activation) {
        this.activations.add(activation);
        return activation;
    }

    @Override
    public Activation getById(String id) {
        return this.activations.stream().filter(a -> a.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Activation> getAll() {
        return this.activations;
    }

    @Override
    public void delete(Activation activation) {
        this.activations.remove(activation);
    }
}
