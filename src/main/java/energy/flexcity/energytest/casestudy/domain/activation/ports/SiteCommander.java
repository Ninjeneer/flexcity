package energy.flexcity.energytest.casestudy.domain.activation.ports;

import energy.flexcity.energytest.casestudy.domain.site.entity.Site;

public interface SiteCommander {
    void command(Site site, String command);
}
