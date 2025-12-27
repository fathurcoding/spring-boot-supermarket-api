package ui.ft.ccit.faculty.transaksi.detailtransaksi.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailTransaksiRepository extends JpaRepository<DetailTransaksi, DetailTransaksiId> {
    Page<DetailTransaksi> findByIdKodeTransaksi(String kodeTransaksi, Pageable pageable);
}
