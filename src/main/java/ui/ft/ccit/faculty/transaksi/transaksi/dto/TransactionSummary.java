package ui.ft.ccit.faculty.transaksi.transaksi.dto;

import java.math.BigDecimal;

public class TransactionSummary {
    private BigDecimal subtotal;
    private BigDecimal discountAmount;
    private BigDecimal grandTotal;

    public TransactionSummary(BigDecimal subtotal, BigDecimal discountAmount, BigDecimal grandTotal) {
        this.subtotal = subtotal;
        this.discountAmount = discountAmount;
        this.grandTotal = grandTotal;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }
}
