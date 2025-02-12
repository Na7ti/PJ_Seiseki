package com.example.spring_project.service;

import com.example.spring_project.model.Student;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

	// 設定ファイルから秘密鍵を読み込む
	@Value("${jwt.secret:}")
	private String configuredSecretKey;

	// トークンの有効期限（ミリ秒）
	@Value("${jwt.expiration:86400000}") // デフォルト24時間
	private Long tokenValidityInMilliseconds;

	// 秘密鍵を保持
	private Key key;

	/**
	 * アプリケーション起動時に秘密鍵を初期化
	 */
	@PostConstruct
	public void init() {
		// 設定ファイルでキーが指定されていない場合は新しいキーを生成
		if (configuredSecretKey == null || configuredSecretKey.trim().isEmpty()) {
			// ランダムな256ビットのキーを生成
			byte[] randomKey = new byte[32];
			new java.security.SecureRandom().nextBytes(randomKey);
			this.configuredSecretKey = Base64.getEncoder().encodeToString(randomKey);
		}

		// キーの作成
		byte[] keyBytes = Base64.getDecoder().decode(configuredSecretKey);
		this.key = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
	}

	/**
	 * ユーザー情報からJWTトークンを生成
	 * 
	 * @param student 学生情報
	 * @return 生成されたJWTトークン
	 */
	public String generateToken(Student student) {
		Map<String, Object> claims = new HashMap<>();
		// トークンに含める追加の情報をclaimsに設定
		claims.put("email", student.getMailAddress());

		return createToken(claims, student.getMailAddress());
	}

	/**
	 * トークンの検証
	 * 
	 * @param token 検証するトークン
	 * @return トークンが有効な場合true
	 */
	public boolean validateToken(String token) {
		try {
			// トークンの解析と検証
			Jwts.parser()
					.setSigningKey(key)
					.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * トークンからメールアドレスを取得
	 * 
	 * @param token JWTトークン
	 * @return メールアドレス
	 */
	public String getEmailFromToken(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody();

		return claims.getSubject();
	}

	/**
	 * トークンの作成
	 */
	private String createToken(Map<String, Object> claims, String subject) {
		long now = System.currentTimeMillis();

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(now))
				.setExpiration(new Date(now + tokenValidityInMilliseconds))
				.signWith(SignatureAlgorithm.HS256, key) // 署名アルゴリズムと鍵を設定
				.compact();
	}

	/**
	 * トークンから有効期限を取得
	 * 
	 * @param token JWTトークン
	 * @return 有効期限
	 */
	public Date getExpirationDateFromToken(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody();

		return claims.getExpiration();
	}
}
