package energy.flexcity.energytest.casestudy.domain.site.entity;


import java.util.List;

public class Site {
    private final String name;
    private final double activationCost;
    private final List<WeekDay> activationDays;

    private final double power;
    private final String commandEndpoint;

    public Site(String name, double activationCost, List<WeekDay> activationDays, double power, String commandEndpoint) {
        this.name = name;
        this.activationCost = activationCost;
        this.activationDays = activationDays;
        this.power = power;
        this.commandEndpoint = commandEndpoint;
    }

    public String getName() {
        return name;
    }

    public double getActivationCost() {
        return activationCost;
    }

    public List<WeekDay> getActivationDays() {
        return activationDays;
    }

    public double getPower() {
        return power;
    }

    public String getCommandEndpoint() {
        return this.commandEndpoint;
    }
}
