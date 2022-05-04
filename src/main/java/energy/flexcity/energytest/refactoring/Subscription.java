package energy.flexcity.energytest.refactoring;

import energy.flexcity.energytest.refactoring.transformers.Transformer;

public class Subscription<T, U> {

    private final Protocol protocol;
    private final String notifyUrl;
    private final String componentName;
    private final Transformer<T, U> transformer;
    private final boolean active;

    public Subscription(
            Protocol protocol,
            String notifyUrl,
            String componentName,
            Transformer<T, U> transformer,
            boolean active) {
        this.protocol = protocol;
        this.notifyUrl = notifyUrl;
        this.componentName = componentName;
        this.transformer = transformer;
        this.active = active;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public String getComponentName() {
        return componentName;
    }

    public U transform(T value) {
        return this.transformer.transform(value);
    }

    public boolean isActive() {
        return active;
    }
}
