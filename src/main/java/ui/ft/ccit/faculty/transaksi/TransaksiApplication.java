package ui.ft.ccit.faculty.transaksi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Transaksi Supermarket API application.
 * 
 * <p>This Spring Boot application provides a RESTful API for managing
 * supermarket transactions including products (Barang), product categories
 * (JenisBarang), and suppliers (Pemasok).</p>
 * 
 * <p>Features:</p>
 * <ul>
 *   <li>REST API with HATEOAS support</li>
 *   <li>JWT authentication via OAuth2 Resource Server</li>
 *   <li>MySQL database integration</li>
 *   <li>Multi-environment configuration (local, docker)</li>
 * </ul>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
@SpringBootApplication
public class TransaksiApplication {

	/**
	 * Application entry point.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(TransaksiApplication.class, args);
	}

}
