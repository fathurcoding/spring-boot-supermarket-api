package ui.ft.ccit.faculty.transaksi.pelanggan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.PelangganRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@org.springframework.transaction.annotation.Transactional
class PelangganRepositoryTest {

	@Autowired
	private PelangganRepository repository;

	@org.junit.jupiter.api.BeforeEach
	void setUp() {
		// idPelanggan, nama, jenisKelamin, alamat, telepon, tglLahir, jenisPelanggan
		repository
				.save(new Pelanggan("P001", "Andi", "L", "Alamat A", "0812", java.time.LocalDate.of(1995, 5, 5), "S"));
	}

	@Test
	void shouldFindPelangganById() {
		Pelanggan pelanggan = repository.findById("P001").orElse(null);
		assertThat(pelanggan).isNotNull();
		assertThat(pelanggan.getNama()).isEqualTo("Andi");
		assertThat(pelanggan.getJenisPelanggan()).isEqualTo("S");
	}

	@Test
	void shouldFindAllPelanggan() {
		var pelangganList = repository.findAll();
		assertThat(pelangganList).isNotEmpty();
		assertThat(pelangganList.size()).isGreaterThanOrEqualTo(1);
	}
}
