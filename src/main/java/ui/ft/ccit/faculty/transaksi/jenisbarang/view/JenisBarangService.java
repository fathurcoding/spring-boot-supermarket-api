package ui.ft.ccit.faculty.transaksi.jenisbarang.view; // Sesuai struktur folder kamu

import ui.ft.ccit.faculty.transaksi.DataAlreadyExistsException; // Pastikan exception ini ada
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;      // Pastikan exception ini ada
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

    // Ubah parameter id menjadi Byte sesuai Model
    public JenisBarang getById(Byte id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("JenisBarang", id.toString()));
    }

    public List<JenisBarang> searchByNama(String keyword) {
        return repository.findByNamaJenisContainingIgnoreCase(keyword);
    }

    // CREATE
    public JenisBarang save(JenisBarang jenisBarang) {
        // Cek jika ID sudah di-set (biasanya untuk update, tapi kalau create id null/kosong)
        if (jenisBarang.getIdJenisBarang() != null && repository.existsById(jenisBarang.getIdJenisBarang())) {
            throw new DataAlreadyExistsException("JenisBarang", jenisBarang.getIdJenisBarang().toString());
        }
        return repository.save(jenisBarang);
    }

    // UPDATE
    public JenisBarang update(Byte id, JenisBarang updated) {
        JenisBarang existing = getById(id); // Akan throw DataNotFoundException jika tidak ada

        // Update hanya field yang ada di Model
        existing.setNamaJenis(updated.getNamaJenis());

        // Field lain (stok, harga, dll) DIHAPUS karena tidak ada di class JenisBarang

        return repository.save(existing);
    }

    // DELETE
    public void delete(Byte id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("JenisBarang", id.toString());
        }
        repository.deleteById(id);
    }
}