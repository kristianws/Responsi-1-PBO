package model;

public class User {
    private String name;
    private String pass;
    private String phone;

    public User(String name, String pass, String phone) {
        assert pass != null && !pass.isBlank();
        assert name != null && !name.isBlank();
        assert phone != null && phone.length() >= 8;

        this.pass = pass;
        this.name = name;
        this.phone = phone;
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


    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name + " (" + phone + ")";
    }

    // file: id|name|phone
    public String toFileString() {
        return name + "|" + pass + "|" + phone + "\n";
    }

    public static User fromFileToString(String line) throws Exception {
        String[] data =line.split("\\|");

        if (data.length != 3) {
            throw new Exception("Format File User Salah : " + line);
        }

        User baru = new User(data[0], data[1], data[2]);
        return baru;
    }

}
