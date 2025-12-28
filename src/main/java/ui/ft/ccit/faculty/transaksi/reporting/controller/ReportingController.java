package ui.ft.ccit.faculty.transaksi.reporting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.barang.model.Barang;
import ui.ft.ccit.faculty.transaksi.reporting.dto.DailyRevenueDTO;
import ui.ft.ccit.faculty.transaksi.reporting.dto.TopProductDTO;
import ui.ft.ccit.faculty.transaksi.reporting.view.ReportingService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@Tag(name = "Reports", description = "Analytical reporting endpoints")
public class ReportingController {

    private final ReportingService reportingService;

    public ReportingController(ReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @GetMapping("/daily-revenue")
    @Operation(summary = "Get Daily Revenue Report", description = "Calculates total revenue and transaction count per day within a date range")
    public ResponseEntity<List<DailyRevenueDTO>> getDailyRevenue(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(reportingService.getDailyRevenue(startDate, endDate));
    }

    @GetMapping("/top-products")
    @Operation(summary = "Get Top Selling Products", description = "Returns top products ordered by total quantity sold")
    public ResponseEntity<List<TopProductDTO>> getTopProducts(
            @RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(reportingService.getTopSellingProducts(limit));
    }

    @GetMapping("/low-stock")
    @Operation(summary = "Get Low Stock Alert", description = "Returns products with stock strictly less than the threshold")
    public ResponseEntity<List<Barang>> getLowStock(
            @RequestParam(defaultValue = "5") int threshold) {
        return ResponseEntity.ok(reportingService.getLowStockProducts(threshold));
    }
}
