package com.sma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="SUBJECT")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty
	@Column(name = "CODE", unique=true, nullable = false)
	private String code;
	
	@Size(min=3, max=50)
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@NotNull
	@Digits(integer=1, fraction = 0)
	@Column(name = "NUMBER_OF_PAPERS", nullable = false)
	private int number_of_papers;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getNumber_of_papers() {
		return number_of_papers;
	}

	public void setNumber_of_papers(int number_of_papers) {
		this.number_of_papers = number_of_papers;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof Subject))
			return false;
		Subject other = (Subject) obj;
		if(id != other.id)
			return false;
		if(code == null){
			if(other.code != null)
				return false;
		}
		else if(!code.equals(other.code))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return "Subject [id=" + id + ", code=" + code + ", name="
                + name + ", number_of_papers=" + number_of_papers + "]";
	}
	
}
