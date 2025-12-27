package ui.ft.ccit.faculty.transaksi.jenisbarang.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JenisBarangRepository extends JpaRepository<JenisBarang, Integer> {
    
    // Existing list method
    List<JenisBarang> findByNamaJenisContainingIgnoreCase(String namaJenis);
    
    // New pagination method
    Page<JenisBarang> findByNamaJenisContainingIgnoreCase(String namaJenis, Pageable pageable);
}