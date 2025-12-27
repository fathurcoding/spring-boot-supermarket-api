package ui.ft.ccit.faculty.transaksi.pemasok.view;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    
    public Page<Pemasok> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Pemasok getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new PemasokNotFoundException(id));
    }

    public List<Pemasok> searchByNama(String keyword) {
        return repository.findByNamaPemasokContainingIgnoreCase(keyword);
    }
    
    public Page<Pemasok> searchByNama(String keyword, Pageable pageable) {
        return repository.findByNamaPemasokContainingIgnoreCase(keyword, pageable);
    }

    // CREATE
    public Pemasok save(Pemasok pemasok) {
        if (pemasok.getIdPemasok() == null || pemasok.getIdPemasok().isBlank()) {
            throw new IllegalArgumentException("ID Pemasok wajib diisi");
        }

        if (repository.existsById(pemasok.getIdPemasok())) {
            throw new PemasokAlreadyExistsException(pemasok.getIdPemasok());
        }
        return repository.save(pemasok);
    }

    // UPDATE
    public Pemasok update(String id, Pemasok updated) {
        Pemasok existing = getById(id);

        if (updated.getNamaPemasok() != null) existing.setNamaPemasok(updated.getNamaPemasok());
        if (updated.getAlamat() != null) existing.setAlamat(updated.getAlamat());
        if (updated.getTelepon() != null) existing.setTelepon(updated.getTelepon());
        if (updated.getEmail() != null) existing.setEmail(updated.getEmail());
        
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