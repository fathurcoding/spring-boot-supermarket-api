package ui.ft.ccit.faculty.transaksi.reporting.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for Daily Revenue Report.
 */
public class DailyRevenueDTO {
    private LocalDate date;
    private BigDecimal totalSales;
    private Long transactionCount;

    public DailyRevenueDTO(LocalDate date, BigDecimal totalSales, Long transactionCount) {
        this.date = date;
        this.totalSales = totalSales;
        this.transactionCount = transactionCount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    public Long getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Long transactionCount) {
        this.transactionCount = transactionCount;
    }
}
