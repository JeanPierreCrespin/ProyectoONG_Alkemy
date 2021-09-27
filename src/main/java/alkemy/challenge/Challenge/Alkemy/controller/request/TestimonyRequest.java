package alkemy.challenge.Challenge.Alkemy.controller.request;

import alkemy.challenge.Challenge.Alkemy.model.Testimonials;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestimonyRequest {

    private String name;
    private String image;
 	private String content;


 	@Autowired
	private static ModelMapper mapper = new ModelMapper();

 	public static TestimonyRequest mapToRequest(Testimonials t) {
       TestimonyRequest tReq = mapper.map(t, TestimonyRequest.class);
       return tReq;
 	}


	public static Testimonials mapToEntity(TestimonyRequest tReq) {
		Testimonials t = mapper.map(tReq, Testimonials.class);
		return t;
	}
}
