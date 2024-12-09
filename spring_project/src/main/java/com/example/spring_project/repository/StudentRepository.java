package com.example.spring_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_project.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

	// 学生IDで学生を検索
	Optional<Student> findByStudentIdNumber(Long studentIdNumber);

	// 名前で学生を検索
	List<Student> findByName(String name);

	// メールアドレスで学生を検索
	Optional<Student> findByMailAddress(String mailAddress);

	// クラスIDで学生を検索
	List<Student> findByClassId(Integer classId);

	// 入学年度で学生を検索
	List<Student> findByAdmissionYear(Integer admissionYear);

	// アクティブな学生のみを検索
	List<Student> findByIsActiveTrue();

	// 名前に特定の文字列を含む学生を検索
	List<Student> findByNameContaining(String namePattern);

	// 入学年度の範囲で学生を検索
	List<Student> findByAdmissionYearBetween(Integer startYear, Integer endYear);

	// クラスIDと入学年度で学生を検索
	List<Student> findByClassIdAndAdmissionYear(Integer classId, Integer admissionYear);

	// メールアドレスの存在確認
	boolean existsByMailAddress(String mailAddress);

	// 学生IDの存在確認
	boolean existsByStudentIdNumber(Long studentIdNumber);
}
