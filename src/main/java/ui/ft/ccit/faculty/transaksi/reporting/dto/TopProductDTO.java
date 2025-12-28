package ui.ft.ccit.faculty.transaksi.reporting.dto;

/**
 * DTO for Top Selling Product Report.
 */
public class TopProductDTO {
    private String productName;
    private Long totalQuantity;

    public TopProductDTO(String productName, Long totalQuantity) {
        this.productName = productName;
        this.totalQuantity = totalQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
