package com.sma.service.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
 
import java.util.ArrayList;
import java.util.List;
 
import static org.mockito.Mockito.when;
 
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sma.model.*;
import com.sma.dao.*;

public class TeacherServiceImplTest {
	@Mock
	TeacherDao dao;
	
	@InjectMocks
	TeacherServiceImpl TeacherService;
	
	@Spy
	List<Teacher> teachers = new ArrayList<Teacher>();
	
	@BeforeClass
	public void setup(){
		MockitoAnnotations.initMocks(this);
		teachers = getTeacherList();
	}

	@Test
	public void findById(){
		Teacher teacher = teachers.get(0);
		when(dao.findById(anyInt())).thenReturn(teacher);
		Assert.assertEquals(TeacherService.findById(teacher.getId()), teacher);
	}
	
	@Test
	public void saveTeacher(){
		doNothing().when(dao).saveTeacher(any(Teacher.class));
		TeacherService.saveTeacher(any(Teacher.class));
		verify(dao, atLeastOnce()).saveTeacher(any(Teacher.class));
	}
	
	@Test
	public void updateTeacher(){
		Teacher teacher = teachers.get(0);
		when(dao.findById(anyInt())).thenReturn(teacher);
		TeacherService.updateTeacher(teacher);
		verify(dao, atLeastOnce()).findById(anyInt());		
	}
	
	@Test
	public void deleteTeacherByTeacherId(){
		doNothing().when(dao).deleteTeacherByTeacherId(anyString());
		TeacherService.deleteTeacherByTeacherId(anyString());
		verify(dao, atLeastOnce()).deleteTeacherByTeacherId(anyString());
	}
	
	@Test
	public void findAllTeachers(){
		when(dao.findAllTeachers()).thenReturn(teachers);
		Assert.assertEquals(TeacherService.findAllTeachers(), teachers);
	}
	
	@Test
	public void findTeacherByTeacherId(){
		Teacher teacher = teachers.get(0);
		when(dao.findTeacherByTeacherId(anyString())).thenReturn(teacher);
		Assert.assertEquals(TeacherService.findTeacherByTeacherId(teacher.getTeacherId()), teacher);
	}
	
	@Test
	public void isTeacherTeacherIdUnique(){
		Teacher teacher = teachers.get(0);
		when(dao.findTeacherByTeacherId(anyString())).thenReturn(teacher);
		Assert.assertEquals(TeacherService.isTeacherIdUnique(teacher.getId(), teacher.getTeacherId()), true);
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
