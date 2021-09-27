package alkemy.challenge.Challenge.Alkemy.controller.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import alkemy.challenge.Challenge.Alkemy.model.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto implements Serializable {

	private static final long serialVersionUID = -6367506422496362375L;
	
	private String name;

	private String content;

	private String image;

	@JsonIgnoreProperties(value = "news")
	private CategoryDto categoryId;

	@Autowired
	private static ModelMapper mapper = new ModelMapper();

	public static NewsDto mapToDto(News news) {
		NewsDto newsDto = mapper.map(news, NewsDto.class);
		return newsDto;
	}

	public static List<NewsDto> mapEntityPageIntoDtoPage(List<News> list) {
		List<NewsDto> listed = new ArrayList<>();
        for (News n : list) {
            listed.add(mapToDto(n));
        }
        return listed;
	} 
}
