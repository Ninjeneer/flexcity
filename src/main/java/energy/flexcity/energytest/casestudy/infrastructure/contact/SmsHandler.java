package energy.flexcity.energytest.casestudy.infrastructure.contact;

public class SmsHandler implements ContactHandler {

    public void sendMessage(String contactInformation, String subject, String message) {
        System.out.println(
            String.format("SMS sent to %s, message is %s", contactInformation, subject + ": " + message));
    }
}
