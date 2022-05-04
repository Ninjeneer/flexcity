package energy.flexcity.energytest.casestudy.domain.site.service;

import energy.flexcity.energytest.casestudy.domain.site.adapters.SiteService;
import energy.flexcity.energytest.casestudy.domain.site.entity.Site;
import energy.flexcity.energytest.casestudy.domain.site.entity.WeekDay;

import java.util.List;

public class SiteServiceImpl implements SiteService {
    /**
     * Mocked function
     * @return List of sites
     */
    @Override
    public List<Site> getAll() {
        return List.of(new Site("Site A", 10, List.of(WeekDay.MONDAY, WeekDay.FRIDAY), 1, "http://endpoint.fr/command"));
    }
}
