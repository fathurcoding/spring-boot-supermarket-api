package ui.ft.ccit.faculty.transaksi.pemasok.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PemasokRepository extends JpaRepository<Pemasok, String> {
    
    // Menambahkan method search agar bisa dipanggil oleh Service
    // Spring Data JPA akan menerjemahkan ini menjadi query:
    // SELECT * FROM pemasok WHERE UPPER(nama_pemasok) LIKE UPPER(%keyword%)
    List<Pemasok> findByNamaPemasokContainingIgnoreCase(String keyword);
}