package com.foodnfun.backend.controller;

import com.foodnfun.backend.dto.FoodDTO;
import com.foodnfun.backend.response.ApiResponse;
import com.foodnfun.backend.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@Tag(name = "Foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Create a food item")
    public ResponseEntity<ApiResponse<FoodDTO>> createFood(@Valid @RequestBody FoodDTO foodDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Food created", foodService.createFood(foodDTO)));
    }

    @GetMapping
    @Operation(summary = "Get all foods (public)")
    public ResponseEntity<ApiResponse<List<FoodDTO>>> getAllFoods() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Foods fetched", foodService.getAllFoods()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get food by ID (public)")
    public ResponseEntity<ApiResponse<FoodDTO>> getFoodById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Food fetched", foodService.getFoodById(id)));
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update food item")
    public ResponseEntity<ApiResponse<FoodDTO>> updateFood(
            @PathVariable Long id, @Valid @RequestBody FoodDTO foodDTO) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Food updated", foodService.updateFood(id, foodDTO)));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete food item")
    public ResponseEntity<ApiResponse<Void>> deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Food deleted", null));
    }
}
