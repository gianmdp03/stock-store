package com.stockstore.stockstore.online.service;

import com.stockstore.stockstore.online.dto.address.AddressDetailDTO;
import com.stockstore.stockstore.online.dto.address.AddressRequestDTO;
import com.stockstore.stockstore.online.dto.address.AddressUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {
    AddressDetailDTO addAddress(String email, AddressRequestDTO dto);
    Page<AddressDetailDTO> listAddresses(String email, Pageable pageable);
    AddressDetailDTO getAddressById(String email, Long id);
    AddressDetailDTO updateAddress(String email, Long id, AddressUpdateDTO dto);
    void deleteAddress(String email, Long id);
}
