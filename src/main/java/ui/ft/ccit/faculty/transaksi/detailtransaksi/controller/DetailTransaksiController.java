package ui.ft.ccit.faculty.transaksi.detailtransaksi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.dto.CreateDetailTransaksiRequest;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.dto.DetailTransaksiMapper;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.dto.DetailTransaksiResponse;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksi;
import ui.ft.ccit.faculty.transaksi.detailtransaksi.view.DetailTransaksiService;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/detail-transaksi")
@Tag(name = "Detail Transaksi", description = "Transaction Detail management endpoints")
public class DetailTransaksiController {

	private final DetailTransaksiService service;

	public DetailTransaksiController(DetailTransaksiService service) {
		this.service = service;
	}

	@GetMapping
	@Operation(summary = "Get all transaction details", description = "Returns a paginated list of transaction details")
	public PagedModel<EntityModel<DetailTransaksiResponse>> getAll(
			@Parameter(description = "Optional transaction code filter") @RequestParam(required = false) String kodeTransaksi,
			@PageableDefault(size = 20) Pageable pageable) {

		Page<DetailTransaksi> page;
		if (kodeTransaksi != null && !kodeTransaksi.isEmpty()) {
			page = service.findByKodeTransaksi(kodeTransaksi, pageable);
		} else {
			page = service.findAll(pageable);
		}

		return PagedModel.of(
				page.getContent().stream()
						.map(DetailTransaksiMapper::toResponse)
						.map(this::toModel)
						.collect(Collectors.toList()),
				new PagedModel.PageMetadata(
						page.getSize(),
						page.getNumber(),
						page.getTotalElements(),
						page.getTotalPages()),
				linkTo(methodOn(DetailTransaksiController.class).getAll(kodeTransaksi, pageable)).withSelfRel());
	}

	@GetMapping("/{kodeTransaksi}/{idBarang}")
	@Operation(summary = "Get transaction detail by ID")
	public EntityModel<DetailTransaksiResponse> getById(@PathVariable String kodeTransaksi,
			@PathVariable String idBarang) {
		DetailTransaksi detail = service.findById(kodeTransaksi, idBarang);
		return toModel(DetailTransaksiMapper.toResponse(detail));
	}

	@PostMapping
	@Operation(summary = "Create new transaction detail")
	public ResponseEntity<EntityModel<DetailTransaksiResponse>> create(
			@Valid @RequestBody CreateDetailTransaksiRequest request) {
		DetailTransaksi detail = DetailTransaksiMapper.toEntity(request);
		DetailTransaksi created = service.create(detail);
		return ResponseEntity
				.created(linkTo(methodOn(DetailTransaksiController.class)
						.getById(created.getId().getKodeTransaksi(), created.getId().getIdBarang())).toUri())
				.body(toModel(DetailTransaksiMapper.toResponse(created)));
	}

	@PutMapping("/{kodeTransaksi}/{idBarang}")
	@Operation(summary = "Update transaction detail")
	public ResponseEntity<EntityModel<DetailTransaksiResponse>> update(
			@PathVariable String kodeTransaksi,
			@PathVariable String idBarang,
			@Valid @RequestBody CreateDetailTransaksiRequest request) {

		// Note: Using CreateRequest for update as well
		DetailTransaksi detail = DetailTransaksiMapper.toEntity(request);
		DetailTransaksi updated = service.update(kodeTransaksi, idBarang, detail);
		return ResponseEntity.ok(toModel(DetailTransaksiMapper.toResponse(updated)));
	}

	@DeleteMapping("/{kodeTransaksi}/{idBarang}")
	@Operation(summary = "Delete transaction detail")
	public ResponseEntity<Void> delete(@PathVariable String kodeTransaksi,
			@PathVariable String idBarang) {
		service.delete(kodeTransaksi, idBarang);
		return ResponseEntity.noContent().build();
	}

	private EntityModel<DetailTransaksiResponse> toModel(DetailTransaksiResponse response) {
		return EntityModel.of(response,
				linkTo(methodOn(DetailTransaksiController.class)
						.getById(response.getKodeTransaksi(), response.getIdBarang())).withSelfRel(),
				linkTo(methodOn(DetailTransaksiController.class).getAll(null, Pageable.unpaged()))
						.withRel("detail-transaksi"));
	}
}
