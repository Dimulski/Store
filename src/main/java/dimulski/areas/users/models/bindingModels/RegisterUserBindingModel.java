package dimulski.areas.users.models.bindingModels;

import dimulski.areas.variables.Variables;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterUserBindingModel {

    @NotNull
    @Size(min = 3, max = 10, message = "Invalid username")
    private String username;

    @NotNull
    @Pattern(regexp = Variables.EMAIL_PATTERN)
    private String email;

    @NotNull
    @Pattern(regexp = Variables.PASSWORD_PATTERN)
    private String password;

    @NotNull
    @Pattern(regexp = Variables.PASSWORD_PATTERN)
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
