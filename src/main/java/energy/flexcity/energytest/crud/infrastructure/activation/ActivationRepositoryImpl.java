package energy.flexcity.energytest.crud.infrastructure.activation;

import energy.flexcity.energytest.crud.domain.activation.entity.Activation;
import energy.flexcity.energytest.crud.domain.activation.ports.ActivationRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivationRepositoryImpl implements ActivationRepository {
    private final Map<String, Activation> activations;

    public ActivationRepositoryImpl() {
        this.activations = new HashMap<>();
    }

    @Override
    public Activation save(Activation activation) {
        this.activations.put(activation.getId(), activation);
        return activation;
    }

    @Override
    public Activation getById(String id) {
        return this.activations.get(id);
    }

    @Override
    public List<Activation> getAll() {
        return new ArrayList<>(this.activations.values());
    }

    @Override
    public void delete(Activation activation) {
        this.activations.remove(activation.getId());
    }
}
