package umkm;

public class Login {

    private String username = "admin";
    private String password = "admin";

    // Constructor default
    public Login() {
    }

    // Constructor dengan argument
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Menghapus method getPassword() untuk keamanan
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
