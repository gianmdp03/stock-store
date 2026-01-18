package com.stockstore.stockstore.online.controller;

import com.stockstore.stockstore.online.dto.address.AddressDetailDTO;
import com.stockstore.stockstore.online.dto.address.AddressRequestDTO;
import com.stockstore.stockstore.online.dto.address.AddressUpdateDTO;
import com.stockstore.stockstore.online.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressDetailDTO> addAddress(Authentication authentication,
                                                       @Valid @RequestBody AddressRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.addAddress(authentication.getName(), dto));
    }

    @GetMapping
    public ResponseEntity<Page<AddressDetailDTO>> listAddresses(Authentication authentication
            , @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.listAddresses(authentication.getName(), pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDetailDTO> getAddressById(Authentication authentication, @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAddressById(authentication.getName(), id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AddressDetailDTO> updateAddress(Authentication authentication,
                                                          @PathVariable Long id,
                                                          @Valid @RequestBody AddressUpdateDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.updateAddress(authentication.getName(), id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(Authentication authentication, @PathVariable Long id){
        addressService.deleteAddress(authentication.getName(), id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
