package alkemy.challenge.Challenge.Alkemy.repository;

import org.springframework.stereotype.Repository;

import alkemy.challenge.Challenge.Alkemy.model.Contact;

import java.util.List;

@Repository
public interface ContactRepository extends BaseRepository<Contact, Long>{

    List<Contact> findAllBySoftDeleteFalseOrderByCreateAt();
}
