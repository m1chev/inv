package enums;

public enum ShowRecords {
    TEN("10"),
    TWENTY_FIVE("25"),
    FIFTY("50"),
    ONE_HUNDRED("100");

    private final String message;

    ShowRecords(String message) {
        this.message = message;
    }

    public String getCount() {
        return message;
    }
}
