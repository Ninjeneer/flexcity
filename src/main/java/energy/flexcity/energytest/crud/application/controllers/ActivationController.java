package energy.flexcity.energytest.crud.application.controllers;

import energy.flexcity.energytest.crud.domain.activation.adapters.ActivationService;
import energy.flexcity.energytest.crud.domain.activation.dto.CreateActivationDto;
import energy.flexcity.energytest.crud.domain.activation.dto.UpdateActivationDto;
import energy.flexcity.energytest.crud.domain.activation.entity.Activation;
import energy.flexcity.energytest.crud.domain.activation.exceptions.ActivationDoesNotExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/activations")
public class ActivationController {

    private final ActivationService activationService;

    public ActivationController(ActivationService activationService) {
        this.activationService = activationService;
    }

    @GetMapping()
    public ResponseEntity<List<Activation>> getActivations() {
        return new ResponseEntity<>(this.activationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activation> getActivationById(@PathVariable("id") String id) {
        return new ResponseEntity<>(this.activationService.getById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Activation> getActivationById(@RequestBody() CreateActivationDto body) {
        return new ResponseEntity<>(this.activationService.create(body), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activation> updateActivation(@PathVariable() String id, @RequestBody() UpdateActivationDto body) {
        try {
            Activation updatedActivation = this.activationService.update(id, body);
            return new ResponseEntity<>(updatedActivation, HttpStatus.OK);
        } catch (ActivationDoesNotExist e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivation(@PathVariable("id") String id) {
        try {
            this.activationService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ActivationDoesNotExist e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
