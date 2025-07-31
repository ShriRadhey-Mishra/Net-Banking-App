public class User {
    public String name, email, password;
    public long phoneNumber;
    public String pin;

    public User() {}

    public User(String name, String email, String password, long phoneNumber, String pin) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.pin = pin;
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
