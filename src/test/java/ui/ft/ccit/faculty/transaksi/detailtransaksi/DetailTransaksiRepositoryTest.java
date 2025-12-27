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
@ActiveProfiles("local")
class DetailTransaksiRepositoryTest {
	
	@Autowired
	private DetailTransaksiRepository repository;
	
	@Test
	void shouldFindDetailTransaksiById() {
		DetailTransaksiId id = new DetailTransaksiId("J001", "B001");
		DetailTransaksi detail = repository.findById(id).orElse(null);
		assertThat(detail).isNotNull();
		assertThat(detail.getJumlah()).isEqualTo(Short.valueOf("5"));
		assertThat(detail.getTransaksi().getKodeTransaksi()).isEqualTo("J001");
		assertThat(detail.getBarang().getIdBarang()).isEqualTo("B001");
	}
	
	@Test
	void shouldFindAllDetailTransaksi() {
		var detailList = repository.findAll();
		assertThat(detailList).isNotEmpty();
		assertThat(detailList.size()).isGreaterThanOrEqualTo(22);
	}
}
