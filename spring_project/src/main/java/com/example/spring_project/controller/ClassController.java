package com.example.spring_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.spring_project.model.ClassEntity;
import com.example.spring_project.repository.ClassRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    @Autowired
    private ClassRepository classRepository;

    // 全クラスの取得
    @GetMapping
    public List<ClassEntity> getAllClasses() {
        return classRepository.findAll();
    }

    // クラスIDによるクラスの取得
    @GetMapping("/{id}")
    public ResponseEntity<ClassEntity> getClassById(@PathVariable("id") Integer id) {
        Optional<ClassEntity> classData = classRepository.findByClassId(id);
        return classData.map(clazz -> new ResponseEntity<>(clazz, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 新規クラスの作成
    @PostMapping
    public ResponseEntity<ClassEntity> createClass(@RequestBody ClassEntity classEntity) {
        try {
            ClassEntity _classEntity = classRepository.save(classEntity);
            return new ResponseEntity<>(_classEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // クラス情報の更新
    @PutMapping("/{id}")
    public ResponseEntity<ClassEntity> updateClass(@PathVariable("id") Integer id, @RequestBody ClassEntity classEntity) {
        Optional<ClassEntity> classData = classRepository.findByClassId(id);

        if (classData.isPresent()) {
            ClassEntity _classEntity = classData.get();
            _classEntity.setClassName(classEntity.getClassName());
            _classEntity.setAdmissionYear(classEntity.getAdmissionYear());
            return new ResponseEntity<>(classRepository.save(_classEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // クラスの削除
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteClass(@PathVariable("id") Integer id) {
        try {
            classRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // クラス名によるクラスの検索
    @GetMapping("/search")
    public List<ClassEntity> searchClassesByName(@RequestParam String name) {
        return classRepository.findByClassNameContaining(name);
    }

    // 入学年度によるクラスの検索
    @GetMapping("/year/{year}")
    public List<ClassEntity> getClassesByYear(@PathVariable Integer year) {
        return classRepository.findByAdmissionYear(year);
    }
}