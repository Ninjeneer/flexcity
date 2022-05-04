package energy.flexcity.energytest.algorithmic;

import java.util.List;

public class Site {
    private final String name;
    private final double activationCost;
    private final List<WeekDay> activationDays;

    private final double power;

    public Site(String name, double activationCost, List<WeekDay> activationDays, double power) {
        this.name = name;
        this.activationCost = activationCost;
        this.activationDays = activationDays;
        this.power = power;
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
}
