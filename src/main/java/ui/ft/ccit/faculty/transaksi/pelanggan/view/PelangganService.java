package ui.ft.ccit.faculty.transaksi.pelanggan.view;

import org.springframework.stereotype.Service;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.PelangganRepository;

import java.util.List;

/**
 * Service layer for Pelanggan business logic.
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
@Service
public class PelangganService {
	
	private final PelangganRepository repository;
	
	public PelangganService(PelangganRepository repository) {
		this.repository = repository;
	}
	
	public List<Pelanggan> findAll() {
		return repository.findAll();
	}
	
	public Pelanggan findById(String id) {
		return repository.findById(id)
				.orElseThrow(() -> new PelangganNotFoundException(id));
	}
	
	public Pelanggan create(Pelanggan pelanggan) {
		if (repository.existsById(pelanggan.getIdPelanggan())) {
			throw new PelangganAlreadyExistsException(pelanggan.getIdPelanggan());
		}
		return repository.save(pelanggan);
	}
	
	public Pelanggan update(String id, Pelanggan pelanggan) {
		findById(id); // Verify exists
		pelanggan.setIdPelanggan(id);
		return repository.save(pelanggan);
	}
	
	public void delete(String id) {
		findById(id); // Verify exists
		repository.deleteById(id);
	}
}
