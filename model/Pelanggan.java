package model;

public class Pelanggan {
    private String id;
    private String name;
    private String phone;

    public Pelanggan(String id, String name, String phone) {
        assert id != null && !id.isBlank();
        assert name != null && !name.isBlank();
        assert phone != null && phone.length() >= 7;

        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + phone;
    }

    // file: id|name|phone
    public String toFileString() {
        return id + "|" + name + "|" + phone;
    }

    public static Pelanggan fromFileString(String line) throws Exception {
        String[] p = line.split("\\|");
        if (p.length != 3)
            throw new Exception("Format Pelanggan salah: " + line);
        return new Pelanggan(p[0], p[1], p[2]);
    }

}
