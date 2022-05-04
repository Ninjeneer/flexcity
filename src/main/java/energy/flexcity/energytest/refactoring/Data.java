package energy.flexcity.energytest.refactoring;

import energy.flexcity.energytest.refactoring.transformers.BooleanStringCompare;
import energy.flexcity.energytest.refactoring.transformers.BooleanThreshold;
import energy.flexcity.energytest.refactoring.transformers.NumberCoefficient;

import java.util.List;

public class Data {

    public static final List<Subscription> SUBSCRIPTIONS;

    static {
        SUBSCRIPTIONS = List.of(
                new Subscription<>(Protocol.HTTP_JSON, "http://localhost/component1", "component1", new NumberCoefficient(1.5), true),
                new Subscription<>(Protocol.HTTP_JSON, "http://localhost/component2", "component2", new NumberCoefficient(1.5), false),
                new Subscription<>(Protocol.HTTP_XML, "http://localhost/component3", "component3", new BooleanThreshold(3.9), true),
                new Subscription<>(Protocol.FTP, "http://localhost/component4", "component4", new BooleanStringCompare("UP"), true)
        );
    }
}
