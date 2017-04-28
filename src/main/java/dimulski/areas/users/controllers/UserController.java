package dimulski.areas.users.controllers;

import dimulski.areas.users.models.bindingModels.LoginUserBindingModel;
import dimulski.areas.users.models.bindingModels.RegisterUserBindingModel;
import dimulski.areas.users.models.viewModels.UserViewModel;
import dimulski.areas.users.service.contracts.UserService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.Param;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController { // should be split

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegisterUserBindingModel registerUserBindingModel) {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegisterUserBindingModel registerUserBindingModel, @Valid BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (!registerUserBindingModel.getPassword().equals(registerUserBindingModel.getConfirmPassword())) {
            bindingResult.reject("error.object", "Confirm password doesn't match password");
            return "register";
        }

        this.userService.register(registerUserBindingModel);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/users")
    public String getUsersPage(Model model) {
        List<UserViewModel> userViewModels = this.userService.findAll();
        model.addAttribute("users", userViewModels);

        return "users";
    }
}
