package dimulski.areas.users.service;

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
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
    public EditUserBindingModel findById(Long id) {
        User user = this.userRepository.findOne(id);
        EditUserBindingModel editUserBindingModel = this.modelMapper.map(user, EditUserBindingModel.class);
        setIsAdmin(user, editUserBindingModel);
        
        return editUserBindingModel;
    }

    @Override
    public void save(EditUserBindingModel editUserBindingModel) {
        User user = this.modelMapper.map(editUserBindingModel, User.class);
        user.setPassword(this.userRepository.findOne(editUserBindingModel.getId()).getPassword());
        this.userRepository.save(user);
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
