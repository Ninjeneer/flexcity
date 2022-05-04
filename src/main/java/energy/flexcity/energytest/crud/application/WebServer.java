package energy.flexcity.energytest.crud.application;

import energy.flexcity.energytest.crud.domain.activation.adapters.ActivationService;
import energy.flexcity.energytest.crud.domain.activation.service.ActivationServiceImpl;
import energy.flexcity.energytest.crud.infrastructure.activation.ActivationRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebServer {
    public static void main(String[] args) {
        SpringApplication.run(WebServer.class, args);
    }


    @Bean
    public ActivationService activationService() {
        return new ActivationServiceImpl(new ActivationRepositoryImpl());
    }
}
