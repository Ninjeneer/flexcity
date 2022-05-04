package energy.flexcity.energytest.algorithmic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ActivationService {

    public List<Site> getSitesToActivate(List<Site> sites, double targetPower, WeekDay date) throws UnreachablePowerRequest {
        List<Site> availableSites = sites.stream()
                .filter(site -> site.getActivationDays().contains(date)) // Get list of sites available this date
                .sorted(Comparator.comparingDouble(Site::getActivationCost)) // Sort them ascending by activation cost (All sites have the same power, so only sort by cost)
                .collect(Collectors.toList());

        double totalPower = availableSites.stream().mapToDouble(Site::getPower).sum();
        double powerToReduce = totalPower - targetPower; // At least this much power must be reduced

        if (totalPower < targetPower) {
            throw new UnreachablePowerRequest();
        }

        List<Site> toActivate = new ArrayList<>();
        Iterator<Site> iterator = availableSites.iterator();

        // Iterate while we do not reach the target power
        while (toActivate.stream().mapToDouble(Site::getPower).sum() < powerToReduce && iterator.hasNext()) {
            toActivate.add(iterator.next());
        }
        return toActivate;
    }
}
