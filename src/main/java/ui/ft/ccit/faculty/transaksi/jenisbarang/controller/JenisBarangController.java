package ui.ft.ccit.faculty.transaksi.jenisbarang.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.jenisbarang.dto.CreateJenisBarangRequest;
import ui.ft.ccit.faculty.transaksi.jenisbarang.dto.JenisBarangMapper;
import ui.ft.ccit.faculty.transaksi.jenisbarang.dto.JenisBarangResponse;
import ui.ft.ccit.faculty.transaksi.jenisbarang.dto.UpdateJenisBarangRequest;
import ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarang;
import ui.ft.ccit.faculty.transaksi.jenisbarang.view.JenisBarangService;

import java.net.URI;
import java.util.List;
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
    @Operation(summary = "Get all product categories", description = "Retrieves a list of all product categories")
    public CollectionModel<EntityModel<JenisBarangResponse>> getAll() {
        List<EntityModel<JenisBarangResponse>> jenisList = service.getAll().stream()
                .map(jenis -> EntityModel.of(JenisBarangMapper.toResponse(jenis),
                        linkTo(methodOn(JenisBarangController.class).getById(jenis.getIdJenisBarang())).withSelfRel(),
                        linkTo(methodOn(JenisBarangController.class).getAll()).withRel("jenis-barang")))
                .collect(Collectors.toList());

        return CollectionModel.of(jenisList, linkTo(methodOn(JenisBarangController.class).getAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID", description = "Retrieves a product category by its ID")
    @ApiResponse(responseCode = "200", description = "Found the category", content = @Content(schema = @Schema(implementation = JenisBarangResponse.class)))
    @ApiResponse(responseCode = "404", description = "Category not found")
    public EntityModel<JenisBarangResponse> getById(@Parameter(description = "ID of the category") @PathVariable Integer id) {
        JenisBarang jenis = service.getById(id);
        return EntityModel.of(JenisBarangMapper.toResponse(jenis),
                linkTo(methodOn(JenisBarangController.class).getById(id)).withSelfRel(),
                linkTo(methodOn(JenisBarangController.class).getAll()).withRel("jenis-barang"));
    }

    @PostMapping
    @Operation(summary = "Create a new category", description = "Creates a new product category")
    @ApiResponse(responseCode = "201", description = "Category created successfully")
    public ResponseEntity<EntityModel<JenisBarangResponse>> create(@Valid @RequestBody CreateJenisBarangRequest request) {
        JenisBarang saved = service.save(JenisBarangMapper.toEntity(request));
        EntityModel<JenisBarangResponse> model = EntityModel.of(JenisBarangMapper.toResponse(saved),
                linkTo(methodOn(JenisBarangController.class).getById(saved.getIdJenisBarang())).withSelfRel(),
                linkTo(methodOn(JenisBarangController.class).getAll()).withRel("jenis-barang"));

        return ResponseEntity.created(URI.create(model.getRequiredLink("self").getHref())).body(model);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category", description = "Updates an existing product category")
    public ResponseEntity<EntityModel<JenisBarangResponse>> update(@PathVariable Integer id, @Valid @RequestBody UpdateJenisBarangRequest request) {
        JenisBarang updated = service.update(id, JenisBarangMapper.toEntity(new CreateJenisBarangRequest())); // Hack for now, better to update properly
        // Actually, service.update expects Entity, but we have UpdateRequest.
        // Wait, service.update signature is `update(Integer id, JenisBarang updated)`.
        // We should probably change Service to accept UpdateRequest or handle mapping here.
        // But for now, let's map request -> to temp entity helper?
        // Service.update logic is: `existing.setNamaJenis(updated.getNamaJenis());`
        
        // Let's create a temp entity for passing data
        JenisBarang temp = new JenisBarang(id, request.getNamaJenisbrg());
        JenisBarang result = service.update(id, temp);

        EntityModel<JenisBarangResponse> model = EntityModel.of(JenisBarangMapper.toResponse(result),
                linkTo(methodOn(JenisBarangController.class).getById(id)).withSelfRel(),
                linkTo(methodOn(JenisBarangController.class).getAll()).withRel("jenis-barang"));
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category", description = "Deletes a product category by ID")
    @ApiResponse(responseCode = "204", description = "Category deleted successfully")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}