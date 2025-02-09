package com.example.spring_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_project.model.ClassEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {

    // クラスIDで検索
    Optional<ClassEntity> findByClassId(Integer classId);

    // クラス名で検索
    List<ClassEntity> findByClassName(String className);

    // クラス名に特定の文字列を含むクラスを検索
    List<ClassEntity> findByClassNameContaining(String classNamePattern);

    // 入学年度でクラスを検索
    List<ClassEntity> findByAdmissionYear(Integer admissionYear);

    // 入学年度の範囲でクラスを検索
    List<ClassEntity> findByAdmissionYearBetween(Integer startYear, Integer endYear);

    // クラスIDと入学年度でクラスを検索
    List<ClassEntity> findByClassIdAndAdmissionYear(Integer classId, Integer admissionYear);

    // クラス名の存在確認
    boolean existsByClassName(String className);

    // クラスIDの存在確認
    boolean existsByClassId(Integer classId);
}
