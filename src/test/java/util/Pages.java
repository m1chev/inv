package util;

public enum Pages {
    INV_URL("https://st2016.inv.bg"),
    BATTLESHIPS_URL("http://www.techhuddle.com/tests/battleships/v4test/index.php"),
    INV_LOGIN_PAGE("/login"),
    INV_CLIENT_PAGE("/clients/manage"),
    INV_ITEM_PAGE("/objects/manage");


    private final String path;

    Pages(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}