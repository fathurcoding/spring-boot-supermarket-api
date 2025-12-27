package ui.ft.ccit.faculty.transaksi.barang.controller;

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
import ui.ft.ccit.faculty.transaksi.barang.dto.*;
import ui.ft.ccit.faculty.transaksi.barang.model.Barang;
import ui.ft.ccit.faculty.transaksi.barang.view.BarangService;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/barang")
@Tag(name = "Barang", description = "Product management endpoints")
public class BarangController {

    private final BarangService service;

    public BarangController(BarangService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Returns a paginated list of products, optionally filtered by name")
    public PagedModel<EntityModel<BarangResponse>> getAll(
            @Parameter(description = "Optional name to search for") @RequestParam(required = false) String nama,
            @PageableDefault(size = 20, sort = "idBarang", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Barang> page;
        if (nama != null && !nama.isEmpty()) {
            page = service.searchByNama(nama, pageable);
        } else {
            page = service.getAll(pageable);
        }

        return PagedModel.of(
                page.getContent().stream()
                        .map(BarangMapper::toResponse)
                        .map(this::toModel)
                        .collect(Collectors.toList()),
                new PagedModel.PageMetadata(
                        page.getSize(),
                        page.getNumber(),
                        page.getTotalElements(),
                        page.getTotalPages()
                ),
                linkTo(methodOn(BarangController.class).getAll(nama, pageable)).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    public EntityModel<BarangResponse> getById(@PathVariable String id) {
        Barang barang = service.getById(id);
        return toModel(BarangMapper.toResponse(barang));
    }

    @PostMapping
    @Operation(summary = "Create new product")
    public ResponseEntity<EntityModel<BarangResponse>> create(@Valid @RequestBody CreateBarangRequest request) {
        Barang barang = BarangMapper.toEntity(request);
        Barang saved = service.save(barang);
        return ResponseEntity
                .created(linkTo(methodOn(BarangController.class).getById(saved.getIdBarang())).toUri())
                .body(toModel(BarangMapper.toResponse(saved)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update product")
    public ResponseEntity<EntityModel<BarangResponse>> update(
            @PathVariable String id, @Valid @RequestBody UpdateBarangRequest request) {
        
        // Use a temporary entity to pass updates to service
        // Ideally service should take DTO or we update existing entity here
        // Based on service logic: it takes an entity 'updated' and copies fields
        Barang temp = new Barang();
        BarangMapper.updateEntity(temp, request);
        
        Barang updated = service.update(id, temp);
        return ResponseEntity.ok(toModel(BarangMapper.toResponse(updated)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private EntityModel<BarangResponse> toModel(BarangResponse response) {
        return EntityModel.of(response,
                linkTo(methodOn(BarangController.class).getById(response.getIdBarang())).withSelfRel(),
                linkTo(methodOn(BarangController.class).getAll(null, Pageable.unpaged())).withRel("barang"));
    }
}
