package alkemy.challenge.Challenge.Alkemy.controller.dto;

import alkemy.challenge.Challenge.Alkemy.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto implements Serializable{

	private static final long serialVersionUID = 1791079430000801611L;
	private String name;
    private String description;
    

    @Autowired
    private static ModelMapper mapper = new ModelMapper();

    public static RoleDto mapToDto(Role r) {
        RoleDto roleDto = mapper.map(r, RoleDto.class);

        return roleDto;
    }

    //metodo para mapear entity
    public static Role mapToEntity(RoleDto roleDto) {
        Role role = mapper.map(roleDto, Role.class);
        return role;
    }

}
