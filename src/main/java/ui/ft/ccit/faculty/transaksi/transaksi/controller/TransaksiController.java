package ui.ft.ccit.faculty.transaksi.transaksi.controller;

import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi;
import ui.ft.ccit.faculty.transaksi.transaksi.view.TransaksiService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * REST Controller for Transaksi endpoints.
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
@RestController
@RequestMapping("/api/transaksi")
public class TransaksiController {
	
	private final TransaksiService service;
	
	public TransaksiController(TransaksiService service) {
		this.service = service;
	}
	
	@GetMapping
	public CollectionModel<EntityModel<Transaksi>> getAll() {
		List<EntityModel<Transaksi>> transaksiList = service.findAll().stream()
				.map(this::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(transaksiList,
				linkTo(methodOn(TransaksiController.class).getAll()).withSelfRel());
	}
	
	@GetMapping("/{kode}")
	public EntityModel<Transaksi> getByKode(@PathVariable String kode) {
		return toModel(service.findById(kode));
	}
	
	@PostMapping
	public ResponseEntity<EntityModel<Transaksi>> create(@RequestBody Transaksi transaksi) {
		Transaksi created = service.create(transaksi);
		return ResponseEntity
				.created(linkTo(methodOn(TransaksiController.class).getByKode(created.getKodeTransaksi())).toUri())
				.body(toModel(created));
	}
	
	@PutMapping("/{kode}")
	public EntityModel<Transaksi> update(@PathVariable String kode, @RequestBody Transaksi transaksi) {
		return toModel(service.update(kode, transaksi));
	}
	
	@DeleteMapping("/{kode}")
	public ResponseEntity<Void> delete(@PathVariable String kode) {
		service.delete(kode);
		return ResponseEntity.noContent().build();
	}
	
	private EntityModel<Transaksi> toModel(Transaksi transaksi) {
		return EntityModel.of(transaksi,
				linkTo(methodOn(TransaksiController.class).getByKode(transaksi.getKodeTransaksi())).withSelfRel(),
				linkTo(methodOn(TransaksiController.class).getAll()).withRel("transaksi"));
	}
}
