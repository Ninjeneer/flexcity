package energy.flexcity.energytest.casestudy.infrastructure;

public class EmailHandler implements ContactHandler {

    public void sendMessage(String contactInformation, String subject, String message) {
        System.out.println(
            String.format("Email sent to %s, subject is %s, message is %s", contactInformation, subject, message));
    }
}
