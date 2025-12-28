package ui.ft.ccit.faculty.transaksi.detailtransaksi.view;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.barang.model.BarangRepository;
import ui.ft.ccit.faculty.transaksi.barang.view.BarangNotFoundException;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksi;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksiId;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksiRepository;
import ui.ft.ccit.faculty.transaksi.transaksi.model.TransaksiRepository;
import ui.ft.ccit.faculty.transaksi.transaksi.view.TransaksiNotFoundException;

import java.util.List;

@Service
@Transactional
public class DetailTransaksiService {

	private final DetailTransaksiRepository repository;
	private final TransaksiRepository transaksiRepository;
	private final BarangRepository barangRepository;
	private final ui.ft.ccit.faculty.transaksi.barang.view.StockManagementService stockManagementService;

	public DetailTransaksiService(DetailTransaksiRepository repository,
			TransaksiRepository transaksiRepository,
			BarangRepository barangRepository,
			ui.ft.ccit.faculty.transaksi.barang.view.StockManagementService stockManagementService) {
		this.repository = repository;
		this.transaksiRepository = transaksiRepository;
		this.barangRepository = barangRepository;
		this.stockManagementService = stockManagementService;
	}

	public List<DetailTransaksi> findAll() {
		return repository.findAll();
	}

	public Page<DetailTransaksi> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Page<DetailTransaksi> findByKodeTransaksi(String kodeTransaksi, Pageable pageable) {
		return repository.findByIdKodeTransaksi(kodeTransaksi, pageable);
	}

	public List<DetailTransaksi> findByKodeTransaksi(String kodeTransaksi) {
		return repository.findByIdKodeTransaksi(kodeTransaksi);
	}

	public DetailTransaksi findById(String kodeTransaksi, String idBarang) {
		DetailTransaksiId id = new DetailTransaksiId(kodeTransaksi, idBarang);
		return repository.findById(id)
				.orElseThrow(() -> new DetailTransaksiNotFoundException(kodeTransaksi, idBarang));
	}

	public DetailTransaksi create(DetailTransaksi detailTransaksi) {
		// Validate composite key existence
		if (repository.existsById(detailTransaksi.getId())) {
			throw new DetailTransaksiAlreadyExistsException(
					detailTransaksi.getId().getKodeTransaksi(),
					detailTransaksi.getId().getIdBarang());
		}

		validateRelations(detailTransaksi);

		// Validate stock availability
		stockManagementService.validateStock(detailTransaksi.getId().getIdBarang(), detailTransaksi.getJumlah());

		DetailTransaksi saved = repository.save(detailTransaksi);

		// Decrease stock
		stockManagementService.updateStock(detailTransaksi.getId().getIdBarang(), -detailTransaksi.getJumlah());

		return saved;
	}

	public DetailTransaksi update(String kodeTransaksi, String idBarang, DetailTransaksi detailTransaksi) {
		DetailTransaksi existing = findById(kodeTransaksi, idBarang); // Verify exists and get old val
		detailTransaksi.setId(new DetailTransaksiId(kodeTransaksi, idBarang));

		validateRelations(detailTransaksi);

		int oldQty = existing.getJumlah();
		int newQty = detailTransaksi.getJumlah();
		int diff = newQty - oldQty;

		if (diff > 0) {
			stockManagementService.validateStock(idBarang, diff);
		}

		DetailTransaksi saved = repository.save(detailTransaksi);

		// Adjust stock (if diff is positive, inventory decreases, so we pass negative
		// diff)
		if (diff != 0) {
			stockManagementService.updateStock(idBarang, -diff);
		}

		return saved;
	}

	private void validateRelations(DetailTransaksi detailTransaksi) {
		if (!transaksiRepository.existsById(detailTransaksi.getId().getKodeTransaksi())) {
			throw new TransaksiNotFoundException(detailTransaksi.getId().getKodeTransaksi());
		}
		if (!barangRepository.existsById(detailTransaksi.getId().getIdBarang())) {
			throw new BarangNotFoundException(detailTransaksi.getId().getIdBarang());
		}
	}

	public void delete(String kodeTransaksi, String idBarang) {
		DetailTransaksi existing = findById(kodeTransaksi, idBarang); // Verify exists and get value
		DetailTransaksiId id = new DetailTransaksiId(kodeTransaksi, idBarang);
		repository.deleteById(id);

		// Restore stock
		stockManagementService.updateStock(idBarang, existing.getJumlah());
	}
}
