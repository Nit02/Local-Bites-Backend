package com.foodnfun.backend.service.impl;

import com.foodnfun.backend.dto.VendorDTO;
import com.foodnfun.backend.entity.Vendor;
import com.foodnfun.backend.exception.ResourceNotFoundException;
import com.foodnfun.backend.repository.VendorRepository;
import com.foodnfun.backend.service.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public VendorDTO createVendor(VendorDTO dto) {
        return mapToDTO(vendorRepository.save(mapToEntity(dto)));
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return mapToDTO(vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with id: " + id)));
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO dto) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with id: " + id));
        if (dto.getName()    != null) vendor.setName(dto.getName());
        if (dto.getEmail()   != null) vendor.setEmail(dto.getEmail());
        if (dto.getPhone()   != null) vendor.setPhone(dto.getPhone());
        if (dto.getAddress() != null) vendor.setAddress(dto.getAddress());
        return mapToDTO(vendorRepository.save(vendor));
    }

    @Override
    public void deleteVendor(Long id) {
        if (!vendorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vendor not found with id: " + id);
        }
        vendorRepository.deleteById(id);
    }

    private Vendor mapToEntity(VendorDTO dto) {
        Vendor vendor = new Vendor();
        vendor.setName(dto.getName());
        vendor.setEmail(dto.getEmail());
        vendor.setPhone(dto.getPhone());
        vendor.setAddress(dto.getAddress());
        return vendor;
    }

    private VendorDTO mapToDTO(Vendor vendor) {
        VendorDTO dto = new VendorDTO();
        dto.setId(vendor.getId());
        dto.setName(vendor.getName());
        dto.setEmail(vendor.getEmail());
        dto.setPhone(vendor.getPhone());
        dto.setAddress(vendor.getAddress());
        return dto;
    }
}
