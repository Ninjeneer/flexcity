package energy.flexcity.energytest.casestudy.application;

import energy.flexcity.energytest.casestudy.domain.activation.service.ActivationServiceImpl;
import energy.flexcity.energytest.casestudy.domain.site.service.SiteServiceImpl;
import energy.flexcity.energytest.casestudy.infrastructure.sitecommander.SiteCommanderImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebServer {

    public static void main(String[] args) {
        SpringApplication.run(WebServer.class, args);
    }

    @Bean
    public ActivationServiceImpl activationService() {
        return new ActivationServiceImpl(new SiteServiceImpl(), new SiteCommanderImpl());
    }
}
