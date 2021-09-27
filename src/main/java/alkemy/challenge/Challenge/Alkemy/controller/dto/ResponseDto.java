package alkemy.challenge.Challenge.Alkemy.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDto implements Serializable {

	private static final long serialVersionUID = -3878460815673450798L;
	
	Integer code;
    String description;
}
