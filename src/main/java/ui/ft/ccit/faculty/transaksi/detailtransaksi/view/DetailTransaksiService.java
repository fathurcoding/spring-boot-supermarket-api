package ui.ft.ccit.faculty.transaksi.detailtransaksi.view;

import org.springframework.stereotype.Service;
import ui.ft.ccit.faculty.transaksi.barang.model.BarangRepository;
import ui.ft.ccit.faculty.transaksi.barang.view.BarangNotFoundException;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksi;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksiId;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksiRepository;
import ui.ft.ccit.faculty.transaksi.transaksi.model.TransaksiRepository;
import ui.ft.ccit.faculty.transaksi.transaksi.view.TransaksiNotFoundException;

import java.util.List;

/**
 * Service layer for DetailTransaksi business logic.
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
@Service
public class DetailTransaksiService {
	
	private final DetailTransaksiRepository repository;
	private final TransaksiRepository transaksiRepository;
	private final BarangRepository barangRepository;
	
	public DetailTransaksiService(DetailTransaksiRepository repository,
	                              TransaksiRepository transaksiRepository,
	                              BarangRepository barangRepository) {
		this.repository = repository;
		this.transaksiRepository = transaksiRepository;
		this.barangRepository = barangRepository;
	}
	
	public List<DetailTransaksi> findAll() {
		return repository.findAll();
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
		
		// Validate foreign keys
		if (!transaksiRepository.existsById(detailTransaksi.getId().getKodeTransaksi())) {
			throw new TransaksiNotFoundException(detailTransaksi.getId().getKodeTransaksi());
		}
		if (!barangRepository.existsById(detailTransaksi.getId().getIdBarang())) {
			throw new BarangNotFoundException(detailTransaksi.getId().getIdBarang());
		}
		
		return repository.save(detailTransaksi);
	}
	
	public DetailTransaksi update(String kodeTransaksi, String idBarang, DetailTransaksi detailTransaksi) {
		findById(kodeTransaksi, idBarang); // Verify exists
		detailTransaksi.setId(new DetailTransaksiId(kodeTransaksi, idBarang));
		return repository.save(detailTransaksi);
	}
	
	public void delete(String kodeTransaksi, String idBarang) {
		findById(kodeTransaksi, idBarang); // Verify exists
		DetailTransaksiId id = new DetailTransaksiId(kodeTransaksi, idBarang);
		repository.deleteById(id);
	}
}
