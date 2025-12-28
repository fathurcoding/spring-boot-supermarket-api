package ui.ft.ccit.faculty.transaksi.detailtransaksi.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetailTransaksiRepository extends JpaRepository<DetailTransaksi, DetailTransaksiId> {
    Page<DetailTransaksi> findByIdKodeTransaksi(String kodeTransaksi, Pageable pageable);

    List<DetailTransaksi> findByIdKodeTransaksi(String kodeTransaksi);

    @org.springframework.data.jpa.repository.Query("SELECT new ui.ft.ccit.faculty.transaksi.reporting.dto.TopProductDTO(d.barang.namaBarang, SUM(d.jumlah)) "
            +
            "FROM DetailTransaksi d GROUP BY d.barang.namaBarang ORDER BY SUM(d.jumlah) DESC")
    List<ui.ft.ccit.faculty.transaksi.reporting.dto.TopProductDTO> findTopSellingProducts(Pageable pageable);
}
