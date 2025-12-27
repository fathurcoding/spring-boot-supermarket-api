package ui.ft.ccit.faculty.transaksi.transaksi.view;

import org.springframework.stereotype.Service;
import ui.ft.ccit.faculty.transaksi.karyawan.model.KaryawanRepository;
import ui.ft.ccit.faculty.transaksi.karyawan.view.KaryawanNotFoundException;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.PelangganRepository;
import ui.ft.ccit.faculty.transaksi.pelanggan.view.PelangganNotFoundException;
import ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi;
import ui.ft.ccit.faculty.transaksi.transaksi.model.TransaksiRepository;

import java.util.List;

/**
 * Service layer for Transaksi business logic.
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
@Service
public class TransaksiService {
	
	private final TransaksiRepository repository;
	private final KaryawanRepository karyawanRepository;
	private final PelangganRepository pelangganRepository;
	
	public TransaksiService(TransaksiRepository repository,
	                        KaryawanRepository karyawanRepository,
	                        PelangganRepository pelangganRepository) {
		this.repository = repository;
		this.karyawanRepository = karyawanRepository;
		this.pelangganRepository = pelangganRepository;
	}
	
	public List<Transaksi> findAll() {
		return repository.findAll();
	}
	
	public Transaksi findById(String kode) {
		return repository.findById(kode)
				.orElseThrow(() -> new TransaksiNotFoundException(kode));
	}
	
	public Transaksi create(Transaksi transaksi) {
		if (repository.existsById(transaksi.getKodeTransaksi())) {
			throw new TransaksiAlreadyExistsException(transaksi.getKodeTransaksi());
		}
		
		// Validate foreign keys
		if (!karyawanRepository.existsById(transaksi.getKaryawan().getIdKaryawan())) {
			throw new KaryawanNotFoundException(transaksi.getKaryawan().getIdKaryawan());
		}
		if (!pelangganRepository.existsById(transaksi.getPelanggan().getIdPelanggan())) {
			throw new PelangganNotFoundException(transaksi.getPelanggan().getIdPelanggan());
		}
		
		return repository.save(transaksi);
	}
	
	public Transaksi update(String kode, Transaksi transaksi) {
		findById(kode); // Verify exists
		transaksi.setKodeTransaksi(kode);
		
		// Validate foreign keys
		if (!karyawanRepository.existsById(transaksi.getKaryawan().getIdKaryawan())) {
			throw new KaryawanNotFoundException(transaksi.getKaryawan().getIdKaryawan());
		}
		if (!pelangganRepository.existsById(transaksi.getPelanggan().getIdPelanggan())) {
			throw new PelangganNotFoundException(transaksi.getPelanggan().getIdPelanggan());
		}
		
		return repository.save(transaksi);
	}
	
	public void delete(String kode) {
		findById(kode); // Verify exists
		repository.deleteById(kode);
	}
}
