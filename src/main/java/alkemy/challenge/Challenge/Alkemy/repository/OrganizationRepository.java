package alkemy.challenge.Challenge.Alkemy.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import alkemy.challenge.Challenge.Alkemy.model.Organization;

@Repository
public interface OrganizationRepository extends BaseRepository<Organization, Long> {

	List<Organization> findAllBySoftDeleteFalseOrderByCreateAt();

}
