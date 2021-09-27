package alkemy.challenge.Challenge.Alkemy.controller.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import alkemy.challenge.Challenge.Alkemy.model.Slide;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlideDto implements Serializable {

	private static final long serialVersionUID = 3103299304100528655L;
	private String imageUrl;
	private String text;
	private Integer order;
	private OrganizationDTOSimple organization;
	
	@Autowired
	private static ModelMapper mapper = new ModelMapper();
	
	public static SlideDto mapToDto (Slide s) {
		SlideDto slideDto = mapper.map(s, SlideDto.class);
		return slideDto;
	}
	
	public static List <SlideDto> mapListToDtoList(List<Slide> slide) {
		List<SlideDto> listed = new ArrayList<>();
		for (Slide s : slide) {
			listed.add(mapToDto(s));
		}
		return listed;
	}
}
