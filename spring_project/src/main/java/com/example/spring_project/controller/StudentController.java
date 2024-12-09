package com.example.spring_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.spring_project.model.Student;
import com.example.spring_project.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/students", produces = "application/json;charset=UTF-8")
@CrossOrigin(origins = { "http://localhost:9000" })
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // 全学生の取得
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 学生IDによる学生の取得
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Optional<Student> studentData = studentRepository.findByStudentIdNumber(id);
        return studentData.map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 新規学生の作成
    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        try {
            Student _student = studentRepository.save(student);
            return new ResponseEntity<>(_student, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 学生情報の更新
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        Optional<Student> studentData = studentRepository.findByStudentIdNumber(id);

        if (studentData.isPresent()) {
            Student _student = studentData.get();
            _student.setName(student.getName());
            _student.setClassId(student.getClassId());
            _student.setMailAddress(student.getMailAddress());
            _student.setIsActive(student.getIsActive());
            _student.setAdmissionYear(student.getAdmissionYear());
            return new ResponseEntity<>(studentRepository.save(_student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 学生の削除
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") String id) {
        try {
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // アクティブな学生の取得
    @GetMapping("/active")
    public List<Student> getActiveStudents() {
        return studentRepository.findByIsActiveTrue();
    }

    // 名前による学生の検索
    @GetMapping("/search")
    public List<Student> searchStudentsByName(@RequestParam String name) {
        return studentRepository.findByNameContaining(name);
    }

    // クラスIDによる学生の検索
    @GetMapping("/class/{classId}")
    public List<Student> getStudentsByClassId(@PathVariable Integer classId) {
        return studentRepository.findByClassId(classId);
    }

    // 入学年度による学生の検索
    @GetMapping("/year/{year}")
    public List<Student> getStudentsByAdmissionYear(@PathVariable Integer year) {
        return studentRepository.findByAdmissionYear(year);
    }
}
