package ui.ft.ccit.faculty.transaksi.barang.view;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.DataAlreadyExistsException;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.barang.model.Barang;
import ui.ft.ccit.faculty.transaksi.barang.model.BarangRepository;

import java.util.List;

@Service
@Transactional
public class BarangService {

    private final BarangRepository barangRepository;

    public BarangService(BarangRepository barangRepository) {
        this.barangRepository = barangRepository;
    }

    public List<Barang> getAll() {
        return barangRepository.findAll();
    }
    
    public Page<Barang> getAll(Pageable pageable) {
        return barangRepository.findAll(pageable);
    }

    public Barang getById(String id) {
        return barangRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Barang", id));
    }

    public List<Barang> searchByNama(String keyword) {
        return barangRepository.findByNamaContainingIgnoreCase(keyword);
    }
    
    public Page<Barang> searchByNama(String keyword, Pageable pageable) {
        return barangRepository.findByNamaContainingIgnoreCase(keyword, pageable);
    }

    // CREATE
    public Barang save(Barang barang) {
        if (barang.getIdBarang() == null || barang.getIdBarang().isBlank()) {
            throw new IllegalArgumentException("idBarang wajib diisi");
        }

        if (barangRepository.existsById(barang.getIdBarang())) {
            throw new DataAlreadyExistsException("Barang", barang.getIdBarang());
        }

        return barangRepository.save(barang);
    }

    // UPDATE
    public Barang update(String id, Barang updated) {
        Barang existing = getById(id); // akan lempar DataNotFoundException

        // Only update fields if they are not null / managed logic
        // But since we are passing 'updated' entity from Controller (mapped from DTO), 
        // we can rely on Mapper logic OR manually set here. 
        // To keep Service clean, we assume 'updated' contains the new state to apply 
        // OR we use the mapper in Service?
        // Ideally: Controller maps DTO -> Entity, Service handles business logic.
        // The previous implementation did manual setting here. Let's keep it robust.
        
        if (updated.getNama() != null) existing.setNama(updated.getNama());
        if (updated.getStok() != null) existing.setStok(updated.getStok());
        if (updated.getHarga() != null) existing.setHarga(updated.getHarga());
        if (updated.getPersenLaba() != null) existing.setPersenLaba(updated.getPersenLaba());
        if (updated.getDiskon() != null) existing.setDiskon(updated.getDiskon());
        if (updated.getIdJenisBarang() != null) existing.setIdJenisBarang(updated.getIdJenisBarang());
        if (updated.getIdPemasok() != null) existing.setIdPemasok(updated.getIdPemasok());

        return barangRepository.save(existing);
    }

    // DELETE
    public void delete(String id) {
        if (!barangRepository.existsById(id)) {
            throw new DataNotFoundException("Barang", id);
        }
        barangRepository.deleteById(id);
    }
}
