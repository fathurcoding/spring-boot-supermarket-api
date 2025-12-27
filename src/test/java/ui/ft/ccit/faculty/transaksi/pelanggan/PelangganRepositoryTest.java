package ui.ft.ccit.faculty.transaksi.pelanggan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.PelangganRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PelangganRepositoryTest {
	
	@Autowired
	private PelangganRepository repository;
	
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
		assertThat(pelangganList.size()).isGreaterThanOrEqualTo(8);
	}
}
