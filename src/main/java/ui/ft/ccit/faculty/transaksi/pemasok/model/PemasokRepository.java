package ui.ft.ccit.faculty.transaksi.pemasok.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PemasokRepository extends JpaRepository<Pemasok, String> {
    
    // Existing list method
    List<Pemasok> findByNamaPemasokContainingIgnoreCase(String keyword);

    // New pagination method
    Page<Pemasok> findByNamaPemasokContainingIgnoreCase(String keyword, Pageable pageable);
}