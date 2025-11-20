package service;

import model.UserAccount;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AuthService {

    private ArrayList<UserAccount> users;
    private StorageUtil storage;

    private static final Pattern USERNAME_REGEX = Pattern.compile("^[A-Za-z][A-Za-z0-9]{4,}$");

    public AuthService(StorageUtil storage, ArrayList<UserAccount> users) {
        this.storage = storage;
        this.users = users;
    }

    public UserAccount login(String username, String password) {
        for (UserAccount user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public boolean usernameValid(String username) {
        return USERNAME_REGEX.matcher(username).matches();
    }

    public boolean register(String username, String password) throws Exception {
        if (!usernameValid(username)) {
            throw new Exception("Username Tidak Valid.");
        }
        if (password == null || password.length() < 4) {
            throw new Exception("Password Minimal 4 karakter");
        }
        for (UserAccount user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                throw new Exception("Username tidak ada");
            }
        }

        UserAccount newUser = new UserAccount(username, password, "USER");
        users.add(newUser);
        
        storage.saveUsers(users);
        return true;
    }

    public void createUserAccount(String username, String password, String role) throws Exception {
        if (!usernameValid(username)) {
            throw new Exception("Username Tidak Valid");
        }
        for (UserAccount user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                throw new Exception("Sudah Ada.");
            }
        }
        users.add(new UserAccount(username, password, role));
        storage.saveUsers(users);
    }

    public ArrayList<UserAccount> getAllUsers() { return users; }

}
