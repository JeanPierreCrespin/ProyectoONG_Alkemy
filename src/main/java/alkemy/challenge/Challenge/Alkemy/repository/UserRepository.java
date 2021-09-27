package alkemy.challenge.Challenge.Alkemy.repository;

import alkemy.challenge.Challenge.Alkemy.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User,Long> {

	Optional<User> findUserByEmail(String email);

	List<User> findAllBySoftDeleteFalseOrderByCreateAt();
}
