package ui.ft.ccit.faculty.transaksi.jenisbarang.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Ubah Generic ke <JenisBarang, Byte> karena ID di model adalah Byte
public interface JenisBarangRepository extends JpaRepository<JenisBarang, Byte> {

    // Spring Data JPA bisa otomatis membuat query dari nama method
    // Mencari berdasarkan 'namaJenis' (sesuai field di Model)
    List<JenisBarang> findByNamaJenisContainingIgnoreCase(String keyword);
}