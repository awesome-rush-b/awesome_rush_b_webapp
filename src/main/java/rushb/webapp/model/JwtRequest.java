package rushb.webapp.model;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;

// This class is required for storing the username and password we received from the client.
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;

    @NotBlank(message = "Username should not be blank!")
    private String username;
    @NotBlank(message = "Password should not be blank!")
    private String password;

    //need default constructor for JSON Parsing
    public JwtRequest()
    {

    }

    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
