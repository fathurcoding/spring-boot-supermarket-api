package ui.ft.ccit.faculty.transaksi.pemasok.controller;

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
import ui.ft.ccit.faculty.transaksi.pemasok.dto.CreatePemasokRequest;
import ui.ft.ccit.faculty.transaksi.pemasok.dto.PemasokMapper;
import ui.ft.ccit.faculty.transaksi.pemasok.dto.PemasokResponse;
import ui.ft.ccit.faculty.transaksi.pemasok.dto.UpdatePemasokRequest;
import ui.ft.ccit.faculty.transaksi.pemasok.model.Pemasok;
import ui.ft.ccit.faculty.transaksi.pemasok.view.PemasokService;

import java.net.URI;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/pemasok")
@Tag(name = "Pemasok", description = "Supplier management endpoints")
public class PemasokController {

    private final PemasokService service;

    public PemasokController(PemasokService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all suppliers", description = "Retrieves a paginated list of suppliers, optionally filtered by name")
    public PagedModel<EntityModel<PemasokResponse>> getAll(
            @Parameter(description = "Optional name to search for") @RequestParam(required = false) String nama,
            @PageableDefault(size = 20, sort = "idPemasok", direction = Sort.Direction.ASC) Pageable pageable) {
        
        Page<Pemasok> page;
        if (nama != null && !nama.isEmpty()) {
            page = service.searchByNama(nama, pageable);
        } else {
            page = service.getAll(pageable);
        }
        
        return PagedModel.of(
                page.getContent().stream()
                        .map(PemasokMapper::toResponse)
                        .map(this::toModel)
                        .collect(Collectors.toList()),
                new PagedModel.PageMetadata(
                        page.getSize(),
                        page.getNumber(),
                        page.getTotalElements(),
                        page.getTotalPages()
                ),
                linkTo(methodOn(PemasokController.class).getAll(nama, pageable)).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get supplier by ID", description = "Retrieves a supplier by its ID")
    @ApiResponse(responseCode = "200", description = "Found the supplier", content = @Content(schema = @Schema(implementation = PemasokResponse.class)))
    @ApiResponse(responseCode = "404", description = "Supplier not found")
    public EntityModel<PemasokResponse> getById(@Parameter(description = "ID of the supplier") @PathVariable String id) {
        Pemasok pemasok = service.getById(id);
        return toModel(PemasokMapper.toResponse(pemasok));
    }

    @PostMapping
    @Operation(summary = "Create a new supplier", description = "Creates a new supplier")
    @ApiResponse(responseCode = "201", description = "Supplier created successfully")
    public ResponseEntity<EntityModel<PemasokResponse>> create(@Valid @RequestBody CreatePemasokRequest request) {
        Pemasok saved = service.save(PemasokMapper.toEntity(request));
        EntityModel<PemasokResponse> model = toModel(PemasokMapper.toResponse(saved));

        return ResponseEntity.created(URI.create(model.getRequiredLink("self").getHref())).body(model);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a supplier", description = "Updates an existing supplier")
    public ResponseEntity<EntityModel<PemasokResponse>> update(
            @PathVariable String id, @Valid @RequestBody UpdatePemasokRequest request) {
        
        // Use temp entity similar to other controllers
        Pemasok temp = new Pemasok();
        PemasokMapper.updateEntity(temp, request);
        
        Pemasok result = service.update(id, temp);
        return ResponseEntity.ok(toModel(PemasokMapper.toResponse(result)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a supplier", description = "Deletes a supplier by ID")
    @ApiResponse(responseCode = "204", description = "Supplier deleted successfully")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    private EntityModel<PemasokResponse> toModel(PemasokResponse response) {
        return EntityModel.of(response,
                linkTo(methodOn(PemasokController.class).getById(response.getIdPemasok())).withSelfRel(),
                linkTo(methodOn(PemasokController.class).getAll(null, Pageable.unpaged())).withRel("pemasok"));
    }
}