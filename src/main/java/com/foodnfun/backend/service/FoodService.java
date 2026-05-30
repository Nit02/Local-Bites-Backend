package com.foodnfun.backend.service;

import com.foodnfun.backend.dto.FoodDTO;
import java.util.List;

public interface FoodService {
    FoodDTO createFood(FoodDTO foodDTO);
    List<FoodDTO> getAllFoods();
    FoodDTO getFoodById(Long id);
    FoodDTO updateFood(Long id, FoodDTO foodDTO);
    void deleteFood(Long id);
}
