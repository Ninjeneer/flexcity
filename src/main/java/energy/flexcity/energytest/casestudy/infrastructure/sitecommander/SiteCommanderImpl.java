package energy.flexcity.energytest.casestudy.infrastructure.sitecommander;

import energy.flexcity.energytest.casestudy.domain.activation.ports.SiteCommander;
import energy.flexcity.energytest.casestudy.domain.site.entity.Site;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

public class SiteCommanderImpl implements SiteCommander {

    /**
     * Send an HTTP request command to a site
     * @param site targeted site
     * @param command activation command
     */
    @Override
    public void command(Site site, String command) {
        try {
            HttpRequest.newBuilder()
                    .uri(new URI(site.getCommandEndpoint()))
                    .POST(HttpRequest.BodyPublishers.ofString("{ \"command\": \"" + command + "\" }"))
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
