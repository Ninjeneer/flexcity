package energy.flexcity.energytest.crud.domain.activation.ports;

import energy.flexcity.energytest.crud.domain.activation.entity.Activation;

import java.util.List;

public interface ActivationRepository {
    Activation save(Activation activation);
    Activation getById(String id);
    List<Activation> getAll();
    void delete(Activation activation);

}
