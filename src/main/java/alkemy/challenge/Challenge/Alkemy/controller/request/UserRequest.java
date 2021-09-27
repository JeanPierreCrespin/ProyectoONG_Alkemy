package alkemy.challenge.Challenge.Alkemy.controller.request;

import alkemy.challenge.Challenge.Alkemy.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private String photo;

	private static ModelMapper mapper = new ModelMapper();

	public static UserRequest mapToRequest(User u) {
		UserRequest userReq = mapper.map(u, UserRequest.class);

		return userReq;
	}

	public static User mapToEntity(UserRequest userReq) {
		User user = mapper.map(userReq, User.class);
		return user;
	}

}
