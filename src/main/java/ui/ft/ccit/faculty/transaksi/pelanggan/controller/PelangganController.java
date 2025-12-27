package ui.ft.ccit.faculty.transaksi.pelanggan.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.pelanggan.dto.*;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;
import ui.ft.ccit.faculty.transaksi.pelanggan.view.PelangganService;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/pelanggan")
@Tag(name = "Pelanggan", description = "Customer management endpoints")
public class PelangganController {
	
	private final PelangganService service;
	
	public PelangganController(PelangganService service) {
		this.service = service;
	}
	
	@GetMapping
	@Operation(summary = "Get all customers", description = "Returns a paginated list of customers, optionally filtered by name")
	public PagedModel<EntityModel<PelangganResponse>> getAll(
			@Parameter(description = "Optional name to search for") @RequestParam(required = false) String nama,
			@PageableDefault(size = 20, sort = "idPelanggan", direction = Sort.Direction.ASC) Pageable pageable) {
		
		Page<Pelanggan> page;
		if (nama != null && !nama.isEmpty()) {
			page = service.searchByName(nama, pageable);
		} else {
			page = service.findAll(pageable);
		}
		
		return PagedModel.of(
				page.getContent().stream()
						.map(PelangganMapper::toResponse)
						.map(this::toModel)
						.collect(Collectors.toList()),
				new PagedModel.PageMetadata(
						page.getSize(),
						page.getNumber(),
						page.getTotalElements(),
						page.getTotalPages()
				),
				linkTo(methodOn(PelangganController.class).getAll(nama, pageable)).withSelfRel()
		);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get customer by ID")
	public EntityModel<PelangganResponse> getById(@PathVariable String id) {
		Pelanggan pelanggan = service.findById(id);
		return toModel(PelangganMapper.toResponse(pelanggan));
	}
	
	@PostMapping
	@Operation(summary = "Create new customer")
	public ResponseEntity<EntityModel<PelangganResponse>> create(@Valid @RequestBody CreatePelangganRequest request) {
		Pelanggan pelanggan = PelangganMapper.toEntity(request);
		Pelanggan created = service.create(pelanggan);
		PelangganResponse response = PelangganMapper.toResponse(created);
		
		return ResponseEntity
				.created(linkTo(methodOn(PelangganController.class).getById(created.getIdPelanggan())).toUri())
				.body(toModel(response));
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Update customer")
	public EntityModel<PelangganResponse> update(
			@PathVariable String id,
			@Valid @RequestBody UpdatePelangganRequest request) {
		
		Pelanggan existing = service.findById(id);
		PelangganMapper.updateEntity(existing, request);
		Pelanggan updated = service.update(id, existing);
		
		return toModel(PelangganMapper.toResponse(updated));
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete customer")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	private EntityModel<PelangganResponse> toModel(PelangganResponse response) {
		return EntityModel.of(response,
				linkTo(methodOn(PelangganController.class).getById(response.getIdPelanggan())).withSelfRel(),
				linkTo(methodOn(PelangganController.class).getAll(null, Pageable.unpaged())).withRel("pelanggan"));
	}
}
