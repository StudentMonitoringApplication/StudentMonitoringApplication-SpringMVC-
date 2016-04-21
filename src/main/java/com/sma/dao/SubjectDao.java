package com.sma.dao;

import java.util.List;

import com.sma.model.Subject;

public interface SubjectDao {
	
	Subject findById(int id);
	
	void saveSubject(Subject subject);
		
	void deleteSubjectByCode(String code);

	List<Subject> findAllSubjects(); 
	
	Subject findSubjectByCode(String code);
}
