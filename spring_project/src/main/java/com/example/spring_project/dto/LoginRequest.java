package com.example.spring_project.dto;

// リクエストデータを受け取るためのDTOクラス
public class LoginRequest {
	private String email; // メールアドレス
	private String password; // パスワード（必要な場合）

	// デフォルトコンストラクタ
	public LoginRequest() {
	}

	// 全フィールドを初期化するコンストラクタ
	public LoginRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}

	// Getter と Setter メソッド
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
