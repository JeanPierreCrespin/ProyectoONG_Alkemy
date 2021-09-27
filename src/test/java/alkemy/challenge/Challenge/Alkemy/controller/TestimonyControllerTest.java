package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.controller.dto.ResponseDto;
import alkemy.challenge.Challenge.Alkemy.controller.dto.TestimonyDto;
import alkemy.challenge.Challenge.Alkemy.controller.request.OrganizationRequest;
import alkemy.challenge.Challenge.Alkemy.controller.request.TestimonyRequest;
import alkemy.challenge.Challenge.Alkemy.exception.ListNotFoundException;
import alkemy.challenge.Challenge.Alkemy.exception.RecordNotExistException;
import alkemy.challenge.Challenge.Alkemy.model.Testimonials;
import alkemy.challenge.Challenge.Alkemy.service.TestimonialsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestimonyControllerTest {

    private TestimonialsService testimonialsService;
    private TestimonyController testimonyController;
    private Testimonials testimonials;
    private TestimonyDto testimonyDto;
    private TestimonyRequest testimonyRequest;
    private Map<String,Object> map;

    @BeforeEach
    void setUp() {
        testimonialsService= Mockito.mock(TestimonialsService.class);
        testimonyController = new TestimonyController(testimonialsService);
        testimonials= new Testimonials(1l,"name","image","content",false);
        testimonyRequest= new TestimonyRequest("name","image","content");
        testimonyDto = TestimonyDto.mapToDto(testimonials);
    }

    //-------createTestimony method test----------------------------------------------------------------------------------
    @Test
    void createTestimonyOk() {

        MockedStatic<TestimonyRequest> mb = Mockito.mockStatic(TestimonyRequest.class);
        mb.when(() -> { TestimonyRequest.mapToEntity(testimonyRequest); }).thenReturn(testimonials);
        when(testimonialsService.create(testimonials)).thenReturn(testimonials);

        ResponseEntity ok= testimonyController.createTestimony(testimonyRequest);
        assertEquals(ok,new ResponseEntity<>(new ResponseDto(200,"Id: " + testimonials.getId()),HttpStatus.OK));
        verify(testimonialsService,times(1)).create(testimonials);
    }

    @Test
    void createTestimonyNameAndContentAreRequired() {

        ResponseEntity ok= testimonyController.createTestimony(new TestimonyRequest(null,null,null));
        assertEquals(ok,new ResponseEntity<>(new ResponseDto(400,"Name and content are required.."),HttpStatus.BAD_REQUEST));

    }

    //-------updateTestimony method test----------------------------------------------------------------------------------
    @Test
    void updateTestimonyOk() throws RecordNotExistException {


        when(testimonialsService.getById(1l)).thenReturn(testimonials);
        when(testimonialsService.update(testimonials)).thenReturn(testimonials);

        ResponseEntity ok= testimonyController.updateTestimony(1l,testimonyRequest);
        assertEquals(ok,new ResponseEntity<>(TestimonyDto.mapToDto(testimonials),HttpStatus.OK));
        verify(testimonialsService,times(1)).update(testimonials);
    }

    @Test
    void updateTestimonyRecordNotExistException() throws RecordNotExistException {

        when(testimonialsService.getById(1l)).thenThrow(new RecordNotExistException(""));
        assertThrows(RecordNotExistException.class, () -> testimonyController.updateTestimony(1l,testimonyRequest));
    }

    //-------removeTestimony method test----------------------------------------------------------------------------------
    @Test
    void removeTestimonyOk() throws RecordNotExistException {

        when(testimonialsService.getById(1l)).thenReturn(testimonials);
        testimonials.setSoftDelete(true);
        when(testimonialsService.softDelete(testimonials)).thenReturn(testimonials);
        ResponseEntity ok= testimonyController.removeTestimony(1l);

        assertEquals(ok,new ResponseEntity<>(new ResponseDto(200,"Has been deleted successfully.."),HttpStatus.OK));
        verify(testimonialsService,times(1)).getById(1l);
        verify(testimonialsService,times(1)).softDelete(testimonials);
    }

    @Test
    void removeTestimonyRecordNotExistException() throws RecordNotExistException {

        when(testimonialsService.getById(1l)).thenThrow(new RecordNotExistException("not Found"));
        assertThrows(RecordNotExistException.class, () -> testimonyController.removeTestimony(1l));
    }

    //-------GetAll method test--------------------------------------------------------------------------------------------
    @Test
    void getTestimonialsOk() throws ListNotFoundException {
        when(testimonialsService.getAllTestimony(0, 10)).thenReturn(map);
        ResponseEntity<?> ok = testimonyController.getTestimonials(0,10);
        assertEquals(ok,new ResponseEntity<>(map,HttpStatus.OK));
        verify(testimonialsService,times(1)).getAllTestimony(0, 10);
    }


    @Test
    void getTestimonialsListNotFoundException() throws ListNotFoundException {
        when(testimonialsService.getAllTestimony(0, 10)).thenThrow(new ListNotFoundException("not Found"));

        assertThrows(ListNotFoundException.class, () -> testimonialsService.getAllTestimony(0, 10));
        verify(testimonialsService,times(1)).getAllTestimony(0, 10);
    }

    //-------GetById method test--------------------------------------------------------------------------------------------
    @Test
    void getByIdTestimonyOk() throws RecordNotExistException {

        when(testimonialsService.getById(1l)).thenReturn(testimonials);
        ResponseEntity ok= testimonyController.getByIdTestimony(1l);

        assertEquals(ok,new ResponseEntity<>(testimonyDto, HttpStatus.OK));
        verify(testimonialsService,times(1)).getById(1l);

    }

    @Test
    void getByIdTestimonyRecordNotExistException() throws RecordNotExistException {

        when(testimonialsService.getById(1l)).thenThrow(new RecordNotExistException("not Found"));
        assertThrows(RecordNotExistException.class, () -> testimonyController.getByIdTestimony(1l));

    }
}