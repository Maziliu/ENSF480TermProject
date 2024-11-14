package mvc.models;

public class User {
    private String email;

    public User(){
        this.email = null;
    }

    public User(String email){
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
