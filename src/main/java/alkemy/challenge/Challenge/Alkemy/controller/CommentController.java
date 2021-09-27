package alkemy.challenge.Challenge.Alkemy.controller;

import java.util.List;

import alkemy.challenge.Challenge.Alkemy.controller.request.CommentBodyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import alkemy.challenge.Challenge.Alkemy.controller.dto.CommentDtoSimple;
import alkemy.challenge.Challenge.Alkemy.controller.dto.ResponseDto;
import alkemy.challenge.Challenge.Alkemy.controller.request.CommentRequest;
import alkemy.challenge.Challenge.Alkemy.exception.CommentNotAuthorizatedException;
import alkemy.challenge.Challenge.Alkemy.exception.ListNotFoundException;
import alkemy.challenge.Challenge.Alkemy.exception.RecordNotExistException;
import alkemy.challenge.Challenge.Alkemy.model.Comment;
import alkemy.challenge.Challenge.Alkemy.service.CommentService;
import alkemy.challenge.Challenge.Alkemy.service.NewsService;
import alkemy.challenge.Challenge.Alkemy.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/comments")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Comment Controller", description = "All Api Rest methods to manage a comments")
public class CommentController {

	@Autowired
	CommentService commentService;
	@Autowired
	UserService userService;
	@Autowired
	NewsService newsService;

	@Operation(summary = "Get all comments in data base")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "	List all comments in data base", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CommentDtoSimple.class)) }),
			@ApiResponse(responseCode = "500", description = "	Internal Server Error") })

	@GetMapping
	public ResponseEntity<?> getallComments() throws ListNotFoundException {
		List<Comment> comments = commentService.getAllCommentsOrderedByCreation();
		if (comments.isEmpty()) {
			return new ResponseEntity<>(comments, HttpStatus.OK);
		} else {
			List<CommentDtoSimple> comentsDtos = CommentDtoSimple.mapToListDto(comments);
			return new ResponseEntity<>(comentsDtos, HttpStatus.OK);
		}
	}

	@Operation(summary = "Create a new comment")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "	Return a OK message", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "	Return a error message"),
			@ApiResponse(responseCode = "404", description = "	Comment or user not found"),
			@ApiResponse(responseCode = "500", description = "	Internal Server Error") })
	@PostMapping
	public ResponseEntity<?> createComment(@RequestBody CommentRequest cReq)
			throws RecordNotExistException{

		if (cReq == null || cReq.getFk_newId() == null || cReq.getFk_newId() < 1 || cReq.getBody() == null
				|| cReq.getBody().isEmpty()) {
			return new ResponseEntity<>(new ResponseDto(400,"All attributes are required"), HttpStatus.BAD_REQUEST);
		}
		Comment c = commentService.setComment(cReq);
		c = commentService.create(c);
		return new ResponseEntity<>(new ResponseDto(200,"Id: " + c.getId()), HttpStatus.OK);
	}

	@Operation(summary = "Update comment")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "	Return a OK message...", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CommentDtoSimple.class)) }),
			@ApiResponse(responseCode = "404", description = "	Comment not found...", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "	Internal Server Error") })
	@PutMapping("/{id}")
	public ResponseEntity<?> updatecomment(@Parameter(description = "	ID of Comment to update")@PathVariable("id") Long id, @RequestBody CommentBodyRequest body)
			throws RecordNotExistException, CommentNotAuthorizatedException {

		Comment update = commentService.updateComment(id, body.getBody());
		CommentDtoSimple auxDto = CommentDtoSimple.mapToDto(update);
		return new ResponseEntity<>(auxDto, HttpStatus.OK);

	}
	
	@Operation(summary = "Get a comment by the id supplied")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "	Return a OK message...", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CommentDtoSimple.class)) }),
			@ApiResponse(responseCode = "404", description = "	Comment not found..."),
			@ApiResponse(responseCode = "500", description = "	Internal Server Error") })
	@GetMapping("/{id}")
	public ResponseEntity<?> getByIdComment(@Parameter(description = "	Id of comment to search")@PathVariable("id")Long id) throws RecordNotExistException{
		Comment found = commentService.getById(id);
		CommentDtoSimple auxDto = CommentDtoSimple.mapToDto(found);
		return new ResponseEntity<>(auxDto,HttpStatus.OK);
	}

	@Operation(summary = "Delete comment")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "	Returns ok if it was deleted...", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "	Comment not found..."), 
			@ApiResponse(responseCode = "403", description = "	You do not have permission to delete.."), 
			@ApiResponse(responseCode = "500", description = "	Internal Server Error") })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteComment(@Parameter(description = "	ID of Comment to delete")@PathVariable("id") Long id)
			throws RecordNotExistException, CommentNotAuthorizatedException {
		commentService.deleteComment(id);
		return new ResponseEntity<>(new ResponseDto(200,"Has been successfully deleted."), HttpStatus.OK);
	}

}
