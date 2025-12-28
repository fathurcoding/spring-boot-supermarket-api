package ui.ft.ccit.faculty.transaksi.transaksi.view;

import org.springframework.stereotype.Service;
import ui.ft.ccit.faculty.transaksi.barang.model.Barang;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksi;

import ui.ft.ccit.faculty.transaksi.pelanggan.model.PelangganRepository;
import ui.ft.ccit.faculty.transaksi.transaksi.dto.TransactionSummary;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class TransactionCalculationService {

    private final PelangganRepository pelangganRepository;

    public TransactionCalculationService(PelangganRepository pelangganRepository) {
        this.pelangganRepository = pelangganRepository;
    }

    /**
     * Calculates the total transaction amount considering item selling prices and
     * customer membership discounts.
     *
     * @param details  List of detail transactions (items).
     * @param memberId ID of the customer (member).
     * @return The calculated final total.
     */
    public BigDecimal calculateTotal(List<DetailTransaksi> details, String memberId) {
        TransactionSummary summary = calculateSummary(details, memberId);
        return summary.getGrandTotal();
    }

    /**
     * Calculates the detailed transaction summary.
     *
     * @param details  List of detail transactions (items).
     * @param memberId ID of the customer (member).
     * @return TransactionSummary containing subtotal, discount, and grand total.
     */
    public TransactionSummary calculateSummary(List<DetailTransaksi> details, String memberId) {
        // Calculate Subtotal based on Selling Price of each item
        BigDecimal subtotal = details.stream()
                .map(detail -> {
                    BigDecimal sellingPrice = calculateSellingPrice(detail.getBarang());
                    BigDecimal qty = BigDecimal.valueOf(detail.getJumlah());
                    return sellingPrice.multiply(qty);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Calculate Membership Discount
        BigDecimal discountPercentage = getMemberDiscountPercentage(memberId);
        BigDecimal discountAmount = subtotal.multiply(discountPercentage).setScale(2, RoundingMode.HALF_UP);

        BigDecimal netAmount = subtotal.subtract(discountAmount);

        // Calculate Tax (11% VAT)
        BigDecimal taxRate = new BigDecimal("0.11");
        BigDecimal taxAmount = netAmount.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);

        BigDecimal grandTotal = netAmount.add(taxAmount).setScale(2, RoundingMode.HALF_UP);

        return new TransactionSummary(subtotal, discountAmount, taxAmount, grandTotal);
    }

    private BigDecimal calculateSellingPrice(Barang barang) {
        if (barang == null)
            return BigDecimal.ZERO;

        BigDecimal basePrice = BigDecimal.valueOf(barang.getHarga());

        // Persen Laba (Profit Margin)
        BigDecimal profitPercent = BigDecimal.valueOf(barang.getPersenLaba() != null ? barang.getPersenLaba() : 0.0);
        BigDecimal profitAmount = basePrice.multiply(profitPercent).divide(BigDecimal.valueOf(100), 2,
                RoundingMode.HALF_UP);

        // Diskon Barang (Item Discount)
        BigDecimal discountPercent = BigDecimal.valueOf(barang.getDiskon() != null ? barang.getDiskon() : 0.0);
        BigDecimal discountAmount = basePrice.multiply(discountPercent).divide(BigDecimal.valueOf(100), 2,
                RoundingMode.HALF_UP);

        // Selling Price = Base + Profit - Discount
        return basePrice.add(profitAmount).subtract(discountAmount).max(BigDecimal.ZERO);
    }

    private BigDecimal getMemberDiscountPercentage(String memberId) {
        if (memberId == null || memberId.isEmpty()) {
            return BigDecimal.ZERO;
        }

        return pelangganRepository.findById(memberId)
                .map(pelanggan -> {
                    String memberType = pelanggan.getJenisPelanggan();
                    if (memberType == null)
                        return BigDecimal.ZERO;

                    if ("Gold".equalsIgnoreCase(memberType)) {
                        return new BigDecimal("0.10"); // 10%
                    } else if ("Silver".equalsIgnoreCase(memberType)) {
                        return new BigDecimal("0.05"); // 5%
                    }
                    return BigDecimal.ZERO;
                })
                .orElse(BigDecimal.ZERO);
    }
}
