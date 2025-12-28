package ui.ft.ccit.faculty.transaksi.barang.view;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.barang.model.Barang;
import ui.ft.ccit.faculty.transaksi.barang.model.BarangRepository;

@Service
public class StockManagementService {

    private final BarangRepository barangRepository;

    public StockManagementService(BarangRepository barangRepository) {
        this.barangRepository = barangRepository;
    }

    /**
     * Updates the stock of a product.
     *
     * @param barangId       The ID of the product.
     * @param quantityChange The amount to change the stock by (negative to
     *                       decrease, positive to increase).
     * @throws RuntimeException           if the product is not found.
     * @throws InsufficientStockException if there is insufficient stock.
     */
    @Transactional
    public void updateStock(String barangId, int quantityChange) {
        Barang barang = barangRepository.findById(barangId)
                .orElseThrow(() -> new BarangNotFoundException(barangId));

        int newStock = barang.getStok() + quantityChange;

        if (newStock < 0) {
            throw new InsufficientStockException("Stok tidak mencukupi untuk barang: " + barang.getNama()
                    + ". Stok saat ini: " + barang.getStok() + ", Dibutuhkan: " + Math.abs(quantityChange));
        }

        barang.setStok((short) newStock);
        barangRepository.save(barang);
    }

    /**
     * Validates if there is enough stock for a required quantity.
     *
     * @param barangId         The ID of the product.
     * @param requiredQuantity The quantity required.
     * @throws RuntimeException           if the product is not found.
     * @throws InsufficientStockException if there is insufficient stock.
     */
    public void validateStock(String barangId, int requiredQuantity) {
        Barang barang = barangRepository.findById(barangId)
                .orElseThrow(() -> new BarangNotFoundException(barangId));

        if (barang.getStok() < requiredQuantity) {
            throw new InsufficientStockException("Stok tidak mencukupi untuk barang: " + barang.getNama()
                    + ". Stok saat ini: " + barang.getStok() + ", Dibutuhkan: " + requiredQuantity);
        }
    }
}
