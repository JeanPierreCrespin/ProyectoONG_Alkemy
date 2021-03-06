package alkemy.challenge.Challenge.Alkemy.controller.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import alkemy.challenge.Challenge.Alkemy.model.Comment;
import lombok.Data;

@Data
public class CommentDtoSimple implements Serializable{
	
	private static final long serialVersionUID = 4752329090175639168L;
	
	private String body;
	
	@Autowired
	private static ModelMapper mapper = new ModelMapper();
    
	public static CommentDtoSimple mapToDto(Comment c) {
		CommentDtoSimple aux = mapper.map(c,CommentDtoSimple.class);
		return aux;
	}
	
	public static List<CommentDtoSimple> mapToListDto(List<Comment> list){
		List<CommentDtoSimple> listed = new ArrayList<CommentDtoSimple>();
		for (Comment c:list) {
			listed.add(mapToDto(c));
		}
		return listed;
	}
    
}
