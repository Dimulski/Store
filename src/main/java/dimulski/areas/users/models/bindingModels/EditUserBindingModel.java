package dimulski.areas.users.models.bindingModels;

import dimulski.areas.users.models.contracts.BasicUserModel;
import dimulski.areas.variables.Variables;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EditUserBindingModel extends BasicUserModel {
    
    private Long id;

    @NotNull(message = "Username cannot be null")
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters.")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Pattern(regexp = Variables.EMAIL_PATTERN, message = "Not a valid email address")
    private String email;
    
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
