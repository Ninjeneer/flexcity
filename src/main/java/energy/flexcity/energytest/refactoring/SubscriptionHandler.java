package energy.flexcity.energytest.refactoring;

import java.util.List;

public class SubscriptionHandler {

    public static void main(String[] args) {
        new SubscriptionHandler().perform(Data.SUBSCRIPTIONS);
    }

    private void perform(List<Subscription> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Start treatment of subscription " + list.get(i).componentName);

            if (list.get(i).active == true) {
                Object o;
                if (list.get(i).protocol == "HTTP/JSON") {
                    o = new HttpJsonReader().readValue(list.get(i).componentName);
                } else if (list.get(i).protocol == "HTTP/XML") {
                    o = new HttpXmlReader().readValue(list.get(i).componentName);
                } else {
                    o = new FtpReader().readValue(list.get(i).componentName);
                }
                System.out.println("\tValue received is " + o);

                if (list.get(i).numberTransformationCoeff != null) {
                    o = (Double) o * list.get(i).numberTransformationCoeff;
                } else if (list.get(i).booleanTransformationThreshold != null) {
                    o = (Double) o > list.get(i).booleanTransformationThreshold;
                } else if (list.get(i).stringTransformationToCompare != null) {
                    o = o.equals(list.get(i).stringTransformationToCompare);
                }
                System.out.println("\tAfter transformation value is " + o);

                boolean r;
                if (o instanceof Boolean) {
                    r = new Notifier().notifyBoolean((Boolean) o, list.get(i).notifyUrl);
                } else {
                    r = new Notifier().notifyDouble((Double) o, list.get(i).notifyUrl);
                }

                // if it did not work, try again
                if (!r) {
                    System.out.println("\tNotification failed, will try again");
                    if (o instanceof Boolean) {
                        r = new Notifier().notifyBoolean((Boolean) o, list.get(i).notifyUrl);
                    } else {
                        r = new Notifier().notifyDouble((Double) o, list.get(i).notifyUrl);
                    }
                }

                if (!r) {
                    System.out.println("\tCould not notify.");
                } else {
                    System.out.println("\tNotification succeeded.");
                }
            } else {
                System.out.println(
                    "\tSubscription " + list.get(i).componentName + " is inactive, won't send notification.");
            }
        }
    }

    static class Subscription {
        public String protocol;
        public String notifyUrl;
        public String componentName;
        public Double numberTransformationCoeff;
        public Double booleanTransformationThreshold;
        public String stringTransformationToCompare;
        public boolean active;

        public Subscription(
            String protocol,
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
    }

    class HttpJsonReader {
        public Object readValue(String componentName) {
            return 3.5;
        }
    }

    class HttpXmlReader {
        public Object readValue(String componentName) {
            return 2.6;
        }
    }

    class FtpReader {
        public Object readValue(String componentName) {
            return "UP";
        }
    }
}
