package com.foodnfun.backend.controller;

import com.foodnfun.backend.dto.MenuItemDTO;
import com.foodnfun.backend.response.ApiResponse;
import com.foodnfun.backend.service.MenuItemsServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
@Tag(name = "Menu Items")
public class MenuItemController {

    private final MenuItemsServices menuItemsServices;

    public MenuItemController(MenuItemsServices menuItemsServices) {
        this.menuItemsServices = menuItemsServices;
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Add a menu item to a vendor")
    public ResponseEntity<ApiResponse<MenuItemDTO>> addMenuItem(@Valid @RequestBody MenuItemDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Menu item added", menuItemsServices.addMenuItem(dto)));
    }

    @GetMapping
    @Operation(summary = "Get all menu items (public)")
    public ResponseEntity<ApiResponse<List<MenuItemDTO>>> getAllMenuItems() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Menu items fetched", menuItemsServices.getAllMenuItems()));
    }

    @GetMapping("/vendor/{vendorId}")
    @Operation(summary = "Get menu items for a vendor (public)")
    public ResponseEntity<ApiResponse<List<MenuItemDTO>>> getByVendor(@PathVariable Long vendorId) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Menu items fetched", menuItemsServices.getMenuItemsByVendor(vendorId)));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete a menu item")
    public ResponseEntity<ApiResponse<Void>> deleteMenuItem(@PathVariable Long id) {
        menuItemsServices.deleteMenuItem(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Menu item deleted", null));
    }
}
