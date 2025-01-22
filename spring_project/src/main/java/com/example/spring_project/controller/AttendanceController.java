package com.example.spring_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.spring_project.model.Attendance;
import com.example.spring_project.repository.AttendanceRepository;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = { "http://localhost:9000" })
public class AttendanceController {

	@Autowired
	private AttendanceRepository attendanceRepository;

	@GetMapping
	public List<Attendance> getAllAttendance() {
		return attendanceRepository.findAll();
	}

	@PostMapping
	public Attendance createAttendance(@RequestBody Attendance attendance) {
		return attendanceRepository.save(attendance);
	}

}
