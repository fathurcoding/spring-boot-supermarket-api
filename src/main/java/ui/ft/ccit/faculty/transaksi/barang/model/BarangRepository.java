package ui.ft.ccit.faculty.transaksi.barang.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarangRepository extends JpaRepository<Barang, String> {

    // Standard Spring Data JPA method for case-insensitive search
    Page<Barang> findByNamaContainingIgnoreCase(String nama, Pageable pageable);
    
    // List version for backward compatibility if needed (optional)
    List<Barang> findByNamaContainingIgnoreCase(String nama);

    // Barang dengan stok kurang dari angka tertentu
    List<Barang> findByStokLessThan(Integer stok);
}
