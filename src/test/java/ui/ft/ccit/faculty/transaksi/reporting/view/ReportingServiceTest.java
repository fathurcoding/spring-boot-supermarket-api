package ui.ft.ccit.faculty.transaksi.reporting.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksiRepository;
import ui.ft.ccit.faculty.transaksi.reporting.dto.TopProductDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportingServiceTest {

    @Mock
    private DetailTransaksiRepository detailRepository;

    @InjectMocks
    private ReportingService reportingService;

    @Test
    void getTopSellingProducts_ShouldDelegatToRepository() {
        // Arrange
        TopProductDTO top1 = new TopProductDTO("Kecap", 100L);
        TopProductDTO top2 = new TopProductDTO("Sabun", 50L);

        when(detailRepository.findTopSellingProducts(any(PageRequest.class)))
                .thenReturn(List.of(top1, top2));

        // Act
        List<TopProductDTO> result = reportingService.getTopSellingProducts(5);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Kecap", result.get(0).getProductName());
        verify(detailRepository).findTopSellingProducts(any(PageRequest.class));
    }
}
