package dimulski.areas.users.models.bindingModels;

import dimulski.areas.variables.Variables;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginUserBindingModel {

    @NotNull(message = "Username cannot be null")
    @NotEmpty(message = "Password cannot be empty")
    private String username;

    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
