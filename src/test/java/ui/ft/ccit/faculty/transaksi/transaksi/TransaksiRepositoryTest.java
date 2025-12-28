package ui.ft.ccit.faculty.transaksi.transaksi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi;
import ui.ft.ccit.faculty.transaksi.transaksi.model.TransaksiRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@org.springframework.transaction.annotation.Transactional
class TransaksiRepositoryTest {

	@Autowired
	private TransaksiRepository repository;
	@Autowired
	private ui.ft.ccit.faculty.transaksi.pelanggan.model.PelangganRepository pelangganRepository;
	@Autowired
	private ui.ft.ccit.faculty.transaksi.karyawan.model.KaryawanRepository karyawanRepository;

	@org.junit.jupiter.api.BeforeEach
	void setUp() {
		var pelanggan = pelangganRepository.save(new ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan(
				"P001", "Andi", "L", "Alamat A", "0812", java.time.LocalDate.of(1995, 5, 5), "S"));
		var karyawan = karyawanRepository.save(new ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan(
				"K001", "Tuti", "P", "Alamat T", "081", java.time.LocalDate.of(1990, 1, 1), 3000000.0));

		var transaksi = new Transaksi("J001", java.time.LocalDateTime.now(), pelanggan, karyawan);
		repository.save(transaksi);
	}

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
		assertThat(transaksiList.size()).isGreaterThanOrEqualTo(1);
	}
}
