package ui.ft.ccit.faculty.transaksi.jenisbarang.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
import ui.ft.ccit.faculty.transaksi.jenisbarang.dto.CreateJenisBarangRequest;
import ui.ft.ccit.faculty.transaksi.jenisbarang.dto.JenisBarangMapper;
import ui.ft.ccit.faculty.transaksi.jenisbarang.dto.JenisBarangResponse;
import ui.ft.ccit.faculty.transaksi.jenisbarang.dto.UpdateJenisBarangRequest;
import ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarang;
import ui.ft.ccit.faculty.transaksi.jenisbarang.view.JenisBarangService;

import java.net.URI;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/jenisbarang")
@Tag(name = "Jenis Barang", description = "API for managing product categories")
public class JenisBarangController {

    private final JenisBarangService service;

    public JenisBarangController(JenisBarangService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all product categories", description = "Retrieves a paginated list of product categories, optionally filtered by name")
    public PagedModel<EntityModel<JenisBarangResponse>> getAll(
            @Parameter(description = "Optional name to search for") @RequestParam(required = false) String nama,
            @PageableDefault(size = 20, sort = "idJenisBarang", direction = Sort.Direction.ASC) Pageable pageable) {
        
        Page<JenisBarang> page;
        if (nama != null && !nama.isEmpty()) {
            page = service.searchByNama(nama, pageable);
        } else {
            page = service.getAll(pageable);
        }
        
        return PagedModel.of(
                page.getContent().stream()
                        .map(JenisBarangMapper::toResponse)
                        .map(this::toModel)
                        .collect(Collectors.toList()),
                new PagedModel.PageMetadata(
                        page.getSize(),
                        page.getNumber(),
                        page.getTotalElements(),
                        page.getTotalPages()
                ),
                linkTo(methodOn(JenisBarangController.class).getAll(nama, pageable)).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID", description = "Retrieves a product category by its ID")
    @ApiResponse(responseCode = "200", description = "Found the category", content = @Content(schema = @Schema(implementation = JenisBarangResponse.class)))
    @ApiResponse(responseCode = "404", description = "Category not found")
    public EntityModel<JenisBarangResponse> getById(@Parameter(description = "ID of the category") @PathVariable Integer id) {
        JenisBarang jenis = service.getById(id);
        return toModel(JenisBarangMapper.toResponse(jenis));
    }

    @PostMapping
    @Operation(summary = "Create a new category", description = "Creates a new product category")
    @ApiResponse(responseCode = "201", description = "Category created successfully")
    public ResponseEntity<EntityModel<JenisBarangResponse>> create(@Valid @RequestBody CreateJenisBarangRequest request) {
        JenisBarang saved = service.save(JenisBarangMapper.toEntity(request));
        EntityModel<JenisBarangResponse> model = toModel(JenisBarangMapper.toResponse(saved));

        return ResponseEntity.created(URI.create(model.getRequiredLink("self").getHref())).body(model);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category", description = "Updates an existing product category")
    public ResponseEntity<EntityModel<JenisBarangResponse>> update(@PathVariable Integer id, @Valid @RequestBody UpdateJenisBarangRequest request) {
        // Fix update logic using a temporary entity helper
        JenisBarang temp = new JenisBarang();
        JenisBarangMapper.updateEntity(temp, request);
        
        JenisBarang result = service.update(id, temp);
        return ResponseEntity.ok(toModel(JenisBarangMapper.toResponse(result)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category", description = "Deletes a product category by ID")
    @ApiResponse(responseCode = "204", description = "Category deleted successfully")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    private EntityModel<JenisBarangResponse> toModel(JenisBarangResponse response) {
        return EntityModel.of(response,
                linkTo(methodOn(JenisBarangController.class).getById(response.getIdJenisBarang())).withSelfRel(),
                linkTo(methodOn(JenisBarangController.class).getAll(null, Pageable.unpaged())).withRel("jenis-barang"));
    }
}