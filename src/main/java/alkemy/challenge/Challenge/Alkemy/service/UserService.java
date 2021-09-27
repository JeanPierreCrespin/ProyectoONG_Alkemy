package alkemy.challenge.Challenge.Alkemy.service;

import java.util.List;
import java.util.Optional;

import alkemy.challenge.Challenge.Alkemy.controller.request.UserRequest;
import alkemy.challenge.Challenge.Alkemy.exception.ListNotFoundException;
import alkemy.challenge.Challenge.Alkemy.exception.RecordNotExistException;
import alkemy.challenge.Challenge.Alkemy.model.Role;
import alkemy.challenge.Challenge.Alkemy.repository.BaseRepository;
import alkemy.challenge.Challenge.Alkemy.service.imp.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import alkemy.challenge.Challenge.Alkemy.model.User;
import alkemy.challenge.Challenge.Alkemy.repository.UserRepository;

@Service
public class UserService extends BaseServiceImpl<User, Long> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    public UserService(BaseRepository<User, Long> baseRepository) {
        super(baseRepository);
    }

    public Optional<User> findByEmail(String email) {return userRepository.findUserByEmail(email);}

    public User getLoggedUser() throws RecordNotExistException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByEmail(userDetails.getUsername()).orElseThrow(() -> new RecordNotExistException("User has not been found..."));
        return user;
    }
    
    public List<User> getAllNotDeleted() throws ListNotFoundException{
    	List<User> list = userRepository.findAllBySoftDeleteFalseOrderByCreateAt();
    	return Optional.ofNullable(list).orElseThrow(() -> new ListNotFoundException("no slide to list"));
    }
    
    public User patchUpdate(Long id, UserRequest userRequest) throws RecordNotExistException {

        User user = this.getById(id);

        if (userRequest.getFirstName() != null) {
            user.setFirstName(userRequest.getFirstName());
        }
        if (userRequest.getLastName() != null) {
            user.setLastName(userRequest.getLastName());
        }
        if (userRequest.getEmail() != null) {
            user.setEmail(userRequest.getEmail());
        }
        if (userRequest.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }
        if (userRequest.getPhoto() != null) {
            user.setPhoto(userRequest.getPhoto());
        }

        User update = this.update(user);
		return update;
    }

    public User deleteUser(User user) {
        user.setSoftDelete(true);
        return this.update(user);
    }

    public User createUser(User user) {

        Role role = new Role();
        role = roleService.findByName("USER");
        user.setRoleId(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setSoftDelete(false);
        return userRepository.save(user);
    }

}
