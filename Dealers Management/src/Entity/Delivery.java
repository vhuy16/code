package Entity;

public class Delivery {

    private String id;
    private String name;
    private double unitPrice;
    private int quantity;
    private String status;

    public Delivery(String id, String name, double unitPrice, int quantity, String status) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.status = status;
    }

    public Delivery(String line) {
        String[] parts = line.split("\\s" + "\\|" + "\\|" + "\\s");
        id = parts[0].trim();
        name = parts[1].trim();
        unitPrice = Double.parseDouble(parts[2]);
        quantity = Integer.parseInt(parts[3]);
        status = parts[4];
    }

    public Delivery() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + " || " + name + " || " + unitPrice + " || " + quantity + " || " + status;
    }

}
