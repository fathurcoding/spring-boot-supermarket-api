package ui.ft.ccit.faculty.transaksi.karyawan.controller;

import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;
import ui.ft.ccit.faculty.transaksi.karyawan.view.KaryawanService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * REST Controller for Karyawan endpoints.
 * 
 * <p>Provides CRUD operations for employee management with HATEOAS support.</p>
 * 
 * <p>Endpoints:</p>
 * <ul>
 *   <li>GET /api/karyawan - List all employees</li>
 *   <li>GET /api/karyawan/{id} - Get employee by ID</li>
 *   <li>POST /api/karyawan - Create new employee (requires auth)</li>
 *   <li>PUT /api/karyawan/{id} - Update employee (requires auth)</li>
 *   <li>DELETE /api/karyawan/{id} - Delete employee (requires auth)</li>
 * </ul>
 * 
 * @author CCIT Faculty Students
 * @version 0.0.1-SNAPSHOT
 * @since 2024-12-27
 */
@RestController
@RequestMapping("/api/karyawan")
public class KaryawanController {
	
	private final KaryawanService service;
	
	/**
	 * Constructor with dependency injection.
	 * 
	 * @param service the karyawan service
	 */
	public KaryawanController(KaryawanService service) {
		this.service = service;
	}
	
	/**
	 * Get all employees with HATEOAS links.
	 * 
	 * @return collection of karyawan with hypermedia links
	 */
	@GetMapping
	public CollectionModel<EntityModel<Karyawan>> getAll() {
		List<EntityModel<Karyawan>> karyawanList = service.findAll().stream()
				.map(this::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(karyawanList,
				linkTo(methodOn(KaryawanController.class).getAll()).withSelfRel());
	}
	
	/**
	 * Get employee by ID with HATEOAS links.
	 * 
	 * @param id the employee ID
	 * @return karyawan with hypermedia links
	 */
	@GetMapping("/{id}")
	public EntityModel<Karyawan> getById(@PathVariable String id) {
		return toModel(service.findById(id));
	}
	
	/**
	 * Create a new employee.
	 * 
	 * @param karyawan the employee data to create
	 * @return created karyawan with hypermedia links and 201 Created status
	 */
	@PostMapping
	public ResponseEntity<EntityModel<Karyawan>> create(@RequestBody Karyawan karyawan) {
		Karyawan created = service.create(karyawan);
		return ResponseEntity
				.created(linkTo(methodOn(KaryawanController.class).getById(created.getIdKaryawan())).toUri())
				.body(toModel(created));
	}
	
	/**
	 * Update an existing employee.
	 * 
	 * @param id the employee ID to update
	 * @param karyawan the updated employee data
	 * @return updated karyawan with hypermedia links
	 */
	@PutMapping("/{id}")
	public EntityModel<Karyawan> update(@PathVariable String id, @RequestBody Karyawan karyawan) {
		return toModel(service.update(id, karyawan));
	}
	
	/**
	 * Delete an employee.
	 * 
	 * @param id the employee ID to delete
	 * @return 204 No Content
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Converts Karyawan entity to EntityModel with HATEOAS links.
	 * 
	 * @param karyawan the karyawan entity
	 * @return entity model with hypermedia links
	 */
	private EntityModel<Karyawan> toModel(Karyawan karyawan) {
		return EntityModel.of(karyawan,
				linkTo(methodOn(KaryawanController.class).getById(karyawan.getIdKaryawan())).withSelfRel(),
				linkTo(methodOn(KaryawanController.class).getAll()).withRel("karyawan"));
	}
}
