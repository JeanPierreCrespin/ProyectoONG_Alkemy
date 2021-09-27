package alkemy.challenge.Challenge.Alkemy.repository;

import alkemy.challenge.Challenge.Alkemy.model.Member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends BaseRepository<Member,Long> {
	
	Page<Member> findAllBySoftDeleteFalseOrderByCreateAt(Pageable paging);
	
}
