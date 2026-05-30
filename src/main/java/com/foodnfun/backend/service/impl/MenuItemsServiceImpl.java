package com.foodnfun.backend.service.impl;

import com.foodnfun.backend.dto.MenuItemDTO;
import com.foodnfun.backend.entity.MenuItem;
import com.foodnfun.backend.entity.Vendor;
import com.foodnfun.backend.exception.ResourceNotFoundException;
import com.foodnfun.backend.repository.MenuItemsRepository;
import com.foodnfun.backend.repository.VendorRepository;
import com.foodnfun.backend.service.MenuItemsServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemsServiceImpl implements MenuItemsServices {

    private final MenuItemsRepository menuItemsRepository;
    private final VendorRepository    vendorRepository;

    public MenuItemsServiceImpl(MenuItemsRepository menuItemsRepository,
                                VendorRepository vendorRepository) {
        this.menuItemsRepository = menuItemsRepository;
        this.vendorRepository    = vendorRepository;
    }

    @Override
    public MenuItemDTO addMenuItem(MenuItemDTO dto) {
        Vendor vendor = vendorRepository.findById(dto.getVendorId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with id: " + dto.getVendorId()));
        MenuItem item = new MenuItem();
        item.setName(dto.getName());
        item.setPrice(dto.getPrice());
        item.setVendor(vendor);
        return mapToDTO(menuItemsRepository.save(item));
    }

    @Override
    public List<MenuItemDTO> getAllMenuItems() {
        return menuItemsRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public List<MenuItemDTO> getMenuItemsByVendor(Long vendorId) {
        return menuItemsRepository.findByVendorId(vendorId).stream().map(this::mapToDTO).toList();
    }

    @Override
    public void deleteMenuItem(Long id) {
        if (!menuItemsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Menu item not found with id: " + id);
        }
        menuItemsRepository.deleteById(id);
    }

    private MenuItemDTO mapToDTO(MenuItem item) {
        MenuItemDTO dto = new MenuItemDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setPrice(item.getPrice());
        dto.setVendorId(item.getVendor().getId());
        return dto;
    }
}
