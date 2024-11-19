package com.example.spring_project.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "class")
public class ClassEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer classId;

	@Column(name = "class_name", nullable = false, length = 50)
	private String className;

	@Column(name = "teacher_name", length = 50)
	private String teacherName;

	@OneToMany(mappedBy = "classEntity")
	@JsonManagedReference
	private List<Student> students;

	// Getters and setters

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	// toString method

	@Override
	public String toString() {
		return "ClassEntity{" +
				"classId=" + classId +
				", className='" + className + '\'' +
				", teacherName='" + teacherName + '\'' +
				'}';
	}
}
