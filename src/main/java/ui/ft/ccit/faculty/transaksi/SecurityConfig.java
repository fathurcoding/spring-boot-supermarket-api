package ui.ft.ccit.faculty.transaksi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security configuration for the Transaksi API.
 * 
 * <p>This configuration implements JWT-based authentication using OAuth2 Resource Server.
 * The security rules are:</p>
 * 
 * <ul>
 *   <li><strong>Public access (no auth required):</strong>
 *     <ul>
 *       <li>All GET requests to /api/**</li>
 *       <li>Root path (/), error pages, health checks</li>
 *     </ul>
 *   </li>
 *   <li><strong>Authenticated access (JWT token required):</strong>
 *     <ul>
 *       <li>POST, PUT, DELETE requests to /api/**</li>
 *       <li>All other requests not explicitly permitted</li>
 *     </ul>
 *   </li>
 * </ul>
 * 
 * <p>JWT tokens are validated against Google OAuth2 issuer:
 * {@code https://accounts.google.com}</p>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/**
	 * Configures the security filter chain.
	 * 
	 * <p>Security configuration includes:</p>
	 * <ul>
	 *   <li>CSRF protection disabled (stateless REST API)</li>
	 *   <li>Public read access for GET requests</li>
	 *   <li>JWT authentication for write operations</li>
	 *   <li>OAuth2 Resource Server with JWT validation</li>
	 * </ul>
	 * 
	 * @param http the {@link HttpSecurity} to configure
	 * @return configured {@link SecurityFilterChain}
	 * @throws Exception if configuration fails
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						// ========== GET API publik ==========
						.requestMatchers(HttpMethod.GET, "/api/**").permitAll()

						// ========== Write API perlu token ==========
						.requestMatchers(HttpMethod.POST, "/api/**").authenticated()
						.requestMatchers(HttpMethod.PUT, "/api/**").authenticated()
						.requestMatchers(HttpMethod.DELETE, "/api/**").authenticated()

						// Endpoint umum tanpa auth
						.requestMatchers("/", "/error", "/actuator/health").permitAll()

						// Selain yang di atas → butuh auth
						.anyRequest().authenticated())

				// Resource server JWT (tanpa form login, tanpa redirect Google)
				.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

		return http.build();
	}
}