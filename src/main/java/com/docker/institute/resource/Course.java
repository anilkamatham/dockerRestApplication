package com.docker.institute.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "INSTRUCTOR_COURSE")
public class Course {
	@Column(name = "COURSE_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "course_generator")
    @TableGenerator(name = "course_generator" , table = "course_generator" , initialValue = 1, allocationSize = 1)
	private int id;
    @Column( name = "COURSE_NAME", length = 255, nullable = false)
	private String name;
    @Column(name = "COURSE_PRICE", length = 4, nullable = false)
	private double price;
    @Column( name = "DURATION" , length = 4, nullable = false)
	private int duration;
	  
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
  
  
}
