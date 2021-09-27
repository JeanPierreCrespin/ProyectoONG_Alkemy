package alkemy.challenge.Challenge.Alkemy.controller.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import alkemy.challenge.Challenge.Alkemy.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto implements Serializable {


	private static final long serialVersionUID = 7694517488195805582L;
		private String name;
	    private String facebookUrl;
	    private String instagramUrl;
	    private String linkedinUrl;
	    private String image;
	    private String description;
	    
	    @Autowired
	    private static ModelMapper mm = new ModelMapper();
	    
	    public static MemberDto entityToDto(Member member) {
	    	MemberDto aux = mm.map(member, MemberDto.class);
	    	return aux;
	    }
	    
	    public static List<MemberDto> entitiesToDtos(List<Member> m){
	    	List <MemberDto> members = new ArrayList<MemberDto>();
	    	m.forEach(entity ->{
	    		members.add(entityToDto(entity));
	    	});
	    	return members;
	    }

}
