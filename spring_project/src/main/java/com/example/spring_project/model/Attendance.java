package com.example.spring_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "attendance")
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Long studentIdNumber;
	@Column
	private String date;
	@Column
	private String status;

	// ゲッターとセッター
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentIdNumber() {
		return studentIdNumber;
	}

	public void setStudentIdNumber(Long studentIdNumber) {
		this.studentIdNumber = studentIdNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// toString メソッド
	@Override
	public String toString() {
		return "Attendance{" +
				"id=" + id +
				", studentIdNumber=" + studentIdNumber +
				", date='" + date + '\'' +
				", status='" + status + '\'' +
				'}';
	}
}
