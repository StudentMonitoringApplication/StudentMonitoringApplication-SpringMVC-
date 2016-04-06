package com.sma.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
 
import java.util.ArrayList;
import java.util.List;
 
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import static org.mockito.Mockito.atLeastOnce;
 
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sma.service.*;
import com.sma.model.*;

public class TeacherControllerTest {
	
	@Mock
	TeacherService service;
	
	@Mock
	MessageSource message;
	
	@InjectMocks
	TeacherController teacherController;
	
	@Spy
	List<Teacher> teachers = new ArrayList<Teacher>();
	
	@Spy
	ModelMap model;
	
	@Mock
	BindingResult result;
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		teachers = getTeacherList();
	}
	
	@Test
	public void listTeachers(){
		when(service.findAllTeachers()).thenReturn(teachers);
		Assert.assertEquals(teacherController.listTeachers(model),	"allteachers");
		Assert.assertEquals(model.get("teachers"), teachers);
		verify(service, atLeastOnce()).findAllTeachers();
	}
	
	@Test
	public void newTeacher(){
		Assert.assertEquals(teacherController.newTeacher(model), "teacherRegistration");
		Assert.assertNotNull(model.get("teacher"));
		Assert.assertFalse((Boolean)model.get("edit"));
		Assert.assertEquals(((Teacher)model.get("teacher")).getId(), 0);
	}
	
	@Test
	public void saveTeacherWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).saveTeacher(any(Teacher.class));
		Assert.assertEquals(teacherController.saveTeacher(teachers.get(0), result, model), "teacherRegistration");
	}
	
	@Test
	public void saveTeacherWithValidationErrorNonUniqueTeacherId(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isTeacherIdUnique(anyInt(), anyString())).thenReturn(false);
		Assert.assertEquals(teacherController.saveTeacher(teachers.get(0), result, model), "teacherRegistration");
	}
	
	@Test
	public void saveTeacherWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isTeacherIdUnique(anyInt(), anyString())).thenReturn(true);
		doNothing().when(service).saveTeacher(any(Teacher.class));
		Assert.assertEquals(teacherController.saveTeacher(teachers.get(0), result, model), "success");
		Assert.assertEquals(model.get("success"), "Teacher Malmike registered successfully");
	}
	
	 
	
	@Test
	public void editTeacher(){
		Teacher teacher = teachers.get(0);
		when(service.findTeacherByTeacherId(anyString())).thenReturn(teacher);
		Assert.assertEquals(teacherController.editTeacher(anyString(), model), "teacherRegistration");
		Assert.assertNotNull(model.get("teacher"));
		Assert.assertTrue((Boolean)model.get("edit"));
		Assert.assertEquals(((Teacher)model.get("teacher")).getId(), 1);
	}
	
	@Test
	public void updateTeacherWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).updateTeacher(any(Teacher.class));
		Assert.assertEquals(teacherController.updateTeacher(teachers.get(0), result, model, ""), "teacherRegistration");
	}
	
	@Test
	public void updateTeacherWithValidationErrorNonUniqueSSN(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isTeacherIdUnique(anyInt(), anyString())).thenReturn(false);
		Assert.assertEquals(teacherController.updateTeacher(teachers.get(0), result, model, ""), "teacherRegistration");
	}
	
	@Test
	public void updateTeacherWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isTeacherIdUnique(anyInt(), anyString())).thenReturn(true);
		doNothing().when(service).updateTeacher(any(Teacher.class));
		Assert.assertEquals(teacherController.updateTeacher(teachers.get(0), result, model, ""), "success");
		Assert.assertEquals(model.get("success"), "Teacher Malmike updated successfully");
	}
	
	@Test
	public void deleteTeacher(){
		doNothing().when(service).deleteTeacherByTeacherId(anyString());
		Assert.assertEquals(teacherController.deleteTeacher("123"), "redirect:/listTeachers");
	}
	
	
	private List<Teacher> getTeacherList() {
		Teacher t1 = new Teacher();
        t1.setId(1);
        t1.setName("Malmike");
        t1.setEmail("malmike21@outlook.com");
        t1.setPassword("password");
        t1.setPhoneNumber("0794243072");
        t1.setStatus("InActive");
        t1.setTeacherId("XXX111");
         
        Teacher t2 = new Teacher();
        t2.setId(2);
        t2.setName("Axel");
        t2.setEmail("axel@outlook.com");
        t2.setPassword("password");
        t2.setPhoneNumber("0794243072");
        t2.setStatus("InActive");
        t2.setTeacherId("XXX222");
         
        teachers.add(t1);
        teachers.add(t2);
        return teachers;
	}
}

