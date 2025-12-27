package ui.ft.ccit.faculty.transaksi.transaksi.view;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.karyawan.model.KaryawanRepository;
import ui.ft.ccit.faculty.transaksi.karyawan.view.KaryawanNotFoundException;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.PelangganRepository;
import ui.ft.ccit.faculty.transaksi.pelanggan.view.PelangganNotFoundException;
import ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi;
import ui.ft.ccit.faculty.transaksi.transaksi.model.TransaksiRepository;

import java.util.List;

@Service
@Transactional
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

	public Page<Transaksi> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Page<Transaksi> searchByKode(String kode, Pageable pageable) {
		return repository.findByKodeTransaksiContainingIgnoreCase(kode, pageable);
	}

	public Transaksi findById(String kode) {
		return repository.findById(kode)
				.orElseThrow(() -> new TransaksiNotFoundException(kode));
	}

	public Transaksi create(Transaksi transaksi) {
		if (repository.existsById(transaksi.getKodeTransaksi())) {
			throw new TransaksiAlreadyExistsException(transaksi.getKodeTransaksi());
		}

		validateRelations(transaksi);

		return repository.save(transaksi);
	}

	public Transaksi update(String kode, Transaksi transaksi) {
		findById(kode); // Verify exists
		transaksi.setKodeTransaksi(kode);

		validateRelations(transaksi);

		return repository.save(transaksi);
	}

	private void validateRelations(Transaksi transaksi) {
		// Validate foreign keys
		if (transaksi.getKaryawan() != null
				&& !karyawanRepository.existsById(transaksi.getKaryawan().getIdKaryawan())) {
			throw new KaryawanNotFoundException(transaksi.getKaryawan().getIdKaryawan());
		}
		if (transaksi.getPelanggan() != null
				&& !pelangganRepository.existsById(transaksi.getPelanggan().getIdPelanggan())) {
			throw new PelangganNotFoundException(transaksi.getPelanggan().getIdPelanggan());
		}
	}

	public void delete(String kode) {
		if (!repository.existsById(kode)) {
			throw new TransaksiNotFoundException(kode);
		}
		repository.deleteById(kode);
	}
}
