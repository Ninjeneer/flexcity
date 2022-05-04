package energy.flexcity.energytest.refactoring;

public enum Protocol {
    HTTP_JSON("HTTP/JSON"),
    HTTP_XML("HTTP/XML"),
    FTP("FTP");

    public final String value;

    Protocol(String value) {
        this.value = value;
    }
}
