package com.example.spring_project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id_number")
    private Long studentIdNumber;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "class_id", insertable = false, updatable = false)
    private Integer classId;

    @Column(name = "mail_address", unique = true, length = 100)
    private String mailAddress;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Column(name = "admission_year", nullable = false)
    private int admissionYear;

    @ManyToOne
    @JoinColumn(name = "class_id")
    @JsonBackReference
    private ClassEntity classEntity;

    // デフォルトコンストラクタ
    public Student() {
    }

    // ゲッターとセッター
    public Long getStudentIdNumber() {
        return studentIdNumber;
    }

    public void setStudentIdNumber(Long studentIdNumber) {
        this.studentIdNumber = studentIdNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(int admissionYear) {
        this.admissionYear = admissionYear;
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    // toString メソッド
    @Override
    public String toString() {
        return "Student{" +
                "studentIdNumber='" + studentIdNumber + '\'' +
                ", name='" + name + '\'' +
                ", classId=" + classId +
                ", mailAddress='" + mailAddress + '\'' +
                ", isActive=" + isActive +
                ", admissionYear=" + admissionYear +
                ", classEntity=" + (classEntity != null ? classEntity.getClassId() : null) +
                '}';
    }
}
