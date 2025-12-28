package ui.ft.ccit.faculty.transaksi.transaksi.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ui.ft.ccit.faculty.transaksi.barang.model.Barang;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksi;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.PelangganRepository;
import ui.ft.ccit.faculty.transaksi.transaksi.dto.TransactionSummary;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionCalculationServiceTest {

    @Mock
    private PelangganRepository pelangganRepository;

    @InjectMocks
    private TransactionCalculationService calculationService;

    private Barang mockBarang;
    private DetailTransaksi mockDetail;

    @BeforeEach
    void setUp() {
        mockBarang = new Barang();
        mockBarang.setHarga(10000.0);
        mockBarang.setPersenLaba(10.0); // Profit 1000 -> Base+Profit = 11000
        mockBarang.setDiskon(0.0); // No item discount

        mockDetail = new DetailTransaksi();
        mockDetail.setBarang(mockBarang);
        mockDetail.setJumlah((short) 2); // Qty 2 -> Subtotal 22000
    }

    @Test
    void calculateSummary_ShouldCalculateCorrectly_WithGoldMember() {
        // Arrange
        String memberId = "M001";
        Pelanggan goldMember = new Pelanggan();
        goldMember.setJenisPelanggan("Gold");

        when(pelangganRepository.findById(memberId)).thenReturn(Optional.of(goldMember));

        // Selling Price per item: 10000 + (10% profit) - 0 = 11000
        // Subtotal: 11000 * 2 = 22000
        // Gold Discount (10%): 22000 * 0.10 = 2200
        // Net: 19800
        // Tax (11%): 19800 * 0.11 = 2178
        // Grand Total: 19800 + 2178 = 21978

        // Act
        TransactionSummary summary = calculationService.calculateSummary(List.of(mockDetail), memberId);

        // Assert
        assertEquals(new BigDecimal("22000.00"), summary.getSubtotal());
        assertEquals(new BigDecimal("2200.00"), summary.getDiscountAmount());
        assertEquals(new BigDecimal("2178.00"), summary.getTaxAmount());
        assertEquals(new BigDecimal("21978.00"), summary.getGrandTotal());
    }

    @Test
    void calculateSummary_ShouldCalculateCorrectly_WithNoMember() {
        // Arrange
        String memberId = null;

        // Subtotal: 22000
        // Discount: 0
        // Net: 22000
        // Tax (11%): 22000 * 0.11 = 2420
        // Grand Total: 24420

        // Act
        TransactionSummary summary = calculationService.calculateSummary(List.of(mockDetail), memberId);

        // Assert
        assertEquals(new BigDecimal("22000.00"), summary.getSubtotal());
        assertEquals(new BigDecimal("0.00"), summary.getDiscountAmount());
        assertEquals(new BigDecimal("2420.00"), summary.getTaxAmount());
        assertEquals(new BigDecimal("24420.00"), summary.getGrandTotal());
    }
}
