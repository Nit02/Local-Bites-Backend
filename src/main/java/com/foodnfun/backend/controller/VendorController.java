package com.foodnfun.backend.controller;

import com.foodnfun.backend.dto.VendorDTO;
import com.foodnfun.backend.response.ApiResponse;
import com.foodnfun.backend.service.VendorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@Tag(name = "Vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Register a vendor")
    public ResponseEntity<ApiResponse<VendorDTO>> createVendor(@Valid @RequestBody VendorDTO vendorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Vendor created", vendorService.createVendor(vendorDTO)));
    }

    @GetMapping
    @Operation(summary = "Get all vendors (public)")
    public ResponseEntity<ApiResponse<List<VendorDTO>>> getAllVendors() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Vendors fetched", vendorService.getAllVendors()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vendor by ID (public)")
    public ResponseEntity<ApiResponse<VendorDTO>> getVendorById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Vendor fetched", vendorService.getVendorById(id)));
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update vendor")
    public ResponseEntity<ApiResponse<VendorDTO>> updateVendor(
            @PathVariable Long id, @Valid @RequestBody VendorDTO vendorDTO) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Vendor updated", vendorService.updateVendor(id, vendorDTO)));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete vendor")
    public ResponseEntity<ApiResponse<Void>> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Vendor deleted", null));
    }
}
