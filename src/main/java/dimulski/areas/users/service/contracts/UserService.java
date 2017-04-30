package dimulski.areas.users.service.contracts;

import dimulski.areas.users.models.bindingModels.EditUserBindingModel;
import dimulski.areas.users.models.bindingModels.RegisterUserBindingModel;
import dimulski.areas.users.models.viewModels.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void register(RegisterUserBindingModel registerUserBindingModel);

    List<UserViewModel> findAll();
    
    EditUserBindingModel findById(Long id);
    
    void save(EditUserBindingModel editUserBindingModel);
}
