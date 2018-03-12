package util.enums;

public enum Pages {
    INV_URL("https://st2016.inv.bg"),
    INV_LOGIN_PAGE("/login"),
    INV_CLIENT_PAGE("/clients/manage"),
    INV_ITEM_PAGE("/objects/manage"),
    INV_INVOICE_PAGE("/invoices"),
    INV_CASH_BOX_PAGE("/cashbox");


    private final String path;

    Pages(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}