package energy.flexcity.energytest.algorithmic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivationServiceTest {

    private ActivationService activationService;

    @BeforeEach
    void setup() {
        this.activationService = new ActivationService();
    }

    @Test
    void shouldReturnTheOnlySiteWithTargetPowerLower() {
        List<Site> availableSites = List.of(new Site("A", 10, List.of(WeekDay.MONDAY), 1));

        List<Site> sitesToShutdown = this.activationService.getSitesToActivate(availableSites, 0.5, WeekDay.MONDAY);
        assertEquals(1, sitesToShutdown.size());
        assertEquals(availableSites.get(0), sitesToShutdown.get(0));
    }

    @Test
    void shouldNotReturnSitesForAGivenDay() {
        List<Site> availableSites = List.of(
                new Site("A", 10, List.of(WeekDay.MONDAY), 1),
                new Site("B", 10, List.of(WeekDay.TUESDAY), 1)
        );

        List<Site> sitesToShutdown = this.activationService.getSitesToActivate(availableSites, 1, WeekDay.WEDNESDAY);
        assertEquals(0, sitesToShutdown.size());
    }

    @Test
    void shouldSelectTheLowerCostForAnActivation() {
        List<Site> availableSites = List.of(
                new Site("A", 10, List.of(WeekDay.MONDAY), 1),
                new Site("B", 5, List.of(WeekDay.MONDAY, WeekDay.TUESDAY), 1)
        );

        List<Site> sitesToShutdown = this.activationService.getSitesToActivate(availableSites, 1, WeekDay.MONDAY);
        assertEquals(1, sitesToShutdown.size());
        assertEquals(availableSites.get(1), sitesToShutdown.get(0));
    }

    @Test
    void shouldSelectTheLowerCostsForAnActivationWithMultipleSites() {
        List<Site> availableSites = List.of(
                new Site("A", 10, List.of(WeekDay.MONDAY), 1),
                new Site("B", 5, List.of(WeekDay.MONDAY, WeekDay.TUESDAY), 1),
                new Site("C", 20, List.of(WeekDay.MONDAY, WeekDay.TUESDAY), 1),
                new Site("D", 15, List.of(WeekDay.MONDAY, WeekDay.TUESDAY), 1),
                new Site("E", 1, List.of(WeekDay.MONDAY, WeekDay.TUESDAY), 1),
                new Site("F", 50, List.of(WeekDay.MONDAY, WeekDay.TUESDAY), 1)
        );

        List<Site> sitesToShutdown = this.activationService.getSitesToActivate(availableSites, 2, WeekDay.MONDAY);
        assertEquals(4, sitesToShutdown.size());
        assertTrue(sitesToShutdown.containsAll(List.of(availableSites.get(0), availableSites.get(1), availableSites.get(3), availableSites.get(4))));
    }
}