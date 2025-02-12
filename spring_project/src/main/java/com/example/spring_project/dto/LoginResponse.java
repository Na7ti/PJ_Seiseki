package com.example.spring_project.dto;

// レスポンスデータを格納するためのDTOクラス
public class LoginResponse {
    private boolean success;      // 認証が成功したかどうか
    private String token;         // JWTトークン
    private String message;       // メッセージ（エラーメッセージなども含む）
    private String email;         // ユーザーのメールアドレス
    
    // デフォルトコンストラクタ
    public LoginResponse() {
    }
    
    // 全フィールドを初期化するコンストラクタ
    public LoginResponse(boolean success, String token, String message) {
        this.success = success;
        this.token = token;
        this.message = message;
    }

    // 全フィールドを初期化するコンストラクタ（メールアドレス付き）
    public LoginResponse(boolean success, String token, String message, String email) {
        this.success = success;
        this.token = token;
        this.message = message;
        this.email = email;
    }
    
    // Getter と Setter メソッド
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
