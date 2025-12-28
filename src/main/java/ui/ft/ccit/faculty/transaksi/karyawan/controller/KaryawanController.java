package ui.ft.ccit.faculty.transaksi.karyawan.controller;

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
import ui.ft.ccit.faculty.transaksi.karyawan.dto.*;
import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;
import ui.ft.ccit.faculty.transaksi.karyawan.view.KaryawanService;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * REST Controller for Karyawan endpoints with DTO support and pagination.
 * 
 * <p>
 * Provides CRUD operations for employee management with HATEOAS support,
 * input validation, and pagination capabilities.
 * </p>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
@RestController
@RequestMapping("/api/karyawan")
@Tag(name = "Karyawan", description = "Employee management endpoints")
public class KaryawanController {

	private final KaryawanService service;

	public KaryawanController(KaryawanService service) {
		this.service = service;
	}

	/**
	 * Get all employees with pagination and HATEOAS links, optionally filtered by
	 * name.
	 * 
	 * @param nama     optional name fragment to filter by
	 * @param pageable pagination parameters (page, size, sort)
	 * @return paged collection of karyawan responses
	 */
	@GetMapping
	@Operation(summary = "Get all employees", description = "Returns a paginated list of all employees with HATEOAS links, optionally filtered by name")
	public PagedModel<EntityModel<KaryawanResponse>> getAll(
			@Parameter(description = "Optional name fragment to search for") @RequestParam(required = false) String nama,
			@PageableDefault(size = 20, sort = "idKaryawan", direction = Sort.Direction.ASC) Pageable pageable) {

		Page<Karyawan> page;
		if (nama != null && !nama.isEmpty()) {
			page = service.searchByName(nama, pageable);
		} else {
			page = service.findAll(pageable);
		}

		return PagedModel.of(
				page.getContent().stream()
						.map(KaryawanMapper::toResponse)
						.map(this::toModel)
						.collect(Collectors.toList()),
				new PagedModel.PageMetadata(
						page.getSize(),
						page.getNumber(),
						page.getTotalElements(),
						page.getTotalPages()),
				linkTo(methodOn(KaryawanController.class).getAll(nama, pageable)).withSelfRel());
	}

	/**
	 * Get employee by ID.
	 * 
	 * @param id the employee ID
	 * @return karyawan response with HATEOAS links
	 */
	@GetMapping("/{id}")
	@Operation(summary = "Get employee by ID", description = "Returns a single employee by their ID")
	public EntityModel<KaryawanResponse> getById(@PathVariable String id) {
		Karyawan karyawan = service.findById(id);
		return toModel(KaryawanMapper.toResponse(karyawan));
	}

	/**
	 * Create a new employee with validation.
	 * 
	 * @param request the employee data to create (validated)
	 * @return created karyawan with HATEOAS links and 201 Created status
	 */
	@PostMapping
	@Operation(summary = "Create new employee", description = "Creates a new employee with validated data")
	public ResponseEntity<EntityModel<KaryawanResponse>> create(@Valid @RequestBody CreateKaryawanRequest request) {
		Karyawan karyawan = KaryawanMapper.toEntity(request);
		Karyawan created = service.create(karyawan);
		KaryawanResponse response = KaryawanMapper.toResponse(created);

		return ResponseEntity
				.created(linkTo(methodOn(KaryawanController.class).getById(created.getIdKaryawan())).toUri())
				.body(toModel(response));
	}

	/**
	 * Update an existing employee with partial update support.
	 * 
	 * @param id      the employee ID to update
	 * @param request the updated employee data (validated, fields optional)
	 * @return updated karyawan with HATEOAS links
	 */
	@PutMapping("/{id}")
	@Operation(summary = "Update employee", description = "Updates an existing employee with validated data. Only provided fields will be updated.")
	public EntityModel<KaryawanResponse> update(
			@PathVariable String id,
			@Valid @RequestBody UpdateKaryawanRequest request) {

		Karyawan existing = service.findById(id);
		KaryawanMapper.updateEntity(existing, request);
		Karyawan updated = service.update(id, existing);

		return toModel(KaryawanMapper.toResponse(updated));
	}

	/**
	 * Delete an employee.
	 * 
	 * @param id the employee ID to delete
	 * @return 204 No Content
	 */
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete employee", description = "Deletes an employee by their ID")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Converts KaryawanResponse to EntityModel with HATEOAS links.
	 * 
	 * @param response the karyawan response DTO
	 * @return entity model with hypermedia links
	 */
	private EntityModel<KaryawanResponse> toModel(KaryawanResponse response) {
		return EntityModel.of(response,
				linkTo(methodOn(KaryawanController.class).getById(response.getIdKaryawan())).withSelfRel(),
				linkTo(methodOn(KaryawanController.class).getAll(null, Pageable.unpaged())).withRel("karyawan"));
	}
}
