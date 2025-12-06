package at.fhtw.mrp.model;

public class User {
    private String username;
    private String password; // später: hashedPassword
    private String token;    // wird beim Login vergeben

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
