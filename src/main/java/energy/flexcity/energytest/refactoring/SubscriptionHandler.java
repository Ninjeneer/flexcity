package energy.flexcity.energytest.refactoring;

import energy.flexcity.energytest.refactoring.readers.FtpReader;
import energy.flexcity.energytest.refactoring.readers.HttpJsonReader;
import energy.flexcity.energytest.refactoring.readers.HttpXmlReader;
import energy.flexcity.energytest.refactoring.readers.Reader;

import java.util.List;

public class SubscriptionHandler {

    public static void main(String[] args) {
        new SubscriptionHandler().perform(Data.SUBSCRIPTIONS);
    }

    private void perform(List<Subscription> subscriptions) {
        for (Subscription subscription : subscriptions) {
            // No external libraries so I won't change it, but the use of a real logger is better than using native prints
            System.out.println("Start treatment of subscription " + subscription.getComponentName());

            // Do not process if the subscription is inactive
            if (!subscription.isActive()) {
                System.out.println(
                        "\tSubscription " + subscription.getComponentName() + " is inactive, won't send notification.");
                continue;
            }

            // Read the component value
            Reader reader = this.readComponentValue(subscription);
            System.out.println("\tValue received is " + reader.getValue());

            // Apply subscription transformation
            // I am not a fan of Object casting, but I struggled to refactor it without over-over-engineering things
            Object transformedValue = subscription.transform(reader.getValue());
            System.out.println("\tAfter transformation value is " + transformedValue);

            // Send notification
            boolean isNotificationSent = this.notify(transformedValue, subscription);
            // If it did not work, try again
            if (!isNotificationSent) {
                System.out.println("\tNotification failed, will try again");
                isNotificationSent = this.notify(transformedValue, subscription);
            }

            if (!isNotificationSent) {
                System.out.println("\tCould not notify.");
            } else {
                System.out.println("\tNotification succeeded.");
            }
        }
    }

    private boolean notify(Object componentValue, Subscription subscription) {
        // Same goes for instanceof checks. Resolving the above Object casting would have allow removing it
        if (componentValue instanceof Boolean) {
            return new Notifier().notifyBoolean((Boolean) componentValue, subscription.getNotifyUrl());
        } else {
            return new Notifier().notifyDouble((Double) componentValue, subscription.getNotifyUrl());
        }
    }

    private Reader readComponentValue(Subscription subscription) {
        Reader reader;
        if (subscription.getProtocol() == Protocol.HTTP_JSON) {
            reader = new HttpJsonReader();
        } else if (subscription.getProtocol() == Protocol.HTTP_XML) {
            reader = new HttpXmlReader();
        } else {
            reader = new FtpReader();
        }
        reader.readValue();
        return reader;
    }
}
