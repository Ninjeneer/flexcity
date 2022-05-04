package energy.flexcity.energytest.refactoring;


import java.util.ArrayList;
import java.util.List;

public class Data {

    public static final List<Subscription> SUBSCRIPTIONS;

    static {
        SUBSCRIPTIONS = new ArrayList<>(4);


        Data.SUBSCRIPTIONS.addAll(List.of(
            new Subscription(Protocol.HTTP_JSON, "http://localhost/component1", "component1", 1.5, null, null, true),
            new Subscription(Protocol.HTTP_JSON, "http://localhost/component2", "component2", 1.5, null, null, false),
            new Subscription(Protocol.HTTP_XML, "http://localhost/component3", "component3", null, 3.9, null, true),
            new Subscription(Protocol.FTP, "http://localhost/component4", "component4", null, null, "UP", true)
        ));
    }
}
