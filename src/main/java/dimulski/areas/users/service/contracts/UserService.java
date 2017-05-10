package dimulski.areas.users.service.contracts;

import dimulski.areas.games.models.viewModels.SmallGameViewModel;
import dimulski.areas.users.models.bindingModels.EditUserBindingModel;
import dimulski.areas.users.models.bindingModels.RegisterUserBindingModel;
import dimulski.areas.users.models.viewModels.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void register(RegisterUserBindingModel registerUserBindingModel);

    List<UserViewModel> findAll();
    
    EditUserBindingModel findById(long id);
    
    void save(EditUserBindingModel editUserBindingModel);
    
    void deleteById(long userId);
    
    void addGameToUser(long gameId, String username);
    
    void removeGame(long gameId, String username);
    
    int getProductCount(String username);
    
    List<SmallGameViewModel> getProducts(String username);
}
