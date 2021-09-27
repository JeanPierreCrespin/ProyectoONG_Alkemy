package alkemy.challenge.Challenge.Alkemy.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import alkemy.challenge.Challenge.Alkemy.controller.request.ActivityRequest;
import alkemy.challenge.Challenge.Alkemy.controller.request.OrganizationRequest;
import alkemy.challenge.Challenge.Alkemy.exception.ListNotFoundException;
import alkemy.challenge.Challenge.Alkemy.exception.RecordNotExistException;
import alkemy.challenge.Challenge.Alkemy.model.Activity;
import alkemy.challenge.Challenge.Alkemy.controller.dto.ActivityDto;
import alkemy.challenge.Challenge.Alkemy.controller.dto.ContactDto;
import alkemy.challenge.Challenge.Alkemy.controller.dto.ResponseDto;
import alkemy.challenge.Challenge.Alkemy.service.ActivityService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

class ActivityControllerTest {

	private ActivityService activityService;
	private ActivityController activityController;
	private ActivityDto activityDto;
	private Activity activity;
	private List<Activity> activityList = new ArrayList<>();
	private List<ActivityDto> activitiesDtos = new ArrayList<>();
	private ActivityRequest activityRequest;
	private ActivityRequest activityRequestNull;

    @BeforeEach
    void setUp() {
    	activityService = Mockito.mock(ActivityService.class);
    	activityController = new ActivityController(activityService);
    	
    	activity = new Activity(1L,"content","imagen","name",false);
    	activityList.add(activity);
    	activityDto = ActivityDto.mapToDto(activity);
    	activitiesDtos = ActivityDto.mapToListDto(activityList);
    	activityRequestNull  = new ActivityRequest();
    	activityRequest = new ActivityRequest("content","imagen","name");
    	activityList.add(activity);
    	
    }

    @Test
    void createActivity() {
    	MockedStatic<ActivityRequest> mb = Mockito.mockStatic(ActivityRequest.class);
		mb.when(() -> { ActivityRequest.mapToEntity(activityRequest); }).thenReturn(activity);
		when(activityService.create(activity)).thenReturn(activity);
		ResponseEntity<?> ok = activityController.createActivity(activityRequest);
		assertEquals(ok,new ResponseEntity<>(new ResponseDto(200,"Id: " + activity.getId()), HttpStatus.OK));
		verify(activityService,times(1)).create(activity);
      
    }
    
    @Test
    void createActivityBadRequest() {
    	ResponseEntity<?> ok = activityController.createActivity(activityRequestNull);
		assertEquals(ok,new ResponseEntity<>(new ResponseDto(400,"The field name and content are required."),HttpStatus.BAD_REQUEST));
    }

    @Test
    void updateActivity() throws RecordNotExistException {
		when(activityService.setAct(activityRequest, 1L)).thenReturn(activity);
		when(activityService.update(activity)).thenReturn(activity);
		ResponseEntity<?> ok = activityController.updateActivity(1L, activityRequest);
		assertEquals(ok,new ResponseEntity<>(ActivityDto.mapToDto(activity), HttpStatus.OK));
		verify(activityService,times(1)).setAct(activityRequest, 1L);
		verify(activityService,times(1)).update(activity); 	
    }
    @Test
    void updateActivityException() throws RecordNotExistException{
    	when(activityService.setAct(activityRequest, 1L)).thenThrow(new RecordNotExistException("record not exist"));
		assertThrows(RecordNotExistException.class, () -> activityService.setAct(activityRequest,1L));
		verify(activityService,times(1)).setAct(activityRequest,1L);
    }

    @Test
    void deleteActivity() throws RecordNotExistException{
		when(activityService.getById(1L)).thenReturn(activity);
		when(activityService.deleteActivity(activity)).thenReturn(activity);
		ResponseEntity<?> ok = activityController.deleteActivity(1L);
		assertEquals(ok,new ResponseEntity<>(new ResponseDto(200,"Has been successfully deleted."),HttpStatus.OK));
		verify(activityService,times(1)).getById(1L);
		verify(activityService,times(1)).deleteActivity(activity);
    }  
    
    @Test
    void deleteActivityException() throws RecordNotExistException{
    	when(activityService.getById(1L)).thenThrow(new RecordNotExistException("record not exist"));
		assertThrows(RecordNotExistException.class,()-> activityController.deleteActivity(1L));
		verify(activityService,times(1)).getById(1L);
    }

    @Test
    void listActivities() throws ListNotFoundException{
    	when(activityService.getAllNotDelete()).thenReturn(activityList);
    	ResponseEntity<?> ok = activityController.listActivities();
		assertEquals(ok,new ResponseEntity<>(ActivityDto.mapToListDto(activityList), HttpStatus.OK));
		verify(activityService,times(1)).getAllNotDelete();
    }
    @Test
    void listActivitiesEception() throws ListNotFoundException{
    	when(activityService.getAllNotDelete()).thenThrow(new ListNotFoundException("List Exception"));
		assertThrows(ListNotFoundException.class, () -> activityService.getAllNotDelete());
		verify(activityService,times(1)).getAllNotDelete();
    }
}