package enums;

public enum ErrorMessages {
    BAD_LOGIN_COMPANY("Company not found"),
    BAD_GROUP_NAME("Name can contain only lowercase/uppercase letters, -, _, : or space characters"),
    RECORD_DUPLICATE("Must be unique"),
    RECORD_NOT_ADDED("Unable to add the data"),
    USER_REQUIRED_FIELD_MISSING("This field is required."),
    REPORT_SETTINGS_WARN("Please note that any change to the Report settings must be followed by a reconversion of the logs to match the new rules. Be very careful!"),
    BAD_LOGIN_CREDENTIALS("Invalid username or password"),
    SHORT_PASSWORD("Password needs to be at least 6 characters long"),
    WEAK_PASSWORD("Your password does not comply to our password policies (see help icon)"),
    DUPLICATE_USERNAME("Username already exists!"),
    PASSWORD_MISS_MATCH("Passwords not equal"),
    USER_CONTAINED_PASSWORD("Password must not contain the username");
    //... add more cases here ...

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}