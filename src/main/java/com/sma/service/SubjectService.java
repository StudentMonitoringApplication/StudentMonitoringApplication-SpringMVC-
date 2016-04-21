package com.sma.service;

import java.util.List;

import com.sma.model.Subject;

public interface SubjectService {
	Subject findById(int id);
	
	void saveSubject(Subject subject);
	
	void updateSubject(Subject subject);
	
	void deleteSubjectByCode(String code);

	List<Subject> findAllSubjects(); 
	
	Subject findSubjectByCode(String code);

	boolean isCodeUnique(Integer id, String code);
}
