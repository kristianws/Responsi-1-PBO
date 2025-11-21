package model;

public class User {
    private int id;
    private String name;
    private String pass;
    private String phone;
    private String role;

    public User(int id, String pass, String name, String phone, String role) {
        assert id >= 0;
        assert pass != null && !pass.isBlank();
        assert name != null && !name.isBlank();
        assert phone != null && phone.length() >= 7;
        assert role == "ADMIN";

        this.id = id;
        this.pass = pass;
        this.name = name;
        this.phone = phone;
        this.role = role;
    }

    public User(int id, String nama, String password, String phone) {
        assert password != null && !pass.isBlank();
        assert nama != null && !name.isBlank();
        assert phone != null && phone.length() >= 7;

        this.id = id;
        this.name = nama;
        this.pass = password;
        this.phone = phone;
        this.role = "USER";
    }

    public int getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
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
        return id + "|" + name + "|" + phone + "|" + pass + "|" + role;
    }

    public static User fromFileToString(String line) throws Exception {
        String[] data =line.split("\\|");

        if (data.length != 5) {
            throw new Exception("Format File User Salah : " + line);
        }

        User baru = new User(Integer.parseInt(data[0]),data[1], data[2], data[3], data[4]);
        return baru;
    }

}
