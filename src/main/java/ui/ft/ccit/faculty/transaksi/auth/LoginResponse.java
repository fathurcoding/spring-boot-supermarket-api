package ui.ft.ccit.faculty.transaksi.auth;

/**
 * Response DTO for successful login.
 * 
 * <p>Contains the JWT token and user information returned after successful authentication.</p>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
public class LoginResponse {
	
	private String token;
	private String tokenType = "Bearer";
	private String username;
	private Role role;
	private Long expiresIn; // seconds
	
	public LoginResponse() {
	}
	
	public LoginResponse(String token, String username, Role role, Long expiresIn) {
		this.token = token;
		this.username = username;
		this.role = role;
		this.expiresIn = expiresIn;
	}
	
	// Getters and Setters
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getTokenType() {
		return tokenType;
	}
	
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public Long getExpiresIn() {
		return expiresIn;
	}
	
	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}
}
