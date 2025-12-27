package ui.ft.ccit.faculty.transaksi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * OpenAPI/Swagger configuration for API documentation.
 * 
 * <p>Provides interactive API documentation at /swagger-ui.html</p>
 * <p>API docs available at /v3/api-docs</p>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
@Configuration
public class OpenAPIConfig {

	@Value("${server.port:8080}")
	private String serverPort;

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(apiInfo())
				.servers(List.of(
						new Server()
								.url("http://localhost:" + serverPort)
								.description("Local Development Server"),
						new Server()
								.url("http://localhost:8080")
								.description("Docker Environment")
				))
				.components(securityComponents())
				.addSecurityItem(securityRequirement());
	}

	private Info apiInfo() {
		return new Info()
				.title("Supermarket Transaction API")
				.version("1.0.0")
				.description("""
						REST API untuk sistem transaksi supermarket dengan dukungan penuh CRUD operations,
						HATEOAS, dan JWT authentication.
						
						## Features
						- ✅ Complete CRUD operations untuk semua domain
						- ✅ HATEOAS support untuk hypermedia navigation
						- ✅ JWT Token authentication (OAuth2 Resource Server)
						- ✅ Relationship management dengan foreign key validation
						- ✅ Exception handling dengan error responses yang informatif
						
						## Authentication
						Untuk menggunakan endpoint yang memerlukan authentication:
						1. Gunakan endpoint `/api/auth/login` untuk mendapatkan JWT token
						2. Klik tombol "Authorize" di atas
						3. Masukkan token dengan format: `Bearer <your-token>`
						4. Token akan otomatis ditambahkan ke semua request
						
						## Database Schema
						API ini mengelola 7 tabel utama:
						- Jenis Barang (Product Categories)
						- Pemasok (Suppliers)
						- Barang (Products)
						- Karyawan (Employees)
						- Pelanggan (Customers)
						- Transaksi (Transactions)
						- Detail Transaksi (Transaction Items)
						""")
				.contact(new Contact()
						.name("CCIT Faculty")
						.email("support@ft.ccit.ui.ac.id")
						.url("https://github.com/fathurcoding/spring-boot-supermarket-api"))
				.license(new License()
						.name("MIT License")
						.url("https://opensource.org/licenses/MIT"));
	}

	private Components securityComponents() {
		return new Components()
				.addSecuritySchemes("Bearer Authentication",
						new SecurityScheme()
								.name("Authorization")
								.type(SecurityScheme.Type.HTTP)
								.scheme("bearer")
								.bearerFormat("JWT")
								.description("""
										JWT Authorization header menggunakan Bearer scheme.
										
										Masukkan token JWT Anda di field di bawah ini.
										Contoh: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
										
										Token akan ditambahkan ke header sebagai: Authorization: Bearer <token>
										"""));
	}

	private SecurityRequirement securityRequirement() {
		return new SecurityRequirement()
				.addList("Bearer Authentication");
	}
}
