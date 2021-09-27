package alkemy.challenge.Challenge.Alkemy.service;

import alkemy.challenge.Challenge.Alkemy.model.Role;
import alkemy.challenge.Challenge.Alkemy.repository.BaseRepository;
import alkemy.challenge.Challenge.Alkemy.repository.RoleRepository;
import alkemy.challenge.Challenge.Alkemy.service.imp.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseServiceImpl<Role, Long> {

	@Autowired
	private RoleRepository roleRepository;

	public RoleService(BaseRepository<Role, Long> baseRepository) {
		super(baseRepository);
	}

	public Role findByName(String admin) {
		Role role = roleRepository.findByName(admin).orElse(null);
		return role;
	}
}
