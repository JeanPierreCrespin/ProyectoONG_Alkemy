package alkemy.challenge.Challenge.Alkemy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import alkemy.challenge.Challenge.Alkemy.model.News;

@Repository
public interface NewsRepository extends BaseRepository<News,Long>{

	Page<News> findAllBySoftDeleteFalseOrderByCreateAt(Pageable paging);

}
