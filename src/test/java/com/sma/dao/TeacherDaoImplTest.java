package com.sma.dao;

import java.math.BigDecimal;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sma.model.*;

public class TeacherDaoImplTest extends EntityDaoImplTest{

	@Autowired
	TeacherDao teacherDao;
	
	@Override
	protected IDataSet getDataSet() throws Exception{
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Teacher.xml"));
		return dataSet;
	}
	
	/* In case you need multiple datasets (mapping different tables) and you do prefer to keep them in separate XML's
    @Override
    protected IDataSet getDataSet() throws Exception {
      IDataSet[] datasets = new IDataSet[] {
              new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Teacher.xml")),
              new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Benefits.xml")),
              new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Departements.xml"))
      };
      return new CompositeDataSet(datasets);
    }
    */
	
	@Test 
	public void findById(){
		Assert.assertNotNull(teacherDao.findById(1));
		Assert.assertNull(teacherDao.findById(3));
	}
	
	@Test
	public void saveTeacher(){
		teacherDao.saveTeacher(getSampleTeacher());
		Assert.assertEquals(teacherDao.findAllTeachers().size(), 3);
	}
	
	@Test
	public void deleteTeacherByTeacherId(){
		teacherDao.deleteTeacherByTeacherId("11111");
		Assert.assertEquals(teacherDao.findAllTeachers().size(), 1);
	}
	
	@Test
	public void deleteTeacherByInvalidTeacherId(){
		teacherDao.deleteTeacherByTeacherId("23423");
		Assert.assertEquals(teacherDao.findAllTeachers().size(), 2);
	}
	
	@Test
	public void findAllTeachers(){
		Assert.assertEquals(teacherDao.findAllTeachers().size(), 2);
	}
	
	@Test
	public void findTeacherByTeacherId(){
		Assert.assertNotNull(teacherDao.findTeacherByTeacherId("11111"));
		Assert.assertNull(teacherDao.findTeacherByTeacherId("14545"));
	}
	
	public Teacher getSampleTeacher(){
		Teacher teacher = new Teacher();
        teacher.setName("Axel");
        teacher.setEmail("axel21@outlook.com");
        teacher.setPassword("password");
        teacher.setPhoneNumber("0794243072");
        teacher.setStatus("InActive");
        teacher.setTeacherId("12345");
        
        return teacher;
	}
}
