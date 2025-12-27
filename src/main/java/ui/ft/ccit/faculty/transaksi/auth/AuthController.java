package ui.ft.ccit.faculty.transaksi.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Authentication controller for JWT token generation.
 * 
 * <p>This is a MOCK implementation for demonstration purposes.
 * In production, this would integrate with a real authentication service.</p>
 * 
 * <p>Demo credentials:</p>
 * <ul>
 *   <li>Username: admin, Password: admin123, Role: ADMIN</li>
 *   <li>Username: manager, Password: manager123, Role: MANAGER</li>
 *   <li>Username: cashier, Password: cashier123, Role: CASHIER</li>
 * </ul>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints untuk autentikasi dan mendapatkan JWT token")
public class AuthController {
	
	@PostMapping("/login")
	@Operation(
			summary = "Mock Login untuk Demo JWT",
			description = "Mock login endpoint untuk mendapatkan JWT token demo. " +
					"Gunakan credentials: admin/admin123 (ADMIN), manager/manager123 (MANAGER), " +
					"atau cashier/cashier123 (CASHIER)"
	)
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Login successful",
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = LoginResponse.class)
					)
			),
			@ApiResponse(
					responseCode = "401",
					description = "Invalid credentials"
			)
	})
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
		// Mock authentication logic
		String username = request.getUsername();
		String password = request.getPassword();
		
		Role role = null;
		boolean authenticated = false;
		
		// Demo credentials
		if ("admin".equals(username) && "admin123".equals(password)) {
			role = Role.ADMIN;
			authenticated = true;
		} else if ("manager".equals(username) && "manager123".equals(password)) {
			role = Role.MANAGER;
			authenticated = true;
		} else if ("cashier".equals(username) && "cashier123".equals(password)) {
			role = Role.CASHIER;
			authenticated = true;
		}
		
		if (!authenticated) {
			Map<String, String> error = new HashMap<>();
			error.put("error", "Unauthorized");
			error.put("message", "Invalid username or password");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}
		
		// Generate mock JWT token
		String mockToken = "mock-jwt-token-" + role.name().toLowerCase() + "-" + 
				System.currentTimeMillis();
		
		LoginResponse response = new LoginResponse(
				mockToken,
				username,
				role,
				3600L // 1 hour expiry
		);
		
		return ResponseEntity.ok(response);
	}
}
