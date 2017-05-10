package dimulski.areas.users.service;

import dimulski.areas.games.entities.Game;
import dimulski.areas.games.models.viewModels.SmallGameViewModel;
import dimulski.areas.games.repositories.GameRepository;
import dimulski.areas.users.entities.Role;
import dimulski.areas.users.entities.User;
import dimulski.areas.users.models.bindingModels.EditUserBindingModel;
import dimulski.areas.users.models.bindingModels.RegisterUserBindingModel;
import dimulski.areas.users.models.contracts.BasicUserModel;
import dimulski.areas.users.models.viewModels.UserViewModel;
import dimulski.areas.users.repositories.UserRepository;
import dimulski.areas.users.service.contracts.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid credentials");
        }

        return user;
    }

    @Override
    public void register(RegisterUserBindingModel registerUserBindingModel) {
        User user = this.modelMapper.map(registerUserBindingModel, User.class);
        String normalPassword = user.getPassword();
        String encryptedPassword = this.bCryptPasswordEncoder.encode(normalPassword);
        user.setPassword(encryptedPassword);
        this.userRepository.save(user);
    }

    @Override
    public List<UserViewModel> findAll() {
        List<UserViewModel> userViewModels = new ArrayList<>();
        List<User> users = this.userRepository.findAll();
        for (User user : users) {
            UserViewModel userViewModel = this.modelMapper.map(user, UserViewModel.class);
            setIsAdmin(user, userViewModel);
            userViewModels.add(userViewModel);
        }

        return userViewModels;
    }

    @Override
    public EditUserBindingModel findById(long id) {
        User user = this.userRepository.findOne(id);
        EditUserBindingModel editUserBindingModel = this.modelMapper.map(user, EditUserBindingModel.class);
        setIsAdmin(user, editUserBindingModel);
        
        return editUserBindingModel;
    }

    @Override
    public void save(EditUserBindingModel editUserBindingModel) {
        User user = this.modelMapper.map(editUserBindingModel, User.class);
        user.setPassword(this.userRepository.findOne(editUserBindingModel.getId()).getPassword());
        if (editUserBindingModel.getIsAdmin()) {
            Role admin = new Role(); // ideally we should get the role with a RoleRepository instead of creating a new one
            admin.setId(1L);
            admin.setAuthority("ROLE_ADMIN");
            user.setAuthorities(new HashSet<Role>());
            user.getAuthorities().add(admin);
        }
        this.userRepository.save(user);
    }

    @Override
    public void deleteById(long userId) {
        this.userRepository.delete(userId);
    }

    @Override
    public void addGameToUser(long gameId, String username) {
        Game game = this.gameRepository.findOne(gameId);
        User user = this.userRepository.findByUsername(username);
        user.getGames().add(game);
        this.userRepository.save(user);
    }

    @Override
    public void removeGame(long gameId, String username) {
        Game game = this.gameRepository.findOne(gameId);
        User user = this.userRepository.findByUsername(username);
        user.getGames().remove(game);
        this.userRepository.save(user);
    }

    @Override
    public int getProductCount(String username) {
        return this.userRepository.findByUsername(username).getGames().size();
    }

    @Override
    public List<SmallGameViewModel> getProducts(String username) {
        List<SmallGameViewModel> gameViewModels = new ArrayList<>();
        User user = this.userRepository.findByUsername(username);
        List<Game> userGames = this.gameRepository.findAllOfUser(user.getId());
        for (Game game : userGames) {
            SmallGameViewModel smallGameViewModel = this.modelMapper.map(game, SmallGameViewModel.class);
            gameViewModels.add(smallGameViewModel);
        }
        
        return gameViewModels;
    }

    private void setIsAdmin(User user, BasicUserModel userModel) {
        if (user.getAuthorities().stream().filter(r -> r.getAuthority().equals("ROLE_ADMIN"))
                .findFirst().orElse(null) != null) {
            userModel.setIsAdmin(true);
        } else {
            userModel.setIsAdmin(false);
        }
    }
}
