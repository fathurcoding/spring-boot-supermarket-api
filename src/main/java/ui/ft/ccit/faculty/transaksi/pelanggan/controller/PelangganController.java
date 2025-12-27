package ui.ft.ccit.faculty.transaksi.pelanggan.controller;

import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;
import ui.ft.ccit.faculty.transaksi.pelanggan.view.PelangganService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * REST Controller for Pelanggan endpoints.
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
@RestController
@RequestMapping("/api/pelanggan")
public class PelangganController {
	
	private final PelangganService service;
	
	public PelangganController(PelangganService service) {
		this.service = service;
	}
	
	@GetMapping
	public CollectionModel<EntityModel<Pelanggan>> getAll() {
		List<EntityModel<Pelanggan>> pelangganList = service.findAll().stream()
				.map(this::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(pelangganList,
				linkTo(methodOn(PelangganController.class).getAll()).withSelfRel());
	}
	
	@GetMapping("/{id}")
	public EntityModel<Pelanggan> getById(@PathVariable String id) {
		return toModel(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<EntityModel<Pelanggan>> create(@RequestBody Pelanggan pelanggan) {
		Pelanggan created = service.create(pelanggan);
		return ResponseEntity
				.created(linkTo(methodOn(PelangganController.class).getById(created.getIdPelanggan())).toUri())
				.body(toModel(created));
	}
	
	@PutMapping("/{id}")
	public EntityModel<Pelanggan> update(@PathVariable String id, @RequestBody Pelanggan pelanggan) {
		return toModel(service.update(id, pelanggan));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	private EntityModel<Pelanggan> toModel(Pelanggan pelanggan) {
		return EntityModel.of(pelanggan,
				linkTo(methodOn(PelangganController.class).getById(pelanggan.getIdPelanggan())).withSelfRel(),
				linkTo(methodOn(PelangganController.class).getAll()).withRel("pelanggan"));
	}
}
