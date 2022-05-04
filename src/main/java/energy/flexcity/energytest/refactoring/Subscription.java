package energy.flexcity.energytest.refactoring;

public class Subscription {

    private final Protocol protocol;
    private final String notifyUrl;
    private final String componentName;
    private final Double numberTransformationCoeff;
    private final Double booleanTransformationThreshold;
    private final String stringTransformationToCompare;
    private final boolean active;

    public Subscription(
            Protocol protocol,
            String notifyUrl,
            String componentName,
            Double numberTransformationCoeff,
            Double booleanTransformationThreshold,
            String stringTransformationToCompare,
            boolean active) {
        this.protocol = protocol;
        this.notifyUrl = notifyUrl;
        this.componentName = componentName;
        this.numberTransformationCoeff = numberTransformationCoeff;
        this.booleanTransformationThreshold = booleanTransformationThreshold;
        this.stringTransformationToCompare = stringTransformationToCompare;
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

    public Double getNumberTransformationCoeff() {
        return numberTransformationCoeff;
    }

    public Double getBooleanTransformationThreshold() {
        return booleanTransformationThreshold;
    }

    public String getStringTransformationToCompare() {
        return stringTransformationToCompare;
    }

    public boolean isActive() {
        return active;
    }
}
