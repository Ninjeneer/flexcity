package energy.flexcity.energytest.refactoring;

import energy.flexcity.energytest.refactoring.readers.FtpReader;
import energy.flexcity.energytest.refactoring.readers.HttpJsonReader;
import energy.flexcity.energytest.refactoring.readers.HttpXmlReader;
import energy.flexcity.energytest.refactoring.readers.Reader;
import energy.flexcity.energytest.refactoring.transformers.BooleanStringCompare;
import energy.flexcity.energytest.refactoring.transformers.BooleanThreshold;
import energy.flexcity.energytest.refactoring.transformers.NumberCoefficient;

import java.util.List;

public class SubscriptionHandler {

    public static void main(String[] args) {
        new SubscriptionHandler().perform(Data.SUBSCRIPTIONS);
    }

    private void perform(List<Subscription> subscriptions) {
        for (Subscription subscription : subscriptions) {
            System.out.println("Start treatment of subscription " + subscription.getComponentName());

            if (!subscription.isActive()) {
                System.out.println(
                        "\tSubscription " + subscription.getComponentName() + " is inactive, won't send notification.");
                continue;
            }

            Reader reader;
            if (subscription.getProtocol() == Protocol.HTTP_JSON) {
                reader = new HttpJsonReader();
            } else if (subscription.getProtocol() == Protocol.HTTP_XML) {
                reader = new HttpXmlReader();
            } else {
                reader = new FtpReader();
            }
            reader.readValue();

            System.out.println("\tValue received is " + reader.getValue());

            Object o = reader.getValue();
            if (subscription.getNumberTransformationCoeff() != null) {
                o = reader.transform(new NumberCoefficient(subscription.getNumberTransformationCoeff()));
            } else if (subscription.getBooleanTransformationThreshold() != null) {
                o = reader.transform(new BooleanThreshold(subscription.getBooleanTransformationThreshold()));
            } else if (subscription.getStringTransformationToCompare() != null) {
                o = reader.transform(new BooleanStringCompare(subscription.getStringTransformationToCompare()));
            }
            System.out.println("\tAfter transformation value is " + o);

            boolean isNotificationSent = this.notify(o, subscription);
            // if it did not work, try again
            if (!isNotificationSent) {
                System.out.println("\tNotification failed, will try again");
                isNotificationSent = this.notify(o, subscription);
            }

            if (!isNotificationSent) {
                System.out.println("\tCould not notify.");
            } else {
                System.out.println("\tNotification succeeded.");
            }
        }
    }

    private boolean notify(Object o, Subscription subscription) {
        if (o instanceof Boolean) {
            return new Notifier().notifyBoolean((Boolean) o, subscription.getNotifyUrl());
        } else {
            return new Notifier().notifyDouble((Double) o, subscription.getNotifyUrl());
        }
    }
}
