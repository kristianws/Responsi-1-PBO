package model;

public class UserAccount {
    private String username;
    private String password;
    private String role;

    public UserAccount(String username, String password, String role) {
        assert username != null && !username.isBlank();
        assert password != null && password.length() >= 4;

        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    @Override
    public String toString() {
        return username + " (" + role + ")";
    }

    public String toFileString() {
        return username + "|" + password + "|" + role;
    }

    public static UserAccount fromFileString(String line) throws Exception {
        String[] p = line.split("\\|");
        if (p.length != 3)
            throw new Exception("Format user salah: " + line);
        return new UserAccount(p[0], p[1], p[2]);
    }

}
