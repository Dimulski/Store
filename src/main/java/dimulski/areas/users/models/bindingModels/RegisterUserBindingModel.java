package dimulski.areas.users.models.bindingModels;

import dimulski.areas.variables.Variables;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterUserBindingModel {

    @NotNull(message = "Username cannot be null")
//    @NotEmpty(message = "Username cannot be empty") // should be removed
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters.")
    private String username;

    @NotNull(message = "Password cannot be null")
//    @NotEmpty(message = "Email cannot be empty")
    @Pattern(regexp = Variables.EMAIL_PATTERN, message = "Not a valid email address")
    private String email;

    @NotNull(message = "Password cannot be null")
//    @NotEmpty(message = "Password cannot be empty")
    @Pattern(regexp = Variables.PASSWORD_PATTERN, message = "Password must contain at least one uppercase letter and one number")
    private String password;

    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
