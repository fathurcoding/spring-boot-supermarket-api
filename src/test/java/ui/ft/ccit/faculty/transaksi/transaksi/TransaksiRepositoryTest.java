package ui.ft.ccit.faculty.transaksi.transaksi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi;
import ui.ft.ccit.faculty.transaksi.transaksi.model.TransaksiRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
class TransaksiRepositoryTest {
	
	@Autowired
	private TransaksiRepository repository;
	
	@Test
	void shouldFindTransaksiByKode() {
		Transaksi transaksi = repository.findById("J001").orElse(null);
		assertThat(transaksi).isNotNull();
		assertThat(transaksi.getPelanggan().getIdPelanggan()).isEqualTo("P001");
		assertThat(transaksi.getKaryawan().getIdKaryawan()).isEqualTo("K001");
	}
	
	@Test
	void shouldFindAllTransaksi() {
		var transaksiList = repository.findAll();
		assertThat(transaksiList).isNotEmpty();
		assertThat(transaksiList.size()).isGreaterThanOrEqualTo(14);
	}
}
