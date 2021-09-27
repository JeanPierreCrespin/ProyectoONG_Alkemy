package alkemy.challenge.Challenge.Alkemy.controller.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import alkemy.challenge.Challenge.Alkemy.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto implements Serializable {

	private static final long serialVersionUID = 2766055239432462287L;
	private String name;
	private String email;
	private String phone;
	private String message;

	@Autowired
	private static ModelMapper mm = new ModelMapper();

	public static ContactDto entityToDto(Contact contact) {
		ContactDto aux = mm.map(contact, ContactDto.class);
		return aux;
	}

	public static List<ContactDto> entitiesToDtos(List<Contact> c) {
		List<ContactDto> contacts = new ArrayList<ContactDto>();
		c.forEach(entity -> {
			contacts.add(entityToDto(entity));
		});
		return contacts;
	}
}
