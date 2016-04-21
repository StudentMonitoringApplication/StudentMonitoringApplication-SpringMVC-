package com.sma.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sma.model.Teacher;
import com.sma.service.SubjectService;
import com.sma.service.TeacherService;

@Controller
@RequestMapping("/")
public class TeacherController {

	@Autowired
	TeacherService tService;
	
	/*@Autowired
	SubjectService sService;*/
	
	@Autowired
	MessageSource messageSource;
	
	/*
	 * This method will list all existing teachers.
	 */
	@RequestMapping(value = { "/", "/listTeachers" }, method = RequestMethod.GET)
	public String listTeachers(ModelMap model) {

		List<Teacher> teachers = tService.findAllTeachers();
		model.addAttribute("teachers", teachers);
		return "allteachers";
	}
	
	/*
	 * This method will provide the medium to add a new teacher.
	 */
	@RequestMapping(value = { "/newTeacher" }, method = RequestMethod.GET)
	public String newTeacher(ModelMap model) {
		Teacher teacher = new Teacher();
		model.addAttribute("teacher", teacher);
		model.addAttribute("edit", false);
		return "teacherRegistration";
	}
	
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * saving teacher in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newTeacher" }, method = RequestMethod.POST)
	public String saveTeacher(@Valid Teacher teacher, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "teacherRegistration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [teacherId] should be implementing custom @Unique annotation 
		 * and applying it on field [teacherId] of Model class [Teacher].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!tService.isTeacherIdUnique(teacher.getId(), teacher.getTeacherId())){
			FieldError ssnError =new FieldError("teacher","teacherId",messageSource.getMessage("non.unique.teacherId", new String[]{teacher.getTeacherId()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "teacherRegistration";
		}
		
		tService.saveTeacher(teacher);

		model.addAttribute("success", "Teacher " + teacher.getName() + " registered successfully");
		return "success";
	}
	

	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{teacherId}-teacher" }, method = RequestMethod.GET)
	public String editTeacher(@PathVariable String teacherId, ModelMap model) {
		Teacher teacher = tService.findTeacherByTeacherId(teacherId);
		model.addAttribute("teacher", teacher);
		model.addAttribute("edit", true);
		return "teacherRegistration";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{teacherId}-teacher" }, method = RequestMethod.POST)
	public String updateTeacher(@Valid Teacher teacher, BindingResult result,
			ModelMap model, @PathVariable String teacherId) {

		if (result.hasErrors()) {
			return "teacherRegistration";
		}

		if(!tService.isTeacherIdUnique(teacher.getId(), teacher.getTeacherId())){
			FieldError ssnError =new FieldError("teacher","teacherId",messageSource.getMessage("non.unique.teacherId", new String[]{teacher.getTeacherId()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "teacherRegistration";
		}

		tService.updateTeacher(teacher);

		model.addAttribute("success", "Teacher " + teacher.getName()	+ " updated successfully");
		return "success";
	}
	
	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{teacherId}-teacher" }, method = RequestMethod.GET)
	public String deleteTeacher(@PathVariable String teacherId) {
		tService.deleteTeacherByTeacherId(teacherId);
		return "redirect:/listTeachers";
	}

}
