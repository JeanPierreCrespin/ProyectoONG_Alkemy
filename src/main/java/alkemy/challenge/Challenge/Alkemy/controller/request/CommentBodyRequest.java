package alkemy.challenge.Challenge.Alkemy.controller.request;

import alkemy.challenge.Challenge.Alkemy.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentBodyRequest implements Serializable {
    private static final long serialVersionUID = -4582227043675481556L;

    private String Body;

    @Autowired
    private static ModelMapper mapper = new ModelMapper();

    public static Comment mapToDto (CommentRequest cRequest) {
        Comment comment = mapper.map(cRequest, Comment.class);
        return comment;
    }
}
