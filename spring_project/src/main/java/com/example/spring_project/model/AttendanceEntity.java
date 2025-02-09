package com.example.spring_project.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "attendance")
public class AttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long attendanceId;

    @Column(name = "attendance_date", nullable = false)
    private LocalDateTime attendanceDate;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "student_id_number", nullable = false)
    @JsonBackReference
    private Student student;

    @Column(name = "student_id_number", insertable = false, updatable = false)
    private Long studentIdNumber;

    // デフォルトコンストラクタ
    public AttendanceEntity() {
    }

    // ゲッターとセッター
    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public LocalDateTime getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDateTime attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
        this.studentIdNumber = student != null ? student.getStudentIdNumber() : null;
    }

    public Long getStudentIdNumber() {
        return studentIdNumber;
    }

    public void setStudentIdNumber(Long studentIdNumber) {
        this.studentIdNumber = studentIdNumber;
    }

    // toString メソッド
    @Override
    public String toString() {
        return "AttendanceEntity{" +
                "attendanceId=" + attendanceId +
                ", attendanceDate=" + attendanceDate +
                ", status='" + status + '\'' +
                ", studentIdNumber=" + studentIdNumber +
                '}';
    }
}