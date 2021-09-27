package alkemy.challenge.Challenge.Alkemy.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import alkemy.challenge.Challenge.Alkemy.model.Role;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {

	Optional<Role> findByName(String name);


}
