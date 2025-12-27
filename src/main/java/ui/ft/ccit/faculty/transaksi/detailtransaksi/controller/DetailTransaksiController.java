package ui.ft.ccit.faculty.transaksi.detailtransaksi.controller;

import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksi;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.view.DetailTransaksiService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * REST Controller for DetailTransaksi endpoints.
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
@RestController
@RequestMapping("/api/detail-transaksi")
public class DetailTransaksiController {
	
	private final DetailTransaksiService service;
	
	public DetailTransaksiController(DetailTransaksiService service) {
		this.service = service;
	}
	
	@GetMapping
	public CollectionModel<EntityModel<DetailTransaksi>> getAll() {
		List<EntityModel<DetailTransaksi>> detailList = service.findAll().stream()
				.map(this::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(detailList,
				linkTo(methodOn(DetailTransaksiController.class).getAll()).withSelfRel());
	}
	
	@GetMapping("/{kodeTransaksi}/{idBarang}")
	public EntityModel<DetailTransaksi> getById(@PathVariable String kodeTransaksi, 
	                                            @PathVariable String idBarang) {
		return toModel(service.findById(kodeTransaksi, idBarang));
	}
	
	@PostMapping
	public ResponseEntity<EntityModel<DetailTransaksi>> create(@RequestBody DetailTransaksi detailTransaksi) {
		DetailTransaksi created = service.create(detailTransaksi);
		return ResponseEntity
				.created(linkTo(methodOn(DetailTransaksiController.class)
						.getById(created.getId().getKodeTransaksi(), created.getId().getIdBarang())).toUri())
				.body(toModel(created));
	}
	
	@PutMapping("/{kodeTransaksi}/{idBarang}")
	public EntityModel<DetailTransaksi> update(@PathVariable String kodeTransaksi,
	                                           @PathVariable String idBarang,
	                                           @RequestBody DetailTransaksi detailTransaksi) {
		return toModel(service.update(kodeTransaksi, idBarang, detailTransaksi));
	}
	
	@DeleteMapping("/{kodeTransaksi}/{idBarang}")
	public ResponseEntity<Void> delete(@PathVariable String kodeTransaksi, 
	                                   @PathVariable String idBarang) {
		service.delete(kodeTransaksi, idBarang);
		return ResponseEntity.noContent().build();
	}
	
	private EntityModel<DetailTransaksi> toModel(DetailTransaksi detail) {
		return EntityModel.of(detail,
				linkTo(methodOn(DetailTransaksiController.class)
						.getById(detail.getId().getKodeTransaksi(), detail.getId().getIdBarang())).withSelfRel(),
				linkTo(methodOn(DetailTransaksiController.class).getAll()).withRel("detail-transaksi"));
	}
}
