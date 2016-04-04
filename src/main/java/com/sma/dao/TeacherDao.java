package com.sma.dao;

import java.util.List;

import com.sma.model.Teacher;


public interface TeacherDao {
	Teacher findById(int id);
	
	void saveTeacher(Teacher teacher);
		
	void deleteTeacherByTeacherId(String teacherId);

	List<Teacher> findAllTeachers(); 
	
	Teacher findTeacherByTeacherId(String teacherId);

}
