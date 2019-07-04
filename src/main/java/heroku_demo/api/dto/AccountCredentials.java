package heroku_demo.api.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccountCredentials{
    @Email(message = "it must be email")
    @NotNull(message = "email can not be null")
    private String email;

    @NotNull(message = "email can not be null")
    @Size(min = 1, max = 10, message = "size must be no more than 10 letters")
    @NotBlank(message = "password can not have 0 symbols or only spaces")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}