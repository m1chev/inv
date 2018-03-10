package enums;

public enum SuccessMessages {
    RECORD_ADD_SUCCESS("The data has been saved"),
    RECORD_DELETE_SUCCESS("Your data has been deleted"),
    RECORD_UPDATE_SUCCESS("Your data has been updated"),
    USER_PIN_WARN("User has no PIN, card, finger or face template registered!");
    //... add more cases here ...

    private final String message;

    SuccessMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}