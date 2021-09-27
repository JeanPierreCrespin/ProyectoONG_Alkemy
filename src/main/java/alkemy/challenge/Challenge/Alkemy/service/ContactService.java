package alkemy.challenge.Challenge.Alkemy.service;

import alkemy.challenge.Challenge.Alkemy.exception.ListNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alkemy.challenge.Challenge.Alkemy.controller.request.ContactRequest;
import alkemy.challenge.Challenge.Alkemy.exception.RecordNotExistException;
import alkemy.challenge.Challenge.Alkemy.model.Contact;
import alkemy.challenge.Challenge.Alkemy.repository.BaseRepository;
import alkemy.challenge.Challenge.Alkemy.repository.ContactRepository;
import alkemy.challenge.Challenge.Alkemy.service.imp.BaseServiceImpl;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService extends BaseServiceImpl<Contact, Long> {

	@Autowired
	private ContactRepository contactRepository;

	public ContactService(BaseRepository<Contact, Long> baseRepository) {
		super(baseRepository);
		// TODO Auto-generated constructor stub
	}

	public Contact softDelete(Contact contact) {
		contact.setSoftDelete(true);
		Contact deleted = this.update(contact);
		return deleted;
	}

	public List<Contact> getAllNotDelete() throws ListNotFoundException {

		List<Contact> list = contactRepository.findAllBySoftDeleteFalseOrderByCreateAt();
		return Optional.ofNullable(list).orElseThrow(() -> new ListNotFoundException("no comment to list"));
	}

	public Contact setCont(ContactRequest contactReq, Long id) throws RecordNotExistException {
		Contact contact = this.getById(id);
		if (contact != null) {
			if (contactReq.getName() != null) {
				contact.setName(contactReq.getName());
			}
			if (contactReq.getPhone() != null) {
				contact.setPhone(contactReq.getPhone());
			}
			if (contactReq.getEmail() != null) {
				contact.setEmail(contactReq.getEmail());
			}
			if (contactReq.getMessage() != null) {
				contact.setMessage(contactReq.getMessage());
			}
		}
		return contact;
	}
}
