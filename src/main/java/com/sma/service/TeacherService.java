package com.sma.service;

import java.util.List;

import com.sma.model.Teacher;

public interface TeacherService {
	Teacher findById(int id);
	
	void saveTeacher(Teacher teacher);
	
	void updateTeacher(Teacher teacher);
	
	void deleteTeacherByTeacherId(String teacherId);

	List<Teacher> findAllTeachers(); 
	
	Teacher findTeacherByTeacherId(String teacherId);

	boolean isTeacherIdUnique(Integer id, String teacherId);
}
