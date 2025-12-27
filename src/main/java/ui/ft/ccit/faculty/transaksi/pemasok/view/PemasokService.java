package ui.ft.ccit.faculty.transaksi.pemasok.view;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.pemasok.model.Pemasok;
import ui.ft.ccit.faculty.transaksi.pemasok.model.PemasokRepository;

import java.util.List;

@Service
@Transactional
public class PemasokService {

    private final PemasokRepository repository;

    public PemasokService(PemasokRepository repository) {
        this.repository = repository;
    }

    public List<Pemasok> getAll() {
        return repository.findAll();
    }

    // REVISI: ID Pemasok bertipe String
    public Pemasok getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new PemasokNotFoundException(id));
    }

    public List<Pemasok> searchByNama(String keyword) {
        return repository.findByNamaPemasokContainingIgnoreCase(keyword);
    }

    // CREATE
    public Pemasok save(Pemasok pemasok) {
        // Validasi ID tidak boleh kosong karena tipe String (bukan auto-increment)
        if (pemasok.getIdPemasok() == null || pemasok.getIdPemasok().isBlank()) {
            throw new IllegalArgumentException("ID Pemasok wajib diisi");
        }

        if (repository.existsById(pemasok.getIdPemasok())) {
            throw new PemasokAlreadyExistsException(pemasok.getIdPemasok());
        }
        return repository.save(pemasok);
    }

    // UPDATE
    // REVISI: Parameter id menggunakan String
    public Pemasok update(String id, Pemasok updated) {
        Pemasok existing = getById(id); // Akan throw Exception jika tidak ketemu

        // Update semua field data diri pemasok
        existing.setNamaPemasok(updated.getNamaPemasok());
        existing.setAlamat(updated.getAlamat());
        existing.setTelepon(updated.getTelepon());
        existing.setEmail(updated.getEmail());

        // ID tidak di-update (Primary Key)
        
        return repository.save(existing);
    }

    // DELETE
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new PemasokNotFoundException(id);
        }
        repository.deleteById(id);
    }
}