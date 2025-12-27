package ui.ft.ccit.faculty.transaksi.jenisbarang.view;

import ui.ft.ccit.faculty.transaksi.DataAlreadyExistsException;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarang;
import ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarangRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JenisBarangService {

    private final JenisBarangRepository repository;

    public JenisBarangService(JenisBarangRepository repository) {
        this.repository = repository;
    }

    public List<JenisBarang> getAll() {
        return repository.findAll();
    }

    public JenisBarang getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("JenisBarang", id.toString()));
    }

    public List<JenisBarang> searchByNama(String keyword) {
        return repository.findByNamaJenisContainingIgnoreCase(keyword);
    }

    // CREATE
    public JenisBarang save(JenisBarang jenisBarang) {
        if (jenisBarang.getIdJenisBarang() != null && repository.existsById(jenisBarang.getIdJenisBarang())) {
            throw new DataAlreadyExistsException("JenisBarang", jenisBarang.getIdJenisBarang().toString());
        }
        return repository.save(jenisBarang);
    }

    // UPDATE
    public JenisBarang update(Integer id, JenisBarang updated) {
        JenisBarang existing = getById(id);
        existing.setNamaJenis(updated.getNamaJenis());
        return repository.save(existing);
    }

    // DELETE
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("JenisBarang", id.toString());
        }
        repository.deleteById(id);
    }
}