package com.sma.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sma.dao.AbstractDao;
import com.sma.dao.TeacherDao;
import com.sma.model.Teacher;

@Repository("teacherDao")
public class TeacherDaoImpl extends AbstractDao<Integer, Teacher> implements TeacherDao {

	public Teacher findById(int id) {
		return getByKey(id);
	}

	public void saveTeacher(Teacher teacher) {
		persist(teacher);
	}

	public void deleteTeacherByTeacherId(String teacherId) {
		Query query = getSession().createSQLQuery("delete from Teacher where teacher_id = :teacherId");
		query.setString("teacherId", teacherId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Teacher> findAllTeachers() {
		Criteria criteria = createEntityCriteria();
		return (List<Teacher>) criteria.list();
	}

	public Teacher findTeacherByTeacherId(String teacherId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("teacherId", teacherId));
		return (Teacher) criteria.uniqueResult();
	}

	
}
