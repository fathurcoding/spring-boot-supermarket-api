package ui.ft.ccit.faculty.transaksi.karyawan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;
import ui.ft.ccit.faculty.transaksi.karyawan.model.KaryawanRepository;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for KaryawanRepository.
 * 
 * <p>Tests database operations for Karyawan entity.</p>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
@SpringBootTest
@ActiveProfiles("local")
class KaryawanRepositoryTest {
	
	@Autowired
	private KaryawanRepository repository;
	
	/**
	 * Test finding karyawan by ID.
	 */
	@Test
	void shouldFindKaryawanById() {
		Karyawan karyawan = repository.findById("K001").orElse(null);
		assertThat(karyawan).isNotNull();
		assertThat(karyawan.getNama()).isEqualTo("Tuti");
		assertThat(karyawan.getJenisKelamin()).isEqualTo("P");
	}
	
	/**
	 * Test finding all karyawan.
	 */
	@Test
	void shouldFindAllKaryawan() {
		var karyawanList = repository.findAll();
		assertThat(karyawanList).isNotEmpty();
		assertThat(karyawanList.size()).isGreaterThanOrEqualTo(8);
	}
	
	/**
	 * Test karyawan existence check.
	 */
	@Test
	void shouldCheckKaryawanExists() {
		boolean exists = repository.existsById("K001");
		assertThat(exists).isTrue();
		
		boolean notExists = repository.existsById("K999");
		assertThat(notExists).isFalse();
	}
}
