package ui.ft.ccit.faculty.transaksi.pemasok.controller;

import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.pemasok.model.Pemasok;
import ui.ft.ccit.faculty.transaksi.pemasok.view.PemasokService; // Import Service yang benar

import java.util.List;

@RestController
@RequestMapping("/api/pemasok")
public class PemasokController {

    // REVISI: Inject PemasokService, bukan PemasokController
    private final PemasokService service;

    public PemasokController(PemasokService service) {
        this.service = service;
    }

    // GET list semua pemasok
    @GetMapping
    public List<Pemasok> list() {
        return service.getAll();
    }

    // GET satu pemasok by id
    // REVISI: Menggunakan @PathVariable String (karena ID Pemasok adalah String)
    @GetMapping("/{id}")
    public Pemasok get(@PathVariable String id) {
        return service.getById(id);
    }

    // SEARCH by nama pemasok
    @GetMapping("/search")
    public List<Pemasok> search(@RequestParam String q) {
        return service.searchByNama(q);
    }

    // POST - create pemasok baru
    @PostMapping
    public Pemasok create(@RequestBody Pemasok pemasok) {
        return service.save(pemasok);
    }

    // PUT - edit/update pemasok
    @PutMapping("/{id}")
    public Pemasok update(@PathVariable String id, @RequestBody Pemasok pemasok) {
        return service.update(id, pemasok);
    }

    // DELETE - hapus pemasok
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}