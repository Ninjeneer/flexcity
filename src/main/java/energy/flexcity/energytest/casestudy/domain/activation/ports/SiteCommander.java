package energy.flexcity.energytest.casestudy.domain.activation.ports;

import energy.flexcity.energytest.casestudy.domain.site.entity.Site;

public interface HttpClient {
    void triggerSite(Site site);
}
