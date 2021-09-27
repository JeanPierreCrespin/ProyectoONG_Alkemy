package alkemy.challenge.Challenge.Alkemy.controller.dto;

import alkemy.challenge.Challenge.Alkemy.controller.request.TestimonyRequest;
import alkemy.challenge.Challenge.Alkemy.model.Testimonials;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestimonyDto implements Serializable {

    private static final long serialVersionUID = 8225707800168686684L;
    private String name;
    private String image;
    private String content;


    @Autowired
    private static ModelMapper mapper = new ModelMapper();

    public static TestimonyDto mapToDto(Testimonials t) {
        TestimonyDto tDto = mapper.map(t, TestimonyDto.class);
        return tDto;
    }


    public static Testimonials mapToEntity(TestimonyRequest tDto) {
        Testimonials t = mapper.map(tDto, Testimonials.class);
        return t;
    }
    
    public static List<TestimonyDto> mapToDtoList (List<Testimonials> testimonials){
        List<TestimonyDto> listed = new ArrayList<>();
        for (Testimonials t : testimonials) {
            listed.add(mapToDto(t));
        }
        return listed;
    }

}
