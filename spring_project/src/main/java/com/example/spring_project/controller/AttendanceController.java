package com.example.spring_project.controller;

import com.example.spring_project.model.AttendanceEntity;
import com.example.spring_project.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @GetMapping
    public List<AttendanceEntity> getAllAttendances() {
        return attendanceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceEntity> getAttendanceById(@PathVariable("id") Long id) {
        Optional<AttendanceEntity> attendanceData = attendanceRepository.findById(id);
        return attendanceData.map(attendance -> new ResponseEntity<>(attendance, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<AttendanceEntity> createAttendance(@RequestBody AttendanceEntity attendanceEntity) {
        try {
            AttendanceEntity savedAttendance = attendanceRepository.save(attendanceEntity);
            return new ResponseEntity<>(savedAttendance, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceEntity> updateAttendance(@PathVariable("id") Long id, @RequestBody AttendanceEntity attendanceEntity) {
        Optional<AttendanceEntity> attendanceData = attendanceRepository.findById(id);

        if (attendanceData.isPresent()) {
            AttendanceEntity updatedAttendance = attendanceData.get();
            updatedAttendance.setAttendanceDate(attendanceEntity.getAttendanceDate());
            updatedAttendance.setStatus(attendanceEntity.getStatus());
            updatedAttendance.setStudent(attendanceEntity.getStudent());
            return new ResponseEntity<>(attendanceRepository.save(updatedAttendance), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAttendance(@PathVariable("id") Long id) {
        try {
            attendanceRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/student/{studentId}")
    public List<AttendanceEntity> getAttendanceByStudentId(@PathVariable("studentId") Long studentId) {
        return attendanceRepository.findByStudentStudentIdNumber(studentId);
    }

    @GetMapping("/date/{date}")
    public List<AttendanceEntity> getAttendanceByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return attendanceRepository.findByAttendanceDate(date);
    }

    @GetMapping("/date-range")
    public List<AttendanceEntity> getAttendanceByDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return attendanceRepository.findByAttendanceDateBetween(start, end);
    }

    @GetMapping("/student/{studentId}/date/{date}")
    public ResponseEntity<AttendanceEntity> getAttendanceByStudentAndDate(
            @PathVariable("studentId") Long studentId,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        Optional<AttendanceEntity> attendanceData = attendanceRepository.findByStudentStudentIdNumberAndAttendanceDate(studentId, date);
        return attendanceData.map(attendance -> new ResponseEntity<>(attendance, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}