package ui.ft.ccit.faculty.transaksi.transaksi.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaksiRepository extends JpaRepository<Transaksi, String> {
    Page<Transaksi> findByKodeTransaksiContainingIgnoreCase(String kode, Pageable pageable);

    // Find transactions between dates
    java.util.List<Transaksi> findByTglTransaksiBetween(java.time.LocalDateTime start, java.time.LocalDateTime end);
}
