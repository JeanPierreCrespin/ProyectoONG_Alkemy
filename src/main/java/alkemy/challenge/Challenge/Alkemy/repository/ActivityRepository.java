package alkemy.challenge.Challenge.Alkemy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import alkemy.challenge.Challenge.Alkemy.model.Activity;

@Repository
public interface ActivityRepository extends BaseRepository<Activity,Long> {
	Optional<Activity> findByName(String name);

    List<Activity> findAllBySoftDeleteFalseOrderByCreateAt();
}
