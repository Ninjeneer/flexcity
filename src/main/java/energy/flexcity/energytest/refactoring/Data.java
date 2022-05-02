package energy.flexcity.energytest.refactoring;

import energy.flexcity.energytest.refactoring.SubscriptionHandler.Subscription;

import java.util.ArrayList;
import java.util.List;

public class Data {

    public static final List<Subscription> SUBSCRIPTIONS;

    static {
        Subscription subscription1 =
            new Subscription("HTTP/JSON", "http://localhost/component1", "component1", 1.5, null, null, true);
        Subscription subscription2 =
            new Subscription("HTTP/JSON", "http://localhost/component2", "component2", 1.5, null, null, false);
        Subscription subscription3 =
                new Subscription("HTTP/XML", "http://localhost/component3", "component3", null, 3.9, null, true);
        Subscription subscription4 =
                new Subscription("FTP", "http://localhost/component4", "component4", null, null, "UP", true);
        SUBSCRIPTIONS = new ArrayList<Subscription>(4);
        SUBSCRIPTIONS.add(subscription1);
        SUBSCRIPTIONS.add(subscription2);
        SUBSCRIPTIONS.add(subscription3);
        SUBSCRIPTIONS.add(subscription4);
    }
}
