package enums;

public enum Pages {
    LOGIN_PAGE("/admins/login"),
    USERS_PAGE("/users"),
    ADMINISTRATORS_PAGE("/admins"),
    ADD_ADMINISTRATORS_PAGE("/admins/add"),
    DELETE_ADMINISTRATORS_PAGE("/admins/delete"),
    ADMIN_LEVEL_PAGE("/adminlevels"),
    ADD_ADMIN_LEVEL_PAGE("/adminlevels/add"),
    DELETE_ADMIN_LEVEL_PAGE("/adminlevels/delete"),
    HOME_PAGE("/home"),
    LOGOUT("/admins/logout"),
    DOOR_MANAGEMENT_PAGE("/dooraccess"),
    DOORS_PAGE("/doors"),
    ADD_DOORS_PAGE("/doors/add"),
    DELETE_DOORS_PAGE("/doors/delete"),
    EDIT_DOORS_PAGE("/doors/edit"),
    ZONES_PAGE("/zones"),
    ADD_ZONES_PAGE("/zones/add"),
    DELETE_ZONES_PAGE("/zones/delete"),
    SCANNERS_PAGE("/modules"),
    GROUPS_PAGE("/groups"),
    ADD_GROUP_PAGE("/groups/add"),
    DELETE_GROUP_PAGE("/groups/delete"),
    LOCATIONS_PAGE("/locations"),
    ADD_LOCATIONS_PAGE("/locations/add"),
    DELETE_LOCATIONS_PAGE("/locations/delete"),
    OVERVIEW_PAGE("/overview"),
    REPORT_SETTINGS_PAGE("/settingsreports"),
    REPORTS_PAGE("/reports"),
    ADD_USER_PAGE("/users/add"),
    DELETE_USER_PAGE("/users/delete"),
    ACCESS_CONTROL_PAGE("/accesscontrols"),
    TIME_SCHEDULES_PAGE("/timeschedules"),
    ADD_TIME_SCHEDULES_PAGE("/timeschedules/add"),
    DELETE_TIME_SCHEDULES_PAGE("/timeschedules/delete"),
    DEVICE_SETTINGS_PAGE("/settingsdevices"),
    PRIVACY_SETTINGS_PAGE("/settingsprivacy"),
    FUNCTION_KEY_SETTINGS_PAGE("/functionkeys"),
    ANTIPASSBACK_PAGE("/apbs");

    //... add more cases here ...

    private final String message;

    Pages(String message) {
        this.message = message;
    }

    public String getUrl() {
        return message;
    }
}
