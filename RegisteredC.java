public class RegisteredC {
    private String Name;
    private String Email;
    private String Password;
    private String Address;
    private int LoyaltyPoints;

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
