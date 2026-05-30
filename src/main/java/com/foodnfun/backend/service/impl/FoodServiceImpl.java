package com.foodnfun.backend.service.impl;

import com.foodnfun.backend.dto.FoodDTO;
import com.foodnfun.backend.entity.Food;
import com.foodnfun.backend.exception.ResourceNotFoundException;
import com.foodnfun.backend.repository.FoodRepository;
import com.foodnfun.backend.service.FoodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public FoodDTO createFood(FoodDTO dto) {
        return mapToDTO(foodRepository.save(mapToEntity(dto)));
    }

    @Override
    public List<FoodDTO> getAllFoods() {
        return foodRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public FoodDTO getFoodById(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found with id: " + id));
        return mapToDTO(food);
    }

    @Override
    public FoodDTO updateFood(Long id, FoodDTO dto) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Food not found with id: " + id));
        if (dto.getName()        != null) food.setName(dto.getName());
        if (dto.getDescription() != null) food.setDescription(dto.getDescription());
        if (dto.getPrice()       > 0)     food.setPrice(dto.getPrice());
        if (dto.getImageUrl()    != null) food.setImageUrl(dto.getImageUrl());
        return mapToDTO(foodRepository.save(food));
    }

    @Override
    public void deleteFood(Long id) {
        if (!foodRepository.existsById(id)) {
            throw new ResourceNotFoundException("Food not found with id: " + id);
        }
        foodRepository.deleteById(id);
    }

    private Food mapToEntity(FoodDTO dto) {
        Food food = new Food();
        food.setName(dto.getName());
        food.setDescription(dto.getDescription());
        food.setPrice(dto.getPrice());
        food.setImageUrl(dto.getImageUrl());
        return food;
    }

    private FoodDTO mapToDTO(Food food) {
        FoodDTO dto = new FoodDTO();
        dto.setId(food.getId());
        dto.setName(food.getName());
        dto.setDescription(food.getDescription());
        dto.setPrice(food.getPrice());
        dto.setImageUrl(food.getImageUrl());
        return dto;
    }
}
