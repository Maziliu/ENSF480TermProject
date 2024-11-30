package ENSF480TermProject.backend.dtos.auth;

public class CredentialsDTO {
    private String email, password;

    public CredentialsDTO() {}

    //Get
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    
    //Set
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
