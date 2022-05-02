package energy.flexcity.energytest.refactoring;

/** This class is just a mock Notifier, no need to refactor it. */
public class Notifier {

    private static int count = 0;

    public boolean notifyBoolean(Boolean value, String notifyUrl) {
        return notify(value, notifyUrl);
    }

    public boolean notifyDouble(Double value, String notifyUrl) {
        return notify(value, notifyUrl);
    }

    private boolean notify(Object value, String notifyUrl) {
        System.out.println("\tWill send value " + value + " to " + notifyUrl);
        count++;
        return count > 3;
    }
}
