package com.foodnfun.backend.service;

import com.foodnfun.backend.dto.VendorDTO;
import java.util.List;

public interface VendorService {
    VendorDTO createVendor(VendorDTO vendorDTO);
    List<VendorDTO> getAllVendors();
    VendorDTO getVendorById(Long id);
    VendorDTO updateVendor(Long id, VendorDTO vendorDTO);
    void deleteVendor(Long id);
}
