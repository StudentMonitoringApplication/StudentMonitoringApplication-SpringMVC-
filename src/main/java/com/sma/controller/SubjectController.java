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

import com.sma.model.Subject;
import com.sma.service.SubjectService;

@Controller
@RequestMapping("/subject")
public class SubjectController {

	@Autowired
	SubjectService service;
	
	@Autowired
	MessageSource messageSource;
	
	
	
	 /* This method will list all existing Subjects.*/
	 
	@RequestMapping(value = { "/subject", "/listSubjects" }, method = RequestMethod.GET)
	public String listSubjects(ModelMap model) {

		List<Subject> subjects = service.findAllSubjects();
		model.addAttribute("subjects", subjects);
		return "allSubjects";
	}
	
	
	  /*This method will provide the medium to add a new Subject.*/
	 
	@RequestMapping(value = { "/newSubject" }, method = RequestMethod.GET)
	public String newSubject(ModelMap model) {
		Subject subject = new Subject();
		model.addAttribute("subject", subject);
		model.addAttribute("edit", false);
		return "subjectRegistration";
	}
	
	
	
	 /*This method will be called on form submission, handling POST request for
	 saving Subject in database. It also validates the user input*/
	 
	@RequestMapping(value = { "/newSubject" }, method = RequestMethod.POST)
	public String saveSubject(@Valid Subject subject, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "subjectRegistration";
		}

		
		 /** Preferred way to achieve uniqueness of field [Code] should be implementing custom @Unique annotation 
		 * and applying it on field [Code] of Model class [Subject].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 **/ 
		 
		if(!service.isCodeUnique(subject.getId(), subject.getCode())){
			FieldError ssnError =new FieldError("subject","code",messageSource.getMessage("non.unique.code", new String[]{subject.getCode()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "subjectRegistration";
		}
		
		service.saveSubject(subject);

		model.addAttribute("success", "subject " + subject.getName() + " registered successfully");
		return "success";
	}
	

	
	 /*This method will provide the medium to update an existing employee.*/
	 
	@RequestMapping(value = { "/edit-{code}-subject" }, method = RequestMethod.GET)
	public String editSubject(@PathVariable String code, ModelMap model) {
		Subject subject = service.findSubjectByCode(code);
		model.addAttribute("subject", subject);
		model.addAttribute("edit", true);
		return "subjectRegistration";
	}
	
	
	 /*This method will be called on form submission, handling POST request for
	 updating employee in database. It also validates the user input*/
	 
	@RequestMapping(value = { "/edit-{code}-subject" }, method = RequestMethod.POST)
	public String updateSubject(@Valid Subject subject, BindingResult result,
			ModelMap model, @PathVariable String code) {

		if (result.hasErrors()) {
			return "subjectRegistration";
		}

		if(!service.isCodeUnique(subject.getId(), subject.getCode())){
			FieldError ssnError =new FieldError("subject","code",messageSource.getMessage("non.unique.code", new String[]{subject.getCode()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "subjectRegistration";
		}

		service.updateSubject(subject);

		model.addAttribute("success", "subject " + subject.getName()	+ " updated successfully");
		return "success";
	}
	
	
	 /*This method will delete an employee by it's SSN value.*/	 
	@RequestMapping(value = { "/delete-{code}-subject" }, method = RequestMethod.GET)
	public String deleteSubject(@PathVariable String code) {
		service.deleteSubjectByCode(code);
		return "redirect:/listSubjects";
	}

}

