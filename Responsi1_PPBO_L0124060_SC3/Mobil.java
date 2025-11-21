package Responsi1_PPBO_L0124060_SC3;

public class Mobil {
    private String noPlat;
    private String brand;
    private String type;
    private Boolean available;
    private double pricePerDay;

    public Mobil(String noPlat, String brand, String type, double pricePerDay) {
        assert noPlat != null && !noPlat.isBlank();
        assert brand != null && !brand.isBlank();
        assert pricePerDay >= 0;

        this.noPlat = noPlat;
        this.brand = brand;
        this.type = type;
        this.pricePerDay = pricePerDay;
        this.available = true;
    }
    // Getter
    public String getNoPlat() {return noPlat;}
    public String getBrand() {return brand;}
    public String getType() {return type;}
    public Boolean getAvailable() {return available;}
    public double getPrice() {return pricePerDay;}

    // Setter
    public void setNoPlat(String noPlat) {this.noPlat = noPlat;}
    public void setBrand(String brand) {this.brand = brand;}
    public void setType(String type) {this.type = type;}
    public void setType(Boolean available) {this.available = available;}
    public void setPrice(double pricePerDay) {this.pricePerDay = pricePerDay;}
    public void rentOut() {this.available = false;}
    public void returnMobil() {this.available = true;}

    @Override
    public String toString() {
        String ketersediaan;
        if (available) {
            ketersediaan = "Ada";
        } else {
            ketersediaan = "Sedang Disewa";
        }
        
        return noPlat + " - " + brand + " " + type + " ( " + ketersediaan + " ) ( " + pricePerDay + " )";
    }

    public String toFileString() {
        return noPlat + "|" + brand + "|" + type + "|" + pricePerDay + "|" + available +"\n";
    }

    public static Mobil fromFileString(String line) throws Exception {
        String[] data =line.split("\\|");

        if (data.length != 5) {
            throw new Exception("Format File Mobil Salah : " + line);
        }

        Mobil baru = new Mobil(data[0], data[1], data[2], Double.parseDouble(data[3]));
        baru.available = Boolean.parseBoolean(data[4]);
        return baru;
    }

}
