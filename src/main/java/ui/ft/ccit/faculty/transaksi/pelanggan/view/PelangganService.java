package ui.ft.ccit.faculty.transaksi.pelanggan.view;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.PelangganRepository;

import java.util.List;

@Service
public class PelangganService {
	
	private final PelangganRepository repository;
	
	public PelangganService(PelangganRepository repository) {
		this.repository = repository;
	}
	
	public List<Pelanggan> findAll() {
		return repository.findAll();
	}
	
	public Page<Pelanggan> findAll(Pageable pageable) {
		return repository.findAll(pageable);
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
		findById(id);
		pelanggan.setIdPelanggan(id);
		return repository.save(pelanggan);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
}
