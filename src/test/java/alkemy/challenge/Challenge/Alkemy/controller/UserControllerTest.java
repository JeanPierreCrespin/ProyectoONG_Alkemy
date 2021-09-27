package alkemy.challenge.Challenge.Alkemy.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import alkemy.challenge.Challenge.Alkemy.controller.dto.ResponseDto;
import alkemy.challenge.Challenge.Alkemy.controller.dto.UserDto;
import alkemy.challenge.Challenge.Alkemy.controller.request.UserRequest;
import alkemy.challenge.Challenge.Alkemy.exception.ListNotFoundException;
import alkemy.challenge.Challenge.Alkemy.exception.RecordNotExistException;
import alkemy.challenge.Challenge.Alkemy.model.Role;
import alkemy.challenge.Challenge.Alkemy.model.User;
import alkemy.challenge.Challenge.Alkemy.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

class UserControllerTest {
	
	private UserService userService;
	private UserController userController;
	private User user;
	private Role role;
	private List<User> userList = new ArrayList<>();
	private List<UserDto> listDto = new ArrayList<>();
	private UserRequest userRequest;
	private UserDto userDto;
	

    @BeforeEach
    void setUp() {
    	userService	= Mockito.mock(UserService.class);
    	userController = new UserController(userService);
    	user = new User("enzo","ruiz diaz","email","pasww",false,role);
    	userList.add(user);
    	listDto = UserDto.mapToDtoList(userList);
    	userRequest = new UserRequest("enzo","ruiz diaz","email","pasww", "photo");
    	userDto = UserDto.mapToDto(user);
    }

    @Test
    void deleteUserOk() throws RecordNotExistException {
    	when(userService.getById(1L)).thenReturn(user);
    	userService.deleteUser(user);
    	ResponseEntity<?> ok = userController.deleteUser(1L);
    	assertEquals(ok,new ResponseEntity<>(new ResponseDto(200,"Has been successfully deleted."),HttpStatus.OK));
    	 verify(userService,times(1)).getById(1L);
    	
    }
    @Test
    void deleteUserException() throws RecordNotExistException {

        when(userService.getById(2l)).thenThrow(new RecordNotExistException("not Found"));

        assertThrows(RecordNotExistException.class, () -> userService.getById(2l));
        verify(userService,times(1)).getById(2L);
    }

    @Test
    void listUsers() throws ListNotFoundException {
   
    			when(userService.getAllNotDeleted()).thenReturn(userList);
    			ResponseEntity<?> ok = userController.listUsers();
    			assertEquals(ok,new ResponseEntity<>(listDto,HttpStatus.OK));
    			verify(userService,times(1)).getAllNotDeleted();
    }

    @Test
    void patchUpdateUserOk() throws RecordNotExistException {
    	
    	when(userService.patchUpdate(1L,userRequest)).thenReturn(user);
    	ResponseEntity<?> ok = userController.patchUpdateUser(1L, userRequest);
    	assertEquals(ok,new ResponseEntity<>(userDto, HttpStatus.OK));
    	verify(userService,times(1)).patchUpdate(1L, userRequest);
    }
    
    @Test
    void patchUpdateUserException() throws RecordNotExistException {
    	when(userService.patchUpdate(1L, userRequest)).thenThrow(new RecordNotExistException("not Found"));

        assertThrows(RecordNotExistException.class, () -> userService.patchUpdate(1L, userRequest));
        verify(userService,times(1)).patchUpdate(1L, userRequest);
    }
}