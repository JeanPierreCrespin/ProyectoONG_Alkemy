package alkemy.challenge.Challenge.Alkemy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import alkemy.challenge.Challenge.Alkemy.model.MyUserDetails;
import alkemy.challenge.Challenge.Alkemy.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	@Transactional(readOnly = true)
	public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userService.findByEmail(username);
		
		if(user.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		
		return new MyUserDetails(user.get());
	}
}


