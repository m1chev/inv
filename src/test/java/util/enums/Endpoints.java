package util.enums;

public enum Endpoints {
    ITEM_ENDPOINT("/item"),
    ITEMS_ENDPOINT("/items"),
    CLIENT_ENDPOINT("/client"),
    CLIENTS_ENDPOINT("/clients"),
    INVOICES_ENDPOINT("/invoices"),
    INVOICE_ENDPOINT("/invoice");


    private final String path;

    Endpoints(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
