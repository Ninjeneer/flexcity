package energy.flexcity.energytest.crud.application.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import energy.flexcity.energytest.crud.domain.activation.adapters.ActivationService;
import energy.flexcity.energytest.crud.domain.activation.dto.CreateActivationDto;
import energy.flexcity.energytest.crud.domain.activation.entity.Activation;
import energy.flexcity.energytest.crud.domain.activation.exceptions.ActivationDoesNotExist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class ActivationControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final ActivationService activationService;

    @Autowired
    ActivationControllerTest(MockMvc mockMvc, ObjectMapper objectMapper, ActivationService activationService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.activationService = activationService;
    }

    @Test
    void shouldCreateActivation() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/activations")
                                .contentType("application/json")
                                .content(this.objectMapper.writeValueAsString(new CreateActivationDto(1000, 1)))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("duration").value(1000))
                .andExpect(jsonPath("power").value(1));
    }

    @Test
    void shouldFindAllActivations() throws Exception {
        this.activationService.create(new CreateActivationDto(1000, 1));
        this.activationService.create(new CreateActivationDto(2000, 5));
        this.activationService.create(new CreateActivationDto(3000, 7));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/activations"))
                .andExpect(status().isOk())
                .andReturn();
        List<Activation> activations = this.objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(3, activations.size());
    }

    @Test
    void shouldFindAnActivationById() throws Exception {
        Activation activation = this.activationService.create(new CreateActivationDto(1000, 1));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/activations/" + activation.getId()))
                .andExpect(status().isOk())
                .andReturn();
        Activation retrievedActivation = this.objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        assertEquals(activation, retrievedActivation);
    }

    @Test
    void shouldDeleteAnActivation() throws Exception {
        Activation activation = this.activationService.create(new CreateActivationDto(1000, 1));

        mockMvc.perform(MockMvcRequestBuilders.delete("/activations/" + activation.getId()))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.delete("/activations/" + activation.getId())).andExpect(status().isNotFound());
    }
}