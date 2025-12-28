package ui.ft.ccit.faculty.transaksi.reporting.view;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ui.ft.ccit.faculty.transaksi.barang.model.Barang;
import ui.ft.ccit.faculty.transaksi.barang.model.BarangRepository;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksi;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksiRepository;
import ui.ft.ccit.faculty.transaksi.reporting.dto.DailyRevenueDTO;
import ui.ft.ccit.faculty.transaksi.reporting.dto.TopProductDTO;
import ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi;
import ui.ft.ccit.faculty.transaksi.transaksi.model.TransaksiRepository;
import ui.ft.ccit.faculty.transaksi.transaksi.view.TransactionCalculationService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportingService {

    private final TransaksiRepository transaksiRepository;
    private final DetailTransaksiRepository detailRepository;
    private final BarangRepository barangRepository;
    private final TransactionCalculationService calculationService;

    public ReportingService(TransaksiRepository transaksiRepository,
            DetailTransaksiRepository detailRepository,
            BarangRepository barangRepository,
            TransactionCalculationService calculationService) {
        this.transaksiRepository = transaksiRepository;
        this.detailRepository = detailRepository;
        this.barangRepository = barangRepository;
        this.calculationService = calculationService;
    }

    /**
     * Calculates daily revenue for a given date range.
     * Note: Calculates totals on-the-fly to ensure accuracy with current pricing
     * rules.
     */
    public List<DailyRevenueDTO> getDailyRevenue(LocalDate startDate, LocalDate endDate) {
        // 1. Fetch transactions in range
        List<Transaksi> transactions = transaksiRepository.findByTglTransaksiBetween(
                startDate.atStartOfDay(),
                endDate.atTime(LocalTime.MAX));

        // 2. Group by Date
        Map<LocalDate, List<Transaksi>> groupedByDate = transactions.stream()
                .collect(Collectors.groupingBy(t -> t.getTglTransaksi().toLocalDate()));

        // 3. Aggregate totals
        List<DailyRevenueDTO> reports = new ArrayList<>();

        groupedByDate.forEach((date, transList) -> {
            BigDecimal totalRevenue = BigDecimal.ZERO;

            for (Transaksi t : transList) {
                // Fetch details for this transaction
                List<DetailTransaksi> details = detailRepository.findByIdKodeTransaksi(t.getKodeTransaksi());
                String memberId = (t.getPelanggan() != null) ? t.getPelanggan().getIdPelanggan() : null;

                // Calculate real total including tax and discounts
                BigDecimal grandTotal = calculationService.calculateTotal(details, memberId);
                totalRevenue = totalRevenue.add(grandTotal);
            }

            reports.add(new DailyRevenueDTO(date, totalRevenue, (long) transList.size()));
        });

        // Sort by date
        reports.sort((a, b) -> a.getDate().compareTo(b.getDate()));

        return reports;
    }

    /**
     * Retrieves top selling products based on total quantity sold.
     */
    public List<TopProductDTO> getTopSellingProducts(int limit) {
        return detailRepository.findTopSellingProducts(PageRequest.of(0, limit));
    }

    /**
     * Retrieves products with stock below threshold.
     */
    public List<Barang> getLowStockProducts(int threshold) {
        return barangRepository.findByStokLessThan(threshold);
    }
}
