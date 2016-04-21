package com.sma.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sma.dao.AbstractDao;
import com.sma.dao.SubjectDao;
import com.sma.model.Subject;

@Repository("subjectDao")
public class SubjectDaoImpl extends AbstractDao<Integer, Subject> implements SubjectDao {

	public Subject findById(int id) {
		return getByKey(id);
	}

	public void saveSubject(Subject subject) {
		persist(subject);
	}

	public void deleteSubjectByCode(String code) {
		Query query = getSession().createSQLQuery("delete from Subject where code = :code");
		query.setString("code", code);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Subject> findAllSubjects() {
		Criteria criteria = createEntityCriteria();
		return (List<Subject>) criteria.list();
	}

	public Subject findSubjectByCode(String code) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("code", code));
		return (Subject) criteria.uniqueResult();
	}

}
