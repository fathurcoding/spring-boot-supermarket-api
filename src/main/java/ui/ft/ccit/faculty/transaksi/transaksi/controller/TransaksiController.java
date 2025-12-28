package ui.ft.ccit.faculty.transaksi.transaksi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.transaksi.dto.CreateTransaksiRequest;
import ui.ft.ccit.faculty.transaksi.transaksi.dto.TransactionSummary;
import ui.ft.ccit.faculty.transaksi.transaksi.dto.TransaksiMapper;
import ui.ft.ccit.faculty.transaksi.transaksi.dto.TransaksiResponse;
import ui.ft.ccit.faculty.transaksi.transaksi.model.Transaksi;
import ui.ft.ccit.faculty.transaksi.transaksi.view.TransaksiService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/transaksi")
@Tag(name = "Transaksi", description = "Transaction management endpoints")
public class TransaksiController {

	private final TransaksiService service;
	private final ui.ft.ccit.faculty.transaksi.detailtransaksi.view.DetailTransaksiService detailService;
	private final ui.ft.ccit.faculty.transaksi.transaksi.view.TransactionCalculationService calculationService;

	public TransaksiController(TransaksiService service,
			ui.ft.ccit.faculty.transaksi.detailtransaksi.view.DetailTransaksiService detailService,
			ui.ft.ccit.faculty.transaksi.transaksi.view.TransactionCalculationService calculationService) {
		this.service = service;
		this.detailService = detailService;
		this.calculationService = calculationService;
	}

	@GetMapping
	@Operation(summary = "Get all transactions", description = "Returns a paginated list of transactions, optionally filtered by code")
	public PagedModel<EntityModel<TransaksiResponse>> getAll(
			@Parameter(description = "Optional code to search for") @RequestParam(required = false) String kode,
			@PageableDefault(size = 20, sort = "tglTransaksi", direction = Sort.Direction.DESC) Pageable pageable) {

		Page<Transaksi> page;
		if (kode != null && !kode.isEmpty()) {
			page = service.searchByKode(kode, pageable);
		} else {
			page = service.findAll(pageable);
		}

		return PagedModel.of(
				page.getContent().stream()
						.map(t -> {
							List<ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksi> details = detailService
									.findByKodeTransaksi(t.getKodeTransaksi());
							String pelangganId = (t.getPelanggan() != null) ? t.getPelanggan().getIdPelanggan() : null;
							BigDecimal total = calculationService.calculateTotal(details, pelangganId);
							return TransaksiMapper.toResponse(t, total);
						})
						.map(this::toModel)
						.collect(Collectors.toList()),
				new PagedModel.PageMetadata(
						page.getSize(),
						page.getNumber(),
						page.getTotalElements(),
						page.getTotalPages()),
				linkTo(methodOn(TransaksiController.class).getAll(kode, pageable)).withSelfRel());
	}

	@GetMapping("/{kode}")
	@Operation(summary = "Get transaction by Code")
	public EntityModel<TransaksiResponse> getByKode(@PathVariable String kode) {
		Transaksi transaksi = service.findById(kode);
		List<ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksi> details = detailService
				.findByKodeTransaksi(transaksi.getKodeTransaksi());
		String pelangganId = (transaksi.getPelanggan() != null) ? transaksi.getPelanggan().getIdPelanggan() : null;
		BigDecimal total = calculationService.calculateTotal(details, pelangganId);
		return toModel(TransaksiMapper.toResponse(transaksi, total));
	}

	@GetMapping("/{kode}/summary")
	@Operation(summary = "Get transaction summary")
	public ResponseEntity<TransactionSummary> getSummary(@PathVariable String kode) {
		Transaksi transaksi = service.findById(kode);
		List<ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksi> details = detailService
				.findByKodeTransaksi(transaksi.getKodeTransaksi());
		String pelangganId = (transaksi.getPelanggan() != null) ? transaksi.getPelanggan().getIdPelanggan() : null;

		TransactionSummary summary = calculationService.calculateSummary(details, pelangganId);
		return ResponseEntity.ok(summary);
	}

	@PostMapping
	@Operation(summary = "Create new transaction")
	public ResponseEntity<EntityModel<TransaksiResponse>> create(@Valid @RequestBody CreateTransaksiRequest request) {
		Transaksi transaksi = TransaksiMapper.toEntity(request);
		Transaksi created = service.create(transaksi);
		// New transaction has no details yet, total is 0
		return ResponseEntity
				.created(linkTo(methodOn(TransaksiController.class).getByKode(created.getKodeTransaksi())).toUri())
				.body(toModel(TransaksiMapper.toResponse(created, BigDecimal.ZERO)));
	}

	@PutMapping("/{kode}")
	@Operation(summary = "Update transaction")
	public ResponseEntity<EntityModel<TransaksiResponse>> update(
			@PathVariable String kode, @Valid @RequestBody CreateTransaksiRequest request) {

		// Note: Reuse CreateRequest for update as structure is same for now.
		// In future might need separate UpdateRequest.
		Transaksi transaksi = TransaksiMapper.toEntity(request);
		Transaksi updated = service.update(kode, transaksi);

		List<ui.ft.ccit.faculty.transaksi.detailtransaksi.model.DetailTransaksi> details = detailService
				.findByKodeTransaksi(updated.getKodeTransaksi());
		String pelangganId = (updated.getPelanggan() != null) ? updated.getPelanggan().getIdPelanggan() : null;
		BigDecimal total = calculationService.calculateTotal(details, pelangganId);

		return ResponseEntity.ok(toModel(TransaksiMapper.toResponse(updated, total)));
	}

	@DeleteMapping("/{kode}")
	@Operation(summary = "Delete transaction")
	public ResponseEntity<Void> delete(@PathVariable String kode) {
		service.delete(kode);
		return ResponseEntity.noContent().build();
	}

	private EntityModel<TransaksiResponse> toModel(TransaksiResponse response) {
		return EntityModel.of(response,
				linkTo(methodOn(TransaksiController.class).getByKode(response.getKodeTransaksi())).withSelfRel(),
				linkTo(methodOn(TransaksiController.class).getAll(null, Pageable.unpaged())).withRel("transaksi"));
	}
}
