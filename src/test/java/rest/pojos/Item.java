package rest.pojos;


public class Item {
    private String name;
    private String price_for_quantity;
    private String quantity_unit;

    public Item(String name, String price_for_quantity, String quantity_unit) {
        this.name = name;
        this.price_for_quantity = price_for_quantity;
        this.quantity_unit = quantity_unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice_for_quantity() {
        return price_for_quantity;
    }

    public void setPrice_for_quantity(String price_for_quantity) {
        this.price_for_quantity = price_for_quantity;
    }

    public String getQuantity_unit() {
        return quantity_unit;
    }

    public void setQuantity_unit(String quantity_unit) {
        this.quantity_unit = quantity_unit;
    }


}
