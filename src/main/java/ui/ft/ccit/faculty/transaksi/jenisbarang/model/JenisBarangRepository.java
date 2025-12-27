package ui.ft.ccit.faculty.transaksi.jenisbarang.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JenisBarangRepository extends JpaRepository<JenisBarang, Integer> {
	List<JenisBarang> findByNamaJenisContainingIgnoreCase(String namaJenis);
}