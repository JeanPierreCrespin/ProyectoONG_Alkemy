package alkemy.challenge.Challenge.Alkemy.controller.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import alkemy.challenge.Challenge.Alkemy.model.Activity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDto implements Serializable {

	private static final long serialVersionUID = -8839774325565786412L;
	private String name;
	private String content;
	private String image;

	@Autowired
	private static ModelMapper mapper = new ModelMapper();

	
	
	public static ActivityDto mapToDto(Activity a) {
		ActivityDto actDto= mapper.map(a, ActivityDto.class);

		return actDto;
	}
	
	public static List<ActivityDto> mapToListDto(List<Activity> actList) {
		List<ActivityDto> actDtos = new ArrayList<ActivityDto>();
		for (Activity a : actList) {
			actDtos.add(mapToDto(a));
		}
		return actDtos;
	}

}
