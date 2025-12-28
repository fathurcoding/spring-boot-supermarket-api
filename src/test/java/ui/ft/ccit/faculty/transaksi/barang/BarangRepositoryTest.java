package ui.ft.ccit.faculty.transaksi.barang;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import ui.ft.ccit.faculty.transaksi.barang.model.Barang;
import ui.ft.ccit.faculty.transaksi.barang.model.BarangRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class BarangRepositoryTest {

        @Autowired
        private BarangRepository barangRepository;

        @Autowired
        private ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarangRepository jenisBarangRepository;

        @Autowired
        private ui.ft.ccit.faculty.transaksi.pemasok.model.PemasokRepository pemasokRepository;

        private Integer savedJenisId;
        private String savedPemasokId = "S003";

        @org.junit.jupiter.api.BeforeEach
        void setupDependencies() {
                // Create and save JenisBarang
                var jenis = new ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarang();
                jenis.setNamaJenis("Perlengkapan Mandi");
                jenis = jenisBarangRepository.save(jenis);
                savedJenisId = jenis.getIdJenisBarang();

                // Create and save Pemasok (if not exists)
                if (!pemasokRepository.existsById(savedPemasokId)) {
                        var pemasok = new ui.ft.ccit.faculty.transaksi.pemasok.model.Pemasok(
                                        savedPemasokId, "PT. Unilever", "Jakarta", "021-123", "info@unilever.com");
                        pemasokRepository.save(pemasok);
                }
        }

        @Test
        void saveAndFindById_shouldPersistAndLoadBarang() {
                Barang barang = new Barang(
                                "T001",
                                "Sabun Mandi",
                                (short) 10,
                                5000.0,
                                20.0,
                                0.0,
                                savedJenisId,
                                savedPemasokId);

                barangRepository.save(barang);

                var found = barangRepository.findById("T001");

                assertThat(found).isPresent();
                assertThat(found.get().getNama()).isEqualTo("Sabun Mandi");
        }

        @Test
        void findByNamaContainingIgnoreCase_shouldReturnMatchingRows() {
                barangRepository.save(
                                new Barang("T001", "Sabun Mandi", (short) 10, 5000.0, 20.0, 0.0, savedJenisId,
                                                savedPemasokId));
                barangRepository.save(
                                new Barang("T002", "Shampoo Wangi", (short) 5, 15000.0, 25.0, 0.0, savedJenisId,
                                                savedPemasokId));

                List<Barang> hasil = barangRepository.findByNamaContainingIgnoreCase("sham");

                assertThat(hasil)
                                .hasSize(1)
                                .first()
                                .extracting(Barang::getIdBarang)
                                .isEqualTo("T002");
        }

        @AfterEach
        void tearDown() {
                if (barangRepository.existsById("T001"))
                        barangRepository.deleteById("T001");
                if (barangRepository.existsById("T002"))
                        barangRepository.deleteById("T002");
                // Dependencies (Pemasok/Jenis) might be left or cleaned up depending on other
                // tests,
                // but Transactional annotation usually rolls back everything nicely.
        }
}
