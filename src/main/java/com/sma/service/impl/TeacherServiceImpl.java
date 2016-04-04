package com.sma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.dao.TeacherDao;
import com.sma.model.Teacher;
import com.sma.service.TeacherService;

@Service("teacherService")
@Transactional
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	private TeacherDao dao;

	public Teacher findById(int id) {
		return dao.findById(id);
	}

	public void saveTeacher(Teacher teacher) {
		dao.saveTeacher(teacher);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateTeacher(Teacher teacher) {
		Teacher entity = dao.findById(teacher.getId());
		if(entity!=null){
			entity.setTeacherId(teacher.getTeacherId());
			entity.setName(teacher.getName());
			entity.setPhoneNumber(teacher.getPhoneNumber());
			entity.setEmail(teacher.getEmail());
			entity.setStatus(teacher.getStatus());
			entity.setPassword(teacher.getPassword());
		}
	}

	public void deleteTeacherByTeacherId(String teacherId) {
		dao.deleteTeacherByTeacherId(teacherId);
	}

	public List<Teacher> findAllTeachers() {
		return dao.findAllTeachers();
	}

	public Teacher findTeacherByTeacherId(String teacherId) {
		return dao.findTeacherByTeacherId(teacherId);
	}

	public boolean isTeacherIdUnique(Integer id, String teacherId) {
		Teacher teacher = findTeacherByTeacherId(teacherId);
		return ( teacher == null || ((id != null) && (teacher.getId() == id)));
	}	
}
