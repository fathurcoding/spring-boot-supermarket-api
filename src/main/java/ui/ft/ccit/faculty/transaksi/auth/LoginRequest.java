package ui.ft.ccit.faculty.transaksi.auth;

import jakarta.validation.constraints.NotBlank;

/**
 * Request DTO for user login.
 * 
 * <p>Used by the authentication endpoint to receive login credentials.</p>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
public class LoginRequest {
	
	@NotBlank(message = "Username is required")
	private String username;
	
	@NotBlank(message = "Password is required")
	private String password;
	
	public LoginRequest() {
	}
	
	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	// Getters and Setters
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
