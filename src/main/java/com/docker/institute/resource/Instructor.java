package com.docker.institute.resource;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "INSTRUCTOR")
public class Instructor {
	@Column(name = "INSTRUCTOR_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="instructor_generator")
	@TableGenerator(name="instructor_generator", table = "instructor_generator" ,initialValue = 1, allocationSize = 1)
	private int id;
	@Column(name = "INSTRUCTOR_NAME",nullable = false, length = 255)
	private String name;
	@Column(name = "AGE", nullable = false, length = 4)
	private int age;
	@Column( name = "SEX", nullable = false, length = 2)
	private String sex;
	@Column(name = "QUALIFICATION", length = 50, nullable = false)
	private String qualification;
	@Column(name = "EXPERIENCE", length = 3, nullable = false)
	private int experience;
	@Column( name = "JOINING_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date joiningDate;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "INSTRUCTOR_ID")
	private List<Course> courses;
	
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
		
}
