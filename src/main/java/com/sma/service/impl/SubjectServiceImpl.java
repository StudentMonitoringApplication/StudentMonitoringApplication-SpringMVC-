package com.sma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sma.dao.SubjectDao;
import com.sma.model.Subject;
import com.sma.service.SubjectService;

@Service("subjectService")
@Transactional
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectDao dao;
	
	public Subject findById(int id) {
		return dao.findById(id);
	}

	public void saveSubject(Subject subject) {
		dao.saveSubject(subject);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateSubject(Subject subject) {
		Subject entity = dao.findById(subject.getId());
		if(entity!=null){
			entity.setCode(subject.getCode());
			entity.setName(subject.getName());
			entity.setNumber_of_papers(subject.getNumber_of_papers());
		}
	}

	public void deleteSubjectByCode(String code) {
		dao.deleteSubjectByCode(code);
	}

	public List<Subject> findAllSubjects() {
		return dao.findAllSubjects();
	}

	public Subject findSubjectByCode(String code) {
		return dao.findSubjectByCode(code);
	}

	public boolean isCodeUnique(Integer id, String code) {
		Subject subject = findSubjectByCode(code);
		return ( subject == null || ((id != null) && (subject.getId() == id)));
	}

}
