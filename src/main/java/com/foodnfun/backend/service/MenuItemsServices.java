package com.foodnfun.backend.service;

import com.foodnfun.backend.dto.MenuItemDTO;
import java.util.List;

public interface MenuItemsServices {
    MenuItemDTO addMenuItem(MenuItemDTO dto);
    List<MenuItemDTO> getAllMenuItems();
    List<MenuItemDTO> getMenuItemsByVendor(Long vendorId);
    void deleteMenuItem(Long id);
}
