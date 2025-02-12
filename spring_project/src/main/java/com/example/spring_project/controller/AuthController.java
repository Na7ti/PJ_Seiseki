package com.example.spring_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.spring_project.model.Student;
import com.example.spring_project.repository.StudentRepository;
import com.example.spring_project.service.TokenService;
import com.example.spring_project.dto.LoginRequest;
import com.example.spring_project.dto.LoginResponse;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private StudentRepository studentRepository; // StudentRepositoryを注入

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		try {
			// メールアドレスで学生を検索
			Optional<Student> student = studentRepository.findByMailAddress(request.getEmail());

			if (student.isEmpty()) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body(new LoginResponse(false, null, "ユーザーが見つかりません。"));
			}

			// トークンの生成
			String token = tokenService.generateToken(student.get());

			// 成功レスポンスの作成
			return ResponseEntity.ok(new LoginResponse(
					true,
					token,
					"ログインに成功しました。",
					student.get().getMailAddress()));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new LoginResponse(false, null, "ログイン処理中にエラーが発生しました。"));
		}
	}
}
