package model;

public class Mobil {
    private String id;
    private String brand;
    private String type;
    private Boolean available;
    private double pricePerDay;

    public Mobil(String id, String brand, String type, double pricePerDay) {
        assert id != null && !id.isBlank();
        assert brand != null && !brand.isBlank();
        assert pricePerDay >= 0;
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.pricePerDay = pricePerDay;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public Boolean getAvailable() {
        return available;
    }

    public double getPrice() {
        return pricePerDay;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setType(Boolean available) {
        this.available = available;
    }

    public void setPrice(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void rentOut() {
        this.available = false;
    }

    public void returnMobil() {
        this.available = true;
    }

    @Override
    public String toString() {
        String ketersediaan;
        if (available) {
            ketersediaan = "Ada";
        } else {
            ketersediaan = "Sedang Disewa";
        }
        
        return id + " - " + brand + " (" + type + ") " + ketersediaan + "( " + pricePerDay + " )";
    }

    public String toFileString() {
        return id + "|" + brand + "|" + type + "|" + pricePerDay + "|" + available;
    }

    public static Mobil fromFileString(String line) throws Exception {
        String[] parts = line.split("\\|");
        if (parts.length != 5)
            throw new Exception("Format mobil salah: " + line);
        Mobil c = new Mobil(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]));
        c.available = Boolean.parseBoolean(parts[4]);
        return c;
    }

}
