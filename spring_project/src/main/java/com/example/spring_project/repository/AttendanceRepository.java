package com.example.spring_project.repository;

import com.example.spring_project.model.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
    List<AttendanceEntity> findByStudentStudentIdNumber(Long studentIdNumber);
    List<AttendanceEntity> findByAttendanceDate(LocalDateTime attendanceDate);
    List<AttendanceEntity> findByAttendanceDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    Optional<AttendanceEntity> findByStudentStudentIdNumberAndAttendanceDate(Long studentIdNumber, LocalDateTime attendanceDate);
}