package ui.ft.ccit.faculty.transaksi.detailtransaksi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksi;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksiId;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksiRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@org.springframework.transaction.annotation.Transactional
class DetailTransaksiRepositoryTest {

	@Autowired
	private DetailTransaksiRepository repository;

	@Autowired
	private ui.ft.ccit.faculty.transaksi.transaksi.model.TransaksiRepository transaksiRepository;

	@Autowired
	private ui.ft.ccit.faculty.transaksi.barang.model.BarangRepository barangRepository;

	@Autowired
	private ui.ft.ccit.faculty.transaksi.pelanggan.model.PelangganRepository pelangganRepository;

	@Autowired
	private ui.ft.ccit.faculty.transaksi.karyawan.model.KaryawanRepository karyawanRepository;

	@Autowired
	private ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarangRepository jenisBarangRepository;

	@org.junit.jupiter.api.BeforeEach
	void setUp() {
		// Setup Dependencies
		ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarang jenis = new ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarang(
				null, "Jenis 1");
		jenis = jenisBarangRepository.save(jenis);

		ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan pelanggan = new ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan(
				"P001", "Cust 1", "L", "Alamat", "081", java.time.LocalDate.of(1990, 1, 1), "G");
		pelanggan = pelangganRepository.save(pelanggan);

		ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan karyawan = new ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan(
				"K001", "Emp 1", "L", "Alamat", "081", java.time.LocalDate.of(1985, 5, 20), 5000000.0);
		karyawan = karyawanRepository.save(karyawan);

		var barang = new ui.ft.ccit.faculty.transaksi.barang.model.Barang("B001", "Barang 1", (short) 100, 1000.0, 10.0,
				0.0, jenis.getIdJenisBarang(), null);
		barangRepository.save(barang);

		var transaksi = new ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi("J001",
				java.time.LocalDateTime.now(), pelanggan, karyawan);
		transaksiRepository.save(transaksi);

		// Save Detail
		var detail = new DetailTransaksi(new DetailTransaksiId("J001", "B001"), transaksi, barang, (short) 5);
		repository.save(detail);
	}

	@Test
	void shouldFindDetailTransaksiById() {
		DetailTransaksiId id = new DetailTransaksiId("J001", "B001");
		DetailTransaksi detail = repository.findById(id).orElse(null);
		assertThat(detail).isNotNull();
		assertThat(detail.getJumlah()).isEqualTo((short) 5);
		assertThat(detail.getTransaksi().getKodeTransaksi()).isEqualTo("J001");
		assertThat(detail.getBarang().getIdBarang()).isEqualTo("B001");
	}

	@Test
	void shouldFindAllDetailTransaksi() {
		var detailList = repository.findAll();
		assertThat(detailList).isNotEmpty();
		assertThat(detailList.size()).isGreaterThanOrEqualTo(1);
	}
}
