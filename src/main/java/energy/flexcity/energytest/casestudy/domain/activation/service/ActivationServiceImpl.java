package energy.flexcity.energytest.casestudy.domain.activation.service;

import energy.flexcity.energytest.casestudy.domain.activation.adapters.ActivationService;
import energy.flexcity.energytest.casestudy.domain.activation.dto.ActivationRequestDto;
import energy.flexcity.energytest.casestudy.domain.activation.entity.Activation;
import energy.flexcity.energytest.casestudy.domain.activation.ports.SiteCommander;
import energy.flexcity.energytest.casestudy.domain.site.adapters.SiteService;
import energy.flexcity.energytest.casestudy.domain.site.entity.Site;
import energy.flexcity.energytest.casestudy.domain.site.entity.WeekDay;
import energy.flexcity.energytest.casestudy.infrastructure.contact.ContactHandler;
import energy.flexcity.energytest.casestudy.infrastructure.contact.EmailHandler;
import energy.flexcity.energytest.casestudy.infrastructure.contact.SmsHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ActivationServiceImpl implements ActivationService {

    private final List<ContactHandler> contactHandlers;
    private final SiteService siteService;
    private final SiteCommander siteCommander;

    public ActivationServiceImpl(SiteService siteService, SiteCommander siteCommander) {
        this.contactHandlers = List.of(new EmailHandler(), new SmsHandler());
        this.siteService = siteService;
        this.siteCommander = siteCommander;
    }

    /**
     * Handle an activation request
     * Send command and notification to selected sites
     *
     * @param activationRequest TSO request
     */
    @Override
    public void handleActivationRequest(ActivationRequestDto activationRequest) {
        List<Site> sitesToReduce = this.getSitesToReduce(activationRequest.power);
        // Trigger selected sites
        sitesToReduce.forEach(site -> {
            // Create the activation
            Activation activation = new Activation(activationRequest.duration, activationRequest.power, site);
            // Ask for the site to stop
            this.notify("Get somehow the site owner contact", "Flexcity - Site command", "Your site is going to be shutdown for " + activationRequest.duration + " minutes.");
            siteCommander.command(site, Activation.COMMAND_STOP);
            // When the duration is expired, ask the site to restart
            activation.onActivationEnd(() -> {
                this.notify("Get somehow the site owner contact", "Flexcity - Site command", "Your site is going to restart.");
                siteCommander.command(site, Activation.COMMAND_START);
            });
        });
    }

    /**
     * Same algorithm from "algorithmic" exercise
     *
     * @param targetPower Power to reach
     * @return list of sites to shut down
     */
    private List<Site> getSitesToReduce(double targetPower) {
        List<Site> availableSites = this.siteService.getAll().stream()
                .filter(site -> site.getActivationDays().contains(this.getTodayWeekDay())) // Get list of sites available this date
                .sorted(Comparator.comparingDouble(Site::getActivationCost)) // Sort them ascending by activation cost
                .collect(Collectors.toList());

        double totalPower = availableSites.stream().mapToDouble(Site::getPower).sum();
        double powerToReduce = totalPower - targetPower; // At least this much power must be reduced

        List<Site> toActivate = new ArrayList<>();
        Iterator<Site> iterator = availableSites.iterator();

        // Iterate while we do not reach the target power
        while (toActivate.stream().mapToDouble(Site::getPower).sum() < powerToReduce && iterator.hasNext()) {
            toActivate.add(iterator.next());
        }
        return toActivate;
    }

    /**
     * Get magically the current day
     *
     * @return Current WeekDay
     */
    private WeekDay getTodayWeekDay() {
        return WeekDay.MONDAY;
    }

    /**
     * Send a notification to a site owner with every handler registered
     *
     * @param contactInformation Owner contact information
     * @param subject            Notification subject
     * @param message            Notification content
     */
    private void notify(String contactInformation, String subject, String message) {
        this.contactHandlers.forEach(handler -> handler.sendMessage(contactInformation, subject, message));
    }
}
