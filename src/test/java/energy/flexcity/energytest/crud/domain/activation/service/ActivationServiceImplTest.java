package energy.flexcity.energytest.crud.domain.activation.service;

import energy.flexcity.energytest.crud.domain.activation.adapters.ActivationService;
import energy.flexcity.energytest.crud.domain.activation.dto.CreateActivationDto;
import energy.flexcity.energytest.crud.domain.activation.entity.Activation;
import energy.flexcity.energytest.crud.domain.activation.exceptions.ActivationDoesNotExist;
import energy.flexcity.energytest.crud.domain.activation.ports.ActivationRepository;
import energy.flexcity.energytest.crud.infrastructure.activation.ActivationRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivationServiceImplTest {
    private ActivationService activationService;

    @BeforeEach
    void setUp() {
        ActivationRepository activationRepository = new ActivationRepositoryImpl();
        this.activationService = new ActivationServiceImpl(activationRepository);
    }

    @Test
    void shouldCreateActivation() {
        Activation activation = this.activationService.create(new CreateActivationDto(10000, 2));
        assertEquals(10000, activation.getDuration());
        assertEquals(2, activation.getPower());
    }

    @Test
    void shouldFindActivationById() {
        Activation activation = this.activationService.create(new CreateActivationDto(10000, 2));
        Activation activation2 = this.activationService.getById(activation.getId());
        assertNotNull(activation2);
        assertEquals(activation, activation2);
    }

    @Test
    void shouldFindAllActivations() {
        Activation activation = this.activationService.create(new CreateActivationDto(10000, 2));
        Activation activation2 = this.activationService.create(new CreateActivationDto(10000, 2));
        List<Activation> activations = this.activationService.getAll();
        assertEquals(2, activations.size());
        assertTrue(activations.containsAll(List.of(activation, activation2)));
    }

    @Test
    void shouldDeleteActivation() throws ActivationDoesNotExist {
        Activation activation = this.activationService.create(new CreateActivationDto(10000, 2));
        assertNotNull(this.activationService.getById(activation.getId()));
        this.activationService.delete(activation.getId());
        assertNull(this.activationService.getById(activation.getId()));
    }
}