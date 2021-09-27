package alkemy.challenge.Challenge.Alkemy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import alkemy.challenge.Challenge.Alkemy.model.Testimonials;

import java.util.Optional;

@Repository
public interface TestimonialsRespository extends BaseRepository<Testimonials, Long> {

    Page<Testimonials> findAllByOrderByCreateAt(Pageable pageable);
    Page<Testimonials>findAllBySoftDeleteFalseOrderByCreateAt(Pageable pageable);

    Optional<Testimonials> findByName(String name);
}
