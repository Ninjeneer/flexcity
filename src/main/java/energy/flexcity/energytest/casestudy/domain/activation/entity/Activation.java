package energy.flexcity.energytest.casestudy.domain.activation.entity;

import energy.flexcity.energytest.casestudy.domain.site.entity.Site;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Activation {
    public static final String COMMAND_START = "start";
    public static final String COMMAND_STOP = "stop";
    private String id;
    private long receivedTimestamp;
    private long duration;
    private double power;

    private Site site;

    public Activation(String id, long receivedTimestamp, long duration, double power, Site site) {
        this.id = id;
        this.receivedTimestamp = receivedTimestamp;
        this.duration = duration;
        this.power = power;
        this.site = site;
    }

    public Activation(long duration, double power, Site site) {
        this.site = site;
        this.id = UUID.randomUUID().toString();
        this.receivedTimestamp = new Date().getTime();
        this.duration = duration;
        this.power = power;
    }

    public Activation() {

    }

    public String getId() {
        return id;
    }

    public long getReceivedTimestamp() {
        return receivedTimestamp;
    }

    public long getDuration() {
        return duration;
    }

    public double getPower() {
        return power;
    }

    public void onActivationEnd(PostActivationProcess postActivationProcess) {
        postActivationProcess.process();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activation that = (Activation) o;
        return receivedTimestamp == that.receivedTimestamp && duration == that.duration && Double.compare(that.power, power) == 0 && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, receivedTimestamp, duration, power);
    }
}
